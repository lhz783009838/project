package com.lab.Object;

/**
 * ��¼��Ŀ��Ϣ
 * @author Administrator
 *
 */
public class Project {

	private int id;
	private String name;
	private String info;
	private String time;
	
	public Project() {
	}

	public Project(int id, String name, String info, String time) {
		this.id = id;
		this.name = name;
		this.info = info;
		this.time = time;
	}
	
	/**
	 * ��ȡ�����һ��ʵ��
	 * @return
	 */
	public static Project getInstance(){
		return new Project();
	}

	/**
	 * ��ȡһ���趨���ԵĶ���ʵ��
	 * @param id
	 * @param name
	 * @param info
	 * @param time
	 * @return
	 */
	public static Project getInstance(int id, String name, String info, String time) {
		return new Project(id,name,info,time);
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}