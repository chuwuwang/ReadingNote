package base

// 扩展作用域
// 1.扩展函数所定义在的类实例叫做分发接收者（dispatch receiver）
// 2.扩展函数所扩展的那个类的实例叫做扩展接收者（extension receiver）
// 3.当以上两个名字出现冲突时，扩展接收者的优先级最高

// 扩展可以很好的解决Java中充斥的各种辅助类的问题


class DD {

    fun method() {
        println("DD invoke")
    }

}

class EE {

    private fun method2() {
        println("EE invoke")
    }

    private fun DD.hello() {
        method()
        method2()
    }

    fun world(dd: DD) {
        dd.hello()
    }

}