package com.zhou.collection_11;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		Node n1 = new Node("n1");
		Node n2 = new Node("n2");
		Node n3 = new Node("n3");
		// 构造一个单向链表
		n1.next = n2;
		n2.next = n3;
		System.out.println(n1);
		
		// 插入一个元素 n4,放在 n1 和 n2 之间
		Node n4 = new Node("n4");
		n1.next = n4;
		n4.next = n2;
		System.out.println(n1);
		
		// 删除元素 n2
		n4.next = n3;
		System.out.println(n1);
	}
	
}

class Node {
	
	public String data; // 存放的元素
	public Node next;   // 指向下一个节点的引用

	public Node(String data) {
		super();
		this.data = data;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + "]";
	}
	
}
