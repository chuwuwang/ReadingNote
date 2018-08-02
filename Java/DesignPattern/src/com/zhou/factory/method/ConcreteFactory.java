package com.zhou.factory.method;

public class ConcreteFactory extends Factory {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Product> T createProduct(Class<T> clazz) {
		Product p = null;
		try {
			p = (Product) Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (T) p;
	}

}
