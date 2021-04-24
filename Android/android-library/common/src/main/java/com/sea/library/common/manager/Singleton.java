package com.sea.library.common.manager;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public final class Singleton {

    private final Map<Class, Object> instanceMap = new HashMap<>();

    private Singleton() {

    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static <T> T getObjectInstance(Class<T> clazz) {
        return SingletonHolder.INSTANCE.getObjectInstanceInner(clazz, null);
    }

    public static <T> T getObjectInstance(Class<T> clazz, Class[] paramTypes, Object... params) {
        return SingletonHolder.INSTANCE.getObjectInstanceInner(clazz, paramTypes, params);
    }

    @SuppressWarnings("unchecked")
    private <T> T getObjectInstanceInner(Class<T> clazz, Class[] paramTypes, Object... params) {
        boolean contains = instanceMap.containsKey(clazz);
        if (contains) {
            return (T) instanceMap.get(clazz);
        }
        synchronized (instanceMap) {
            contains = instanceMap.containsKey(clazz);
            if (contains) {
                return (T) instanceMap.get(clazz);
            }
            try {
                Constructor<T> constructor = clazz.getDeclaredConstructor(paramTypes);
                constructor.setAccessible(true);
                T instance = constructor.newInstance(params);
                instanceMap.put(clazz, instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) instanceMap.get(clazz);
    }

}