package base

class EmptyClass

class MyClass constructor(username: String) {

    private val username: String = username.toUpperCase()

    init {
        println(username)
    }

}

fun main(args: Array<String>) {

    var myClass = MyClass("lainey");

}