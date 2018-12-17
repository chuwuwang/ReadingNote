package com.think.in.java_15;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ApplyTest {

	public static void main(String[] args) throws Exception {

		List<Shape> shapes = new ArrayList<Shape>();
		for (int i = 0; i < 10; i++) {
			shapes.add(new Shape());
		}
		Apply.apply(shapes, Shape.class.getMethod("rotate"));
		Apply.apply(shapes, Shape.class.getMethod("resize", int.class), 5);
		
		List<Square> squares = new ArrayList<Square>();
		for (int i = 0; i < 10; i++) {
			squares.add(new Square());
		}
		Apply.apply(squares, Shape.class.getMethod("rotate"));
		Apply.apply(squares, Shape.class.getMethod("resize", int.class), 5);
		
	}

}

class Apply {

	public static <T, S extends Iterable<? extends T>> void apply(S seq, Method f, Object... args) {
		try {
			for (T t : seq) {
				f.invoke(t, args);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

class Shape {

	public void rotate() {
		System.out.println(this + " rotate");
	}

	public void resize(int newSize) {
		System.out.println(this + " resize " + newSize);
	}

}

class Square extends Shape {

}

class FilledList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;

	public FilledList(Class<? extends T> type, int size) {
		try {
			for (int i = 0; i < size; i++) {
				add(type.newInstance());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
