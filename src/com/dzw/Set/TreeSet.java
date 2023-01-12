package com.dzw.Set;

import java.util.Comparator;

import com.dzw.Tree.BinaryTree;
import com.dzw.Tree.RedBlackTree;

public class TreeSet<E> implements Set<E> {
	private RedBlackTree<E> tree;

	public TreeSet() {
		this(null);
	}

	public TreeSet(Comparator<E> comparator) {
		tree = new RedBlackTree<>(comparator);
	}

	@Override
	public int size() {
		return tree.size();
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public void clear() {
		tree.clear();
	}

	@Override
	public boolean contains(E element) {
		return tree.contains(element);
	}

	@Override
	public void add(E element) {
		tree.add(element);
	}

	@Override
	public void remove(E element) {
		tree.remove(element);
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		tree.inorder(new BinaryTree.Visitor<E>() {
			@Override
			public boolean visit(E element) {
				return visitor.visit(element);
			}
		});
	}

}
