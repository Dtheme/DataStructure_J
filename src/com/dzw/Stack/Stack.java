package com.dzw.Stack;

import com.dzw.Arraylist.ArrayList_optimize;
import com.dzw.Base.List;

public class Stack<E> {
	private final List<E> list = new ArrayList_optimize<>();

	public void clear() {
		list.clear();
	}
	
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E element) {
		list.add(element);
	}


	public E pop() {
		return list.remove(list.size() - 1);
	}


	public E top() {
		return list.get(list.size() - 1);
	}
}
