package flyweight;

/**
 * 具体享元角色类
 */
public class ConcreteFlyweight implements Flyweight {

    private String name; // 内蕴状态

    public ConcreteFlyweight() {

    }

    /**
     * 构造函数, 内蕴状态作为参数传入
     */
    public ConcreteFlyweight(String name) {
        this.name = name;
    }

    /**
     * 外蕴状态作为参数传入方法中, 改变方法的行为, 但是并不改变对象的内蕴状态。
     */
    @Override
    public void operation(int state) {
        System.out.println(name + " operation invoke");
    }

}
