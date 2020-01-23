package com.vural.datastructures.chapter8;

import org.junit.Test;

import java.util.TreeMap;

public class TreeTest {

    @Test
    public void tree() {

        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();

        LinkedBinaryTree.Node<String> root = tree.createNode("root", null, null, null);
        tree.root = root;

        LinkedBinaryTree.Node<String> firstLeftChild = tree.createNode("1L", root, null, null);
        root.setLeft(firstLeftChild);
        LinkedBinaryTree.Node<String> firstRightChild = tree.createNode("1R", root, null, null);
        root.setRight(firstRightChild);
        AbstractBinaryTree.printPreorderIndent(tree, root, 1);
    }
}
