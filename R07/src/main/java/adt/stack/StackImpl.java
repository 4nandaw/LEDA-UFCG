package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		T topo = null;
		if (!isEmpty()) {
			topo = this.array[this.top];
		}
		return topo;
	}

	@Override
	public boolean isEmpty() {
		return this.top == - 1;
	}

	@Override
	public boolean isFull() {
		return array.length - 1 == this.top;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		if (element != null) {
			this.top++;
			this.array[this.top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		T elem = this.array[this.top];
		this.top--;
		return elem;
	}

}
