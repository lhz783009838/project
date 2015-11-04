package com.lab.business;

import java.util.GregorianCalendar;

import com.lab.DBQuery.DataProcess;

/**
 * 信息管理类
 * @author Administrator
 *
 */
public class New_Manager {

	/**
	 * 添加记录
	 * @param title
	 * @param content
	 */
	public static void Add_News(String title,String content){
		
		//创建日历对象
		GregorianCalendar gc = new GregorianCalendar();
		//创建数据库插入语句
		String query = "insert into news(title,content,datetime)values('"+title+"','"+content+"','"+gc.getTime().toLocaleString()+"')";
		
		//调用插入语句
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * 更新信息方法
	 * @param title
	 * @param content
	 * @param id
	 */
	public static void Edit_News(String title,String content,int id){
		GregorianCalendar gc = new GregorianCalendar();
		
		//更新语句
		String query = "update news set title='"+title+"',content='"+content+"',datetime='"+gc.getTime().toLocaleString()+"'where id="+id;
		
		DataProcess.ExeQuery(query);
	}
	
	/**
	 * 删除信息
	 * @param id
	 */
	public static void Delete_News(int id){
		String strDelete = "delete from news where id="+id;
		//删除
		DataProcess.ExeQuery(strDelete);
	}
	
	
}
