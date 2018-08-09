package base

// 在Kotlin中，Consumer in  Producer out

class ParameterizedProducer<out T>(private val value: T) {

    fun get(): T {
        return this.value
    }

}

class ParameterizedConsumer<in T> {

    fun toString(value: T): String {
        return value.toString()
    }

}

fun main(args: Array<String>) {

    val parameterizedProducer = ParameterizedProducer("welcome")
    val myRef: ParameterizedProducer<Any> = parameterizedProducer
    var assert = myRef is ParameterizedProducer<Any>
    println(assert)

    val parameterizedConsumer = ParameterizedConsumer<Number>()
    var ref: ParameterizedConsumer<Int> = parameterizedConsumer
    assert = ref is ParameterizedConsumer<Int>
    println(assert)
}