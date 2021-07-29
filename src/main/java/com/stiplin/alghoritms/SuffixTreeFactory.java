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
            System.out.println(1);
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

    private Node prevNode;

    private Edge prevEdge;

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

    public boolean hasPrevNode() {
        return prevNode != null;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public Edge getPrevEdge() {
        return prevEdge;
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
            try {
                return node.hasChild(character);
            } catch (NullPointerException ex) {
                throw ex;
            }
        } else {
            try {
                return source.charAt(edgePosition + 1) == character;
            } catch (Throwable ex) {
                System.out.println(1);
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
                if (edge.getRight() >= edgePosition) {
                    edgePosition++;
                }
            }

            Edge edge = node.getChild(leaveCharacter);
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

    void back() {
        if(this.isNodePosition()) {
            throw new IllegalStateException("");
        }
        this.edgePosition--;
    }

    public void setNode(Node node) {
        this.node = node;
        this.prevNode = null;
        this.leaveCharacter = null;
        this.edgePosition = 0;
    }

    public Node split() {
        return this.getEdge().split(source.charAt(this.getEdgePosition() + 1), this.getEdgePosition());
    }

    public void toNode() {
        this.leaveCharacter = null;
        this.edgePosition = 0;
    }

    Node putChild(int index) {
        Node result = this.getNode();

        if (!this.isNodePosition()) {
            result = this.split();
        }
        result.putChild(source.charAt(index), index, source.length() - 1);

        return result;
    }

    void moveTo(int indexFrom, int indexTo) {
        for (int i = indexFrom; i <= indexTo; i++) {
            char currentCharacter = source.charAt(i);
            if (!this.canMove(currentCharacter)) {
                this.putChild(i);
            }
            this.move(currentCharacter);
        }
    }

}

public class SuffixTreeFactory {

    public Node buildSuffixTree(String str) {
        return buildSuffixTree(str + '$', '$');
    }

    /*
     Считываем символы

Если находимся в вершине
	не можем идти - добавляем все символы
	можем идти - идем к символу
Если находимся в ребре
	Если можем идти - идем к символу
	Если не можем идти
		сплитим
		к созданной ноде добавляем все символы
		обновляем суффиксные ссылки для созданой вершины


Обновление суффиксных сылок:
	Если текущая вершина корень
		Если расстояние между созданой и текущей вершиной 1, то созданную вершину линкуем к корню и продолжаем считывать символы
		иначе от корня ищем точку - префикс от корня до созданной ноде
		При необходимости её сплитим
		созданную вершину линкуем к точке и вызываем алгоритм обновления суффиксных ссылок для суффикса
	Если текущая вершина не корень
		берем от нее суффиксную ссылку
		спускаемся до такой же точки как подстрока от текущей вершины до созданной ноды. При необходимости создаем эту точку.
		созданную вершину линкуем к точке
		текущая вершина теперь вершина которая "над" точкой
		Если она содержит суффиксную ссылку - выполняем обновление суффиксной ссылки
		Если нет, продолжаем считывать символы. Это теперь текущая позиция
    * */

    public Node buildSuffixTree(String str, char lastSymbol) {
        Node root = new Node();
        Position currentPosition = new Position(str, root, null, 0);

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            System.out.println(currentChar);
            System.out.println(print(root, 0));
            if (currentPosition.canMove(currentChar)) {
                currentPosition.move(currentChar);
            } else {
                if (currentPosition.isNodePosition()) {
                    currentPosition.putChild(i);
                    //TODO а если мы не в корне?
                } else {
                    Node suffixTreeNode = currentPosition.putChild(i);
                    Edge edge = currentPosition.getEdge();
                    currentPosition = createSuffixLinks(
                            str,
                            i,
                            currentPosition,
                            suffixTreeNode,
                            root,
                            edge.getLeft() + 1,
                            edge.getRight()
                    );
                }
            }
        }

        System.out.println(print(root, 0));
        return root;
    }

    Position createSuffixLinks(
            String str,
            int currentIndex,
            Position currentPosition,
            Node suffixTreeNode,
            Node root,
            int left,
            int right
    ) {
        if (currentPosition.getNode() == root) {
            if (left > right) {
                suffixTreeNode.setSuffixLink(root);
                currentPosition.setNode(root);
                currentPosition.putChild(currentIndex);

                return currentPosition;
            }
            currentPosition.setNode(root);
            currentPosition.moveTo(left, right);
            suffixTreeNode.setSuffixLink(currentPosition.putChild(currentIndex));
            currentPosition.setNode(root);
            return createSuffixLinks(str, currentIndex, currentPosition, suffixTreeNode.getSuffixLink(), root, left+1, right);
            //	    Если текущая вершина корень
            //		Если расстояние между созданой и текущей вершиной 1, то созданную вершину линкуем к корню и продолжаем считывать символы
            //		иначе от корня ищем точку - префикс от корня до созданной ноде
            //		При необходимости её сплитим
            //		созданную вершину линкуем к точке и вызываем алгоритм обновления суффиксных ссылок для суффикса
        } else {
            Node currentSuffix = currentPosition.getNode().getSuffixLink();
            Position suffixPosition = new Position(str, currentSuffix, null, 0);
            suffixPosition.moveTo(left, right);
            suffixTreeNode.setSuffixLink(suffixPosition.putChild(currentIndex));

            if (currentSuffix.hasSuffixLink() || currentSuffix == root) {
                return createSuffixLinks(str, currentIndex, suffixPosition, suffixTreeNode.getSuffixLink(), root, left, right);
            }

            return new Position(str, currentSuffix, null, 0);
            //Обновление суффиксных сылок:

            //	Если текущая вершина не корень
            //		берем от нее суффиксную ссылку
            //		спускаемся до такой же точки как подстрока от текущей вершины до созданной ноды. При необходимости создаем эту точку.
            //		созданную вершину линкуем к точке
            //		текущая вершина теперь вершина которая "над" точкой
            //		Если она содержит суффиксную ссылку - выполняем обновление суффиксной ссылки
            //		Если нет, продолжаем считывать символы. Это теперь текущая позиция
        }
    }

    public static void main(String[] args) {
        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("ababb"), 0));
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("aacbbab"), 0));
//        System.out.println(print(new SuffixTreeFactory().buildSuffixTree("aacbbabbabbbbb"), 0));
//        System.out.println(maxValue("aacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccb"));
    }


    private static String print(Node node, int depth) {
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
