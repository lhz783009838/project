package com.lab.business;

import java.util.GregorianCalendar;

import com.lab.DBQuery.DataProcess;

/**
 * ��Ϣ������
 * @author Administrator
 *
 */
public class New_Manager {

	/**
	 * ��Ӽ�¼
	 * @param title
	 * @param content
	 */
	public static void Add_News(String title,String content){
		
		//������������
		GregorianCalendar gc = new GregorianCalendar();
		//�������ݿ�������
		String query = "insert into news(title,content,datetime)values('"+title+"','"+content+"','"+gc.getTime().toLocaleString()+"')";
		
		//���ò������
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * ������Ϣ����
	 * @param title
	 * @param content
	 * @param id
	 */
	public static void Edit_News(String title,String content,int id){
		GregorianCalendar gc = new GregorianCalendar();
		
		//�������
		String query = "update news set title='"+title+"',content='"+content+"',datetime='"+gc.getTime().toLocaleString()+"'where id="+id;
		
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * ɾ����Ϣ
	 * @param id
	 */
	public static void Delete_News(int id){
		String strDelete = "delete from news where id="+id;
		//ɾ��
		DataProcess.ExeQuery(strDelete);
	}
	
	
}
