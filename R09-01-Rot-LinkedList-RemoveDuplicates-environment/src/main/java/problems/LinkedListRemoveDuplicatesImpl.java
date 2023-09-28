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

        while (!head.isNil()) {
            aux = head;
            while (!aux.isNil() && aux.getNext().isNil()) {
                if (head.getData().equals(aux.next.getData())) {
                    aux.setNext(aux.getNext().getNext());
                } else {
                    aux = aux.getNext();
                }
            }
            aux = head.getNext();
        }
    }
}

