package adt.bst;

import java.util.ArrayList;

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
		int height = -1;
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
		if (element != null && search(element).isEmpty()) {
			insert(element, getRoot());
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insert(element, (BSTNode<T>) node.getLeft());
			} else {
				insert(element, (BSTNode<T>) node.getRight());
			}
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
		BSTNode<T> sucessor = null;
		BSTNode<T> node = search(element);
		
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				sucessor = minimum((BSTNode<T>) node.getRight());
			} else {
				sucessor = sucessor(node);
			}
		}
		return sucessor;
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> sucessor = (BSTNode<T>) node.getParent();
		if (node.getParent() != null && !sucessor.isEmpty() && node.equals(sucessor.getRight())) {
			sucessor = sucessor((BSTNode<T>) node.getParent());
		}
		return sucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> predecessor = null;
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				predecessor = maximum((BSTNode<T>) node.getLeft());
			} else {
				predecessor = predecessor(node);
			}
		}
		return predecessor;
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> predecessor = (BSTNode<T>) node.getParent();
		if (node.getParent() != null && !predecessor.isEmpty() && node.equals(predecessor.getLeft())) {
			predecessor = predecessor((BSTNode<T>) node.getParent());
		}
		return predecessor;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		remove(node);
	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty() && node != null) {
			if (node.isLeaf()) {
				node.setData(null); // Ou node = new BSTNode<T>(); ?
			} else if (hasOneChild(node)) {
				if (node.getParent().getData().compareTo(node.getData()) > 0) {
					if (!node.getLeft().isEmpty()) {
						node.getParent().setLeft(node.getLeft());
						node.getLeft().setParent(node.getParent());
					} else {
						node.getParent().setLeft(node.getRight());
						node.getRight().setParent(node.getParent());
					}
				} else {
					if (!node.getLeft().isEmpty()) {
						node.getParent().setRight(node.getLeft());
						node.getLeft().setParent(node.getParent());
					} else {
						node.getParent().setLeft(node.getRight());
						node.getRight().setParent(node.getParent());
					}
				}
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor);
			}
		}
	}

	private boolean hasOneChild(BSTNode<T> node) {
		return ((node.getLeft().isEmpty() && !node.getRight().isEmpty()) ||
		 (!node.getLeft().isEmpty() && node.getRight().isEmpty()));
	}

	@Override
	public T[] preOrder() {
		ArrayList<T> list = new ArrayList<>();
		preOrder(getRoot(), list);
		return (T[]) list.toArray(new Comparable[list.size()]);
	}

	private void preOrder(BSTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty() && node != null) {
			list.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), list);
			preOrder((BSTNode<T>) node.getRight(), list);
		}
	}

	@Override
	public T[] order() {
		ArrayList<T> list = new ArrayList<>();
		order(getRoot(), list);
		return (T[]) list.toArray(new Comparable[list.size()]);
	}

	private void order(BSTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty() && node != null) {
			order((BSTNode<T>) node.getLeft(), list);
			list.add(node.getData());
			order((BSTNode<T>) node.getRight(), list);
		}
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> list = new ArrayList<>();
		postOrder(getRoot(), list);
		return (T[]) list.toArray(new Comparable[list.size()]);
	}

	private void postOrder(BSTNode<T> node, ArrayList<T> list) {
		if (!node.isEmpty() && node != null) {
			postOrder((BSTNode<T>) node.getLeft(), list);
			postOrder((BSTNode<T>) node.getRight(), list);
			list.add(node.getData());
		}
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
