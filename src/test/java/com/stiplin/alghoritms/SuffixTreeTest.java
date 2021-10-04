package com.stiplin.alghoritms;

import org.junit.Assert;
import org.junit.Test;

public class SuffixTreeTest {

    // TODO need add test cases for abcabcddd && aacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccb

    @Test
    public void testaaaaa() {
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
    public void testabrashvabracadabra() {
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

    @Test
    public void testababbabbba$() {
        SuffixTreeFactory suffixTreeFactory = new SuffixTreeFactory();
        String sting = "ababbabbba$";
        Node root = new Node();

        Position currentPosition = new Position(sting, root, null, 0);

        //Add 'a'
        suffixTreeFactory.addSymbol(sting, 0, root, currentPosition);
        Assert.assertEquals(1, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertFalse(root.hasSuffixLink());
        Edge edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        Assert.assertEquals(currentPosition.getNode(), root);
        Assert.assertNull(currentPosition.getLeaveCharacter());
        Assert.assertEquals(0, currentPosition.getEdgePosition());

        //Add 'ab'
        suffixTreeFactory.addSymbol(sting, 1, root, currentPosition);
        Assert.assertEquals(2, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertEquals(currentPosition.getNode(), root);
        Assert.assertNull(currentPosition.getLeaveCharacter());
        Assert.assertEquals(0, currentPosition.getEdgePosition());

        //Add symbol 'aba'
        suffixTreeFactory.addSymbol(sting, 2, root, currentPosition);
        Assert.assertEquals(2, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertEquals(currentPosition.getNode(), root);
        Assert.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assert.assertEquals(0, currentPosition.getEdgePosition());

        //Add symbol 'abab'
        suffixTreeFactory.addSymbol(sting, 3, root, currentPosition);
        Assert.assertEquals(2, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertEquals(currentPosition.getNode(), root);
        Assert.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assert.assertEquals(1, currentPosition.getEdgePosition());

        //Add symbol 'ababb'
        suffixTreeFactory.addSymbol(sting, 4, root, currentPosition);
        Assert.assertEquals(2, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        Node nodeab = edge.getDest();
        edge = root.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        Node nodeb = edge.getDest();

        Assert.assertFalse(nodeab.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        Assert.assertFalse(nodeb.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('b');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());


        Assert.assertEquals(currentPosition.getNode(), nodeb);
        Assert.assertNull(currentPosition.getLeaveCharacter());
        Assert.assertEquals(0, currentPosition.getEdgePosition());

        //Add symbol 'ababba'
        suffixTreeFactory.addSymbol(sting, 5, root, currentPosition);
        Assert.assertEquals(2, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assert.assertFalse(nodeab.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        Assert.assertFalse(nodeb.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('b');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());


        Assert.assertEquals(currentPosition.getNode(), nodeb);
        Assert.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assert.assertEquals(2, currentPosition.getEdgePosition());

        //Add symbol 'ababbab'
        suffixTreeFactory.addSymbol(sting, 6, root, currentPosition);
        Assert.assertEquals(2, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assert.assertFalse(nodeab.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        Assert.assertFalse(nodeb.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('b');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());


        Assert.assertEquals(currentPosition.getNode(), nodeb);
        Assert.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assert.assertEquals(3, currentPosition.getEdgePosition());

        //Add symbol 'ababbabb'
        suffixTreeFactory.addSymbol(sting, 7, root, currentPosition);
        Assert.assertEquals(2, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assert.assertFalse(nodeab.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        Assert.assertFalse(nodeb.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('b');
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());


        Assert.assertEquals(currentPosition.getNode(), nodeb);
        Assert.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assert.assertEquals(4, currentPosition.getEdgePosition());

        //Add symbol 'ababbabbb'
        suffixTreeFactory.addSymbol(sting, 8, root, currentPosition);
        Assert.assertEquals(2, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assert.assertFalse(nodeab.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        Assert.assertFalse(nodeb.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Node nodeabb = edge.getDest();
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(4, edge.getRight());
        edge = nodeb.getChild('a');
        Node nodebabb = edge.getDest();
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(4, edge.getRight());
        edge = nodeb.getChild('b');
        Node nodebb = edge.getDest();
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(4, edge.getRight());

        Assert.assertFalse(nodeabb.isTerminal());
        Assert.assertTrue(nodeabb.hasSuffixLink());
        Assert.assertEquals(2, nodeabb.getChildren().size());
        Assert.assertEquals(nodeabb.getSuffixLink(), nodebb);
        Assert.assertTrue(nodeabb.hasChild('a'));
        Assert.assertTrue(nodeabb.hasChild('b'));
        edge = nodeabb.getChild('a');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeabb.getChild('b');
        Assert.assertEquals(8, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertFalse(nodebabb.isTerminal());
        Assert.assertTrue(nodebabb.hasSuffixLink());
        Assert.assertEquals(2, nodebabb.getChildren().size());
        Assert.assertEquals(nodebabb.getSuffixLink(), nodeabb);
        Assert.assertTrue(nodebabb.hasChild('a'));
        Assert.assertTrue(nodebabb.hasChild('b'));
        edge = nodebabb.getChild('a');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodebabb.getChild('b');
        Assert.assertEquals(8, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertFalse(nodebb.isTerminal());
        Assert.assertTrue(nodebb.hasSuffixLink());
        Assert.assertEquals(2, nodebb.getChildren().size());
        Assert.assertEquals(nodebb.getSuffixLink(), nodeb);
        Assert.assertTrue(nodebb.hasChild('a'));
        Assert.assertTrue(nodebb.hasChild('b'));
        edge = nodebb.getChild('a');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodebb.getChild('b');
        Assert.assertEquals(8, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertEquals(currentPosition.getNode(), nodeb);
        Assert.assertEquals(Character.valueOf('b'), currentPosition.getLeaveCharacter());
        Assert.assertEquals(4, currentPosition.getEdgePosition());

        //Add symbol 'ababbabbba'
        suffixTreeFactory.addSymbol(sting, 9, root, currentPosition);
        Assert.assertEquals(2, root.getChildren().size());
        Assert.assertTrue(root.hasChild('a'));
        Assert.assertTrue(root.hasChild('b'));
        Assert.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assert.assertEquals(0, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assert.assertEquals(1, edge.getLeft());
        Assert.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assert.assertFalse(nodeab.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        Assert.assertFalse(nodeb.isTerminal());
        Assert.assertTrue(nodeab.hasSuffixLink());
        Assert.assertEquals(2, nodeab.getChildren().size());
        Assert.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assert.assertTrue(nodeab.hasChild('a'));
        Assert.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        nodeabb = edge.getDest();
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(4, edge.getRight());
        edge = nodeb.getChild('a');
        nodebabb = edge.getDest();
        Assert.assertEquals(2, edge.getLeft());
        Assert.assertEquals(4, edge.getRight());
        edge = nodeb.getChild('b');
        nodebb = edge.getDest();
        Assert.assertEquals(4, edge.getLeft());
        Assert.assertEquals(4, edge.getRight());

        Assert.assertFalse(nodeabb.isTerminal());
        Assert.assertTrue(nodeabb.hasSuffixLink());
        Assert.assertEquals(2, nodeabb.getChildren().size());
        Assert.assertEquals(nodeabb.getSuffixLink(), nodebb);
        Assert.assertTrue(nodeabb.hasChild('a'));
        Assert.assertTrue(nodeabb.hasChild('b'));
        edge = nodeabb.getChild('a');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodeabb.getChild('b');
        Assert.assertEquals(8, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertFalse(nodebabb.isTerminal());
        Assert.assertTrue(nodebabb.hasSuffixLink());
        Assert.assertEquals(2, nodebabb.getChildren().size());
        Assert.assertEquals(nodebabb.getSuffixLink(), nodeabb);
        Assert.assertTrue(nodebabb.hasChild('a'));
        Assert.assertTrue(nodebabb.hasChild('b'));
        edge = nodebabb.getChild('a');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodebabb.getChild('b');
        Assert.assertEquals(8, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertFalse(nodebb.isTerminal());
        Assert.assertTrue(nodebb.hasSuffixLink());
        Assert.assertEquals(2, nodebb.getChildren().size());
        Assert.assertEquals(nodebb.getSuffixLink(), nodeb);
        Assert.assertTrue(nodebb.hasChild('a'));
        Assert.assertTrue(nodebb.hasChild('b'));
        edge = nodebb.getChild('a');
        Assert.assertEquals(5, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());
        edge = nodebb.getChild('b');
        Assert.assertEquals(8, edge.getLeft());
        Assert.assertEquals(10, edge.getRight());
        Assert.assertTrue(edge.getDest().isTerminal());

        Assert.assertEquals(currentPosition.getNode(), nodebb);
        Assert.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assert.assertEquals(5, currentPosition.getEdgePosition());

        suffixTreeFactory.addSymbol(sting, 10, root, currentPosition);
    }

}
