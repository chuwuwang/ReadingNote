package base

fun main(args: Array<String>) {
    val list = listOf("hello", "world", "hello world", "welcome", "goodbye")
    for (item in list) {
        println(item)
    }
    println("------------------------")

    when {
        "world" in list -> println("world in collection")
    }
}