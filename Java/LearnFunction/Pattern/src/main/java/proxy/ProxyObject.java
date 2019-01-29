package proxy;

public class ProxyObject implements Proxy {

    private RealObject realObject = new RealObject();

    @Override
    public void operation() {
        // 调用目标对象之前可以做相关操作
        System.out.println("before");

        realObject.operation();

        // 调用目标对象之后可以做相关操作
        System.out.println("after");
    }

}