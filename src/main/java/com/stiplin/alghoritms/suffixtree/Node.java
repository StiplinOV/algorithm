package com.stiplin.alghoritms.suffixtree;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node<T> {

    private final Map<T, Edge<T>> children = new HashMap<>();

    private Node<T> suffixLink;

    boolean hasChild(T character) {
        return children.containsKey(character);
    }

    Edge<T> getChild(T character) {
        return children.get(character);
    }

    void putChild(T character, int left, int right) {
        this.putChild(character, new Edge<>(left, right));
    }

    void putChild(T character, Edge<T> child) {
        this.children.put(character, child);
    }

    boolean hasSuffixLink() {
        return this.suffixLink != null;
    }

    Node<T> getSuffixLink() {
        return this.suffixLink;
    }

    public void setSuffixLink(Node<T> suffixLink) {
        this.suffixLink = suffixLink;
    }

    boolean isTerminal() {
        return this.children.isEmpty();
    }

    public Map<T, Edge<T>> getChildren() {
        return children;
    }

    public Set<T> characters() {
        return children.keySet();
    }

}
