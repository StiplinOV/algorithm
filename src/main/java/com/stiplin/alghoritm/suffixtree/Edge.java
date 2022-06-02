package com.stiplin.alghoritm.suffixtree;

import java.util.Objects;

public class Edge<T> {

    private final int left;

    private int right;

    private Node<T> dest;

    Edge(int left, int right) {
        this(left, right, null);
    }

    Edge(int left, int right, Node<T> dest) {
        this.left = left;
        this.right = right;
        this.dest = Objects.requireNonNullElseGet(dest, Node::new);
    }

    public int getLeft() {
        return left;
    }

    public int getLength() {
        return this.getRight() - this.getLeft() + 1;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public Node<T> getDest() {
        return dest;
    }

    public void setDest(Node<T> dest) {
        this.dest = dest;
    }

    Node<T> split(T prevCharacter, int position) {
        int prevRight = this.getRight();
        Node<T> prevDest = this.getDest();
        Node<T> newDest = new Node<>();
        Edge<T> newEdge = new Edge<>(position + 1, prevRight, prevDest);
        this.setDest(newDest);
        this.setRight(position);
        newDest.putChild(prevCharacter, newEdge);

        return newDest;
    }

}

