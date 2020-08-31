
官方地址：https://realm.io/cn/docs/java/latest/

Android设备数据文件目录：adb pull /data/data/< packagename >/files/.

查看数据库程序：Realm Studio

## 开始使用

### 优势

* 易用：Realm不是在SQLite基础上的ORM，它有自己的数据查询引擎。并且十分容易使用。

* 快速：由于它是完全重新开始开发的数据库实现，所以它比任何的ORM速度都快很多，甚至比SLite速度都要快。

* 跨平台：Realm支持iOS & OS X (Objective‑C & Swift) & Android。我们可以在这些平台上共享Realm数据库文件，并且上层逻辑可以不用任何改动的情况下实现移植。

* 高级：Realm支持加密，格式化查询，易于移植，支持JSON，流式api，数据变更通知等高级特性。

* 可视化：Realm还提供了一个轻量级的数据库查看工具，在Mac Appstore可以下载Realm Browser这个工具，开发者可以查看数据库当中的内容，执行简单的插入和删除数据的操作。（windows上还不清楚）

### 前提

* Android Studio 1.5.1或者更高版本。

* JDK版本 >=7。

* 较新的Android SDK版本。

* 我们支持Android API 9以上的所有版本（Android 2.3 Gingerbread及以上）。

**Note：** 目前我们还不支持Android以外的Java环境；我们不再支持Eclipse IDE，请迁移到Android Studio。

### 安装

* 第一步：在项目的build.gradle文件中添加如下class path依赖。

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath "io.realm:realm-gradle-plugin:3.1.1"
    }
}
```

* 第二步：在app的build.gradle文件中应用realm-android 插件。

```
apply plugin: 'realm-android'
```

## 

### 字段类型

Realm支持以下字段类型：boolean、byte、short、int、long、float、double、String、Date和byte[]。

整数类型short、int和long都被映射到Realm内的相同类型（实际上为 long ）。再者，还可以使用RealmObject的子类和RealmList<? extends RealmObject>来表示模型关系。

Realm对象中还可以声明包装类型（boxed type）属性，包括：Boolean、Byte、Short、Integer、Long、Float和Double。通过使用包装类型，可以使这些属性存取空值（null）。

### 注解

```
class Dog(

    @Index
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var age: Int

) : RealmObject()
```

#### @Required修饰类型和空值（null）

某些时候，空值（null）对于属性并不合适。这时可以使用注解@Required告诉Realm强制禁止空值（null）被存储。

只有Boolean、 Byte、 Short、 Integer、 Long、 Float、 Double、 String、 byte[]以及Date可以被@Required修饰。在其它类型属性上使用@Required修饰会导致编译失败。

基本数据类型（primitive types）不需要使用注解@Required，因为他们本身就不可为空。RealmObject属性永远可以为空。

#### @Ignore忽略的属性

注解@Ignore意味着一个字段不应该被保存到Realm。某些时候输入的信息包含比模型更多的字段，而你不希望处理这些未使用的数据字段，你可以用@Ignore来标识这些你希望被Realm忽略的字段。

#### @PrimaryKey主键（primary keys）

@PrimaryKey可以用来定义字段为主键，该字段类型必须为字符串（String）或整数（short、int 或 long）以及它们的包装类型（Short、Int 或 Long）。不可以存在多个主键。

使用支持索引的属性类型作为主键同时意味着为该字段建立索引。

当创建Realm对象时，所有字段会被设置为默认值。为了避免与具有相同主键的另一个对象冲突，建议创建一个unmanaged对象，为字段的赋值，然后用copyToRealm()方法将该对象复制到Realm。

主键的存在意味着可以使用copyToRealmOrUpdate() 方法，它会用此主键尝试寻找一个已存在的对象，如果对象存在，就更新该对象；反之，它会创建一个新的对象。当copyToRealmOrUpdate() 的调用对象没有主键时，会抛出异常。

使用主键会对性能产生影响。创建和更新对象将会慢一点，而查询则会变快。很难量化这些性能的差异，因为性能的改变跟你数据库的大小息息相关。

Realm.createObject()会返回一个所有字段被设置为默认值的新对象。如果该模型类存在主键，那么有可能返回对象的主键的默认值与其它已存在的对象冲突。建议创建一个非托管（unmanaged）Realm对象，并给其主键赋值，然后调用copyToRealm()来避免冲突。

#### @Index索引（Index）属性

注解@Index会为字段增加搜索索引。这会导致插入速度变慢，同时数据文件体积有所增加，但能加速查询。

因此建议仅在需要加速查询时才添加索引。目前仅支持索引的属性类型包括：String、byte、short、int、long、boolean和Date。

### POJO

RealmObject、RealmModel、RealmList< T >。

自定义实体类继承RealmObject类。

RealmList是Realm模型对象的容器，其行为与Java的普通List近乎一样。同一个Realm模型对象可以存在于多个RealmList中。同一个Realm模型对象可以在同一个RealmList中存在多次。你可以使用RealmList来表现一对多和多对多的数据关系。

RealmList的获取器（getter）永不会返回null。其返回对象永远是一个RealmList实例，但其长度有可能为0。

***

## 在Android中使用

### 在Application的oncreate()方法中使用自定义配置

RealmConfiguration支持的方法：

* Builder.name：指定数据库的名称。如不指定默认名为default。
* Builder.schemaVersion：指定数据库的版本号。
* Builder.encryptionKey：指定数据库的密钥。
* Builder.migration：指定迁移操作的迁移类。
* Builder.deleteRealmIfMigrationNeeded：声明版本冲突时自动删除原数据库。
* Builder.inMemory：声明数据库只在内存中持久化。
* build：完成配置构建。

```
override fun onCreate() {
    super.onCreate()

    val lifecycleCallback = LifecycleCallback()
    registerActivityLifecycleCallbacks(lifecycleCallback)

    Realm.init(this)
    val realmConfiguration = RealmConfiguration.Builder().build()
    Realm.setDefaultConfiguration(realmConfiguration)
}
```

***

* https://juejin.im/post/5ee09ef6e51d4578a12e3f5b

> MMKV、SP、REALM、ROOM、WCDB 数据持久化方案

* https://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650238381&idx=1&sn=a0fc72220763166ca2e34519ff2055b3&chksm=88639cc2bf1415d40d466e76945338bc88efae957d63e3ea026c801f05883b485d185ffe0b73&scene=38#wechat_redirect

* https://juejin.im/post/5d4a30ec5188251f6b1ef376