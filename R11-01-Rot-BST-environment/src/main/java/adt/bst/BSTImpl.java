package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BSTNode<T> node) {
		int height = 0;
		if (!node.isEmpty()) {
			height = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> search = new BSTNode<>();
		if (element != null && !node.isEmpty()) {
			if (element.compareTo(node.getData()) < 0) {
				search = search(element, (BSTNode<T>) node.getLeft());
			} else if (element.compareTo(node.getData()) > 0) {
				search = search(element, (BSTNode<T>) node.getRight());
			} else {
				search = node;
			}
		}
		return search;
	}

	@Override
	public void insert(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			//
			//
			//
			//
			//
			//
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> node = null;
		if (!isEmpty()) {
			node = maximum(getRoot());
		}
		return node;
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (!node.getRight().isEmpty()) {
			result = maximum((BSTNode<T>) node.getRight());
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> node = null;
		if (!isEmpty()) {
			node = minimum(getRoot());
		}
		return node;
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = node;
		if (!node.getLeft().isEmpty()) {
			result = minimum((BSTNode<T>) node.getLeft());
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}
