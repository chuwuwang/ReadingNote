## kotlin中run、apply、let、also、with、repeat、takeIf、takeUnless的用法和区别

### let - 内联扩展函数

```
/**
 * Calls the specified function [block] with `this` value as its argument and returns its result.
 *
 * For detailed usage information see the documentation for [scope functions](https://kotlinlang.org/docs/reference/scope-functions.html#let).
 */
@kotlin.internal.InlineOnly
public inline fun <T, R> T.let(block: (T) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(this)
}
```

功能：调用某对象的let函数。

则该对象为函数的参数，在函数块内可以通过it指代该对象，返回值为函数块的最后一行或指定return表达式。

**let函数适用的场景**

* 最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理。

* 需要去明确一个变量所处特定的作用域范围内可以使用。

```
view?.let {
    it.setVideoView(activity.course_video_view)
    it.setCurtainView(activity.course_video_curtain_view)
    it.setControllerView(activity.course_video_controller_view)
}
```

### with - 内联函数

```
/**
 * Calls the specified function [block] with the given [receiver] as its receiver and returns its result.
 *
 * For detailed usage information see the documentation for [scope functions](https://kotlinlang.org/docs/reference/scope-functions.html#with).
 */
@kotlin.internal.InlineOnly
public inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return receiver.block()
}
```

with函数不是以扩展的形式存在的。

它是将某对象作为函数的参数，在函数块内可以通过this指代该对象，返回值为函数块的最后一行或指定return表达式。

**with函数的适用的场景**

* 适用于调用同一个类的多个方法时，可以省去类名重复，直接调用类的方法即可，经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上。

```
with(item) {
    holder.tvNewsTitle.text = StringUtils.trimToEmpty(titleEn)
    holder.tvNewsSummary.text = StringUtils.trimToEmpty(summary)
}
```

### run - 内联扩展函数

```
/**
 * Calls the specified function [block] and returns its result.
 *
 * For detailed usage information see the documentation for [scope functions](https://kotlinlang.org/docs/reference/scope-functions.html#run).
 */
@kotlin.internal.InlineOnly
public inline fun <R> run(block: () -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

/**
 * Calls the specified function [block] with `this` value as its receiver and returns its result.
 *
 * For detailed usage information see the documentation for [scope functions](https://kotlinlang.org/docs/reference/scope-functions.html#run).
 */
@kotlin.internal.InlineOnly
public inline fun <T, R> T.run(block: T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}
```

功能：调用run函数块。

返回值为函数块最后一行，或者指定return表达式。

run函数实际上可以说是let和with两个函数的结合体，run函数只接收一个lambda函数为参数，以闭包形式返回，返回值为最后一行的值或者指定的return的表达式。

**run函数的适用场景**

适用于let，with函数任何场景。因为run函数是let，with两个函数结合体，准确来说它弥补了let函数在函数体内必须使用it参数替代对象，在run函数中可以像with函数一样可以省略，直接访问实例的公有属性和方法，另一方面它弥补了with函数传入对象判空问题，在run函数中可以像let函数一样做判空处理。

```
user?.run {
    println(name)
    println(number)
}
```

### apply - 内联扩展函数

```
/**
 * Calls the specified function [block] with `this` value as its receiver and returns `this` value.
 *
 * For detailed usage information see the documentation for [scope functions](https://kotlinlang.org/docs/reference/scope-functions.html#apply).
 */
@kotlin.internal.InlineOnly
public inline fun <T> T.apply(block: T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
    return this
}
```

功能：调用某对象的apply函数。

在函数块内可以通过this指代该对象，返回值为该对象本身。

从结构上来看apply函数和run函数很像，唯一不同点就是它们各自返回的值不一样，run函数是以闭包形式返回最后一行代码的值，而apply函数的返回的是传入对象的本身。

**apply函数的适用场景**

