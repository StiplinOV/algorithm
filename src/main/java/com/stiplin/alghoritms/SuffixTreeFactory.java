package com.stiplin.alghoritms;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Node {

    private final Map<Character, Edge> children = new HashMap<>();

    private Node suffixLink;

    boolean hasChild(char character) {
        return children.containsKey(character);
    }

    Edge getChild(char character) {
        return children.get(character);
    }

    void putChild(char character, int left, int right) {
        if (this.hasChild(character)) {
            throw new IllegalStateException();
        }
        this.putChild(character, new Edge(left, right));
    }

    void putChild(char character, Edge child) {
        if (this.hasChild(character)) {
            System.out.println(1);
        }
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

    public Collection<Edge> edges() {
        return children.values();
    }

    public int getNumberOfTerminals() {
        Collection<Edge> edges = children.values();
        int result = 0;

        if (edges.isEmpty()) {
            return 1;
        }
        for (Edge edge : edges) {
            result += edge.getNumberOfTerminals();
        }

        return result;
    }

}

class Edge {

    private final int left;

    private int right;

    private Node dest;

    Edge(int left, int right) {
        this(left, right, null);
    }

    Edge(int left, int right, Node dest) {
        if (left > right) {
            throw new IllegalArgumentException();
        }
        this.left = left;
        this.right = right;
        if (dest == null) {
            this.dest = new Node();
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
        if (this.left > right) {
            throw new IllegalArgumentException();
        }
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

    public int getNumberOfTerminals() {
        return this.getDest().getNumberOfTerminals();
    }

}

class Position {

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
        try {
            return this.node.getChild(leaveCharacter);
        } catch (NullPointerException ex) {
            System.out.println(1);
            throw ex;
        }
    }

    int getEdgePosition() {
        return this.edgePosition;
    }

    boolean isNodePosition() {
        return leaveCharacter == null;
    }

    boolean canMove(char character) {
        if (leaveCharacter == null) {
            try {
                return node.hasChild(character);
            } catch (NullPointerException ex) {
                throw ex;
            }
        } else {
            try {
                return source.charAt(edgePosition + 1) == character;
            } catch (Throwable ex) {
                //System.out.println(1);
                throw ex;
            }
        }
    }

    void move(char character) {
        try {
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
        } catch (NullPointerException ex) {
            throw ex;
        }
    }

    void back() {
        if (this.isNodePosition()) {
            throw new IllegalStateException("");
        }
        this.edgePosition--;
    }

    public void setNode(Node node) {
        if (node == null) {
            System.out.println(1);
        }
        this.node = node;
        this.leaveCharacter = null;
        this.edgePosition = 0;
    }

    public boolean canSplit() {
        if (leaveCharacter == null) {
            return false;
        }
        Edge edge = this.getNode().getChild(leaveCharacter);
        return edge.getRight() > edgePosition;
    }

    public Node split() {
        return this.getEdge().split(source.charAt(this.getEdgePosition() + 1), this.getEdgePosition());
    }

    Node getNextNode() {
        if (leaveCharacter == null) {
            throw new IllegalStateException();
        }
        Edge edge = this.getNode().getChild(leaveCharacter);
        if (edge.getRight() == edgePosition) {
            return edge.getDest();
        }
        throw new IllegalStateException();
    }

    public void toNode() {
        this.leaveCharacter = null;
        this.edgePosition = 0;
    }

    public void toSuffixLink() {
        this.setNode(this.getNode().getSuffixLink());
    }

    Node putChild(int index) {
        Node result = this.getNode();

        if (!isNodePosition()) {
            throw new IllegalStateException();
        }

        result.putChild(source.charAt(index), index, source.length() - 1);

        return result;
    }

    void moveTo(int indexFrom, int indexTo) {
        for (int i = indexFrom; i <= indexTo; i++) {
            char currentCharacter = source.charAt(i);
            this.move(currentCharacter);
        }
    }

}

public class SuffixTreeFactory {

    public Node buildSuffixTree(String str) {
        return buildSuffixTree(str + '$', '$');
    }

    public Node buildSuffixTree(String str, Object o) {
        Node root = new Node();
        Position currentPosition = new Position(str, root, null, 0);

        for (int i = 0; i < str.length(); i++) {
            addSymbol(str, i, root, currentPosition);
        }

        System.out.println(print(root, 0));
        return root;
    }

    public void addSymbol(String source, int symbolPosition, Node root, Position currentPosition) {
        char currentChar = source.charAt(symbolPosition);
        if (currentPosition.canMove(currentChar)) {
            currentPosition.move(currentChar);
        } else {
            if (currentPosition.isNodePosition()) {
                currentPosition.putChild(symbolPosition);
            } else {
                splitNodeAndCreateSuffixLinks(source, symbolPosition, currentPosition, root);
            }
        }
    }

    private Node splitNodeAndCreateSuffixLinks(String str, int currentIndex, Position currentPosition, Node root) {
        Node newNode;
        if (currentPosition.canSplit()) {
            newNode = currentPosition.split();
            newNode.putChild(str.charAt(currentIndex), currentIndex, str.length() - 1);
            moveToSuffixLink(str, currentIndex, currentPosition, root, newNode);
        } else {
            newNode = currentPosition.getNextNode();
            if (newNode.hasChild(str.charAt(currentIndex))) {
                currentPosition.move(str.charAt(currentIndex));
                return newNode;
            } else {
                newNode.putChild(str.charAt(currentIndex), currentIndex, str.length() - 1);
            }
        }

        return newNode;
    }

    private void moveToSuffixLink(String str, int currentIndex, Position currentPosition, Node root, Node newNode) {
        if (newNode.hasSuffixLink()) {
            currentPosition.setNode(newNode);
            currentPosition.toSuffixLink();
            moveToSuffixLink(str, currentIndex, currentPosition, root, newNode.getSuffixLink());
            return;
        }
        if (newNode == root) {
            currentPosition.setNode(root);
            if (root.hasChild(str.charAt(currentIndex))) {
                currentPosition.move(str.charAt(currentIndex));
            } else {
                currentPosition.putChild(currentIndex);
            }
            return;
        }
        if (currentPosition.getNode() == root) {
            if (currentPosition.getEdge().getLength() == 1) {//TODO
                currentPosition.setNode(root);
                newNode.setSuffixLink(root);
                moveToSuffixLink(str, currentIndex, currentPosition, root, root);
//                if (!newNode.hasChild(str.charAt(currentIndex))) {
//                    currentPosition.putChild(currentIndex);
//                }
            } else {
                int left = currentPosition.getEdge().getLeft() + 1;
                int right = currentPosition.getEdge().getRight();
                currentPosition.setNode(root);
                Node suffixLink = down(str, currentIndex, currentPosition, left, right, root);
                newNode.setSuffixLink(suffixLink);
                //moveToSuffixLink(str, currentIndex, currentPosition, root, suffixLink);
            }
        } else {
            int left = currentPosition.getEdge().getLeft();
            int right = currentPosition.getEdge().getRight();
            currentPosition.toSuffixLink();
            Node suffixLink = down(str, currentIndex, currentPosition, left, right, root);
            newNode.setSuffixLink(suffixLink);
        }
    }

    private Node down(String str, int currentIndex, Position currentPosition, int left, int right, Node root) {
        currentPosition.moveTo(left, right);
        if (currentPosition.isNodePosition()) {
            Node result = currentPosition.getNode();
            if (!result.hasChild(str.charAt(currentIndex))) {
                currentPosition.putChild(currentIndex);
            }
            return result;
        } else {
            return splitNodeAndCreateSuffixLinks(str, currentIndex, currentPosition, root);
        }
    }

    public static void main(String[] args) {
        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("ababbabbba"), 0));
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("aacbbab"), 0));
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("aacbbabbabbbbb"), 0));
//        System.out.println(maxValue("aacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccb"));
    }


    static String print(Node node, int depth) {
        StringBuilder result = new StringBuilder();
        if (node.isTerminal()) {
            return "TERMINAL\n";
        }
        result.append("{\n");
        for (int i = 0; i <= depth; i++) {
            result.append("\t");
        }
        result.append(node.hashCode()).append("\n");
        for (char key : node.getChildren().keySet()) {
            Edge edge = node.getChild(key);
            for (int i = 0; i <= depth; i++) {
                result.append("\t");
            }
            result.append(key).append(" => [").append(edge.getLeft()).append(";").append(edge.getRight()).append("], dest=").append(print(edge.getDest(), depth + 1));
        }
        for (int i = 0; i <= depth; i++) {
            result.append("\t");
        }
        result.append("suffixLink: ").append(node.getSuffixLink() == null ? "null" : node.getSuffixLink().hashCode()).append("\n");
        for (int i = 0; i < depth; i++) {
            result.append("\t");
        }
        result.append("}\n");

        return result.toString();
    }

    private static int maxValue(String t) {
        Node root = new SuffixTreeFactory().buildSuffixTree(t);

        return maxValue(root, 0, 0);
    }

    private static int maxValue(Node node, int maxValueParam, int initialLength) {
        int maxValue = maxValueParam;

        for (Edge edge : node.edges()) {
            int edgeMaxValue = maxValue(edge, initialLength);
            if (maxValue < edgeMaxValue) {
                maxValue = edgeMaxValue;
            }
        }

        return maxValue;
    }

    private static int maxValue(Edge edge, int initialLength) {
        int edgeLength = edge.getLength();
        if (edge.getDest().isTerminal()) {
            edgeLength -= 1;
        }
        int rootToDestLength = initialLength + edgeLength;
        int initialMaxValue = (rootToDestLength) * edge.getNumberOfTerminals();

        return maxValue(edge.getDest(), initialMaxValue, rootToDestLength);
    }

}
