package base

/*

    Kotlin：声明处协变  Java：使用处协变

    Kotlin中的out关键字叫做variance annotation，因为它是在类型参数声明处所指定的，因此我们称
    之为声明处协变（declaration-site variance）

    对于Java来说则是使用处协变（use-site variance）其中类型通配符使得类型协变成为可能

 */


/*
    如果泛型类只是将泛型类型作为其方法的输出类型，那么我们就可以使用out
    producer = output = out
 */
interface Producer<out T> {

    fun producer(): T

}

/*
    如果泛型类只是将泛型类型作为其方法的输入类型，那么我们就可以使用in
    consumer = input = in
 */
interface Consumer<in T> {

    fun consumer(item: T)

}

/*
    如果泛型类将泛型类型作为其方法的输入类型和输出类型，那么我们就不能使用in与out来修饰泛型
 */
interface ProducerConsumer<T> {

    fun producer(): T

    fun consumer(item: T)

}

open class Fruits

open class Apples : Fruits()

class ApplePear : Apples()

class FruitProducer : Producer<Fruits> {

    override fun producer(): Fruits {
        println("Producer Fruits")
        return Fruits()
    }

}

class AppleProducer : Producer<Apples> {

    override fun producer(): Apples {
        println("Producer Apples")
        return Apples()
    }

}

class ApplePearProducer : Producer<ApplePear> {

    override fun producer(): ApplePear {
        println("Producer ApplePear")
        return ApplePear()
    }

}

fun main(args: Array<String>) {

    val producer1: Producer<Fruits> = FruitProducer()
    val producer2: Producer<Fruits> = AppleProducer()
    val producer3: Producer<Fruits> = ApplePearProducer()

}