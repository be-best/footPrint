package com.domain;

import java.util.List;

/**
 * ��ҳ��ʾ��һ��JavaBean
 * @author 45��ը
 *
 */
public class PageBean<T> {
	private Integer currPage; //��ǰҳ��
	private Integer pageSize; //ÿҳ��ʾ����
	private Integer totalCount; //����
	private Integer totalPage; //��ҳ��
	private List<T> list; //ÿҳ��ʾ��һ������
	
	public PageBean() {
		
	}
	
	
	public PageBean(Integer currPage, Integer pageSize, Integer totalCount,
			Integer totalPage, List<T> list) {
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.list = list;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
