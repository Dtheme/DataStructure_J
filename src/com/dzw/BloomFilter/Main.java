package com.dzw.BloomFilter;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BloomFilter<Integer> bf = new BloomFilter<>(1_00_0000, 0.01);
		for (int i = 1; i <= 1_00_0000; i++) {
			bf.put(i);
		}

		int count = 0;
		for (int i = 1_00_0001; i <= 2_00_0000; i++) {
			if (bf.contains(i)) {
				count++;
			}
		}
		System.out.println(count);
		
		// 数组
		String[] urls = {};
		BloomFilter<String> bf1 = new BloomFilter<String>(1000000, 0.01);
		
		for (String url : urls) {
			if (bf1.put(url) == false) continue;
			// 爬这个url
			// ......
		}
	}

}
