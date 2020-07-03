## 简介

DataBinding是以声明的方式，将布局中组件与应用程序源数据绑定在一起的框架库。

### 作用

* 将布局组件与源数据绑定，使源数据变化的同时布局组件及时同步更新。

* 减少Activity中View的定义(private View view)与初始化(findViewById)，让Activity代码更专注于界面的逻辑更新。

* 可自定义适配器，实现扩展组件的属性功能。

* 可自定义事件，实现各种组件的事件触发功能。

### 特点

* 使用简单，主要以声明的方式实现。

* 功能强大，可自定义适配器&事件，兼容各种界面逻辑需求。

***

## 使用说明

### Lib引入

* 在build.gradle中添加以下代码：

```
dataBinding {
    enabled = true
}
```

**注：如果使在module里面使用databinding框架的话，在module与主项目的build.gradle都要添加上述代码，不然允许会报 compileDebugJavaWithJavac 相关错误。** 

* 需要在项目gradle.properties中添加以下代码：

```
android.databinding.enableV2=true
```

```
<import
    alias="Hello"
    type="com.nsz.kotlin.aac.live.data.Student" />

<variable
    name="student"
    type="com.nsz.kotlin.aac.live.data.Student" />
```

***

## 单向绑定

单向绑定可以通过两种方式实现。

### 使用ObservableXxx类型替代原有类型

ObservableXxx
这里的xxx包含基本类型和集合类型，比如int对应ObservableInt，boolean对应ObservableBoolean等，ArrayList对应ObservableArrayList，引用类型统一使用ObservableField<T>，比如String对应ObservableField<String>。为方便起见，下文将ObservableXxx统一用ObservableField表示。

```
data class User(var name: ObservableField<String>, var age: ObservableInt)

user.name.set(xxx)
```

### 实体继承BaseObservable类，并用@Bindable注解声明属性的getter方法。

* 让实体类继承BaseObservable类。

* 为属性的set方法添加Bindable注解。

* 自定义set方法调用notifyPropertyChanged触发刷新。

notifyPropertyChanged方法继承自BaseObservable，其参数为BR.name。这里需要解释一下BR类，它是DataBinding编译器自动生成的，其内部为所有可变对象定义了一个int类型的唯一标识，这里的可变对象包含：

* 在xml布局中声明的variable变量
    
* 使用@Bindable注解标识的方法对应的属性名(属性不必真的存在，含有getter即可)。

* 这也就是为什么一定要声明Bindable注解，这样就能指定刷新特定的属性了。同时由于getter内可以写任意代码，因此可操作性更强。

```
public class ObservableBean extends BaseObservable {

    private String name;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
        notifyPropertyChanged(BR.name);
    }

}
```
**不知道为什么，如果用Kotlin来写的话，编译器不能生成BR.xx的属性。**

相比于ObservableField而言虽然不必改变属性的类型了，但对应实体类来说面临了更多的问题：

* 使用了继承而不是实现，如果实体类本身有继承关系此方案不可行(如果一定要这么做只能实现Observable接口，并把BaseObservable的内部实现拷贝到现有实体中)。

* 需重写set方法，但在Kotlin中对data类型内部的属性使用set太不友好，看看一个简单的data类改成啥样了...。

### 绑定原理

***

## 双向绑定

如果说单向绑定是数据驱动，那么双向绑定就是数据驱动+事件驱动，用@={}表达式标识双向绑定。

所以要实现双向绑定也就是在单向绑定的基础上加上事件驱动逻辑即可。事件驱动最终要表现在数据变化上，因此可以总结完成事件驱动的三个步骤。

* 监听事件变化并抛给框架

* 获取控件当前属性值

* 用当前属性值更新数据

***

## @BindingAdapter注解

DataBinding中自定义属性依赖于注解 @BindingAdapter

* 作用于方法（和类无关，这个自定义属性的方法可以写在任何地方）

* 它定义了xml的属性赋值的java实现（注解的方法中就是我们对这个控件进行处理）

* 方法必须为公共静（public static）方法，可以有一到多个参数。

