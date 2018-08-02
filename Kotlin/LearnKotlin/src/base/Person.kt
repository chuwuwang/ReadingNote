package base

class Person constructor(username: String) {

    private var username: String
    private var age: Int
    private var address: String

    init {
        println(username)

        this.username = username
        this.age = 20;
        this.address = "beijing"
    }

    constructor(username: String, age: Int) : this(username) {
        println(" $username , $age ")

        this.username = username;
        this.age = age
        this.address = "beijing"
    }

}