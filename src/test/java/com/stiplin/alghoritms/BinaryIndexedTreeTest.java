package com.stiplin.alghoritms;

import org.junit.Assert;
import org.junit.Test;


public class BinaryIndexedTreeTest {

    @Test
    public void testGet() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(1, 2, 3);
        Assert.assertEquals(1, binaryIndexedTree.get(0));
        Assert.assertEquals(2, binaryIndexedTree.get(1));
        Assert.assertEquals(3, binaryIndexedTree.get(2));
    }

    @Test
    public void testGetSum() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(1, 2, 3);
        Assert.assertEquals(1, binaryIndexedTree.getSum(0));
        Assert.assertEquals(3, binaryIndexedTree.getSum(1));
        Assert.assertEquals(6, binaryIndexedTree.getSum(2));
    }

    @Test
    public void testSetValue() {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(1,2,3);
        binaryIndexedTree.setValue(0, 4);
        Assert.assertEquals(4, binaryIndexedTree.get(0));
        binaryIndexedTree.setValue(1, 5);
        Assert.assertEquals(5, binaryIndexedTree.get(1));
        binaryIndexedTree.setValue(2, 6);
        Assert.assertEquals(6, binaryIndexedTree.get(2));
    }

}
