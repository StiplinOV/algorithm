package com.stiplin.algorithm.suffixtree;

import com.stiplin.algorithm.suffixtree.source.Source;

public class Position<T> {

    private final Source<T> source;

    private Node<T> node;

    private T leaveCharacter;

    private int edgePosition;

    Position(Source<T> source, Node<T> node, T leaveCharacter, int edgePosition) {
        this.source = source;
        this.node = node;
        this.leaveCharacter = leaveCharacter;
        this.edgePosition = edgePosition;
    }

    public Node<T> getNode() {
        return node;
    }

    public T getLeaveCharacter() {
        return leaveCharacter;
    }

    Edge<T> getEdge() {
        return this.node.getChild(leaveCharacter);
    }

    int getEdgePosition() {
        return this.edgePosition;
    }

    boolean isNodePosition() {
        return leaveCharacter == null;
    }

    boolean canMove(T element) {
        if (leaveCharacter == null) {
            return node.hasChild(element);
        } else {
            if (hasNextNode()) {
                return new Position<T>(source, this.getNextNode(), null, 0).canMove(element);
            }
            return source.get(edgePosition + 1) == element;
        }
    }

    public void setNode(Node<T> node) {
        this.node = node;
        this.leaveCharacter = null;
        this.edgePosition = 0;
    }

    public boolean canSplit() {
        return this.getNode().getChild(leaveCharacter).getRight() > edgePosition;
    }

    public Node<T> split() {
        return this.getEdge().split(source.get(this.getEdgePosition() + 1), this.getEdgePosition());
    }

    boolean hasNextNode() {
        if (leaveCharacter == null) {
            return false;
        }
        Edge<T> edge = this.getNode().getChild(leaveCharacter);
        return edge.getRight() == edgePosition;
    }

    Node<T> getNextNode() {
        return this.getNode().getChild(leaveCharacter).getDest();
    }

    public void toSuffixLink() {
        this.setNode(this.getNode().getSuffixLink());
    }

    void putChild(int index) {
        Node<T> result = this.getNode();
        if (hasNextNode()) {
            result = getNextNode();
        }

        result.putChild(source.get(index), index, source.size() - 1);
    }

    void moveTo(int indexFrom, int indexTo) {
        int remainingShifts = indexTo - indexFrom + 1;
        int currentCharPosition = indexFrom;
        while (remainingShifts != 0) {
            if (leaveCharacter == null) {
                this.move(source.get(currentCharPosition));
                remainingShifts -= 1;
            } else {
                Edge<T> edge = node.getChild(leaveCharacter);
                if (edge.getRight() == edgePosition) {
                    this.move(source.get(currentCharPosition));
                    remainingShifts -= 1;
                } else {
                    if (edge.getRight() - edgePosition > remainingShifts) {
                        edgePosition += remainingShifts;
                        return;
                    } else {
                        remainingShifts -= edge.getRight() - edgePosition;
                        edgePosition = edge.getRight();
                    }
                }
            }
            currentCharPosition = indexTo - remainingShifts + 1;
        }
    }

    void move(T element) {
        if (leaveCharacter == null) {
            leaveCharacter = element;
            edgePosition = node.getChild(element).getLeft();
        } else {
            Edge<T> edge = node.getChild(leaveCharacter);
            if (edge.getRight() > edgePosition) {
                edgePosition++;
            } else {
                setNode(edge.getDest());
                leaveCharacter = element;
                edgePosition = edge.getDest().getChild(leaveCharacter).getLeft();
            }
        }
    }

}