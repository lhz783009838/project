package com.lab.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lab.DBQuery.DataProcess;
import com.lab.Object.Project;

/**
 * 项目的数据访问类
 * @author Administrator
 *
 */
public class Project_Manager {

	/**
	 * 增加项目数据
	 * @param name
	 * @param info
	 * @param time
	 */
	public static void Add_Project(String name,String info,String time){
		//数据库操作语句
		String query = "insert into project(name,info,time)values('"+name+"','"+info+"','"+time+"')";
		
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * 更改项目数据 
	 * @param id
	 * @param name
	 * @param info
	 * @param time
	 */
	public static void Edit_Project(int id,String name,String info,String time){
		//数据库操作语句
		String query = "update project set name='"+name+"',info='"+info+"',time='"+time+"'where id="+id;
		
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * 删除项目数据
	 * @param id
	 */
	public static void Delete_Project(int id){
		//数据库操作语句
		String query = "delete from project where id="+id;
		
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * 通过id获取记录
	 * @param id
	 * @return
	 */
	public static Project getProject(int id){
		//查询语句
		String query = "select * from project where id="+id;
		
		//数据库操作
		Connection conn = DataProcess.getConnetion();
		//获取project对象
		Project pro = Project.getInstance();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			//如果结果集中有记录
			if(rs.next()){
				String name = rs.getString("name");
				String info = rs.getString("info");
				String time = rs.getString("time");
				
				//将属性保存到对象中
				pro.setId(id);
				pro.setName(name);
				pro.setInfo(info);
				pro.setTime(time);
			}
			
			//关闭流
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
	 * 取到所有记录
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
				//保存到对象中
				pro.setId(id);
				pro.setName(name);
				pro.setInfo(info);
				pro.setTime(time);
				//保存到集合中
				pl.add(pro);
			}
			
			//关闭流
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