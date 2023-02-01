package com.dzw.Base;

public abstract class AbstractList<E> implements List<E> {
	/**
	 * 元素的数量
	 */
	protected int size;
	/**
	 * @return 元素的数量
	 */
	public int size() {
		return size;
	}

	/**
	 * @return 是否为空
	 */
	public boolean isEmpty() {
		 return size == 0;
	}

	/**
	 * @param element 是否包含某个元素element
	 * @return  是否包含某个元素
	 */
	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}

	/**
	 * @param element 添加元素到尾部
	 */
	public void add(E element) {
		add(size, element);
	}
	
	protected void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
	}
	
	protected void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	protected void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
}
