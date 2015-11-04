package com.lab.Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.lab.DBQuery.DataProcess;

/**
 * ����ʵ����
 * 
 * @author Administrator
 *
 */
public class News {

	private int id;
	private String title;
	private String content; // ��������
	private String time; // ʱ������

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
		//���������ڴ��Bean
		Vector Items = new Vector();
		//���ݿ⴦��
		Connection conn = DataProcess.getConnetion();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(strSql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
			
			//��ҳ����
			Pageable pgb = new Pageable(rs);  //�õ�һ����ҳ������
			pgb.setPageSize(5);	 //���÷�ҳ�Ĵ�С
			pgb.gotoPage(page);  //����Ҫת�����
			
			//�ڽ�����ж�λ��ĳ�У��������һ�о���Ҫת��ҳ��ĵ�һ��
			rs.absolute(pgb.getRowsCount());
			int i = 0;
			
			//ȡ��Ҫת��ҳ����Щ�е���Ϣ����������Bean�У����ҽ���ЩBean����������
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
	 * ���ݲ�������ID��ȡ����
	 * @param ID
	 * @return
	 */
	public static News getNews(int ID){
		String selectStr = "select * from news where id="+ID;
		
		//�������ݿ�
		Connection conn = DataProcess.getConnetion();
		
		News news = new News();
		
		try {
			Statement st = conn.createStatement();
			//ִ�����ݿ�������,������ѯ������ظ������rs
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
