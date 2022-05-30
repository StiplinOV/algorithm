package com.stiplin.alghoritms.suffixtree;

public class Position {

    private final String source;

    private Node node;

    private Character leaveCharacter;

    private int edgePosition;

    Position(String source, Node node, Character leaveCharacter, int edgePosition) {
        this.source = source;
        this.node = node;
        this.leaveCharacter = leaveCharacter;
        this.edgePosition = edgePosition;
    }

    public Node getNode() {
        return node;
    }

    public Character getLeaveCharacter() {
        return leaveCharacter;
    }

    Edge getEdge() {
        return this.node.getChild(leaveCharacter);
    }

    int getEdgePosition() {
        return this.edgePosition;
    }

    boolean isNodePosition() {
        return leaveCharacter == null;
    }

    boolean canMove(char character) {
        if (leaveCharacter == null) {
            return node.hasChild(character);
        } else {
            if (hasNextNode()) {
                return new Position(source, this.getNextNode(), null, 0).canMove(character);
            }
            return source.charAt(edgePosition + 1) == character;
        }
    }

    public void setNode(Node node) {
        this.node = node;
        this.leaveCharacter = null;
        this.edgePosition = 0;
    }

    public boolean canSplit() {
        return this.getNode().getChild(leaveCharacter).getRight() > edgePosition;
    }

    public Node split() {
        return this.getEdge().split(source.charAt(this.getEdgePosition() + 1), this.getEdgePosition());
    }

    boolean hasNextNode() {
        if (leaveCharacter == null) {
            return false;
        }
        Edge edge = this.getNode().getChild(leaveCharacter);
        return edge.getRight() == edgePosition;
    }

    Node getNextNode() {
        return this.getNode().getChild(leaveCharacter).getDest();
    }

    public void toSuffixLink() {
        this.setNode(this.getNode().getSuffixLink());
    }

    void putChild(int index) {
        Node result = this.getNode();
        if (hasNextNode()) {
            result = getNextNode();
        }

        result.putChild(source.charAt(index), index, source.length() - 1);
    }

    void moveTo(int indexFrom, int indexTo) {
        int remainingShifts = indexTo - indexFrom + 1;
        int currentCharPosition = indexFrom;
        while (remainingShifts != 0) {
            if (leaveCharacter == null) {
                this.move(source.charAt(currentCharPosition));
                remainingShifts -= 1;
            } else {
                Edge edge = node.getChild(leaveCharacter);
                if (edge.getRight() == edgePosition) {
                    this.move(source.charAt(currentCharPosition));
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

    void move(char character) {
        if (leaveCharacter == null) {
            leaveCharacter = character;
            edgePosition = node.getChild(character).getLeft();
        } else {
            Edge edge = node.getChild(leaveCharacter);
            if (edge.getRight() > edgePosition) {
                edgePosition++;
            } else {
                setNode(edge.getDest());
                leaveCharacter = character;
                edgePosition = edge.getDest().getChild(leaveCharacter).getLeft();
            }
        }
    }

}