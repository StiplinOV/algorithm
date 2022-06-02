package com.stiplin.alghoritm;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryIndexedTreeTest {

    @Test
    public void testGet() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(1, 2, 3);
        Assertions.assertEquals(1, binaryIndexedTree.get(0));
        Assertions.assertEquals(2, binaryIndexedTree.get(1));
        Assertions.assertEquals(3, binaryIndexedTree.get(2));
    }

    @Test
    public void testGetSum() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(1, 2, 3);
        Assertions.assertEquals(1, binaryIndexedTree.getSum(0));
        Assertions.assertEquals(3, binaryIndexedTree.getSum(1));
        Assertions.assertEquals(6, binaryIndexedTree.getSum(2));
        Assertions.assertEquals(1, binaryIndexedTree.getSum(0, 0));
        Assertions.assertEquals(3, binaryIndexedTree.getSum(0, 1));
        Assertions.assertEquals(6, binaryIndexedTree.getSum(0, 2));
        Assertions.assertEquals(2, binaryIndexedTree.getSum(1, 1));
        Assertions.assertEquals(5, binaryIndexedTree.getSum(1, 2));
        Assertions.assertEquals(3, binaryIndexedTree.getSum(2, 2));

        try {
            binaryIndexedTree.getSum(2, 0);
            Assertions.fail("from index more than to index");
        } catch (IllegalArgumentException ex) {
            Assertions.assertNotNull(ex.getMessage());
        }
        try {
            binaryIndexedTree.getSum(0, 3);
            Assertions.fail("to index more than btree length");
        } catch (IllegalArgumentException ex) {
            Assertions.assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void testSetValue() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(1, 2, 3);
        binaryIndexedTree.setValue(0, 4);
        Assertions.assertEquals(4, binaryIndexedTree.get(0));
        binaryIndexedTree.setValue(1, 5);
        Assertions.assertEquals(5, binaryIndexedTree.get(1));
        binaryIndexedTree.setValue(2, 6);
        Assertions.assertEquals(6, binaryIndexedTree.get(2));
    }

}
