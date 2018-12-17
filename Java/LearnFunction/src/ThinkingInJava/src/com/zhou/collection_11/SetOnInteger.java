package com.zhou.collection_11;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetOnInteger {
	
	public static void main(String[] args) {
		
		Random random = new Random(47);
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < 10000; i++) {
			set.add(random.nextInt(30));
		}
		System.out.println(set);
	}
	
}
