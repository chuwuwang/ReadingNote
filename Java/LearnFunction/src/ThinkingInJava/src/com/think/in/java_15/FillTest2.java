package com.think.in.java_15;

import java.util.Collection;

public class FillTest2 {

}

interface Addable<T> {

	void add(T t);

}

class Fill2 {

	public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
		for (int i = 0; i < size; i++) {
			try {
				addable.add(classToken.newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

}

class AddableCollectionAdapter<T> implements Addable<T> {

	private Collection<T> c;

	public AddableCollectionAdapter(Collection<T> c) {
		this.c = c;
	}

	public void add(T t) {
		c.add(t);
	}

}

class Adapter {

	public static <T> Addable<T> collectionAdapter(Collection<T> c) {

		return new AddableCollectionAdapter<T>(c);

	}

}

