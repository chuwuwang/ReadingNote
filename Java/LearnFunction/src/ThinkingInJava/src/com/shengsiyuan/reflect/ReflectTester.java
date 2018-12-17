package com.shengsiyuan.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTester {

	public Object copy(Object object) throws Exception {
		Class<? extends Object> classType = object.getClass();
		Object objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});

		// 获取 对象的所有成员变量
		Field[] fields = classType.getFields();
		for (Field field : fields) {

			System.out.println(field.toString());

			String name = field.getName();
			// 将属性的首字母转化为大写
			String firstLetter = name.substring(0, 1).toUpperCase();
			String getMethodName = "get" + firstLetter + name.substring(1);
			String setMethodName = "set" + firstLetter + name.substring(1);

			Method getMethod = classType.getMethod(getMethodName, new Class[] {});
			Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });

			Object value = getMethod.invoke(object, new Object[] {});

			setMethod.invoke(objectCopy, new Object[] { value });

		}

		return objectCopy;

	}

}
