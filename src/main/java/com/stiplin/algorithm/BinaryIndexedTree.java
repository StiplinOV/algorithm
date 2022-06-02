package com.stiplin.algorithm;

public class BinaryIndexedTree {

    private final long[] btree;

    public BinaryIndexedTree(int size) {
        this.btree = new long[size];
    }

    public BinaryIndexedTree(long... initValues) {
        //TODO need to optimize init
        this(initValues.length);
        for (int i = 0; i < initValues.length; i++) {
            this.setValue(i, initValues[i]);
        }
    }

    public long get(int index) {
        long result = this.getSum(index);

        return index > 0 ? result - getSum(index - 1) : result;
    }

    public void setValue(int index, long value) {
        long oldValue = this.get(index);
        long difference = value - oldValue;
        while (index < btree.length) {
            btree[index] += difference;
            index |= index + 1;
        }
    }

    public long getSum(int fromIndexInclusive, int toIndexInclusive) {
        if (fromIndexInclusive > toIndexInclusive) {
            throw new IllegalArgumentException("from index more than to index");
        }
        long leftSum = fromIndexInclusive > 0 ? this.getSum(fromIndexInclusive - 1) : 0;
        return this.getSum(toIndexInclusive) - leftSum;
    }

    public long getSum(int toIndexInclusive) {
        if (toIndexInclusive >= btree.length) {
            throw new IllegalArgumentException("to index more than btree length");
        }
        long result = 0;
        while (toIndexInclusive >= 0) {
            result += btree[toIndexInclusive];
            if (toIndexInclusive == 0) {
                break;
            }
            toIndexInclusive &= toIndexInclusive + 1;
            toIndexInclusive--;
        }
        return result;
    }

}