```
@BindingAdapter("hello")
@JvmStatic
fun setHello(view: MyTextView, name: String) {
    Log.d("lz", "name: $name")
    view.text = name
}

<com.nsz.kotlin.aac.data.binding.MyTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginStart="32dp"
    android:layout_marginTop="8dp"
    android:layout_marginEnd="32dp"
    android:textColor="@android:color/black"
    android:textSize="14sp"
    app:hello='@{ "custom attr " + observableBean.name }' />

@android.databinding.BindingAdapter(value = {"app:imgUrl", "app:placeholder"}, requireAll = false)
public static void loadImg(ImageView imageView, String url, Drawable placeholder) {
    GlideApp.with(imageView)
            .load(url)
            .placeholder(placeholder)
            .into(imageView);
}

<ImageView
    android:id="@+id/img_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:adjustViewBounds="true"
    app:imgUrl="@{user.url}"
    app:placeholder="@{ @drawable/ic_launcher_background }" />
```
这里requireAll = false表示我们可以使用这两个两个属性中的任一个或同时使用。如果requireAll = true 则两个属性必须同时使用，不然报错。默认为 true。

***

## @BindingMethod与@BindingMethods

BindingMethods包含若干BindingMethod，BindingMethod是BindingMethods的子集。BindingMethods内部有一个BindingMethod数组，存放的是一个一个的BindingMethod。

* @BindingMethods注解一般用于标记类。

* @BindingMethod注解需要与@BindingMethods注解结合使用才能发挥其功效。

用法极其简单，有3个字段，这3个字段都是必填项，少一个都不行：

* type：要操作的属性属于哪个View类，类型为class对象，比如：ImageView.class。

* attribute：xml属性，类型为String ，比如：bindingMethodToast。

* method：指定xml属性对应的set方法，类型为String，比如：showBindingMethodToast。

```
@BindingMethods(
        @BindingMethod(type = MyTextView.class, attribute = "showToast", method = "showInputToast")
)
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void showInputToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

}

<com.nsz.kotlin.aac.data.binding.MyTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginStart="32dp"
    android:layout_marginTop="8dp"
    android:layout_marginEnd="32dp"
    android:textColor="@android:color/black"
    android:textSize="14sp"
    app:hello='@{ "custom attr " + observableBean.name }'
    app:showToast="@{ observableBean.name }" />
```
***

## @BindingConversion

* 作用于方法。

* 被该注解标记的方法，被视为DataBinding的转换方法。

* 方法必须为公共静态（public static）方法，有且只能有1个参数。

```
@BindingConversion
@JvmStatic
fun convertColorToDrawable(color: String): ColorDrawable {
    val parseColor = Color.parseColor(color)
    return ColorDrawable(parseColor)
}

<Button
    android:layout_width="match_parent"
    android:layout_height="48dp"
    android:layout_marginStart="32dp"
    android:layout_marginTop="8dp"
    android:layout_marginEnd="32dp"
    android:background="@{ bol ? @string/blue : @string/gray }"
    android:onClick="@{ (view) -> activity.onChangeUI(view, bol) }"
    android:text="事件自定义参数绑定"
    android:textSize="14sp" />
```
***

## @InverseBindingAdapter

* attribute：自定义属性，必填项。比如android:weak

* event：用于触发更改的事件。在数据绑定系统的{@link BindingAdapter}中，它用于在使用双向绑定时设置事件监听器，可选项。

如果填写，则使用填写的内容作为event的值。如果不填，在编译时会根据attribute的属性名再加上后缀"AttrChanged"。比如android:weakAttrChanged。

```
object InverseBindingAdapterHelper {

    @BindingAdapter("android:weak", requireAll = false)
    @JvmStatic
    fun setX(view: RatingBar, value: Float) {
        if (view.rating == value) return
        view.rating = value
    }

    @InverseBindingAdapter(attribute = "android:weak", event = "android:weakAttrChanged")
    @JvmStatic
    fun getX(view: RatingBar): Float {
        return view.rating
    }

    @BindingAdapter(
        value = ["android:weakAttrChanged"], requireAll = false
    )
    @JvmStatic
    fun setListener(
        view: RatingBar,
        inverseBindingListener: InverseBindingListener
    ) {
        Log.d("nz", "inverseBindingListener")
        view.setOnRatingBarChangeListener { _, fl, b ->
            Log.d("nz", "fl: $fl b: $b")
            inverseBindingListener.onChange()

        }
    }

}

<RatingBar
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:layout_marginStart="32dp"
        android:layout_marginTop="8dp"
        android:numStars="6"
        android:weak="@={ fx }"
        android:layout_marginEnd="32dp" />
```

