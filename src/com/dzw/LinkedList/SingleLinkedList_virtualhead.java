package com.dzw.LinkedList;

import com.dzw.Base.AbstractList;

/**
 * 这个是一个经典的单向链表的实现方案 看看实现方式就好
 * 维护一个虚拟头结点（实际不存在）
 * @param <E>
 */
public class SingleLinkedList_virtualhead<E> extends AbstractList<E> {
	private Node<E> first;
	
	public SingleLinkedList_virtualhead() {
		first = new Node<>(null, null);
	}
	
	private static class Node<E> {
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E old = node.element;
		node.element = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		Node<E> prev = index == 0 ? first : node(index - 1);
		prev.next = new Node<>(element, prev.next);

		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> prev = index == 0 ? first : node(index - 1);
		Node<E> node = prev.next;
		prev.next = node.next;
			
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i;
				
				node = node.next;
			}
		} else {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) return i;
				
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 获取index位置对应的节点对象
	 * @param index
	 * @return
	 */
	private Node<E> node(int index) {
		rangeCheck(index);
		
		Node<E> node = first.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first.next;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(node.element);
			
			node = node.next;
		}
		string.append("]");

		return string.toString();
	}
}
