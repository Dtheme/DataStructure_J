package com.dzw.Tree;
import com.dzw.Tree.BinarySearchTree.Visitor;
import com.dzw.Utils.Files;
import com.dzw.Utils.FileInfo;
import com.dzw.Utils.printer.BinaryTrees;
import com.dzw.models.Person;
import com.dzw.Tree.*;

import java.util.*;

/**
 *	二叉树 testcase
 */
@SuppressWarnings({"unchecked","unused"})
public class Main {
	
	private static class PersonComparator implements Comparator<Person> {
		public int compare(Person e1, Person e2) {
			return e1.getAge() - e2.getAge();
		}
	}
	
	private static class PersonComparator2 implements Comparator<Person> {
		public int compare(Person e1, Person e2) {
			return e2.getAge() - e1.getAge();
		}
	}

	static void test1() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};


		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
	}
	
	static void test2() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};
		
		BinarySearchTree<Person> bst1 = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst1.add(new Person(data[i]));
		}
		
		BinaryTrees.println(bst1);
		
		BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
				return o2.getAge() - o1.getAge();
			}
		});
		for (int i = 0; i < data.length; i++) {
			bst2.add(new Person(data[i]));
		}
		BinaryTrees.println(bst2);
	}
	
	static void test3() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < 40; i++) {
			bst.add((int)(Math.random() * 100));
		}
		
		String str = BinaryTrees.printString(bst);
		str += "\n";
		Files.writeToFile("/Users/dzw/Desktop/output", str, true);
		
		// BinaryTrees.println(bst);
	}
	
	static void test4() {
		BinaryTrees.println(new BinaryTreeInfo() {
			
			@Override
			public Object string(Object node) {
				return node.toString() + "_";
			}
			
			@Override
			public Object root() {
				return "A";
			}
			
			@Override
			public Object right(Object node) {
				if (node.equals("A")) return "C";
				if (node.equals("C")) return "E";
				return null;
			}
			
			@Override
			public Object left(Object node) {
				if (node.equals("A")) return "B";
				if (node.equals("C")) return "D";
				return null;
			}
		});
		
		// test3();
		
		
		/*
		 * Java的匿名类，类似于iOS中的Block、闭包
		 */
		
//		BinarySearchTree<Person> bst1 = new BinarySearchTree<>(new Comparator<Person>() {
//			@Override
//			public int compare(Person o1, Person o2) {
//				return 0;
//			}
//		});
//		bst1.add(new Person(12));
//		bst1.add(new Person(15));
//		
//		BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
//		bst2.add(new Person(12));
//		bst2.add(new Person(15));
//
		
		
//		BinarySearchTree<Car> bst4 = new BinarySearchTree<>(new Car);
//		
//		
//		java.util.Comparator<Integer> iComparator;
	}
	
	static void test5() {
		BinarySearchTree<Person> bst = new BinarySearchTree<>();
		bst.add(new Person(10, "A"));
		bst.add(new Person(12, "B"));
		bst.add(new Person(6, "C"));
		
		bst.add(new Person(10, "D"));
		
		BinaryTrees.println(bst);
	}
	
	static void test6() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}

//		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//		for (int i = 0; i < 10; i++) {
//			bst.add((int)(Math.random() * 100));
//		}
		BinaryTrees.println(bst);
		System.out.println(bst.isComplete());
		
		// bst.levelOrderTraversal();
		
		/*
		 *       7
		 *    4    9
		    2   5
		 */
		
		bst.levelOrder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print("_" + element + "_ ");
				return true;
			}
		});
		
		bst.inorder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print("_" + (element + 3) + "_ ");

				return true;
			}
		});
		
		 System.out.println(bst.height());
	}
	
	static void test7() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		
		bst.remove(7);
		
		BinaryTrees.println(bst);
	}
	
	static void test8() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 1
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);
		System.out.println(bst.isComplete());
	}
	
	static void test9() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 1
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);
		
		bst.preorder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 2 ? true : false;
			}
		});
		System.out.println();
		
		bst.inorder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 4 ? true : false;
			}
		});
		System.out.println();
		
		bst.postorder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 4 ? true : false;
			}
		});
		System.out.println();
		
		bst.levelOrder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 9 ? true : false;
			}
		});
		System.out.println();
	}

	static void testRedBlackTree1() {

	}

	static void testRedBlackTree2() {
		Integer data[] = new Integer[] {
				67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
		};

		AVLTree<Integer> avl = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BinaryTrees.println(avl);
//			System.out.println("---------------------------------------");
		}

//		for (int i = 0; i < data.length; i++) {
//			avl.remove(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BinaryTrees.println(avl);
//			System.out.println("---------------------------------------");
//		}


		BinaryTrees.println(avl);
	}

	static void testRedBlackTree3() {
		List<Integer> data = new ArrayList<>();
		for (int i = 0; i < 100_0000; i++) {
			data.add((int)(Math.random() * 100_0000));
		}

		BST<Integer> bst = new BST<>();
		for (int i = 0; i < data.size(); i++) {
			bst.add(data.get(i));
		}
		for (int i = 0; i < data.size(); i++) {
			bst.contains(data.get(i));
		}
		for (int i = 0; i < data.size(); i++) {
			bst.remove(data.get(i));
		}

		AVLTree<Integer> avl = new AVLTree<>();
		for (int i = 0; i < data.size(); i++) {
			avl.add(data.get(i));
		}
		for (int i = 0; i < data.size(); i++) {
			avl.contains(data.get(i));
		}
		for (int i = 0; i < data.size(); i++) {
			avl.remove(data.get(i));
		}
	}

	static void testRedBlackTree4() {
		Integer data[] = new Integer[] {
				55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
		};

		RedBlackTree<Integer> rb = new RedBlackTree<>();
		for (int i = 0; i < data.length; i++) {
			rb.add(data[i]);
			System.out.println("【" + data[i] + "】");
			BinaryTrees.println(rb);
			System.out.println("---------------------------------------");
		}
	}

	static void testRedBlackTree5() {
		Integer data[] = new Integer[] {
				55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
		};

		RedBlackTree<Integer> rb = new RedBlackTree<>();
		for (int i = 0; i < data.length; i++) {
			rb.add(data[i]);
		}

		BinaryTrees.println(rb);

		for (int i = 0; i < data.length; i++) {
			rb.remove(data[i]);
			System.out.println("---------------------------------------");
			System.out.println("【" + data[i] + "】");
			BinaryTrees.println(rb);
		}
	}
	static void testBTree() {
		Integer data[] = new Integer[] {
				55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
		};

		BTree<Integer> bT = new BTree<>();
		for (int i = 0; i < data.length; i++) {
			bT.add(data[i]);
		}
//		for (int i = 0; i < data.length; i++) {
//			bT.remove(data[i]);
//			System.out.println("---------------------------------------");
//			System.out.println("【" + data[i] + "】");
//			System.out.println(bT.toString());
//		}
	}
	public static void main(String[] args) {
//		test6();
//		test8();
//		test9();
//
//		testRedBlackTree1();
		testRedBlackTree2();
//		testRedBlackTree3();
//		testRedBlackTree4();
//		testRedBlackTree5();
		testBTree();
	}
}
