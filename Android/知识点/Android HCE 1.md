## SE 

Android手机可以配备安全元件（Secure Element，简称SE）。这是一个能够自行与读卡器通信的芯片 -- 在任何时候它都不需要与Android操作系统通信来履行职责。它里面包含了微小的应用程序，可以直接与读卡器进行相关交互操作。

由于SE是防篡改的，所以很难从它们身上获取任何数据。此外，由于它们不与操作系统通信（注意：NFC控制器直接将数据转发到SE，这些数据是不经过操作系统），这意味着即使在操作系统被入侵的设备上，读卡器和手机之间的数据交互也是安全的。

这个和POS行业中的安全芯片很类似。

![](https://github.com/chuwuwang/Resources/blob/master/android/secure-element.png)

***

## HCE

HCE是Host Card Emulation的缩写，这是一个通过在主机操作系统上运行的代码来模拟卡的过程。

当使用HCE来模拟NFC卡片时，数据将被转发到直接运行Android应用程序的主机CPU，而不是将NFC协议帧路由到SE。

![](https://github.com/chuwuwang/Resources/blob/master/android/host-based-card.png)

***

## SE更安全，为什么还要用HCE？

1. 如果在非Root的手机上，HCE也是很安全的。

2. SE是一个单独模块，需要额外的硬件成本。

***

## 如何使用HCE

### 检查HCE支持

通过检查FEATURE_NFC_HOST_CARD_EMULATION功能来检查设备是否支持HCE 。

```
fun hasHCEFunction(context: Context): Boolean {
    return context.packageManager.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION)
}

// 清单文件注册
<uses-feature
    android:name="android.hardware.nfc.hce"
    android:required="true" />

<uses-permission android:name="android.permission.NFC" />
```

### 实现HostApduService类

实现HostApduService类，重写onDeactivated()和processCommandApdu()这两个方法。

只要读卡器向HCE服务发送应用协议数据单元 (APDU)，系统就会调用processCommandApdu()。APDU也在ISO/IEC 7816-4规范中定义。

APDU是在读卡器和HCE服务之间进行数据交换的应用级数据包。该应用级协议为半双工：读卡器会向HCE服务发送APDU命令，反之它会等待HCE服务发送响应APDU命令。

**注意：** ISO/IEC 7816-4规范也定义了多逻辑通道的概念，可以在单逻辑通道上具有多个并行的APDU。不过，Android的HCE实现仅支持单逻辑通道，因此只有一个单线程的APDU交换。

Android使用AID来确定读卡器要与哪个HCE服务通信。通常，读卡器向设备发送的第一个APDU命令是 “SELECT AID” 命令，此命令包含读卡器要与之通信的AID。

Android应用程序从该命令中提取该AID，将其解析为HCE服务，然后将该命令转发到解析后的服务。

通过processCommandApdu()方法的返回值来响应APDU命令，这个方法是在主线程上调用的，此线程不应被屏蔽。

所以，如果不能立刻响应APDU命令的话，可以返回null。然后，可以在另一个线程上执行相关的操作，在最后调用sendResponseApdu()方法发送响应数据。

Android会一直将读卡器的新APDU命令转发到HCE服务，直到发生下列任一情况：

* 读卡器发送另一个 “SELECT AID” APDU命令，操作系统会将其解析为其他HCE服务。

* 读卡器和设备之间的NFC链接断开。

在这两种情况下，系统会调用onDeactivated()方法，其参数表示发生了以上哪种情况。

**建议：** 一般来说，尽量控制APDU数据的大小，这样可以确保读卡器在短时间内就能读取到设备上的数据。合理的数据大小上限约为1KB，通常可在300毫秒内完成交换。

```
override fun onDeactivated(reason: Int) {
    CommonLog.e("onDeactivated: $reason")
}

override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
    var hexString = ""
    if (commandApdu != null) {
        hexString = ByteHelper.bytes2HexString(commandApdu)
    }
    CommonLog.e("processCommand hexString: $hexString")
    val string = ByteHelper.hexString2String(hexString)
    CommonLog.e("processCommand string: $string")

    val dataIn = "https://www.baidu.com/"
    return dataIn.toByteArray(StandardCharsets.US_ASCII)
}
```

### host-apdu-service配置

开发HCE功能必须使用到HostApduService，在使用HostApduService时需要配置HostApduService的对应AID，同一个手机可能安装多个HCE APP，或者同一个APP包含多个HostApduService,这时候就需要搞清楚配置的AID和对应的HostApduService之间的响应向后问题。

首先介绍一下关于HostApduService的AID配置问题，AID有两种模式：other和payment。

```
<host-apdu-service> 标记必须包含 <android:description> 属性，其中包含可在界面中显示的简单易懂的服务说明。
requireDeviceUnlock 属性可用于指定必须先解锁设备，然后才能调用此服务来处理 APDU。
<host-apdu-service> 必须包含一个或多个 <aid-group> 标记。

每个 <aid-group> 标记都必须符合以下条件：
包含 android:description 属性，其中包含适合在界面中显示的简单易懂的 AID 群组说明。
将其 android:category 属性设置为指示 AID 群组所属的类别，例如，由 CATEGORY_PAYMENT 或 CATEGORY_OTHER 定义的字符串常量。
每个 <aid-group> 必须包含一个或多个 <aid-filter> 标记，其中每个标记都包含一个 AID。必须以十六进制格式指定 AID，并且包含的字符数为偶数。
```

```
// 第一种
<host-apdu-service xmlns:android="http://schemas.android.com/apk/res/android"
    android:apduServiceBanner="@drawable/icon"
    android:description="@string/servicedesc"
    android:requireDeviceUnlock="false" >
    <!--
          可以通过指定多个aid-filter，来注册多个AID。
          category可以指定为 other 或者 payment。
          requireDeviceUnlock为false的时候，可以在锁屏状态下完成处理，为true的时候，则会要求用户解锁屏幕。
    -->
    <aid-group
        android:category="other"
        android:description="@string/app_name" >
        <aid-filter android:name="@string/AID1" />
    </aid-group>
</host-apdu-service>

// 第二种
<host-apdu-service xmlns:android="http://schemas.android.com/apk/res/android"
    android:apduServiceBanner="@drawable/alipay"
    android:description="@string/servicedesc"
    android:requireDeviceUnlock="false" >
    <!--
          可以通过指定多个aid-filter，来注册多个AID。
          category可以指定为 other 或者 payment。
          requireDeviceUnlock为false的时候，可以在锁屏状态下完成处理，为true的时候，则会要求用户解锁屏幕
    -->
    <aid-group
        android:category="payment"
        android:description="@string/app_name" >
        <aid-filter android:name="@string/AID2" /> 
    </aid-group>
</host-apdu-service>
```

下面我们来详细说明一下关于不同模式下的AID响应问题（前提：一个手机，手机上有A、B两个HCE APP，通过读卡器向手机发送APDU选择指令）

* A和B的应用AID设置的都是payment模式。

    * 只有手机当前选定的默认支付APP会响应，另外一个APP的AID选择指令是不会响应的。

* A和B的应用AID设置的都是other模式。

    * 当A和B的AID是相同的时候系统会弹出对话框，列出A和B，让用户选择。

    * 如果A和B的AID不同，那么两个APP之间没有相互影响。

* A和B的应用AID设置的分别是payment（A）和other（B）模式。

    * 如果A和B的AID相同，那么只有A会响应选择指令。

    * 如果A和B的AID不同，那么两者之间不会相互影响。

### 检查HCE服务是否为默认服务

```
private fun setDefaultPaymentApp() {
    val adapter = NfcAdapter.getDefaultAdapter(this)
    val cardEmulation = CardEmulation.getInstance(adapter)
    val canonicalName = HCEService::class.java.canonicalName ?: return
    val componentName = ComponentName(applicationContext, canonicalName)
    val isDefaultServiceForCategory = cardEmulation.isDefaultServiceForCategory(componentName, CardEmulation.CATEGORY_PAYMENT)
    if (isDefaultServiceForCategory) {
        CommonLog.e("当前应用是系统默认支付程序")
    } else {
        CommonLog.e("当前应用不是默认支付程序，需手动设置")
        val intent = Intent(CardEmulation.ACTION_CHANGE_DEFAULT)
        intent.putExtra(CardEmulation.EXTRA_CATEGORY, CardEmulation.CATEGORY_PAYMENT)
        intent.putExtra(CardEmulation.EXTRA_SERVICE_COMPONENT, componentName)
        startActivityForResult(intent, 0)
    }
}
```

***

## HCE和SE共存，如何交互？

***

> https://www.jianshu.com/p/ae9ead0b5592

> https://medium.com/swlh/why-you-need-to-be-very-careful-while-working-with-nfc-hce-apis-in-android-9bde32cc7924

> https://developer.android.com/guide/topics/connectivity/nfc/hce

> https://blog.csdn.net/hugege20111029/article/details/43987041

> https://blog.csdn.net/hellogv/article/details/39833327