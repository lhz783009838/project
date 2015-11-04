package com.lab.Object;

public class Teacher {

	//属性 
	private int id;
	private String name;
	private String sex;
	private String zhicheng;
	private String image;
	private String info;
	
	public Teacher() {
	}

	public Teacher(int id, String name, String sex, String zhicheng,
			String image, String info) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.zhicheng = zhicheng;
		this.image = image;
		this.info = info;
	}
	
	/**
	 * 创建对象实例
	 * @return
	 */
	public static Teacher getInstance(){
		return new Teacher();
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getZhicheng() {
		return zhicheng;
	}

	public void setZhicheng(String zhicheng) {
		this.zhicheng = zhicheng;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