整体作用功能和run函数很像，唯一不同点就是它返回的值是对象本身，而run函数是一个闭包形式返回，返回的是最后一行的值。正是基于这一点差异它的适用场景稍微与run函数有点不一样。apply一般用于一个对象实例初始化的时候，需要对对象中的属性进行赋值。或者动态inflate出一个XML的View的时候需要给View绑定数据也会用到，这种情景非常常见。特别是在我们开发中会有一些数据model向View model转化实例化的过程中需要用到。

```
view = View.inflate(activity, R.layout.biz_exam_plan_layout_sheet_inner, null).apply {
    course_comment_tv_label.paint.isFakeBoldText = true
    course_comment_tv_score.paint.isFakeBoldText = true
    course_comment_tv_cancel.paint.isFakeBoldText = true
    course_comment_tv_confirm.paint.isFakeBoldText = true
    course_comment_seek_bar.max = 10
    course_comment_seek_bar.progress = 0
}
```

### also - 内联扩展函数

```
/**
 * Calls the specified function [block] with `this` value as its argument and returns `this` value.
 *
 * For detailed usage information see the documentation for [scope functions](https://kotlinlang.org/docs/reference/scope-functions.html#also).
 */
@kotlin.internal.InlineOnly
@SinceKotlin("1.1")
public inline fun <T> T.also(block: (T) -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block(this)
    return this
}
```

功能：调用某对象的also函数。

则该对象为函数的参数，在函数块内可以通过it指代该对象，返回值为该对象本身。

**also函数的适用场景**

适用于let函数的任何场景，also函数和let很像，只是唯一的不同点就是let函数最后的返回值是最后一行的返回值，而also函数的返回值是返回当前的这个对象。一般可用于多个扩展函数链式调用。

### repeat - 内联扩展函数

```
/**
 * Executes the given function [action] specified number of [times].
 *
 * A zero-based index of current iteration is passed as a parameter to [action].
 *
 * @sample samples.misc.ControlFlow.repeat
 */
@kotlin.internal.InlineOnly
public inline fun repeat(times: Int, action: (Int) -> Unit) {
    contract { callsInPlace(action) }

    for (index in 0 until times) {
        action(index)
    }
}
```

执行参数action函数times次。比如打印3次Hello World。

```
repeat(3) {
    println("Hello World")
}
```

### takeIf - 内联扩展函数

```
/**
 * Returns `this` value if it satisfies the given [predicate] or `null`, if it doesn't.
 */
@kotlin.internal.InlineOnly
@SinceKotlin("1.1")
public inline fun <T> T.takeIf(predicate: (T) -> Boolean): T? {
    contract {
        callsInPlace(predicate, InvocationKind.EXACTLY_ONCE)
    }
    return if (predicate(this)) this else null
}
```

如果满足predicate所制定的条件，则返回这个对象，否则返回null。

```
val s = "hi".takeIf {
    it.length < 3
}
println(s)
```

```
// 原始代码
if(someObject!= null && status) { 
   doThis()
}
// 改进的代码
someObject?.takeIf {
    status
}.apply {
    doThis()
}
```

```
// 语法上仍然正确。但逻辑错误
someObject?.takeIf { status } .apply { doThis() }

// 正确的。注意可空性检查?
someObject?.takeIf { status } ?.apply { doThis() }
```

doThis()在第一行中不管status true还是false都会执行。因为即使takeIf返回null，它仍然会被调用。（这里假设doThis()不是someObject的函数）。所以在这里，第二行的?是非常微妙且重要的。

### takeUnless - 内联扩展函数

```
/**
 * Returns `this` value if it _does not_ satisfy the given [predicate] or `null`, if it does.
 */
@kotlin.internal.InlineOnly
@SinceKotlin("1.1")
public inline fun <T> T.takeUnless(predicate: (T) -> Boolean): T? {
    contract {
        callsInPlace(predicate, InvocationKind.EXACTLY_ONCE)
    }
    return if (!predicate(this)) this else null
}
```

如果不满足predicate所制定的条件，则返回这个对象，否则返回 null。与takeIf相反。

```
val n = "nsz".takeUnless {
    it.length == 2
}
println(n)
```

> https://www.jianshu.com/p/371af11c7bb3

> https://blog.csdn.net/u013064109/article/details/78786646?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase