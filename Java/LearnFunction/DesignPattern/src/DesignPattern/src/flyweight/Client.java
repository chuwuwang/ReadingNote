package flyweight;

public class Client {

    public static void main(String[] args) {
        FlyweightFactory factory = FlyweightFactory.getInstance();

        Flyweight fly = factory.getFlyweight("apple");
        fly.operation(1);

        fly = factory.getFlyweight("pear");
        fly.operation(2);

        fly = factory.getFlyweight("apple");
        fly.operation(3);

        fly = factory.getFlyweight("pear");
        fly.operation(2);

        int flyweightSize = factory.getFlyweightSize();
        System.out.println("object size:" + flyweightSize);
    }

}
