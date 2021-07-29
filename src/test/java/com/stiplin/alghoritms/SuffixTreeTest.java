package com.stiplin.alghoritms;

import org.junit.Assert;
import org.junit.Test;

public class SuffixTreeTest {

    @Test
    public void test() {
        SuffixTreeFactory factory = new SuffixTreeFactory();
        Node root = factory.buildSuffixTree("aaaaa");

        Assert.assertEquals(2, root.characters().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('$'));
        Assert.assertFalse(root.hasSuffixLink());

        Edge edge = root.getChild('$');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(5, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(0, edge.getRight());
        Node node1 = edge.getDest();

        Assert.assertEquals(2, node1.characters().size());
        Assert.assertTrue(node1.hasChild('a'));
        Assert.assertTrue(node1.hasChild('$'));
        Assert.assertEquals(node1.getSuffixLink(), root);

        edge = node1.getChild('$');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(5, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = node1.getChild('a');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        Node node2 = edge.getDest();

        Assert.assertEquals(2, node2.characters().size());
        Assert.assertTrue(node2.hasChild('a'));
        Assert.assertTrue(node2.hasChild('$'));
        Assert.assertEquals(node2.getSuffixLink(), node1);

        edge = node2.getChild('$');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(5, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = node2.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(2, edge.getRight());
        Node node3 = edge.getDest();

        Assert.assertEquals(2, node3.characters().size());
        Assert.assertTrue(node3.hasChild('a'));
        Assert.assertTrue(node3.hasChild('$'));
        Assert.assertEquals(node3.getSuffixLink(), node2);

        edge = node3.getChild('$');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(5, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = node3.getChild('a');
        Assert.assertEquals(3, edge.getLeft());
        Assert.assertEquals(3, edge.getRight());
        Node node4 = edge.getDest();

        Assert.assertEquals(2, node4.characters().size());
        Assert.assertTrue(node4.hasChild('a'));
        Assert.assertTrue(node4.hasChild('$'));
        Assert.assertEquals(node4.getSuffixLink(), node3);

        edge = node4.getChild('$');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(5, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = node4.getChild('a');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(5, edge.getRight());
       // root = edge.getDest();
    }

    @Test
    public void test1() {
        SuffixTreeFactory factory = new SuffixTreeFactory();
        Node root = factory.buildSuffixTree("abrashvabracadabra");

        Assert.assertEquals(9, root.characters().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertTrue(root.hasChild('r'));
        Assert.assertTrue(root.hasChild('s'));
        Assert.assertTrue(root.hasChild('c'));
        Assert.assertTrue(root.hasChild('d'));
        Assert.assertTrue(root.hasChild('v'));
        Assert.assertTrue(root.hasChild('h'));
        Assert.assertTrue(root.hasChild('$'));
        Assert.assertFalse(root.hasSuffixLink());

        Edge edge = root.getChild('$');
        Assert.assertEquals(18, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('s');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('c');
        Assert.assertEquals(11, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('d');
        Assert.assertEquals(13, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('v');
        Assert.assertEquals(6, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('h');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Node nodeA = root.getChild('a').getDest();
        Node nodeB = root.getChild('b').getDest();
        Node nodeR = root.getChild('r').getDest();
        Assert.assertEquals(5, nodeA.characters().size());
        Assert.assertTrue(nodeA.hasChild('b'));
        Assert.assertTrue(nodeA.hasChild('s'));
        Assert.assertTrue(nodeA.hasChild('c'));
        Assert.assertTrue(nodeA.hasChild('d'));
        Assert.assertTrue(nodeA.hasChild('$'));
        Assert.assertEquals(nodeA.getSuffixLink(), root);

        edge = nodeA.getChild('$');
        Assert.assertEquals(18, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeA.getChild('s');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeA.getChild('c');
        Assert.assertEquals(11, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeA.getChild('d');
        Assert.assertEquals(13, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeA.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(3, edge.getRight());
        Assert.assertFalse(edge.getDest().isTerminal());
        Assert.assertEquals(nodeA.getSuffixLink(), root);

        Node nodeAb = edge.getDest();
        Assert.assertEquals(3, nodeR.characters().size());
        Assert.assertTrue(nodeAb.hasChild('s'));
        Assert.assertTrue(nodeAb.hasChild('c'));
        Assert.assertTrue(nodeAb.hasChild('$'));
        Assert.assertEquals(nodeAb.getSuffixLink(), nodeB);

        edge = nodeAb.getChild('s');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeAb.getChild('c');
        Assert.assertEquals(11, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeAb.getChild('$');
        Assert.assertEquals(18, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertEquals(3, nodeB.characters().size());
        Assert.assertTrue(nodeB.hasChild('s'));
        Assert.assertTrue(nodeB.hasChild('c'));
        Assert.assertTrue(nodeB.hasChild('$'));
        Assert.assertEquals(nodeB.getSuffixLink(), nodeR);

        edge = nodeB.getChild('s');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeB.getChild('c');
        Assert.assertEquals(11, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeB.getChild('$');
        Assert.assertEquals(18, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertEquals(3, nodeR.characters().size());
        Assert.assertTrue(nodeR.hasChild('s'));
        Assert.assertTrue(nodeR.hasChild('c'));
        Assert.assertTrue(nodeR.hasChild('$'));
        Assert.assertEquals(nodeR.getSuffixLink(), nodeA);

        edge = nodeR.getChild('s');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeR.getChild('c');
        Assert.assertEquals(11, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = nodeR.getChild('$');
        Assert.assertEquals(18, edge.getLeft());
        Assert.assertEquals(18, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
    }

}
