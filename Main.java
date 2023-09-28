package main.java.problems;

import problems.LinkedListRemoveDuplicatesImpl;

public class Main {
    public static void main(String[] args) {
        SingleLinkedListNode<Integer> node8 = new SingleLinkedListNode<Integer>(2, new SingleLinkedListNode<>());
        SingleLinkedListNode<Integer> node7 = new SingleLinkedListNode<Integer>(3, node8);
        SingleLinkedListNode<Integer> node6 = new SingleLinkedListNode<Integer>(2, node7);
        SingleLinkedListNode<Integer> node5 = new SingleLinkedListNode<Integer>(3, node6);
        SingleLinkedListNode<Integer> node4 = new SingleLinkedListNode<Integer>(2, node5);
        SingleLinkedListNode<Integer> node3 = new SingleLinkedListNode<Integer>(3, node4);
        SingleLinkedListNode<Integer> node2 = new SingleLinkedListNode<Integer>(2, node3);
        SingleLinkedListNode<Integer> node1 = new SingleLinkedListNode<Integer>(3, node2);

        LinkedListRemoveDuplicatesImpl<Integer> removeDuplicates = new LinkedListRemoveDuplicatesImpl<>();
        removeDuplicates.removeDuplicates(node1);

        SingleLinkedListNode node = node1;
        while (!node.isNil()) {
            System.out.println(node);
            node = node.next;
        }
    }
}
