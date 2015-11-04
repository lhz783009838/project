package com.lab.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lab.DBQuery.DataProcess;
import com.lab.Object.Project;

/**
 * ��Ŀ�����ݷ�����
 * @author Administrator
 *
 */
public class Project_Manager {

	/**
	 * ������Ŀ����
	 * @param name
	 * @param info
	 * @param time
	 */
	public static void Add_Project(String name,String info,String time){
		//���ݿ�������
		String query = "insert into project(name,info,time)values('"+name+"','"+info+"','"+time+"')";
		
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * ������Ŀ���� 
	 * @param id
	 * @param name
	 * @param info
	 * @param time
	 */
	public static void Edit_Project(int id,String name,String info,String time){
		//���ݿ�������
		String query = "update project set name='"+name+"',info='"+info+"',time='"+time+"'where id="+id;
		
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * ɾ����Ŀ����
	 * @param id
	 */
	public static void Delete_Project(int id){
		//���ݿ�������
		String query = "delete from project where id="+id;
		
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * ͨ��id��ȡ��¼
	 * @param id
	 * @return
	 */
	public static Project getProject(int id){
		//��ѯ���
		String query = "select * from project where id="+id;
		
		//���ݿ����
		Connection conn = DataProcess.getConnetion();
		//��ȡproject����
		Project pro = Project.getInstance();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			//�����������м�¼
			if(rs.next()){
				String name = rs.getString("name");
				String info = rs.getString("info");
				String time = rs.getString("time");
				
				//�����Ա��浽������
				pro.setId(id);
				pro.setName(name);
				pro.setInfo(info);
				pro.setTime(time);
			}
			
			//�ر���
			rs.close();
			conn.close();
			st.close();
			return pro;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ȡ�����м�¼
	 * @return
	 */
	public static ArrayList<Project> getProjectList(){
		String query = "select * from project";
		Connection conn = DataProcess.getConnetion();
		ArrayList<Project> pl = new ArrayList<Project>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String info = rs.getString("info");
				String time = rs.getString("time");
				
				Project pro = Project.getInstance();
				//���浽������
				pro.setId(id);
				pro.setName(name);
				pro.setInfo(info);
				pro.setTime(time);
				//���浽������
				pl.add(pro);
			}
			
			//�ر���
			rs.close();
			conn.close();
			st.close();
			return pl;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}