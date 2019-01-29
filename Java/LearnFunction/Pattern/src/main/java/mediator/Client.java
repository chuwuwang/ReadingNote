package mediator;

public class Client {

    public static void main(String[] args) {
        MainBoard mediator = new MainBoard();

        CPU cpu = new CPU(mediator);
        CDDevice cd = new CDDevice(mediator);
        SoundCard sc = new SoundCard(mediator);
        GraphicsCard gc = new GraphicsCard(mediator);

        mediator.setCpu(cpu);
        mediator.setCdDevice(cd);
        mediator.setSoundCard(sc);
        mediator.setGraphicsCard(gc);

        cd.load();
    }

}
