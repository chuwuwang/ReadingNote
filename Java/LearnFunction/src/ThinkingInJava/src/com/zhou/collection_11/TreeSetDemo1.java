package com.zhou.collection_11;

import java.util.Iterator;
import java.util.TreeSet;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TreeSetDemo1 {
	
	public static void main(String[] args) {
		
		TreeSet set = new TreeSet();
		set.add("a");
		set.add("d");
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
	
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
	}
	
}
