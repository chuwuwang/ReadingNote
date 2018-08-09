package base;

import java.util.ArrayList;
import java.util.List;

public class PECS {

    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Object obj = new Object();

        List<Cat> cats = new ArrayList<>();
        List<? extends Animal> catList = cats;
        // animals.add(cat);
        // Cat c = animals.get(0);
        Animal animal = catList.get(0);

        List<Animal> animals = new ArrayList<>();
        List<? super Animal> contravariantAnimals = animals;
        contravariantAnimals.add(cat);
        contravariantAnimals.add(dog);
        // contravariantAnimals.add(obj);
        // Animal animal = contravariantAnimals.get(0);
        Object object = contravariantAnimals.get(0);

    }

}

class Animal {

}

class Cat extends Animal {

}

class Dog extends Animal {

}
