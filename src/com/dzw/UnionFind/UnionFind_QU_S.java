package com.dzw.UnionFind;

import java.util.Arrays;

/**
 * Quick Union - 基于size的优化
 */
public class UnionFind_QU_S extends UnionFind_QU {
	private final int[] sizes;

	public UnionFind_QU_S(int capacity) {
		super(capacity);

		sizes = new int[capacity];
		Arrays.fill(sizes, 1);
	}

	/**
	 * 将v1的根节点嫁接到v2的根节点上
	 */
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) return;
		
		if (sizes[p1] < sizes[p2]) {
			parents[p1] = p2;
			sizes[p2] += sizes[p1];
		} else {
			parents[p2] = p1;
			sizes[p1] += sizes[p2];
		}
	}

}
