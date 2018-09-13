package factory.method;

public abstract class Factory {

    /**
     * 抽象工厂方法，具体生产什么由子类去实现
     *
     * @param clazz 产品对象类型
     * @return 具体的产品对象
     */
    public abstract <T extends Product> T createProduct(Class<T> clazz);

}
