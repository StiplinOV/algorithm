package com.stiplin.alghoritms.suffixtree;

import com.stiplin.alghoritms.suffixtree.source.StringSource;

class Factory {

    public Node<Character> buildSuffixTree(String str) {
        //TODO string concatenation is noneffective. need refactoring
        return buildSuffixTree(str + '$', '$');
    }

    public Node<Character> buildSuffixTree(String str, Object o) {
        Node<Character> root = new Node<>();
        Position<Character> currentPosition = new Position<>(new StringSource(str), root, null, 0);

        for (int i = 0; i < str.length(); i++) {
            addSymbol(str, i, root, currentPosition);
        }

        return root;
    }

    public void addSymbol(String source, int symbolPosition, Node<Character> root, Position<Character> currentPosition) {
        char currentChar = source.charAt(symbolPosition);
        if (currentPosition.canMove(currentChar)) {
            currentPosition.move(currentChar);
            if (currentPosition.hasNextNode()) {
                currentPosition.setNode(currentPosition.getNextNode());
            }
        } else {
            if (currentPosition.hasNextNode()) {
                currentPosition.setNode(currentPosition.getNextNode());
            }
            if (currentPosition.isNodePosition()) {
                currentPosition.putChild(symbolPosition);
                while (currentPosition.getNode().hasSuffixLink()) {
                    currentPosition.toSuffixLink();
                    if (currentPosition.canMove(currentChar)) {
                        currentPosition.move(currentChar);
                        break;
                    } else {
                        currentPosition.putChild(symbolPosition);
                    }
                }
            } else {
                Node<Character> newNode = currentPosition.split();
                newNode.putChild(currentChar, symbolPosition, source.length() - 1);
                createSuffixLinks(source, symbolPosition, currentPosition, newNode, root);
            }
        }
    }

    private void createSuffixLinks(
            String str,
            int currentIndex,
            Position<Character> currentPosition,
            Node<Character> lastNode,
            Node<Character> root
    ) {
        moveToSuffixLink(currentPosition, root);
        while (!currentPosition.isNodePosition() || currentPosition.getNode() != root) {
            if (currentPosition.canSplit()) {
                Node<Character> newNode = currentPosition.split();
                lastNode.setSuffixLink(newNode);
                lastNode = newNode;
                newNode.putChild(str.charAt(currentIndex), currentIndex, str.length() - 1);
            } else {
                if (currentPosition.hasNextNode()) {
                    lastNode.setSuffixLink(currentPosition.getNextNode());
                }
                if (currentPosition.canMove(str.charAt(currentIndex))) {
                    currentPosition.move(str.charAt(currentIndex));
                    return;
                }
                currentPosition.putChild(currentIndex);
                if (currentPosition.hasNextNode()) {
                    lastNode = currentPosition.getNextNode();
                }
            }
            moveToSuffixLink(currentPosition, root);
        }
        if (currentPosition.getNode() == root && currentPosition.isNodePosition()) {
            lastNode.setSuffixLink(root);
            if (currentPosition.canMove(str.charAt(currentIndex))) {
                currentPosition.move(str.charAt(currentIndex));
            } else {
                currentPosition.putChild(currentIndex);
            }
        }
        if (currentPosition.hasNextNode()) {
            currentPosition.setNode(currentPosition.getNextNode());
        }
    }

    private void moveToSuffixLink(Position<Character> currentPosition, Node<Character> root) {
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

}
