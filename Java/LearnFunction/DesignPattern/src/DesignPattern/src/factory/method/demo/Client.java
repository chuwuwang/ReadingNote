package factory.method.demo;

public class Client {

    public static void main(String[] args) {
        AudiFactory factory = new AudiCarFactory();

        AudiQ3 q3 = factory.createAudiCar(AudiQ3.class);
        q3.drive();
        q3.selfNavigation();

        AudiQ5 q5 = factory.createAudiCar(AudiQ5.class);
        q5.drive();
        q5.selfNavigation();

        AudiQ7 q7 = factory.createAudiCar(AudiQ7.class);
        q7.drive();
        q7.selfNavigation();
    }

}
