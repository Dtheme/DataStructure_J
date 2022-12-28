package com.dzw.models;

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
		if (obj instanceof Person) {
			Person person  = (Person) obj;
			return this.age == person.age;
		}
		return false;
	}

	@Override
	public int compareTo(Person e) {
//		if (age > e.age) return 1;
//		if (age < e.age) return -1;
//		return 0;
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
