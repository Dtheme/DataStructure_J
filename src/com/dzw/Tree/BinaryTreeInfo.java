package com.dzw.Tree;
/**
 *  搜索树公共接口
 */
public interface BinaryTreeInfo {
	/**
	 * 根节点
	 */
	Object root();
	/**
	 * 左子节点
	 */
	Object left(Object node);
	/**
	 * 右子节点
	 */
	Object right(Object node);
	/**
	 * 节点toString
	 */
	Object string(Object node);
}
