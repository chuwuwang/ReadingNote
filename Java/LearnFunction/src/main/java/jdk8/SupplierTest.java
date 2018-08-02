package jdk8;

import java.util.function.Supplier;

public class SupplierTest {

    public static void main(String[] args) {

        Supplier<Student> supplier = Student::new;
        System.out.println(supplier.get().name);
        System.out.println(supplier.get().age);

    }

}

class Student {

    public String name = "bei";
    public int age = 20;

    public Student() {

    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
