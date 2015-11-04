package com.lab.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lab.DBQuery.DataProcess;
import com.lab.Object.Teacher;

public class Teacher_Manager {
	
	/**
	 * �����¼�¼
	 * @param name
	 * @param sex
	 * @param zhicheng
	 * @param image
	 * @param info
	 */
	public static void Add_Teacher(String name,String sex,String zhicheng,String image,String info){
		
		//�Ա��ж�
		String xingbie="";
		if(sex.equals("male")){
			xingbie="��";
		}else{
			xingbie="Ů";
		}
		
		//sql�������
		String query = "insert into teacher(name,sex,zhicheng,image,info)values('"+name+"','"+xingbie+"','"+zhicheng+"','"+image+"','"+info+"')";
		
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
	public static void Edit_Teacher(String name,String sex,String zhicheng,String image,String info,int id){
		
		//�Ա��ж�
				String xingbie="";
				if(sex.equals("male")){
					xingbie="��";
				}else{
					xingbie="Ů";
				}
				
				String query = "update teacher set name='"+name+"',zhicheng='"+zhicheng+"',sex='"+xingbie+"',image='"+image+"',info='"+info+"' where id="+id;
				
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
	public static void Delete_Teacher(int id){
		
		String query = "delete from teacher where id="+id;
		
		try {
			DataProcess.ExeQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
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
