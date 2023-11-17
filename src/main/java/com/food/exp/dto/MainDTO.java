package com.food.exp.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("mainDTO")
public class MainDTO {

    private String rst_id;
    private String rst_name;
    private String rst_addr1;
    private String rst_cate;
    private Double rev_star_avg;
    private int rev_count;
    private List<FileDTO> photos;	// 리뷰 사진 저장하는 리스트임!!
    
    
	public MainDTO() {}


	public MainDTO(String rst_id, String rst_name, String rst_addr1, String rst_cate, Double rev_star_avg,
			int rev_count, List<FileDTO> photos) {
		this.rst_id = rst_id;
		this.rst_name = rst_name;
		this.rst_addr1 = rst_addr1;
		this.rst_cate = rst_cate;
		this.rev_star_avg = rev_star_avg;
		this.rev_count = rev_count;
		this.photos = photos;
	}

	public String getRst_id() {
		return rst_id;
	}

	public void setRst_id(String rst_id) {
		this.rst_id = rst_id;
	}

	public String getRst_name() {
		return rst_name;
	}

	public void setRst_name(String rst_name) {
		this.rst_name = rst_name;
	}

	public String getRst_addr1() {
		return rst_addr1;
	}

	public void setRst_addr1(String rst_addr1) {
		this.rst_addr1 = rst_addr1;
	}

	public String getRst_cate() {
		return rst_cate;
	}

	public void setRst_cate(String rst_cate) {
		this.rst_cate = rst_cate;
	}

	public Double getRev_star_avg() {
		return rev_star_avg;
	}

	public void setRev_star_avg(Double rev_star_avg) {
		this.rev_star_avg = rev_star_avg;
	}

	public int getRev_count() {
		return rev_count;
	}

	public void setRev_count(int rev_count) {
		this.rev_count = rev_count;
	}


	public List<FileDTO> getPhotos() {
		return photos;
	}


	public void setPhotos(List<FileDTO> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "MainDTO [rst_id=" + rst_id + ", rst_name=" + rst_name + ", rst_addr1=" + rst_addr1 + ", rst_cate="
				+ rst_cate + ", rev_star_avg=" + rev_star_avg + ", rev_count=" + rev_count + ", photos=" + photos + "]";
	}
}