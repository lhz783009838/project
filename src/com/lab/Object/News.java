package com.lab.Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.lab.DBQuery.DataProcess;

/**
 * 新闻实体类
 * 
 * @author Administrator
 *
 */
public class News {

	private int id;
	private String title;
	private String content; // 新闻内容
	private String time; // 时间属性

	public News() {
	}

	public News(int id, String title, String content, String time) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static Vector search(String strSql,int page){
		//容器，用于存放Bean
		Vector Items = new Vector();
		//数据库处理
		Connection conn = DataProcess.getConnetion();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(strSql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
			
			//分页功能
			Pageable pgb = new Pageable(rs);  //得到一个分页功能类
			pgb.setPageSize(5);	 //设置分页的大小
			pgb.gotoPage(page);  //设置要转向的类
			
			//在结果集中定位到某行，在这里，这一行就是要转向页面的第一行
			rs.absolute(pgb.getRowsCount());
			int i = 0;
			
			//取得要转向页的那些行的信息，将其存放在Bean中，并且将这些Bean存入容器中
			do{
				Items.add(new News(rs.getInt("id"),rs.getString("title"),rs.getString("content"),rs.getString("datetime")));
				i++;
			}while(rs.next() && i < pgb.getCurrentPageRowsCount());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Items;
	}
	
	/**
	 * 根据参数新闻ID获取新闻
	 * @param ID
	 * @return
	 */
	public static News getNews(int ID){
		String selectStr = "select * from news where id="+ID;
		
		//连接数据库
		Connection conn = DataProcess.getConnetion();
		
		News news = new News();
		
		try {
			Statement st = conn.createStatement();
			//执行数据库操作语句,并将查询结果返回给结果集rs
			ResultSet rs = st.executeQuery(selectStr);
			
			if(rs.next()){
				String title = rs.getString("title");
				String content = rs.getString("content");
				String time = rs.getString("datetime");
				
				news.setId(ID);
				news.setTitle(title);
				news.setContent(content);
				news.setTime(time);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return news;
	}
}
