package com.stiplin.algorithm.suffixtree.source;

public interface Source<T> {

    T get(int index);

    int size();

}
