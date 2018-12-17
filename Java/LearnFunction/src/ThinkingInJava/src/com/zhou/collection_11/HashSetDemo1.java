package com.zhou.collection_11;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetDemo1 {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		HashSet set = new HashSet();
		set.add("aa");
		set.add("dd");
		set.add("f");
		set.add("b");
		set.add("cc");
		set.add("a");
		
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	
	
}
