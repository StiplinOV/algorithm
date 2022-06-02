package com.stiplin.algorithm.suffixtree;

import com.stiplin.algorithm.DataStructureFactory;
import com.stiplin.algorithm.suffixtree.source.StringSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuffixTreeTest {

    // TODO need add test cases for abcabcddd && aacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccbaacbbabbabbbbbaaaaaaabbbbcacacbcabaccaabbbcaaabbccccbbbcbccccbbcaabaaabcbaacbcbaccaaaccbccbcaacbaccb

    @Test
    public void testaaaaa() {
        Node<Character> root = DataStructureFactory.createSuffixTreeFromString("aaaaa");

        Assertions.assertEquals(2, root.characters().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('$'));
        Assertions.assertFalse(root.hasSuffixLink());

        Edge<Character> edge = root.getChild('$');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(5, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(0, edge.getRight());
        Node<Character> node1 = edge.getDest();

        Assertions.assertEquals(2, node1.characters().size());
        Assertions.assertTrue(node1.hasChild('a'));
        Assertions.assertTrue(node1.hasChild('$'));
        Assertions.assertEquals(node1.getSuffixLink(), root);

        edge = node1.getChild('$');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(5, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = node1.getChild('a');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        Node<Character> node2 = edge.getDest();

        Assertions.assertEquals(2, node2.characters().size());
        Assertions.assertTrue(node2.hasChild('a'));
        Assertions.assertTrue(node2.hasChild('$'));
        Assertions.assertEquals(node2.getSuffixLink(), node1);

        edge = node2.getChild('$');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(5, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = node2.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(2, edge.getRight());
        Node<Character> node3 = edge.getDest();

        Assertions.assertEquals(2, node3.characters().size());
        Assertions.assertTrue(node3.hasChild('a'));
        Assertions.assertTrue(node3.hasChild('$'));
        Assertions.assertEquals(node3.getSuffixLink(), node2);

        edge = node3.getChild('$');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(5, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = node3.getChild('a');
        Assertions.assertEquals(3, edge.getLeft());
        Assertions.assertEquals(3, edge.getRight());
        Node<Character> node4 = edge.getDest();

        Assertions.assertEquals(2, node4.characters().size());
        Assertions.assertTrue(node4.hasChild('a'));
        Assertions.assertTrue(node4.hasChild('$'));
        Assertions.assertEquals(node4.getSuffixLink(), node3);

        edge = node4.getChild('$');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(5, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = node4.getChild('a');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(5, edge.getRight());
        // root = edge.getDest();
    }

    @Test
    public void testabrashvabracadabra() {
        Node<Character> root = DataStructureFactory.createSuffixTreeFromString("abrashvabracadabra");

        Assertions.assertEquals(9, root.characters().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertTrue(root.hasChild('r'));
        Assertions.assertTrue(root.hasChild('s'));
        Assertions.assertTrue(root.hasChild('c'));
        Assertions.assertTrue(root.hasChild('d'));
        Assertions.assertTrue(root.hasChild('v'));
        Assertions.assertTrue(root.hasChild('h'));
        Assertions.assertTrue(root.hasChild('$'));
        Assertions.assertFalse(root.hasSuffixLink());

        Edge<Character> edge = root.getChild('$');
        Assertions.assertEquals(18, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('s');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('c');
        Assertions.assertEquals(11, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('d');
        Assertions.assertEquals(13, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('v');
        Assertions.assertEquals(6, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('h');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Node<Character> nodeA = root.getChild('a').getDest();
        Node<Character> nodeB = root.getChild('b').getDest();
        Node<Character> nodeR = root.getChild('r').getDest();
        Assertions.assertEquals(5, nodeA.characters().size());
        Assertions.assertTrue(nodeA.hasChild('b'));
        Assertions.assertTrue(nodeA.hasChild('s'));
        Assertions.assertTrue(nodeA.hasChild('c'));
        Assertions.assertTrue(nodeA.hasChild('d'));
        Assertions.assertTrue(nodeA.hasChild('$'));
        Assertions.assertEquals(nodeA.getSuffixLink(), root);

        edge = nodeA.getChild('$');
        Assertions.assertEquals(18, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeA.getChild('s');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeA.getChild('c');
        Assertions.assertEquals(11, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeA.getChild('d');
        Assertions.assertEquals(13, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeA.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(3, edge.getRight());
        Assertions.assertFalse(edge.getDest().isTerminal());
        Assertions.assertEquals(nodeA.getSuffixLink(), root);

        Node<Character> nodeAb = edge.getDest();
        Assertions.assertEquals(3, nodeR.characters().size());
        Assertions.assertTrue(nodeAb.hasChild('s'));
        Assertions.assertTrue(nodeAb.hasChild('c'));
        Assertions.assertTrue(nodeAb.hasChild('$'));
        Assertions.assertEquals(nodeAb.getSuffixLink(), nodeB);

        edge = nodeAb.getChild('s');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeAb.getChild('c');
        Assertions.assertEquals(11, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeAb.getChild('$');
        Assertions.assertEquals(18, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertEquals(3, nodeB.characters().size());
        Assertions.assertTrue(nodeB.hasChild('s'));
        Assertions.assertTrue(nodeB.hasChild('c'));
        Assertions.assertTrue(nodeB.hasChild('$'));
        Assertions.assertEquals(nodeB.getSuffixLink(), nodeR);

        edge = nodeB.getChild('s');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeB.getChild('c');
        Assertions.assertEquals(11, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeB.getChild('$');
        Assertions.assertEquals(18, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertEquals(3, nodeR.characters().size());
        Assertions.assertTrue(nodeR.hasChild('s'));
        Assertions.assertTrue(nodeR.hasChild('c'));
        Assertions.assertTrue(nodeR.hasChild('$'));
        Assertions.assertEquals(nodeR.getSuffixLink(), nodeA);

        edge = nodeR.getChild('s');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeR.getChild('c');
        Assertions.assertEquals(11, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = nodeR.getChild('$');
        Assertions.assertEquals(18, edge.getLeft());
        Assertions.assertEquals(18, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
    }

    @Test
    public void testababbabbba$() {
        SuffixTreeFactory factory = new SuffixTreeFactory();
        StringSource characterSource = new StringSource("ababbabbba$");
        Node<Character> root = new Node<>();

        Position<Character> currentPosition = new Position<>(characterSource, root, null, 0);

        //Add 'a'
        factory.addSymbol(characterSource, 0, root, currentPosition);
        Assertions.assertEquals(1, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertFalse(root.hasSuffixLink());
        Edge<Character> edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        Assertions.assertEquals(currentPosition.getNode(), root);
        Assertions.assertNull(currentPosition.getLeaveCharacter());
        Assertions.assertEquals(0, currentPosition.getEdgePosition());

        //Add 'ab'
        factory.addSymbol(characterSource, 1, root, currentPosition);
        Assertions.assertEquals(2, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertEquals(currentPosition.getNode(), root);
        Assertions.assertNull(currentPosition.getLeaveCharacter());
        Assertions.assertEquals(0, currentPosition.getEdgePosition());

        //Add symbol 'aba'
        factory.addSymbol(characterSource, 2, root, currentPosition);
        Assertions.assertEquals(2, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertEquals(currentPosition.getNode(), root);
        Assertions.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assertions.assertEquals(0, currentPosition.getEdgePosition());

        //Add symbol 'abab'
        factory.addSymbol(characterSource, 3, root, currentPosition);
        Assertions.assertEquals(2, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        edge = root.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertEquals(currentPosition.getNode(), root);
        Assertions.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assertions.assertEquals(1, currentPosition.getEdgePosition());

        //Add symbol 'ababb'
        factory.addSymbol(characterSource, 4, root, currentPosition);
        Assertions.assertEquals(2, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        Node<Character> nodeab = edge.getDest();
        edge = root.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        Node<Character> nodeb = edge.getDest();

        Assertions.assertFalse(nodeab.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        Assertions.assertFalse(nodeb.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('b');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());


        Assertions.assertEquals(currentPosition.getNode(), nodeb);
        Assertions.assertNull(currentPosition.getLeaveCharacter());
        Assertions.assertEquals(0, currentPosition.getEdgePosition());

        //Add symbol 'ababba'
        factory.addSymbol(characterSource, 5, root, currentPosition);
        Assertions.assertEquals(2, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assertions.assertFalse(nodeab.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        Assertions.assertFalse(nodeb.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('b');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());


        Assertions.assertEquals(currentPosition.getNode(), nodeb);
        Assertions.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assertions.assertEquals(2, currentPosition.getEdgePosition());

        //Add symbol 'ababbab'
        factory.addSymbol(characterSource, 6, root, currentPosition);
        Assertions.assertEquals(2, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assertions.assertFalse(nodeab.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        Assertions.assertFalse(nodeb.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('b');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());


        Assertions.assertEquals(currentPosition.getNode(), nodeb);
        Assertions.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assertions.assertEquals(3, currentPosition.getEdgePosition());

        //Add symbol 'ababbabb'
        factory.addSymbol(characterSource, 7, root, currentPosition);
        Assertions.assertEquals(2, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assertions.assertFalse(nodeab.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        Assertions.assertFalse(nodeb.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeb.getChild('b');
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());


        Assertions.assertEquals(currentPosition.getNode(), nodeb);
        Assertions.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assertions.assertEquals(4, currentPosition.getEdgePosition());

        //Add symbol 'ababbabbb'
        factory.addSymbol(characterSource, 8, root, currentPosition);
        Assertions.assertEquals(2, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assertions.assertFalse(nodeab.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        Assertions.assertFalse(nodeb.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        Node<Character> nodeabb = edge.getDest();
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(4, edge.getRight());
        edge = nodeb.getChild('a');
        Node<Character> nodebabb = edge.getDest();
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(4, edge.getRight());
        edge = nodeb.getChild('b');
        Node<Character> nodebb = edge.getDest();
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(4, edge.getRight());

        Assertions.assertFalse(nodeabb.isTerminal());
        Assertions.assertTrue(nodeabb.hasSuffixLink());
        Assertions.assertEquals(2, nodeabb.getChildren().size());
        Assertions.assertEquals(nodeabb.getSuffixLink(), nodebb);
        Assertions.assertTrue(nodeabb.hasChild('a'));
        Assertions.assertTrue(nodeabb.hasChild('b'));
        edge = nodeabb.getChild('a');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeabb.getChild('b');
        Assertions.assertEquals(8, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertFalse(nodebabb.isTerminal());
        Assertions.assertTrue(nodebabb.hasSuffixLink());
        Assertions.assertEquals(2, nodebabb.getChildren().size());
        Assertions.assertEquals(nodebabb.getSuffixLink(), nodeabb);
        Assertions.assertTrue(nodebabb.hasChild('a'));
        Assertions.assertTrue(nodebabb.hasChild('b'));
        edge = nodebabb.getChild('a');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodebabb.getChild('b');
        Assertions.assertEquals(8, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertFalse(nodebb.isTerminal());
        Assertions.assertTrue(nodebb.hasSuffixLink());
        Assertions.assertEquals(2, nodebb.getChildren().size());
        Assertions.assertEquals(nodebb.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodebb.hasChild('a'));
        Assertions.assertTrue(nodebb.hasChild('b'));
        edge = nodebb.getChild('a');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodebb.getChild('b');
        Assertions.assertEquals(8, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertEquals(currentPosition.getNode(), nodeb);
        Assertions.assertEquals(Character.valueOf('b'), currentPosition.getLeaveCharacter());
        Assertions.assertEquals(4, currentPosition.getEdgePosition());

        //Add symbol 'ababbabbba'
        factory.addSymbol(characterSource, 9, root, currentPosition);
        Assertions.assertEquals(2, root.getChildren().size());
        Assertions.assertTrue(root.hasChild('a'));
        Assertions.assertTrue(root.hasChild('b'));
        Assertions.assertFalse(root.hasSuffixLink());
        edge = root.getChild('a');
        Assertions.assertEquals(0, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeab = edge.getDest();
        edge = root.getChild('b');
        Assertions.assertEquals(1, edge.getLeft());
        Assertions.assertEquals(1, edge.getRight());
        nodeb = edge.getDest();

        Assertions.assertFalse(nodeab.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        Assertions.assertFalse(nodeb.isTerminal());
        Assertions.assertTrue(nodeab.hasSuffixLink());
        Assertions.assertEquals(2, nodeab.getChildren().size());
        Assertions.assertEquals(nodeab.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodeab.hasChild('a'));
        Assertions.assertTrue(nodeab.hasChild('b'));

        edge = nodeab.getChild('a');
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeab.getChild('b');
        nodeabb = edge.getDest();
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(4, edge.getRight());
        edge = nodeb.getChild('a');
        nodebabb = edge.getDest();
        Assertions.assertEquals(2, edge.getLeft());
        Assertions.assertEquals(4, edge.getRight());
        edge = nodeb.getChild('b');
        nodebb = edge.getDest();
        Assertions.assertEquals(4, edge.getLeft());
        Assertions.assertEquals(4, edge.getRight());

        Assertions.assertFalse(nodeabb.isTerminal());
        Assertions.assertTrue(nodeabb.hasSuffixLink());
        Assertions.assertEquals(2, nodeabb.getChildren().size());
        Assertions.assertEquals(nodeabb.getSuffixLink(), nodebb);
        Assertions.assertTrue(nodeabb.hasChild('a'));
        Assertions.assertTrue(nodeabb.hasChild('b'));
        edge = nodeabb.getChild('a');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodeabb.getChild('b');
        Assertions.assertEquals(8, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertFalse(nodebabb.isTerminal());
        Assertions.assertTrue(nodebabb.hasSuffixLink());
        Assertions.assertEquals(2, nodebabb.getChildren().size());
        Assertions.assertEquals(nodebabb.getSuffixLink(), nodeabb);
        Assertions.assertTrue(nodebabb.hasChild('a'));
        Assertions.assertTrue(nodebabb.hasChild('b'));
        edge = nodebabb.getChild('a');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodebabb.getChild('b');
        Assertions.assertEquals(8, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertFalse(nodebb.isTerminal());
        Assertions.assertTrue(nodebb.hasSuffixLink());
        Assertions.assertEquals(2, nodebb.getChildren().size());
        Assertions.assertEquals(nodebb.getSuffixLink(), nodeb);
        Assertions.assertTrue(nodebb.hasChild('a'));
        Assertions.assertTrue(nodebb.hasChild('b'));
        edge = nodebb.getChild('a');
        Assertions.assertEquals(5, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());
        edge = nodebb.getChild('b');
        Assertions.assertEquals(8, edge.getLeft());
        Assertions.assertEquals(10, edge.getRight());
        Assertions.assertTrue(edge.getDest().isTerminal());

        Assertions.assertEquals(currentPosition.getNode(), nodebb);
        Assertions.assertEquals(Character.valueOf('a'), currentPosition.getLeaveCharacter());
        Assertions.assertEquals(5, currentPosition.getEdgePosition());

        factory.addSymbol(characterSource, 10, root, currentPosition);
    }

}
