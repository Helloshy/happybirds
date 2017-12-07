package com.kapphk.web.utils;

public class GridReq {
	private int rows;// 每页显示行数
	private int page;// 当前页数
	//默认页跟行数
	public static final Integer PAGE = 1 ;
	public static final Integer ROWS = 10 ;
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page >= 1 ? (page-1) * rows : page * rows;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
