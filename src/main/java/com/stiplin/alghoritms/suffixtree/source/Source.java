package com.stiplin.alghoritms.suffixtree.source;

public interface Source<T> {

    T get(int index);

    int size();

}
