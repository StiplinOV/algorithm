package com.stiplin.alghoritms.suffixtree;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node {

    private final Map<Character, Edge> children = new HashMap<>();

    private Node suffixLink;

    boolean hasChild(char character) {
        return children.containsKey(character);
    }

    Edge getChild(char character) {
        return children.get(character);
    }

    void putChild(char character, int left, int right) {
        this.putChild(character, new Edge(left, right));
    }

    void putChild(char character, Edge child) {
        this.children.put(character, child);
    }

    boolean hasSuffixLink() {
        return this.suffixLink != null;
    }

    Node getSuffixLink() {
        return this.suffixLink;
    }

    public void setSuffixLink(Node suffixLink) {
        this.suffixLink = suffixLink;
    }

    boolean isTerminal() {
        return this.children.isEmpty();
    }

    public Map<Character, Edge> getChildren() {
        return children;
    }

    public Set<Character> characters() {
        return children.keySet();
    }

}
