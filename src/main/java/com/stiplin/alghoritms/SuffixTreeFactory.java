package com.stiplin.alghoritms;

import java.util.Collection;
import java.util.HashMap;
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

    void putChild(char character, int left, int right) {
        if(this.hasChild(character)) {
            System.out.println(1);
        }
        this.putChild(character, new SuffixTreeEdge(left, right));
    }

    void putChild(char character, SuffixTreeEdge child) {
        if(this.hasChild(character)) {
            System.out.println(1);
        }
        this.children.put(character, child);
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

    public Collection<SuffixTreeEdge> edges() {
        return children.values();
    }

    public int getNumberOfTerminals() {
        Collection<SuffixTreeEdge> edges = children.values();
        int result = 0;

        if (edges.isEmpty()) {
            return 1;
        }
        for (SuffixTreeEdge edge : edges) {
            result += edge.getNumberOfTerminals();
        }

        return result;
    }

}

class SuffixTreeEdge {

    private final int left;

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

    public int getNumberOfTerminals() {
        return this.getDest().getNumberOfTerminals();
    }

}

class Position {

    private final String source;

    private SuffixTreeNode node;

    private SuffixTreeNode prevNode;

    private SuffixTreeEdge prevEdge;

    private Character leaveCharacter;

    private int edgePosition;

    Position(String source, SuffixTreeNode node, Character leaveCharacter, int edgePosition) {
        this.source = source;
        this.node = node;
        this.leaveCharacter = leaveCharacter;
        this.edgePosition = edgePosition;
    }

    public SuffixTreeNode getNode() {
        return node;
    }

    public boolean hasPrevNode() {
        return prevNode != null;
    }

    public SuffixTreeNode getPrevNode() {
        return prevNode;
    }

    public SuffixTreeEdge getPrevEdge() {
        return prevEdge;
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
            try {
                return node.hasChild(character);
            } catch (NullPointerException ex) {
                throw ex;
            }
        } else {
            return source.charAt(edgePosition + 1) == character;
        }
    }

    void move(char character) {
        try {
            if (leaveCharacter == null) {
                leaveCharacter = character;
                edgePosition = node.getChild(character).getLeft();
            } else {
                SuffixTreeEdge edge = node.getChild(leaveCharacter);
                if (edge.getRight() >= edgePosition) {
                    edgePosition++;
                }
            }

            SuffixTreeEdge edge = node.getChild(leaveCharacter);
            if (edge.getRight() == edgePosition) {
                prevNode = node;
                prevEdge = getEdge();
                node = edge.getDest();
                leaveCharacter = null;
                edgePosition = 0;
            }
        } catch (NullPointerException ex) {
            throw ex;
        }
    }

    public void setNode(SuffixTreeNode node) {
        this.node = node;
        this.prevNode = null;
        this.leaveCharacter = null;
        this.edgePosition = 0;
    }

    public void split() {
        this.setNode(this.getEdge().split(source.charAt(this.getEdgePosition() + 1), this.getEdgePosition()));
    }

}

public class SuffixTreeFactory {

    public SuffixTreeNode buildSuffixTree(String str) {
        return buildSuffixTree(str + '$', '$');
    }

