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
	 * �����¼�¼
	 * @param name
	 * @param sex
	 * @param zhicheng
	 * @param image
	 * @param info
	 */
	public static void Add_Student(String name,int grade_id,String sex,String image){
		
		//�Ա��ж�
		String xingbie="";
		if(sex.equals("male")){
			xingbie="��";
		}else{
			xingbie="Ů";
		}
		
		//sql�������
		String query = "insert into student(name,grade_id,sex,image)values('"+name+"','"+grade_id+"','"+xingbie+"','"+image+"')";
		
		//���ݿ���� 
		try {
			DataProcess.ExeQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���¼�¼
	 * @param name
	 * @param sex
	 * @param zhicheng
	 * @param image
	 * @param info
	 * @param id
	 */
	public static void Edit_Student(String name,int grade_id,String sex,String image,int id){
		
		//�Ա��ж�
				String xingbie="";
				if(sex.equals("male")){
					xingbie="��";
				}else{
					xingbie="Ů";
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
	 * ɾ����¼
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
	 * ͨ��id��ѯ
	 * @param id
	 * @return
	 */
	public static Teacher getTeacher(int id){
		
		String query = "select * from teacher where id="+id;
		Teacher tc = Teacher.getInstance();
		//���ݿ����
		Connection conn = DataProcess.getConnetion();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			//����������������
			if(rs.next()){
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String zhicheng = rs.getString("zhicheng");
				String image = rs.getString("image");
				String info = rs.getString("info");
				
				//������ֵ���浽������
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
	 * ��ѯ���м�¼
	 * @return
	 */
	public static ArrayList<Teacher> getTeacherList(){
		
		String query = "select * from teacher";
		
		//���ݿ����
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
				
				//���浽teacher������
				Teacher tc = Teacher.getInstance();
				tc.setId(id);
				tc.setName(name);
				tc.setSex(sex);
				tc.setZhicheng(zhicheng);
				tc.setImage(image);
				tc.setInfo(info);
				
				//���浽������
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
