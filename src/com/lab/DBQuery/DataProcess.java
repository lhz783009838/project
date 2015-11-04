package com.lab.DBQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProcess {

	/**
	 * ����պ���
	 */
	public DataProcess() {
	}
	
	/**
	 * ��ȡconnection�ķ���
	 * @return
	 */
	public static Connection getConnetion(){
		Connection conn =null;
		String CLASSFORNAME = "com.mysql.jdbc.Driver";
		String MSG = "jdbc:mysql://192.168.18.132/lab";
		
		try {
			//ע������
			Class.forName(CLASSFORNAME);
			//ͨ�����������ȡ���� 
			conn = DriverManager.getConnection(MSG, "root", "admin");
			return conn;
			
		} catch (ClassNotFoundException | SQLException e) {
			//����ʧ�����׳��쳣
			System.out.println("�������ݿ�ʧ��");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ִ��SQL��䷵�صĽ������
	 * @param SQL
	 * @return
	 */
	public static int nCount(String SQL){
		//��������ݿ������
		Connection myConnection = getConnetion();
		//���ص�����
		int count = 0;
		
		Statement st;
		try {
			st = myConnection.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				//���������к������ݣ������getint����ȡ�ý�����е�һ����¼��ֵ��count
				count = rs.getInt(1);
				//�ر���
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
	 * �����ɾ�����(��ɾ��)
	 * @param SQL
	 */
	public static void ExeQuery(String SQL){
		
		//��ȡ����
		Connection myConnection = getConnetion();
		//����Ԥ����
		try {
			Statement st = myConnection.createStatement();
			//ִ�����
			st.executeUpdate(SQL);
			//�ر���
			myConnection.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ���ز�ѯ���(��)
	 * @param SQL
	 * @param conn
	 * @return
	 */
	public static ResultSet getResult(String SQL,Connection conn){
		
		//�������� 
		ResultSet rs = null;
		Statement st;
		try {
			st = conn.createStatement();
			//ִ�����ݿ����
			rs = st.executeQuery(SQL);
			//�ر�st
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * �ر�RS
	 */
	public static void closeResultSet(ResultSet rs){
		
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *�ر�Conn 
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
