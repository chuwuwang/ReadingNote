package base

fun breakDemo() {

    nsz@ for (outer in 1..5) {
        for (inner in 1..10) {
            println("outer: $outer")
            println("inner: $inner")
            if (inner % 2 == 0) {
                continue@nsz
            }
        }
    }


}

fun main(args: Array<String>) {

    breakDemo()

}