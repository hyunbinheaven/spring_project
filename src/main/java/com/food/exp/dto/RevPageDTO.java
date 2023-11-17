package com.food.exp.dto;

import java.util.List;

public class RevPageDTO {

	int curPage;
	int perPage = 10;
	int totalNum;
	int totalCount;
	List<RevDTO> revlist;
	
	public RevPageDTO() {}

	public RevPageDTO(int curPage, int perPage, int totalNum, int totalCount, List<RevDTO> revlist) {
		super();
		this.curPage = curPage;
		this.perPage = perPage;
		this.totalNum = totalNum;
		this.totalCount = totalCount;
		this.revlist = revlist;
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

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<RevDTO> getRevlist() {
		return revlist;
	}

	public void setRevlist(List<RevDTO> revlist) {
		this.revlist = revlist;
	}

	@Override
	public String toString() {
		return "RevPageDTO [curPage=" + curPage + ", perPage=" + perPage + ", totalNum=" + totalNum + ", totalCount="
				+ totalCount + ", revlist=" + revlist + "]";
	}

	
}
