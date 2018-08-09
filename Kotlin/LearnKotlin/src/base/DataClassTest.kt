package base

data class People(val name: String, var age: Int, var address: String)

fun main(args: Array<String>) {
    val p1 = People("leo", 20, "beijing")
    println(p1)

    val p2 = p1.copy(address = "shanghai")
    println(p2)

    val  (name, age, address) = p1;
    println(name + age + address)
}

// 数据类需要满足如下条件
//
// 1.主构造方法至少要有一个参数
// 2.所有的主构造方法参数都需要被标记为var或者val的
// 3.数据类不能是抽象的、open的、sealed的以及inner的


// 对于数据类，编译器会自动生成如下内容
// 1.equals/hashCode对
// 2.toString（）方法，形式为People（name=xxx, age=xxx, address=xxx）
// 3.针对属性的componentN方法，并且是按照属性的声明顺序来生成的


// 关于数据类成员的继承要点：
// 1.如果数据类中显示定义了equals，hashCode或者是toString方法，或是在数据类的父类中将这些方法声明为了final，
//   那么这些方法就不会在生成，转而使用已有的。
// 2.如果父类拥有ComponentN方法，并且是open的以及返回兼容的类型，那么编译器就会在数据类中生成相应的componentN方法，
//   并且重写父类的这些方法；如果父类中的这些方法由于不兼容的签名或是被定义为final的，那么编译器就会报错。
// 3.在数据类中显式提供componentN方法已经copy方法实现是不被允许的。


// 解构声明
// 在主构造方法中有多少个参数，就会依次生成对应的component1、component2、component3...
// 这些方法返回的就是对应字段的值，componentN方法是用来实现解构声明的