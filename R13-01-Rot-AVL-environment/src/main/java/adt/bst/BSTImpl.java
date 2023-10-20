package adt.bst;

import adt.bt.BTNode;

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
		return height(getRoot());
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
		return search(getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> search = new BSTNode<>();
		if (element != null && !node.isEmpty()) {
			if (node.getData().compareTo(element) > 0) {
				search = search((BSTNode<T>) node.getLeft(), element);
			} else if (node.getData().compareTo(element) < 0) {
				search = search((BSTNode<T>) node.getRight(), element);
			} else {
				search = node;
			}
		}
		return search;
	}

	@Override
	public void insert(T element) {
		if (element != null && search(element).isEmpty()) {
			insert(root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (node.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> minimum() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
		BSTNode<T> node = search(element);
		remove(node);
	}

	private void remove(BSTNode<T> node) {
		if (!isEmpty() && node != null) {
			// Primeiro caso
			if (node.isLeaf()) {
				node.setData(null);

			// Segundo caso	
			} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty() || !node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				if (node.getParent() != null) {
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
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}

				} else {
					if (node.getLeft().isEmpty()) {
						root = (BSTNode<T>) node.getRight();
						root.setParent(null);
					} else {
						root = (BSTNode<T>) node.getLeft();
						root.setParent(null);
					}
				}

			// Terceiro caso
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor);
			}
		}
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
