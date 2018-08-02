package base

fun main(args: Array<String>) {

    MyTest.a
    MyTest.method()

}

class MyTest {

    companion object MyObject {

        val a = 5;

        fun method() {
            println("method")
        }

    }

}