package com.sea.library.common.manager;

/**
 * Singleton helper class for lazily initialization.
 */
public abstract class BaseSingleton<T> {

    public BaseSingleton() {

    }

    private T instance;

    protected abstract T create();

    public final T get() {
        synchronized (this) {
            if (instance == null) {
                instance = create();
            }
            return instance;
        }
    }

}