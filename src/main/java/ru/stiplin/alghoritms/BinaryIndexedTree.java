package ru.stiplin.alghoritms;

public class BinaryIndexedTree {

    long[] btree;

    BinaryIndexedTree(int size) {
        this.btree = new long[size];
    }

    BinaryIndexedTree(int size, long[] initValues) {
        this(size);
        for (int i = 0; i < initValues.length; i++) {
            this.setValue(i, initValues[i]);
        }
    }

    long get(int index) {
        long result = this.getSum(index);

        return index > 0 ? result - getSum(index - 1) : result;
    }

    void setValue(int index, long value) {
        long oldValue = this.get(index);
        long difference = value - oldValue;
        while (index < btree.length) {
            btree[index] += difference;
            index |= index + 1;
        }
    }

    long getSum(int toIndexInclusive) {
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
