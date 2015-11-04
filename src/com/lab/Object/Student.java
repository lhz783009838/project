package com.lab.Object;

public class Student {

	private int id; 
	private String name;
	private int grade_id;
	private String image;
	private String sex;
	
	public Student() {
	}

	public Student(int id, String name, int grade_id, String image, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.grade_id = grade_id;
		this.image = image;
		this.sex = sex;
	}
	
	public static Student getInstance(){
		return new Student();
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

	public int getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
