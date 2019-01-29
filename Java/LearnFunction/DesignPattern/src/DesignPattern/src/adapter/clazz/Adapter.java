package adapter.clazz;

public class Adapter extends Adapted implements Target {

    @Override
    public void normalMethod() {
        super.specificMethod();
    }

}
