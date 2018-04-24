package com.xx.leave.utils.commom;

import java.util.List;

public class PageBean<T> {
	private int pageCode;
	private int totalRecord;
	private int pageSize;
	private List<T> pagelist;
	private String url;
	public PageBean() {
		super();
	}
	public PageBean(int pageCode, int totalRecord, int pageSize,
			List<T> pagelist) {
		super();
		this.pageCode = pageCode;
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.pagelist = pagelist;
	}
	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getPagelist() {
		return pagelist;
	}
	public void setPagelist(List<T> pagelist) {
		this.pagelist = pagelist;
	}
	/*
	 * 鑾峰彇鎬婚〉鏁�
	 */
	public int getTp(){
		int tp=totalRecord/pageSize;
		return totalRecord % pageSize == 0 ?tp:tp+1;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
