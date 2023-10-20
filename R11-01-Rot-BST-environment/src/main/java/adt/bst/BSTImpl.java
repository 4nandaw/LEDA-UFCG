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
		return this.height(this.root);
	}

	private int height(BSTNode<T> node) {
		int height = -1;
		if (!node.isEmpty()) {
			height = 1 + Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight()));
		}
		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> search = new BSTNode<>();
		if (!node.isEmpty() && node != null) {
			if (element.compareTo((node.getData())) < 0) {
				search = search((BSTNode<T>) node.getLeft(), element);

			} else if (element.compareTo((node.getData())) > 0) {
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
			insert(getRoot(), element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());

			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}
		else if (element.compareTo(node.getData()) < 0) {
			insert((BSTNode<T>) node.getLeft(), element);
		} else {
			insert((BSTNode<T>) node.getRight(), element);
		}

	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> node = null;
		if (!isEmpty()) {
			node = findMaximum(this.root);
		}
		return node;
	}

	private BSTNode<T> findMaximum(BSTNode<T> node) {
		BSTNode<T> auxiliar = node;
		if (!node.getRight().isEmpty()) {
			auxiliar = findMaximum((BSTNode<T>) node.getRight());
		}
		return auxiliar;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> node = null;
		if (!isEmpty()) {
			node = findMinimum(this.root);
		}
		return node;
	}

	private BSTNode<T> findMinimum(BSTNode<T> node) {
		BSTNode<T> auxiliar = node;
		if (!node.getLeft().isEmpty()) {
			 auxiliar = findMinimum((BSTNode<T>) node.getLeft());
		}
		return auxiliar;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> sucessor = null;
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty())
				sucessor = findMinimum((BSTNode<T>) node.getRight());
			else {
				sucessor = findSucessor(node);
			}
		}
		return sucessor;
	}

	private BSTNode<T> findSucessor(BSTNode<T> node) {
		BSTNode<T> sucessor = (BSTNode<T>) node.getParent();
		if (node.getParent() != null && !sucessor.isEmpty() && node.equals(sucessor.getRight())) {
			sucessor = findSucessor((BSTNode<T>) node.getParent());
		}
		return sucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> predecessor = null;
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty())
				predecessor = findMinimum((BSTNode<T>) node.getLeft());
			else {
				predecessor = findPredecessor(node);
			}
		}
		return predecessor;
	}

	private BSTNode<T> findPredecessor(BSTNode<T> node) {
		BSTNode<T> predecessor = (BSTNode<T>) node.getParent();
		if (node.getParent() != null && !predecessor.isEmpty() && node.equals(predecessor.getLeft())) {
			predecessor = findPredecessor((BSTNode<T>) node.getParent());
		}
		return predecessor;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		remove(node);
	}

	private void remove(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			// Primeiro caso
			if (node.isLeaf()) {
				node.setData(null);

			// Segundo caso
			} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty() ||
						!node.getLeft().isEmpty() && node.getRight().isEmpty()) {

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
		ArrayList<T> list = new ArrayList<>();
		preOrder(list, getRoot());
		return (T[]) list.toArray(new Comparable[list.size()]);
	}

	private void preOrder(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty() && node != null) {
			list.add(node.getData());
			preOrder(list, (BSTNode<T>) node.getLeft());
			preOrder(list, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] order() {
		ArrayList<T> list = new ArrayList<>();
		order(list, getRoot());
		return (T[]) list.toArray(new Comparable[list.size()]);
	}

	private void order(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty() && node != null) {
			order(list, (BSTNode<T>) node.getLeft());
			list.add(node.getData());
			order(list, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> list = new ArrayList<>();
		postOrder(list, getRoot());
		return (T[]) list.toArray(new Comparable[list.size()]);
	}

	private void postOrder(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty() && node != null) {
			postOrder(list, (BSTNode<T>) node.getLeft());
			postOrder(list, (BSTNode<T>) node.getRight());
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
