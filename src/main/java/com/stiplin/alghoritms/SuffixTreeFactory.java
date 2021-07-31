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

    public void setNode(Node node) {
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

    boolean hasNextNode() {
        if (leaveCharacter == null) {
            return false;
        }
        Edge edge = this.getNode().getChild(leaveCharacter);
        return edge.getRight() == edgePosition;
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

    public void toSuffixLink() {
        this.setNode(this.getNode().getSuffixLink());
    }

    void putChild(int index) {
        Node result = this.getNode();
        if(hasNextNode()) {
            result = getNextNode();
        } else if (!isNodePosition()) {
            throw new IllegalStateException();
        }

        result.putChild(source.charAt(index), index, source.length() - 1);
    }

    void moveTo(int indexFrom, int indexTo) {
        if (leaveCharacter != null) {
            Edge edge = node.getChild(leaveCharacter);
            if (indexFrom == edgePosition) {
                if (indexTo > edge.getRight()) {
                    edgePosition = edge.getRight();
                    moveTo(edge.getRight() + 1, indexTo);
                    return;
                } else {
                    edgePosition = indexTo;
                    moveTo(indexTo + 1, edge.getRight());
                }
            }
        }
        for (int i = indexFrom; i <= indexTo; i++) {
            char currentCharacter = source.charAt(i);
            this.move(currentCharacter);
        }
    }

}

class SuffixTreeFactory {

    public Node buildSuffixTree(String str) {
        return buildSuffixTree(str + '$', '$');
    }

    public Node buildSuffixTree(String str, Object o) {
        Node root = new Node();
        Position currentPosition = new Position(str, root, null, 0);

        for (int i = 0; i < str.length(); i++) {
            addSymbol(str, i, root, currentPosition);
        }

        return root;
    }

    public void addSymbol(String source, int symbolPosition, Node root, Position currentPosition) {
        char currentChar = source.charAt(symbolPosition);
        if (currentPosition.canMove(currentChar)) {
            currentPosition.move(currentChar);
            if(currentPosition.hasNextNode()) {
                currentPosition.setNode(currentPosition.getNextNode());
            }
        } else {
            if(currentPosition.hasNextNode()) {
                currentPosition.setNode(currentPosition.getNextNode());
            }
            if (currentPosition.isNodePosition()) {
                currentPosition.putChild(symbolPosition);
                while(currentPosition.getNode().hasSuffixLink()) {
                    currentPosition.toSuffixLink();
                    if (currentPosition.canMove(currentChar)) {
                        currentPosition.move(currentChar);
                        break;
                    } else {
                        currentPosition.putChild(symbolPosition);
                    }
                }
            } else {
                Node newNode = currentPosition.split();
                newNode.putChild(currentChar, symbolPosition, source.length() - 1);
                createSuffixLinks(source, symbolPosition, currentPosition, newNode, root);
            }
        }
    }

    private void createSuffixLinks(String str, int currentIndex, Position currentPosition, Node lastNode, Node root) {
        moveToSuffixLink(currentPosition, root);
        while (!currentPosition.isNodePosition() || currentPosition.getNode() != root) {
            if(currentPosition.canSplit()) {
                Node newNode = currentPosition.split();
                lastNode.setSuffixLink(newNode);
                //currentPosition.setNode(newNode);
                lastNode = newNode;
                newNode.putChild(str.charAt(currentIndex), currentIndex, str.length() - 1);
                moveToSuffixLink(currentPosition, root);

            } else {
                if (currentPosition.hasNextNode()) {
                    lastNode.setSuffixLink(currentPosition.getNextNode());
                }else {
                    lastNode.setSuffixLink(currentPosition.getNode());
                }
                if(currentPosition.canMove(str.charAt(currentIndex))) {
                    currentPosition.move(str.charAt(currentIndex));
                    return;
                }
                currentPosition.putChild(currentIndex);
                //currentPosition.toSuffixLink();
                if(currentPosition.hasNextNode()) {
                    lastNode = currentPosition.getNextNode();
                } else {
                    lastNode = currentPosition.getNode();
                }

                moveToSuffixLink(currentPosition, root);
            }
        }
        if (currentPosition.getNode() == root && currentPosition.isNodePosition()) {
            lastNode.setSuffixLink(root);
            if(currentPosition.canMove(str.charAt(currentIndex))) {
                currentPosition.move(str.charAt(currentIndex));
            } else {
                currentPosition.putChild(currentIndex);
            }
        }
        if(currentPosition.hasNextNode()) {
            currentPosition.setNode(currentPosition.getNextNode());
        }
    }

    private void moveToSuffixLink(Position currentPosition, Node root) {
        if (currentPosition.getNode() == root) {
            if (currentPosition.getEdge().getLength() == 1) {
                currentPosition.setNode(root);
            } else {
                int left = currentPosition.getEdge().getLeft() + 1;
                int right = currentPosition.getEdge().getRight();
                currentPosition.setNode(root);
                currentPosition.moveTo(left, right);
            }
        } else {
            int left = currentPosition.getEdge().getLeft();
            int right = currentPosition.getEdge().getRight();
            currentPosition.toSuffixLink();
            currentPosition.moveTo(left, right);
        }
    }

    public static void main(String[] args) {
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("ababbabbba"), 0));
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("aacbbab"), 0));
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("aacbbabbabbbbb"), 0));
        System.out.println(maxValue("aacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccb"));
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
