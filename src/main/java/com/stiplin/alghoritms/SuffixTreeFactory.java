package com.stiplin.alghoritms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class SuffixTreeNode {

    private final Map<Character, SuffixTreeEdge> children = new HashMap<>();

    private SuffixTreeNode suffixLink;

    boolean hasChild(char character) {
        return children.containsKey(character);
    }

    SuffixTreeEdge getChild(char character) {
        return children.get(character);
    }

    SuffixTreeEdge putChild(char character, int left, int right) {
        return this.putChild(character, new SuffixTreeEdge(left, right));
    }

    SuffixTreeEdge putChild(char character, SuffixTreeEdge child) {
        return this.children.put(character, child);
    }

    boolean hasSuffixLink() {
        return this.suffixLink != null;
    }

    SuffixTreeNode getSuffixLink() {
        return this.suffixLink;
    }

    public void setSuffixLink(SuffixTreeNode suffixLink) {
        this.suffixLink = suffixLink;
    }

    boolean isTerminal() {
        return this.children.isEmpty();
    }

    public Map<Character, SuffixTreeEdge> getChildren() {
        return children;
    }

    public Set<Character> characters() {
        return children.keySet();
    }

    @Override
    public String toString() {
        return this.children.isEmpty() ? "TERMINAL" : "SuffixTreeNode{" +
                "children=" + children +
                ", suffixLink=" + (suffixLink == null ? "null" : suffixLink.hashCode()) +
                '}';
    }
}

class SuffixTreeEdge {

    private int left;

    private int right;

    private SuffixTreeNode dest;

    SuffixTreeEdge(int left, int right) {
        this(left, right, null);
    }

    SuffixTreeEdge(int left, int right, SuffixTreeNode dest) {
        this.left = left;
        this.right = right;
        if (dest == null) {
            this.dest = new SuffixTreeNode();
        } else {
            this.dest = dest;
        }
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

    public SuffixTreeNode getDest() {
        return dest;
    }

    public void setDest(SuffixTreeNode dest) {
        this.dest = dest;
    }

    SuffixTreeNode split(char prevCharacter, int position) {
        int prevRight = this.getRight();
        SuffixTreeNode prevDest = this.getDest();
        SuffixTreeNode newDest = new SuffixTreeNode();
        SuffixTreeEdge newEdge = new SuffixTreeEdge(position + 1, prevRight, prevDest);
        this.setDest(newDest);
        this.setRight(position);
        newDest.putChild(prevCharacter, newEdge);

        return newDest;
    }

    @Override
    public String toString() {
        return "SuffixTreeEdge{[" + left + ";" + right + "], dest=" + dest + '}';
    }
}

class Position {

    String source;

    SuffixTreeNode node;

    Character leaveCharacter;

    int edgePosition;

    Position(String source, SuffixTreeNode node, Character leaveCharacter, int edgePosition) {
        this.source = source;
        this.node = node;
        this.leaveCharacter = leaveCharacter;
        this.edgePosition = edgePosition;
    }

    public SuffixTreeNode getNode() {
        return node;
    }

    SuffixTreeEdge getEdge() {
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
            SuffixTreeEdge edge = node.getChild(leaveCharacter);
            if (edge.getRight() > edgePosition) {
                return source.charAt(edgePosition + 1) == character;
            } else {
                return edge.getDest().hasChild(character);
            }
        }
    }

    void move(char character) {
        if (leaveCharacter == null) {
            leaveCharacter = character;
            edgePosition = node.getChild(character).getLeft();
        } else {
            SuffixTreeEdge edge = node.getChild(leaveCharacter);
            if (edge.getRight() >= edgePosition) {
                edgePosition++;
            } else {
                node = edge.getDest();
                edgePosition = node.getChild(character).getLeft();
                leaveCharacter = character;
            }
        }

        SuffixTreeEdge edge = node.getChild(leaveCharacter);
        if (edge.getRight() == edgePosition) {
            node = edge.getDest();
            leaveCharacter = null;
            edgePosition = 0;
        }

    }

    public void setNode(SuffixTreeNode node) {
        this.node = node;
        this.leaveCharacter = null;
        this.edgePosition = 0;
    }
}

public class SuffixTreeFactory {

    public SuffixTreeNode buildSuffixTree(String str) {
        return buildSuffixTree(str, '\0');
    }

