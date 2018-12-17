package com.zhou.jvm_07;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与 instanceof 关键字演示
 *
 */
public class ClassLoaderTest {

	public static void main(String[] args) throws Exception {

		ClassLoader myLoader = new ClassLoader() {

			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if (is == null) {
						return super.loadClass(name);
					}
					byte[] b = new byte[is.available()];
					is.read(b);
					// return defineClass(name,b，0，b.length);
					return null;
				} catch (IOException e) {
					throw new ClassNotFoundException(name);
				}
			}

		};

		Object obj = myLoader.loadClass("com.zhou.jvm_07.ClassLoaderTest").newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj instanceof com.zhou.jvm_07.ClassLoaderTest);
	}

}
