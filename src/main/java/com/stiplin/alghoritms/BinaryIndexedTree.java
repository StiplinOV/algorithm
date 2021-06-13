package com.stiplin.alghoritms;

public class BinaryIndexedTree {

    long[] btree;

    BinaryIndexedTree(int size) {
        this.btree = new long[size];
    }

    BinaryIndexedTree(long... initValues) {
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

    public long getSum(int toIndexInclusive) {
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