***

## 事件绑定

组件事件绑定有2种方式：

* 事件直接绑定

* 事件自定义参数绑定

```
<variable
    name="bol"
    type="java.lang.Boolean" />

<variable
    name="activity"
    type="com.nsz.kotlin.aac.data.binding.DataBindingActivity" />

<Button
    android:layout_width="match_parent"
    android:layout_height="48dp"
    android:layout_marginStart="32dp"
    android:layout_marginTop="8dp"
    android:layout_marginEnd="32dp"
    android:onClick="@{ activity::onClickForEvent }"
    android:text="事件直接绑定"
    android:textSize="14sp" />

<Button
    android:layout_width="match_parent"
    android:layout_height="48dp"
    android:layout_marginStart="32dp"
    android:layout_marginTop="8dp"
    android:layout_marginEnd="32dp"
    android:onClick="@{ (view) -> activity.onChangeUI(view, bol) }"
    android:text="事件自定义参数绑定"
    android:textSize="14sp" />

fun onClickForEvent(view: View) {
    Log.e("lz", "onClickForEvent")
}

fun onChangeUI(view: View, bool: Boolean) {
    Log.e("lz", "onChangeUI $bool")
}
```

***

## 使用技巧

### VIEW的隐藏与显示

* 多状态使用，判断数字状态
```
android:visibility="@{ student.age == 20 ? View.VISIBLE : View.GONE }"
```

* 判断是不是空，TextUtils空就不显示
```
<import type="android.view.View" />
<import type="android.text.TextUtils" />

android:visibility="@{ TextUtils.isEmpty(student.NAME) ? View.INVISIBLE : View.VISIBLE }"
```

* 默认值。当绑定数据还未赋值时可指定默认值android:text="@{user.firstName, default="Placeholder text"}"。

* 验空的三目运算可换为"??"操作符。比如：android:text="@{user.displayName ?? user.lastName}"等价于android:text="@{user.displayName != null ? user.displayName : user.lastName}"。

* String placeholder可以这么用。android:text="@{@string/nameFormat(firstName, lastName)}"。

* binding表达式转义字符。"<"符号用&lt;代替。"&"符号用&amp;代替。

* 字符串拼接
```
android:text="@{ @string/app_name + student.name, default = chu }"

android:text='@{ "age: " + student.age, default = 112 }'
```

* 不在xml中写任何逻辑代码，databinding在xml中只负责数据绑定和设置监听。

* xml标签中使用databinding的属性统一移到标签底部。

* 尽量使用官方定义的BindingAdapter进行数据绑定与设置监听。

***

* android:text=@{user.name}这种常见的binding方式当user对象为空时，会引起空指针问题吗？为什么？

控件真正和数据完成绑定发生在bindingImpl类(这个类也是自动生成的)的executeBindings()方法中，里面有判空的逻辑判断。

* View的全部属性都支持binding吗？比如padding？margin？为什么？

    * View源码中是否有签名符合要求的setPadding、setMargin方法。

    * 在databinding框架内是否有声明@BindAdapter("android:padding")注解且签名符合要求的静态方法。

    * 在databinding框架内是否有声明@BindingMethod注解且该注解在View源码中有映射关系实现setPadding/setMargin。

查看View源码你会发现其中没有setMargin方法，有setPadding方法但是多参的setPadding(int left, int top, int right, int bottom)不符合方法签名要求，所以二者都不满足。

在databinding-adapters库中定义了常用组件的扩展绑定关系，比如View对应其ViewBindingAdapter，TextView对应其TextViewBindingAdapter等等。

其中ViewBindingAdapter完成了padding属性的绑定。

但并没有margin属性，且在其@BindingMethod声明中也没有映射实现，因此得到结论：android:padding属性支持绑定，而margin不支持。

以layout_marginBottom为例，下面是一个简单的实现。
```
@BindingAdapter("android:layout_marginBottom")
public static void setBottomMargin(View view, float bottomMargin) {
    MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
    layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,
        layoutParams.rightMargin, Math.round(bottomMargin));
    view.setLayoutParams(layoutParams);
}
```

* 双向绑定是如何处理无限循环调用的？

> https://www.jianshu.com/p/2aa355b6a42d

> https://www.jianshu.com/p/2a45f61a9482
