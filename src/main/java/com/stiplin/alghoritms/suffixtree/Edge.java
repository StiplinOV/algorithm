package com.stiplin.alghoritms.suffixtree;

import java.util.Objects;

public class Edge {

    private final int left;

    private int right;

    private Node dest;

    Edge(int left, int right) {
        this(left, right, null);
    }

    Edge(int left, int right, Node dest) {
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

    public Node getDest() {
        return dest;
    }

    public void setDest(Node dest) {
        this.dest = dest;
    }

    Node split(char prevCharacter, int position) {
        int prevRight = this.getRight();
        Node prevDest = this.getDest();
        Node newDest = new Node();
        Edge newEdge = new Edge(position + 1, prevRight, prevDest);
        this.setDest(newDest);
        this.setRight(position);
        newDest.putChild(prevCharacter, newEdge);

        return newDest;
    }

}

