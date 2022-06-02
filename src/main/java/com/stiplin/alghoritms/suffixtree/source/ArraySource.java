package com.stiplin.alghoritms.suffixtree.source;

public class ArraySource<T> implements Source<T> {

    private final T[] array;

    public ArraySource(T[] array) {
        this.array = array;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public int size() {
        return array.length;
    }
}
