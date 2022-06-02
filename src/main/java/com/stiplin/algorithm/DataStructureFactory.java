package com.stiplin.algorithm;

import com.stiplin.algorithm.suffixtree.Node;
import com.stiplin.algorithm.suffixtree.SuffixTreeFactory;
import com.stiplin.algorithm.suffixtree.source.StringSource;

public class DataStructureFactory {

    public static Node<Character> createSuffixTreeFromString(String source) {
        return new SuffixTreeFactory().buildSuffixTree(new StringSource(source), '$');
    }

}
