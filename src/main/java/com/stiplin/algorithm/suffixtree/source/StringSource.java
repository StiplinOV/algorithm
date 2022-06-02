package com.stiplin.algorithm.suffixtree.source;

public class StringSource implements Source<Character> {

    private final String source;

    public StringSource(String source) {
        this.source = source;
    }

    @Override
    public Character get(int index) {
        return source.charAt(index);
    }

    @Override
    public int size() {
        return source.length();
    }
}
