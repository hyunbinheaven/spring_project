package com.food.exp.dto;

import java.util.List;

public class PageDTO {

	List<LikesDTO> list;
	int curPage;
	int perPage = 5;
	int totalCount;
	int totalNum;
	
	public PageDTO() {}

	public PageDTO(List<LikesDTO> list, int curPage, int perPage, int totalCount, int totalNum) {
		super();
		this.list = list;
		this.curPage = curPage;
		this.perPage = perPage;
		this.totalCount = totalCount;
		this.totalNum = totalNum;
	}

	public List<LikesDTO> getList() {
		return list;
	}

	public void setList(List<LikesDTO> list) {
		this.list = list;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	@Override
	public String toString() {
		return "PageDTO [list=" + list + ", curPage=" + curPage + ", perPage=" + perPage + ", totalCount=" + totalCount
				+ ", totalNum=" + totalNum + "]";
	}

}
