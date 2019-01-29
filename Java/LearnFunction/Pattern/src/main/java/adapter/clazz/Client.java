package adapter.clazz;

public class Client {

    public static void main(String[] args) {
        ConcreteTarget concreteTarget = new ConcreteTarget();
        concreteTarget.normalMethod();

        Adapter adapter = new Adapter();
        adapter.normalMethod();

    }

}
