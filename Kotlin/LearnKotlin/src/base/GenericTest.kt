package base

/*
    泛型 generic 表示变量类型的参数化


 */

class MyGeneric<T>(t: T) {

    var variable: T = t

}

fun main(args: Array<String>) {

    var myGeneric = MyGeneric("hello world")
    println(myGeneric.variable)

    val myGenericClass = MyGenericClass<String>("abc")
    myTest(myGenericClass)

}

// 协变 covariant 与逆变 contravariant
// 概念和来源
// List<Object>
// List<String>

class MyGenericClass<T>(t: T) {

    private var t: T = t

    fun get(): T = this.t

}

fun myTest(myClass: MyGenericClass<String>) {
    var myObject: MyGenericClass<String> = myClass
    myObject.get()
}