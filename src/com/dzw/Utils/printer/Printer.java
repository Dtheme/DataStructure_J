package com.dzw.Utils.printer;
import com.dzw.Tree.BinaryTreeInfo;

/**
 * 第三方二叉树打印工具
 */
public abstract class Printer {
	/**
	 * 二叉树的基本信息
	 */
	protected BinaryTreeInfo tree;
	
	public Printer(BinaryTreeInfo tree) {
		this.tree = tree;
	}
	
	/**
	 * 生成打印的字符串
	 */
	public abstract String printString();
	
	/**
	 * 打印后换行
	 */
	public void println() {
		print();
		System.out.println();
	}
	
	/**
	 * 打印
	 */
	public void print() {
		System.out.print(printString());
	}
}
