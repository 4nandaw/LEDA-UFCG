package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return getHead().isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = getHead();
		while (!aux.isNIL()) {
			size++;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		T search = null;
		if (!isEmpty() && element != null) {
			SingleLinkedListNode<T> auxHead = getHead();
			while (!auxHead.isNIL() && !auxHead.getData().equals(element)) {
				auxHead = auxHead.getNext();
			}
			search = auxHead.getData();
		}
		return search;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> auxHead = getHead();
			if (getHead().isNIL()) {
				SingleLinkedListNode<T> newAuxHead = new SingleLinkedListNode<>(element, auxHead);
				setHead(newAuxHead);
			} else {
				while (!auxHead.isNIL()) {
					auxHead = auxHead.getNext();
				}
				auxHead.data = element;
				auxHead.next = new SingleLinkedListNode<>();
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (getHead().data.equals(element)) {
				setHead(getHead().getNext());
			} else {
				SingleLinkedListNode<T> auxHead = getHead();
				while (!auxHead.isNIL() && !auxHead.getData().equals(element)) {
					auxHead = auxHead.getNext();
				}
				if (!auxHead.isNIL()) {
					auxHead.data = auxHead.getNext().getData();
					auxHead.next = auxHead.getNext().getNext();
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		SingleLinkedListNode<T> auxHead = getHead();

		int i = 0;
		while (!auxHead.isNIL()) {
			array[i] = auxHead.getData();
			auxHead = auxHead.getNext();
			i++;
		}
		 return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
