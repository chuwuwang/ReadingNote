package base

class ExtensionTest {

    fun add(a: Int, b: Int) = a + b

    fun subtract(a: Int, b: Int) = a - b

}

fun ExtensionTest.multiply(a: Int, b: Int) = a * b

fun main(args: Array<String>) {

    val extensionTest = ExtensionTest()

    extensionTest.add(1, 2)
    extensionTest.subtract(3, 4)
    extensionTest.multiply(2, 4)

    CC().foo()

}

class CC {

    fun foo() {
        println("number")
    }

}

fun CC.foo() {
    println("foo")
}

fun Any?.toString(): String {
    if (null == this) {
        return ""
    }
    return toString()
}