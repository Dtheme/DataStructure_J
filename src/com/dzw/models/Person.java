package com.dzw.models;

import java.util.Objects;

/**
 * 用于测试泛型
 */

@SuppressWarnings({"unused"})
public class Person implements Comparable<Person> {
	private int age;
	private String name;
	private float height;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	public Person(int age, float height, String name) {
		this.age = age;
		this.height = height;
		this.name = name;
	}
	public Person(int age) {
		this.age = age;
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		
		System.out.println("Person - finalize");
	}
	
	@Override
	/**
	 * 用来比较2个对象是否相等
	 */
	public boolean equals(Object obj) {
//		if (obj == null) return false;
//		if (obj instanceof Person person) {
//			return this.age == person.age;
//		}
//		return false;


		//hashmap测试用
		// 内存地址
		if (this == obj) return true;
		if (obj == null || obj.getClass() != getClass()) return false;
		// if (obj == null || !(obj instanceof Person)) return false;

		// 比较成员变量
		Person person = (Person) obj;
		return person.age == age
				&& person.height == height
				&& (Objects.equals(person.name, name));//(person.name == null ? name == null : person.name.equals(name))
	}

	@Override
	public int hashCode() {
		int hashCode = Integer.hashCode(age);
		hashCode = hashCode * 31 + Float.hashCode(height);
		hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
		return hashCode;
	}

	@Override
	public int compareTo(Person e) {
		return age - e.age;
	}
/**
 *  debug用于
 */
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

}
