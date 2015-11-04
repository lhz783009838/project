package com.lab.Object;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���ݿ��ҳ
 * 
 * @author Administrator
 *
 */
public class Pageable {

	// �������
	private int pageSize; // ÿҳ��ʾ��
	private int totalRows; // �������������(����)
	private int totalPages; // ���������ҳ�� = totalRows / pageSize ;
	private static int currentPage; // ��ǰҳ
	private int rowsCount; // ���ݿ��α�ָ�����

	/**
	 * ���캯��
	 */
	public Pageable(ResultSet rs) {

		try {
			// �����ݿ��α�ָ�����������
			rs.last();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			this.setTotalRows(rs.getRow()); // �õ��������������
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.beforeFirst(); // �����ݿ��α�ָ����������ǰ��
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// get & set
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ���÷�ҳ��С
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize >= 1) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = 1;
		}

		this.setTotalPages(pageSize);
	}

	/**
	 * ת��ָ��ҳ��
	 * 
	 * @param page
	 */
	public void gotoPage(int page) {
		switch (page) {
		case -1:
			this.setCurrentPage(1); // ���ת����ҳ����ǰҳΪ��һҳ
			break;
		case -2:
			int t = this.getCurrentPage();
			this.setCurrentPage(t - 1); // ת����һҳ����ǰҳ -1
			break;
		case -3:
			int n = this.getCurrentPage();
			this.setCurrentPage(n + 1); // ת����һҳ,��ǰҳ +1
			break;
		case -4:
			this.setCurrentPage(this.getTotalPages()); // ת��βҳ
			break;
		default:
			this.setCurrentPage(page); // ת������ҳ��
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * ���õ�ǰҳ
	 * 
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		// ��ǰҳΪ0����ʱ����ǰҳ����Ϊ1
		if (currentPage <= 0)
			this.currentPage = 1;
		// �����ǰҳ������ҳ������ǰҳΪ��ҳ��
		if (currentPage > this.getTotalPages())
			this.currentPage = this.getTotalPages();
		// ��������£���ǰҳΪָ����ҳ��
		else
			this.currentPage = currentPage;

		// �������ݿ��α�ָ��λ��
		this.setRowsCount((this.currentPage - 1) * this.getPageSize() + 1);
	}

	/**
	 * ���ص�ǰҳ�ļ�¼����
	 */
	public int getCurrentPageRowsCount() {
		// �����ҳ��Ϊ0����������ҳ��
		if (this.getPageSize() == 0)
			return this.getTotalRows();
		// ������ݿ��¼��������0������0
		if (this.getTotalRows() == 0)
			return 0;
		// �����ǰҳ �� ��ҳ�� ����� ����ÿҳ��ʾ��
		if (this.getCurrentPage() != this.getTotalPages())
			return this.getPageSize();
		// ���򷵻� ������ - ����ҳ��-1��*ÿҳ����
		return this.getTotalRows() - (this.getTotalPages() - 1)
				* this.getPageSize();
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * ������ҳ��
	 * 
	 * @param totalPages
	 */
	public void setTotalPages(int totalPages) {
		//��������ݿ�������Ϊ0
		if (this.getTotalRows() == 0) {
			this.totalPages = 0;
		//���ÿҳ����Ϊ0
		}else if (this.getPageSize() == 0){
			this.totalPages = 1;
		}else {
			//��� ������ / ÿҳ����  ������ ���� ��ҳ�� = ������ /ÿҳ����   +1 
			if(this.getTotalRows() % this.getPageSize() != 0) 
				this.totalPages=this.getTotalRows()/this.getPageSize()+1;
			else
				this.totalPages=this.getTotalRows()/this.getPageSize();
		}
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}
	
	/**
	 * ת����ǰҳ�ĵ�һ����¼
	 * @throws java.sql.SQLException
	 */
	public void pageFirst() throws java.sql.SQLException{
		this.setRowsCount((this.getCurrentPage()-1)*this.getPageSize()+1);
	}
	
	/**
	 * ת����ǰ���һ����¼
	 * @throws java.sql.SQLException
	 */
	public void pageLast() throws java.sql.SQLException{
		this.setRowsCount((this.getCurrentPage()-1)*this.getPageSize()+this.getCurrentPageRowsCount());
	}
}