    public SuffixTreeNode buildSuffixTree(String str, char lastSymbol) {
        SuffixTreeNode root = new SuffixTreeNode();
        Position currentPosition = new Position(str, root, null, 0);

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
           // System.out.println(currentChar);
           // System.out.println(i);
           // System.out.println(print(root, 0));

            if (currentPosition.canMove(currentChar)) {
                currentPosition.move(currentChar);
            } else {
                if (currentPosition.isNodePosition()) {
                    if (currentPosition.getNode() == root) {
                        root.putChild(currentChar, i, str.length() - 1);
                    } else {
                        this.putStringAndCreateSuffixLinks(str, currentPosition.getPrevNode(), currentPosition.getPrevEdge(), currentPosition, root, i);
                    }
                } else {
                    SuffixTreeNode prevNode = currentPosition.getNode();
                    SuffixTreeEdge prevEdge = currentPosition.getEdge();
                    currentPosition.split();
                    this.putStringAndCreateSuffixLinks(
                            str,
                            prevNode,
                            prevEdge,
                            currentPosition,
                            root,
                            i
                    );
                }
            }
        }
        //System.out.println(print(root, 0));
        return root;
    }

    void putStringAndCreateSuffixLinks(
            String sourceStr,
            SuffixTreeNode parentNode,
            SuffixTreeEdge parentEdge,
            Position position,
            SuffixTreeNode rootNode,
            int from
    ) {
        if(from == 11) {
            System.out.println(1);
        }
        char parentNodeCharacter = sourceStr.charAt(parentEdge.getLeft());
        char childStartCharacter = sourceStr.charAt(from);
        SuffixTreeNode nodeForSuffixLink = parentEdge.getDest();

        position.setNode(parentNode);
        if (parentNode == rootNode) {
            parentEdge.getDest().putChild(childStartCharacter, from, sourceStr.length() - 1);
            putStringAndCreateSuffixLinksForRoot(sourceStr, parentEdge, position, rootNode, from);
        } else {
            nodeForSuffixLink.putChild(childStartCharacter, from, sourceStr.length() - 1);
            SuffixTreeNode parentSuffix = parentNode.getSuffixLink();
            position.setNode(parentSuffix);
            do {
                if (position.canMove(parentNodeCharacter)) {
                    for (int i = parentEdge.getLeft(); i <= parentEdge.getRight(); i++) {
                        position.move(sourceStr.charAt(i));
                    }
                    if (position.isNodePosition()) {
                        nodeForSuffixLink.setSuffixLink(position.getNode());
                    } else {
                        position.split();
                        nodeForSuffixLink.setSuffixLink(position.getNode());
                    }
                } else {
                    //parentSuffix.putChild(parentNodeCharacter, parentEdge.getLeft(), parentEdge.getRight());
                    nodeForSuffixLink.setSuffixLink(parentSuffix.getChild(parentNodeCharacter).getDest());
                }
                nodeForSuffixLink.getSuffixLink().putChild(childStartCharacter, from, sourceStr.length() - 1);
                nodeForSuffixLink = nodeForSuffixLink.getSuffixLink();
                if (parentSuffix == rootNode) {
                    //position.setNode(rootNode);
                } else {
                    parentNode = parentSuffix;
                    parentSuffix = parentNode.getSuffixLink();
                    position.setNode(parentSuffix);
                }
            } while (!nodeForSuffixLink.hasSuffixLink() && parentSuffix != rootNode);
            if (parentSuffix == rootNode) {
                if(position.isNodePosition()) {
                    parentEdge = rootNode.getChild(parentNodeCharacter);
                    //parentEdge.getDest().putChild(childStartCharacter, from, sourceStr.length() - 1);
                } else {
                    parentEdge = position.getEdge();
                }
                putStringAndCreateSuffixLinksForRoot(sourceStr, parentEdge, position, rootNode, from);
            }
        }
    }

    void putStringAndCreateSuffixLinksForRoot(
            String sourceStr,
            SuffixTreeEdge edge,
            Position position,
            SuffixTreeNode rootNode,
            int from
    ) {
        char childStartCharacter = sourceStr.charAt(from);
        SuffixTreeNode nodeForSuffixLink = edge.getDest();
        //nodeForSuffixLink.putChild(childStartCharacter, from, sourceStr.length() - 1);

        position.setNode(rootNode);
        int right = edge.getRight();
        for (int i = edge.getLeft() + 1; i <= right; i++) {
            for (int j = i; j <= right; j++) {
                position.move(sourceStr.charAt(j));
            }
            if (position.isNodePosition()) {
                System.out.println(1);
                //TODO???
                if (position.getNode().hasChild(childStartCharacter)) {
                    for (int j = from; j < sourceStr.length(); j++) {
                        if (position.canMove(sourceStr.charAt(j))) {
                            position.move(sourceStr.charAt(j));
                        } else {
                            if (position.isNodePosition()) {
                                position.getNode().putChild(sourceStr.charAt(j), j, sourceStr.length() - 1);
                            } else {
                                position.split();
                            }
                        }
                    }
                } else {
                    position.getNode().putChild(childStartCharacter, from, sourceStr.length() - 1);
                }
            } else {
                position.split();
                position.getNode().putChild(childStartCharacter, from, sourceStr.length() - 1);
            }
            nodeForSuffixLink.setSuffixLink(position.getNode());
            nodeForSuffixLink = nodeForSuffixLink.getSuffixLink();
            position.setNode(rootNode);
        }
        nodeForSuffixLink.setSuffixLink(rootNode);
        if (rootNode.hasChild(childStartCharacter)) {
            position.setNode(rootNode);
            for (int i = from; i < sourceStr.length(); i++) {
                char character = sourceStr.charAt(i);
                if (position.canMove(character)) {
                    position.move(character);
                } else {
                    if(position.isNodePosition()) {
                        position.getNode().putChild(character, i, sourceStr.length() - 1);
                    } else {
                        position.split();
                    }
                    break;
                }
            }
        } else {
            rootNode.putChild(childStartCharacter, from, sourceStr.length() - 1);
        }
        position.setNode(rootNode);
    }

    public static void main(String[] args) {
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("aaaaa"), 0));
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("aacbbab"), 0));
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("aacbbabbabbbbb"), 0));
        System.out.println(maxValue("aacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccb"));
    }


    private static String print(SuffixTreeNode node, int depth) {
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
            SuffixTreeEdge edge = node.getChild(key);
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
        SuffixTreeNode root = new SuffixTreeFactory().buildSuffixTree(t);

        return maxValue(root, 0, 0);
    }

    private static int maxValue(SuffixTreeNode node, int maxValueParam, int initialLength) {
        int maxValue = maxValueParam;

        for (SuffixTreeEdge edge : node.edges()) {
            int edgeMaxValue = maxValue(edge, initialLength);
            if (maxValue < edgeMaxValue) {
                maxValue = edgeMaxValue;
            }
        }

        return maxValue;
    }

    private static int maxValue(SuffixTreeEdge edge, int initialLength) {
        int edgeLength = edge.getLength();
        if (edge.getDest().isTerminal()) {
            edgeLength -= 1;
        }
        int rootToDestLength = initialLength + edgeLength;
        int initialMaxValue = (rootToDestLength) * edge.getNumberOfTerminals();

        return maxValue(edge.getDest(), initialMaxValue, rootToDestLength);
    }

}
