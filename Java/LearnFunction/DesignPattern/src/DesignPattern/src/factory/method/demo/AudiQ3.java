package factory.method.demo;

public class AudiQ3 extends AudiCar {

    @Override
    public void drive() {
        System.out.println("Q3 启动了");
    }

    @Override
    public void selfNavigation() {
        System.out.println("Q3 开始自动导航");
    }

}
