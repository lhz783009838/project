package com.lab.DBQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProcess {

	/**
	 * 构造空函数
	 */
	public DataProcess() {
	}
	
	/**
	 * 获取connection的方法
	 * @return
	 */
	public static Connection getConnetion(){
		Connection conn =null;
		String CLASSFORNAME = "com.mysql.jdbc.Driver";
		String MSG = "jdbc:mysql://192.168.18.132/lab";
		
		try {
			//注册驱动
			Class.forName(CLASSFORNAME);
			//通过驱动管理获取连接 
			conn = DriverManager.getConnection(MSG, "root", "admin");
			return conn;
			
		} catch (ClassNotFoundException | SQLException e) {
			//连接失败是抛出异常
			System.out.println("连接数据库失败");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 执行SQL语句返回的结果个数
	 * @param SQL
	 * @return
	 */
	public static int nCount(String SQL){
		//获得与数据库的连接
		Connection myConnection = getConnetion();
		//返回的数量
		int count = 0;
		
		Statement st;
		try {
			st = myConnection.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				//如果结果集中海油数据，则调用getint方法取得结果集中第一个记录赋值给count
				count = rs.getInt(1);
				//关闭流
				rs.close();
				st.close();
				myConnection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
		
	}
	/**
	 * 插入或删除语句(增删改)
	 * @param SQL
	 */
	public static void ExeQuery(String SQL){
		
		//获取连接
		Connection myConnection = getConnetion();
		//创建预处理
		try {
			Statement st = myConnection.createStatement();
			//执行语句
			st.executeUpdate(SQL);
			//关闭流
			myConnection.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 返回查询结果(查)
	 * @param SQL
	 * @param conn
	 * @return
	 */
	public static ResultSet getResult(String SQL,Connection conn){
		
		//定义结果集 
		ResultSet rs = null;
		Statement st;
		try {
			st = conn.createStatement();
			//执行数据库操作
			rs = st.executeQuery(SQL);
			//关闭st
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 关闭RS
	 */
	public static void closeResultSet(ResultSet rs){
		
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *关闭Conn 
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
