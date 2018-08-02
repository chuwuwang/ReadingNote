package com.zhou.prototype;

public class Client {

	public static void main(String[] args) {
		WordDocument originDoc = new WordDocument();
		originDoc.setText("这是一篇文档");
		originDoc.addImage("图片1");
		originDoc.addImage("图片2");
		originDoc.addImage("图片3");
		originDoc.showDocument();

		WordDocument newDoc = (WordDocument) originDoc.clone();
		newDoc.showDocument();

		System.out.println("equals = " + originDoc.equals(newDoc));

		newDoc.setText("这是修改过的文本");
		newDoc.addImage("hello.jpg");
		newDoc.addImage("world.png");
		newDoc.showDocument();

		originDoc.showDocument();
	}

}
