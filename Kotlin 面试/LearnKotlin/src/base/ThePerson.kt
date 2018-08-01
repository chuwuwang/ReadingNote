package base


/**
 * 延迟初始化属性
 *
 * kotlin要求非空类型的属性必须要在构造方法中进行初始化
 * 有时，这种要求不太方便，比如可以通过依赖注入或是单元测试的情况下进行属性的赋值
 *
 * 通过lateinit关键字标识属性为延迟初始化，需要满足如下3个条件
 *
 * 1.lateinit只能用在类体中声明的var属性上，不能用在primary constructor声明的属性上
 * 2.属性不能拥有自定义的setter和getter
 * 3.属性类型需要为非空，且不能是原生数据类型
 *
 */
class ThePerson(address: String, name: String) {

    val age: Int = 20

    var address: String = address
        get() {
            println("get invoke")
            return field
        }
        set(value) {
            println("set invoke")
            field = value
        }

    var name: String = name

    lateinit var pst: String

}

fun main(args: Array<String>) {

    val person = ThePerson("shanghai", "li")
    println(person.age)
    println(person.address)

    person.address = "beijing"
    println(person.address)
    println(person.name)

}