package com.zhou.collection_11;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Stitistics {

	public static void main(String[] args) {

		Random random = new Random(47);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < 10000; i++) {
			int r = random.nextInt(20);
			Integer req = map.get(r);
			map.put(r, req == null ? 1 : req + 1);
		}
		System.err.println(map);

	}

}
