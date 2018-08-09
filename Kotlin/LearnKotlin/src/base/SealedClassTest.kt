package base

/*
    密封类 sealed class


 */

sealed class Calculator

class Add : Calculator()

class Subtract : Calculator()

class Multiply : Calculator()

fun calculator(cal: Calculator, a: Int, b: Int) = when (cal) {
    is Add -> a + b
    is Subtract -> a - b
    is Multiply -> a * b
}

fun main(args: Array<String>) {
    calculator(Add(), 1, 2)
    calculator(Subtract(), 1, 2)
}