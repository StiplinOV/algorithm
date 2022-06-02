package com.stiplin.algorithm.suffixtree.source;

public class SingletonSource<T> implements Source<T> {

    private final T element;

    public SingletonSource(T element) {
        this.element = element;
    }

    @Override
    public T get(int index) {
        if (index == 0) {
            return element;
        }
        throw new IllegalArgumentException("Expected index == 0 but was " + index);
    }

    @Override
    public int size() {
        return 1;
    }
}
