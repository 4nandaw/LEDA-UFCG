package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<T>();
		this.last = (DoubleLinkedListNode<T>) head;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<>();
			newLast.setData(element);
			newLast.setPrevious(getLast());
			newLast.setNext(new DoubleLinkedListNode<>());

			getLast().setNext(newLast);

			if (getLast().isNIL()) {
				setHead(newLast);
			}
			setLast(newLast);
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>();
			newHead.setData(element);
			newHead.setNext(getHead());
			newHead.setPrevious(new DoubleLinkedListNode<>());
			((DoubleLinkedListNode<T>) getHead()).setPrevious(newHead);

			if (getHead().isNIL()) {
				setLast(newHead);
			}
			setHead(newHead);
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			setHead(getHead().getNext());

			if (isEmpty()) {
				setLast((DoubleLinkedListNode<T>) getHead());
			}
			((DoubleLinkedListNode<T>) getHead()).setPrevious(new DoubleLinkedListNode<>());
		}
	}
	
	@Override
	public void removeLast() {
		if (!isEmpty() && !getLast().isNIL()) {
			setLast(getLast().getPrevious());

			if (getLast().isNIL()) {
				setHead((DoubleLinkedListNode<T>) getLast());
			} else {
				((DoubleLinkedListNode<T>) getLast()).setNext(new DoubleLinkedListNode<>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (getHead().getData().equals(element)) {
				removeFirst();
			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) getHead();
				while (!aux.getData().equals(element) && !aux.isNIL()) {
					aux = (DoubleLinkedListNode<T>) aux.next;
				}
				if (!aux.isNIL()) {
					aux.getPrevious().setNext(aux.getNext());
					((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
				}
			}
		}
	}

	@Override
	public T search(T element) {
		T search = null;
		if (!isEmpty() && element != null) {
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) getHead();
			DoubleLinkedListNode<T> auxLast = getLast();

			while (!auxHead.equals(auxLast) && !auxHead.getNext().equals(auxLast) && 
			!auxHead.getData().equals(element) && !auxLast.getData().equals(element)) {
				auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
				auxLast = auxLast.getPrevious();
			}

			if (auxHead.getData().equals(element)) {
				search = auxHead.getData();
			}
			if (auxLast.getData().equals(element)) {
				search = auxLast.getData();
			}
		}
		return search;
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
