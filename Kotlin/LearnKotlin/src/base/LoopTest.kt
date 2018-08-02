package base

fun main(args: Array<String>) {

    for (i in 2..10) {
        println(i)
    }
    println("------------------------")

    for (i in 2..10 step 2) {
        println(i)
    }
    println("------------------------")

    for (i in 10 downTo 2 step 4) {
        println(i)
    }

}