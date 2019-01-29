package flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂角色类
 */
public class FlyweightFactory {

    private static FlyweightFactory sInstance;

    private Map<String, Flyweight> files = new HashMap<>();

    private FlyweightFactory() {

    }

    public static FlyweightFactory getInstance() {
        if (sInstance == null) {
            sInstance = new FlyweightFactory();
        }

        return sInstance;
    }

    public Flyweight getFlyweight(String name) {
        // 先从缓存中查找对象
        Flyweight fly = files.get(name);

        if (fly == null) {
            // 如果对象不存在则创建一个新的Flyweight对象
            fly = new ConcreteFlyweight(name);

            // 把这个新的Flyweight对象添加到缓存中
            files.put(name, fly);
        }

        return fly;
    }

    public int getFlyweightSize() {
        return files.size();
    }

}