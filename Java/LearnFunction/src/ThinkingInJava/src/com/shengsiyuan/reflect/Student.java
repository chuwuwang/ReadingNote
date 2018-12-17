package com.shengsiyuan.reflect;

public class Student extends Person implements Action {

	public int grade;

	private String add;

	public Student() {
	}

	public Student(String name) {
		super(name);
	}

	public Student(int grade, String name) {
		super(name);
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	@SuppressWarnings("unused")
	private void learn(String course) {
		System.out.println(name + " learn " + course);
	}

	@Override
	public void work() {
		System.out.println("--work--");
	}

	@Override
	public String toString() {
		return "Student [grade=" + grade + ", add=" + add + ", name=" + name + "]";
	}

}