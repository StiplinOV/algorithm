package com.stiplin.alghoritm;

import com.stiplin.alghoritm.suffixtree.Node;
import com.stiplin.alghoritm.suffixtree.SuffixTreeFactory;
import com.stiplin.alghoritm.suffixtree.source.StringSource;

public class DataStructureFactory {

    public static Node<Character> createSuffixTreeFromString(String source) {
        return new SuffixTreeFactory().buildSuffixTree(new StringSource(source), '$');
    }

}
