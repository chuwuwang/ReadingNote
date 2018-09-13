package factory.method;

public class Client {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        ConcreteProductA p = factory.createProduct(ConcreteProductA.class);
        p.method();
    }

}
