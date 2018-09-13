package proxy;

public class RealObject implements Proxy {

    @Override
    public void operation() {
        System.out.println("real operation");
    }

}
