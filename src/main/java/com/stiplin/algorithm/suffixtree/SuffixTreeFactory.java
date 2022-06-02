package com.stiplin.algorithm.suffixtree;

import com.stiplin.algorithm.suffixtree.source.CompositeSource;
import com.stiplin.algorithm.suffixtree.source.SingletonSource;
import com.stiplin.algorithm.suffixtree.source.Source;

public class SuffixTreeFactory {

    public <T> Node<T> buildSuffixTree(Source<T> source, T lastSymbol) {
        return buildSuffixTree(new CompositeSource<>(source, new SingletonSource<>(lastSymbol)));
    }

    public <T> Node<T> buildSuffixTree(Source<T> source) {
        Node<T> root = new Node<>();
        Position<T> currentPosition = new Position<>(source, root, null, 0);

        for (int i = 0; i < source.size(); i++) {
            addSymbol(source, i, root, currentPosition);
        }

        return root;
    }

    <T> void addSymbol(Source<T> source, int symbolPosition, Node<T> root, Position<T> currentPosition) {
        T currentElement = source.get(symbolPosition);
        if (currentPosition.canMove(currentElement)) {
            currentPosition.move(currentElement);
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
                    if (currentPosition.canMove(currentElement)) {
                        currentPosition.move(currentElement);
                        break;
                    } else {
                        currentPosition.putChild(symbolPosition);
                    }
                }
            } else {
                Node<T> newNode = currentPosition.split();
                newNode.putChild(currentElement, symbolPosition, source.size() - 1);
                createSuffixLinks(source, symbolPosition, currentPosition, newNode, root);
            }
        }
    }

    private <T> void createSuffixLinks(Source<T> source, int currentIndex, Position<T> currentPosition, Node<T> lastNode, Node<T> root) {
        moveToSuffixLink(currentPosition, root);
        while (!currentPosition.isNodePosition() || currentPosition.getNode() != root) {
            if (currentPosition.canSplit()) {
                Node<T> newNode = currentPosition.split();
                lastNode.setSuffixLink(newNode);
                lastNode = newNode;
                newNode.putChild(source.get(currentIndex), currentIndex, source.size() - 1);
            } else {
                if (currentPosition.hasNextNode()) {
                    lastNode.setSuffixLink(currentPosition.getNextNode());
                }
                if (currentPosition.canMove(source.get(currentIndex))) {
                    currentPosition.move(source.get(currentIndex));
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
            if (currentPosition.canMove(source.get(currentIndex))) {
                currentPosition.move(source.get(currentIndex));
            } else {
                currentPosition.putChild(currentIndex);
            }
        }
        if (currentPosition.hasNextNode()) {
            currentPosition.setNode(currentPosition.getNextNode());
        }
    }

    private <T> void moveToSuffixLink(Position<T> currentPosition, Node<T> root) {
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
