package array;

import java.lang.reflect.Array;

public class GenericArray<T> {

    private T[] array;

    public GenericArray(Class<T> type, int size) {
        array = ( T[] ) Array.newInstance(type, size);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] create() {
        return array;
    }


}