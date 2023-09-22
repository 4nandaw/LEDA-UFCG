package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				setData(element);
				next = new RecursiveDoubleLinkedListImpl<T>();
			} else {
				next.insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				insert(element);
			} else {
				RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>();
				node.setData(data);
				data = element;
				node.setNext(next);
				next = node;
				node.setPrevious(this);
				((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(node);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (getData().equals(element)) {
				if (previous.isEmpty()) {
					removeFirst();
				} else if (next.isEmpty()) {
					removeLast();
				} else {
					previous.setNext(next);
					((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(previous);
				}
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (next.isEmpty() && previous.isEmpty()) {
				data = null;
				next = null;
				previous = null;
			} else {
				data = next.getData();
				next = next.getNext();
				((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(this);
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (next.isEmpty()) {
				data = null;
				next = null;
				if (previous.isEmpty()) {
					previous = null;
				}
			} else {
				((RecursiveDoubleLinkedListImpl<T>) next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}