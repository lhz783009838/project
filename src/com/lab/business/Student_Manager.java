package com.lab.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lab.DBQuery.DataProcess;
import com.lab.Object.Teacher;

public class Student_Manager {

	/**
	 * 插入新记录
	 * @param name
	 * @param sex
	 * @param zhicheng
	 * @param image
	 * @param info
	 */
	public static void Add_Student(String name,int grade_id,String sex,String image){
		
		//性别判断
		String xingbie="";
		if(sex.equals("male")){
			xingbie="男";
		}else{
			xingbie="女";
		}
		
		//sql插入语句
		String query = "insert into student(name,grade_id,sex,image)values('"+name+"','"+grade_id+"','"+xingbie+"','"+image+"')";
		
		//数据库操作 
		try {
			DataProcess.ExeQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新记录
	 * @param name
	 * @param sex
	 * @param zhicheng
	 * @param image
	 * @param info
	 * @param id
	 */
	public static void Edit_Student(String name,int grade_id,String sex,String image,int id){
		
		//性别判断
				String xingbie="";
				if(sex.equals("male")){
					xingbie="男";
				}else{
					xingbie="女";
				}
				
				String query = "update student set name='"+name+"',grade_id='"+grade_id+"',sex='"+xingbie+"',image='"+image+"' where id="+id;
				
				try {
					DataProcess.ExeQuery(query);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	/**
	 * 删除记录
	 * @param id
	 */
	public static void Delete_Student(int id){
		
		String query = "delete from teacher where id="+id;
		
		try {
			DataProcess.ExeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public static Teacher getTeacher(int id){
		
		String query = "select * from teacher where id="+id;
		Teacher tc = Teacher.getInstance();
		//数据库操作
		Connection conn = DataProcess.getConnetion();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			//如果结果集中有属性
			if(rs.next()){
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String zhicheng = rs.getString("zhicheng");
				String image = rs.getString("image");
				String info = rs.getString("info");
				
				//将属性值保存到对象中
				tc.setId(id);
				tc.setName(name);
				tc.setSex(sex);
				tc.setZhicheng(zhicheng);
				tc.setImage(image);
				tc.setInfo(info);
			}
			conn.close();
			rs.close();
			st.close();
			return tc;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public static ArrayList<Teacher> getTeacherList(){
		
		String query = "select * from teacher";
		
		//数据库操作
		Connection conn = DataProcess.getConnetion();
		ArrayList<Teacher> tl = new ArrayList<Teacher>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String zhicheng = rs.getString("zhicheng");
				String image = rs.getString("image");
				String info = rs.getString("info");
				
				//保存到teacher对象中
				Teacher tc = Teacher.getInstance();
				tc.setId(id);
				tc.setName(name);
				tc.setSex(sex);
				tc.setZhicheng(zhicheng);
				tc.setImage(image);
				tc.setInfo(info);
				
				//保存到集合中
				tl.add(tc);
				
			}
			
			conn.close();
			rs.close();
			st.close();
			return tl;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
