package com.zhou.flyweight;

public class Client {

	public static void main(String[] args) {
		FlyweightFactory factory = FlyweightFactory.getInstance();
		
		Flyweight fly = factory.getFlyweight("zhangsan");
		fly.operation(1);

		fly = factory.getFlyweight("lisi");
		fly.operation(2);

		fly = factory.getFlyweight("zhangsan");
		fly.operation(3);
		
		fly = factory.getFlyweight("lisi");
		fly.operation(2);
		
		System.out.println("object size:" + factory.getFlyweightSize());
	}

}
