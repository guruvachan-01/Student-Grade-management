package com.coder.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int rollno;
	private int hindi;
	private int english;
	private int math;
	private int phy;
	private int chem;
	private float percentag;
	private String  grade;
	

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", rollno=" + rollno + ", hindi=" + hindi + ", english="
				+ english + ", math=" + math + ", phy=" + phy + ", chem=" + chem + ", percentag=" + percentag
				+ ", grade=" + grade + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getRollno() {
		return rollno;
	}


	public void setRollno(int rollno) {
		this.rollno = rollno;
	}


	public int getHindi() {
		return hindi;
	}


	public void setHindi(int hindi) {
		this.hindi = hindi;
	}


	public int getEnglish() {
		return english;
	}


	public void setEnglish(int english) {
		this.english = english;
	}


	public int getMath() {
		return math;
	}


	public void setMath(int math) {
		this.math = math;
	}


	public int getPhy() {
		return phy;
	}


	public void setPhy(int phy) {
		this.phy = phy;
	}


	public int getChem() {
		return chem;
	}


	public void setChem(int chem) {
		this.chem = chem;
	}


	public float getPercentag() {
		return percentag;
	}


	public void setPercentag(float percentag) {
		this.percentag = percentag;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
