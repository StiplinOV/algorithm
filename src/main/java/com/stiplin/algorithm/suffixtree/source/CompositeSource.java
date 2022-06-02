package com.stiplin.algorithm.suffixtree.source;

public class CompositeSource<T> implements Source<T> {

    private final Source<T> left;

    private final Source<T> right;

    public CompositeSource(Source<T> left, Source<T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public T get(int index) {
        if (index >= left.size()) {
            return right.get(index - left.size());
        }
        return left.get(index);
    }

    @Override
    public int size() {
        return left.size() + right.size();
    }

}
