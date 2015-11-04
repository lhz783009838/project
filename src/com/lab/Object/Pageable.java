package com.lab.Object;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库分页
 * 
 * @author Administrator
 *
 */
public class Pageable {

	// 定义变量
	private int pageSize; // 每页显示数
	private int totalRows; // 结果集的总行数(条数)
	private int totalPages; // 结果集的总页数 = totalRows / pageSize ;
	private static int currentPage; // 当前页
	private int rowsCount; // 数据库游标指向的行

	/**
	 * 构造函数
	 */
	public Pageable(ResultSet rs) {

		try {
			// 将数据库游标指向结果集的最后
			rs.last();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			this.setTotalRows(rs.getRow()); // 得到结果集的总行数
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.beforeFirst(); // 将数据库游标指向结果集的最前方
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
	 * 设置分页大小
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
	 * 转到指定页面
	 * 
	 * @param page
	 */
	public void gotoPage(int page) {
		switch (page) {
		case -1:
			this.setCurrentPage(1); // 如果转向首页，当前页为第一页
			break;
		case -2:
			int t = this.getCurrentPage();
			this.setCurrentPage(t - 1); // 转向上一页，当前页 -1
			break;
		case -3:
			int n = this.getCurrentPage();
			this.setCurrentPage(n + 1); // 转向上一页,当前页 +1
			break;
		case -4:
			this.setCurrentPage(this.getTotalPages()); // 转向尾页
			break;
		default:
			this.setCurrentPage(page); // 转向其他页面
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页
	 * 
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		// 当前页为0或负数时，当前页设置为1
		if (currentPage <= 0)
			this.currentPage = 1;
		// 如果当前页大于总页数，则当前页为总页数
		if (currentPage > this.getTotalPages())
			this.currentPage = this.getTotalPages();
		// 正常情况下，当前页为指定的页数
		else
			this.currentPage = currentPage;

		// 设置数据库游标指向位置
		this.setRowsCount((this.currentPage - 1) * this.getPageSize() + 1);
	}

	/**
	 * 返回当前页的记录条数
	 */
	public int getCurrentPageRowsCount() {
		// 如果分页数为0，返回所有页数
		if (this.getPageSize() == 0)
			return this.getTotalRows();
		// 如果数据库记录总行数我0，返回0
		if (this.getTotalRows() == 0)
			return 0;
		// 如果当前页 和 总页数 不相等 返回每页显示数
		if (this.getCurrentPage() != this.getTotalPages())
			return this.getPageSize();
		// 否则返回 总条数 - （总页数-1）*每页条数
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
	 * 设置总页数
	 * 
	 * @param totalPages
	 */
	public void setTotalPages(int totalPages) {
		//如果总数据库总条数为0
		if (this.getTotalRows() == 0) {
			this.totalPages = 0;
		//如果每页条数为0
		}else if (this.getPageSize() == 0){
			this.totalPages = 1;
		}else {
			//如果 总条数 / 每页条数  有余数 ，则 总页数 = 总条数 /每页条数   +1 
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
	 * 转到当前页的第一条记录
	 * @throws java.sql.SQLException
	 */
	public void pageFirst() throws java.sql.SQLException{
		this.setRowsCount((this.getCurrentPage()-1)*this.getPageSize()+1);
	}
	
	/**
	 * 转到当前最后一条记录
	 * @throws java.sql.SQLException
	 */
	public void pageLast() throws java.sql.SQLException{
		this.setRowsCount((this.getCurrentPage()-1)*this.getPageSize()+this.getCurrentPageRowsCount());
	}
}
