package com.dzw.Trie;

import com.dzw.Utils.Asserts;
public class Main {

	static void test1() {
		Trie<Integer> trie = new Trie<>();
		trie.add("carry", 1);
		trie.add("dog", 2);
		trie.add("catalog", 3);
		trie.add("cast", 4);
		trie.add("dzw", 5);
		Asserts.test(trie.size() == 5);
		Asserts.test(trie.startsWith("do"));
		Asserts.test(trie.startsWith("c"));
		Asserts.test(trie.startsWith("ca"));
		Asserts.test(trie.startsWith("carry"));
		Asserts.test(trie.startsWith("cat"));
		Asserts.test(!trie.startsWith("hehe"));
		Asserts.test(trie.get("dzw") == 5);
		Asserts.test(trie.remove("carry") == 1);
		Asserts.test(trie.remove("catalog") == 3);
		Asserts.test(trie.remove("cast") == 4);
		Asserts.test(trie.size() == 2);
		Asserts.test(trie.startsWith("d"));
		Asserts.test(trie.startsWith("do"));
		Asserts.test(!trie.startsWith("c"));
	}

	public static void main(String[] args) {
		test1();
	}

}
