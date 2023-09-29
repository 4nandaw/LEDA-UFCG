package problems;

import adt.linkedList.SingleLinkedListNode;

public class LinkedListRemoveDuplicatesImpl<T> implements LinkedListRemoveDuplicates<T>{

    /**
     * Restricoes extras:
     * - Você NÃO pode usar recursão
     * - Você pode criar métodos auxiliares se achar necessário, desde que sejam criados
     *   nesta classe
     */
    public void removeDuplicates(SingleLinkedListNode<T> node){
        SingleLinkedListNode<T> head = node;
        SingleLinkedListNode<T> aux = node;

        while (!head.isNIL()) {
            aux = head;
            while (!aux.isNIL() && aux.getNext().isNIL()) {
                if (head.getData().equals(aux.getNext().getData())) {
                    aux.setNext(aux.getNext().getNext());
                } else {
                    aux = aux.getNext();
                }
            }
            head = head.getNext();
        }
    }
}

