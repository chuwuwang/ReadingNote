package com.zhou.collection_11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * print containers
 * 
 * @author zhou.ni
 * 
 */
public class PrintingContainers {

	public static void main(String[] args) {
		
		System.out.println(fill(new ArrayList<String>()));
		System.out.println(fill(new LinkedList<String>()));
		System.out.println(fill(new HashSet<String>()));
		System.out.println(fill(new TreeSet<String>()));
		System.out.println(fill(new LinkedHashSet<String>()));
		
		System.err.println(fill(new HashMap<String, String>()));
		System.err.println(fill(new TreeMap<String, String>()));
		System.err.println(fill(new LinkedHashMap<String, String>()));
		
	}

	@SuppressWarnings("rawtypes")
	static Collection fill(Collection<String> collection) {
		collection.add("rat");
		collection.add("cat");
		collection.add("dog");
		collection.add("dog");
		return collection;
	}

	@SuppressWarnings("rawtypes")
	static Map fill(Map<String, String> map) {
		map.put("rat", "Fuzzy");
		map.put("cat", "Rags");
		map.put("dog", "Bosco");
		map.put("dog", "Bosco");
		return map;
	}

}
