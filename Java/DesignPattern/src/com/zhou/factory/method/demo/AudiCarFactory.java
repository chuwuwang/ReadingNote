package com.zhou.factory.method.demo;

public class AudiCarFactory extends AudiFactory {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AudiCar> T createAudiCar(Class<T> clazz) {
		AudiCar car = null;
		try {
			car = (AudiCar) Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (T) car;
	}

}
