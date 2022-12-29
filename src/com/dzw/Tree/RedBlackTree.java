package com.dzw.Tree;
import java.util.Comparator;

/**
 * 红黑树：
 * 1、每个结点是红的或者黑的
 * 2、根结点是黑的
 * 3、每个叶子节点是黑的（因为这条性质，一般用叶子结点在代码中被特殊表示）
 * 4、如果一个节点是红的，则它的两个儿子都是黑的（不存在相邻红色）
 * 5、从任一节点到叶子节点，所包含的黑色节点数目相同（即黑高度相同）
 * 6、最长路径长度不超过最短路径长度的2倍（2n-1，一条黑红黑红…一条全黑）
 *
 * 搜索：平均时间复杂度，最坏情况时间复杂度 O(log(n))
 * 插入：平均时间复杂度，最坏情况时间复杂度 O(log(n))
 * 删除：平均时间复杂度，最坏情况时间复杂度 O(log(n))
 * 空间复杂度：平均时间复杂度，最坏情况时间复杂度 O(n)
 */
public class RedBlackTree<E> extends BBST<E> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	public RedBlackTree() {
		this(null);
	}

	/**
	 * 这里使用的是Java实现的比较器
	 * @param comparator 比较器
	 */
	public RedBlackTree(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * ❗️核心方法：添加节点之后的平衡、恢复rbTree的性质的操作（这是红黑树真正复杂的地方）
	 *
	 * @param node 添加进来的节点 节点默认设成红色
	 */
	@Override
	protected void afterAdd(Node<E> node) {
	}

	/**
	 * ❗️核心方法：删除之后的平衡、恢复rbTree的性质的操作
	 * @param node 被删除的节点
	 */
	@Override
	protected void afterRemove(Node<E> node) {

	}

	private Node<E> color(Node<E> node, boolean color) {
		{
			if (node == null) return node;
			((RBNode<E>) node).color = color;
			return node;
		}
	}

	private Node<E> red(Node<E> node) {
		return color(node, RED);
	}
	
	private Node<E> black(Node<E> node) {
		return color(node, BLACK);
	}
	
	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode<E>)node).color;
	}
	
	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}
	
	private boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}
	
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new RBNode<>(element, parent);
	}

	private static class RBNode<E> extends Node<E> {
		boolean color = RED;
		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}
		
		@Override
		public String toString() {
			String str = "";
			if (color == RED) {
				str = "R_";
			}
			return str + element.toString();
		}
	}
}
