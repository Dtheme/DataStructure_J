package com.dzw.models;

/**
 * 用于测试泛型
 */

@SuppressWarnings({"unused"})
public class Person implements Comparable<Person> {
	private int age;
	private String name;

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
	public Person(int age) {
		this.age = age;
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		
		System.out.println("Person - finalize");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Person person) {
			return this.age == person.age;
		}
		return false;
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