    public SuffixTreeNode buildSuffixTree(String str, char lastSymbol) {
        SuffixTreeNode root = new SuffixTreeNode();
        Position currentPosition = new Position(str, root, null, 0);

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            if (currentPosition.canMove(currentChar)) {
                currentPosition.move(currentChar);
            } else {
                if (currentPosition.isNodePosition()) {
                    SuffixTreeNode currentNode = currentPosition.getNode();
                    currentNode.putChild(currentChar, i, str.length() - 1);
                    while (currentNode.hasSuffixLink()) {
                        currentNode = currentNode.getSuffixLink();
                        currentNode.putChild(currentChar, i, str.length() - 1);
                        currentPosition.setNode(currentNode);
                    }
                } else {
                    SuffixTreeNode prevNode = currentPosition.getNode();
                    SuffixTreeEdge edge = currentPosition.getEdge();
                    SuffixTreeNode newNode = edge.split(str.charAt(currentPosition.getEdgePosition() + 1), currentPosition.getEdgePosition());
                    newNode.putChild(currentChar, i, str.length() - 1);
                    currentPosition.setNode(newNode);
                    if (prevNode == root) {
                        currentPosition.setNode(root);
                        int right = edge.getRight();
                        for (int j = edge.getLeft() + 1; j <= right && edge.getLength() > 1; j++) {
//                            if (prevNode != root) {
//                                prevNode = prevNode.getSuffixLink();
//                            }

                            for (int k = j; k <= right; k++) {
                                currentPosition.move(str.charAt(k));
                            }
                            if (currentPosition.isNodePosition()) {
                                newNode.setSuffixLink(currentPosition.getNode());
                            } else {
                                newNode.setSuffixLink(currentPosition.getEdge().split(str.charAt(currentPosition.edgePosition + 1), currentPosition.edgePosition));
                            }
                            newNode = newNode.getSuffixLink();
                            newNode.putChild(currentChar, i, str.length() - 1);
                            currentPosition.setNode(root);
                        }


                        newNode.setSuffixLink(root);
                        root.putChild(currentChar, i, str.length() - 1);
                        currentPosition.setNode(root);
                    } else {
                        for (int j = edge.getLeft(); j <= edge.getRight(); j++) {
                            if (prevNode != root) {
                                prevNode = prevNode.getSuffixLink();
                            }
                            currentPosition.setNode(prevNode);
                            for (int k = j; k <= edge.getRight(); k++) {
                                currentPosition.move(str.charAt(k));
                            }
                            if (currentPosition.isNodePosition()) {
                                newNode.setSuffixLink(currentPosition.getNode());
                            } else {
                                newNode.setSuffixLink(currentPosition.getEdge().split(str.charAt(currentPosition.edgePosition + 1), currentPosition.edgePosition));
                            }
                            newNode = newNode.getSuffixLink();
                            newNode.putChild(currentChar, i, str.length() - 1);
                            currentPosition.setNode(prevNode);
                            edge = prevNode.getChild(str.charAt(j));
                        }
                        root.putChild(currentChar, i, str.length() - 1);
                    }
                }
            }
        }
        System.out.println(print(root, 0));
        return root;
    }

    private String print(SuffixTreeNode node, int depth) {
        StringBuilder result = new StringBuilder();
        if (node.isTerminal()) {
            return "TERMINAL\n";
        }
        result.append("{\n");
        for(int i = 0; i <= depth; i++) {
            result.append("\t");
        }
        result.append(node.hashCode()).append("\n");
        for (char key : node.getChildren().keySet()) {
            SuffixTreeEdge edge = node.getChild(key);
            for(int i = 0; i <= depth; i++) {
                result.append("\t");
            }
            result.append(key).append(" => [").append(edge.getLeft()).append(";").append(edge.getRight()).append("], dest=").append(print(edge.getDest(), depth+1));
        }
        for(int i = 0; i <= depth; i++) {
            result.append("\t");
        }
        result.append("suffixLink: ").append(node.getSuffixLink() == null ? "null" : node.getSuffixLink().hashCode()).append("\n");
        for(int i = 0; i < depth; i++) {
            result.append("\t");
        }
        result.append("}\n");

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new SuffixTreeFactory().buildSuffixTree("abrashvabracadabra$"));
    }

}
