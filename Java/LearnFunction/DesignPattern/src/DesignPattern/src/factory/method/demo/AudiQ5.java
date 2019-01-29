package factory.method.demo;

public class AudiQ5 extends AudiCar {

    @Override
    public void drive() {
        System.out.println("Q5 启动了");
    }

    @Override
    public void selfNavigation() {
        System.out.println("Q5 开始自动导航");
    }

}
