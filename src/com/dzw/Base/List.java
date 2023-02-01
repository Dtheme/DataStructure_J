package com.dzw.Base;

	/**
	 *
	 * 链表公共接口
	 */
public interface List<E> {
	static final int ELEMENT_NOT_FOUND = -1;
	/**
	 * 清除所有元素
	 */
	void clear();

	/**
	 * @return 元素的数量
	 */
	int size();

	/**
	 * @return 是否为空
	 */
	boolean isEmpty();

	/**
	 * @param element 是否包含某个元素element
	 * @return 是否包含某个元素
	 */
	boolean contains(E element);

	/**
	 * @param element 添加元素element到尾部
	 */
	void add(E element);

	/**
	 * @param index 获取index位置的元素
	 * @return index位置的元素值
	 */
	E get(int index);

	/**
	 * @param index 设置index位置的元素
	 * @param element 元素值
	 * @return 原来的元素ֵ
	 */
	E set(int index, E element);

	/**
	 * @param index 在index位置插入一个元素
	 * @param element 插入的元素值
	 */
	void add(int index, E element);

	/**
	 * @param index 删除index位置的元素
	 * @return 删除的元素值
	 */
	E remove(int index);

	/**
	 * @param element 查看元素element的索引
	 * @return element的索引
	 */
	int indexOf(E element);
}
