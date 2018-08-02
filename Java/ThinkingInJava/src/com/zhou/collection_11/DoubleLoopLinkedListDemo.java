package com.zhou.collection_11;

public class DoubleLoopLinkedListDemo {

	public static void main(String[] args) {
		Node1 n1 = new Node1("n1");
		Node1 n2 = new Node1("n2");
		Node1 n3 = new Node1("n3");
		// 构造一个双向循环链表
		n1.next = n2;
		n1.previous = n3;

		n2.next = n3;
		n2.previous = n1;

		n3.next = n1;
		n3.previous = n2;
		
		// 插入一个元素 n4,放在 n1 和 n2 之间
		Node1 n4 = new Node1("n4");
		n1.next = n4;
		n4.previous = n1;
		n4.next = n2;
		n2.previous = n4;
		
		// 删除元素 n2
		n4.next = n3;
		n3.previous = n4;
		n2.next = null;
		n2.previous = null;
	}

}

class Node1 {

	public Node1 previous; // 指向前一个节点，前驱
	public Node1 next; 	   // 指向下一个节点，后继
	public String data;    // 存放数据

	public Node1(String data) {
		super();
		this.data = data;
	}
	
}