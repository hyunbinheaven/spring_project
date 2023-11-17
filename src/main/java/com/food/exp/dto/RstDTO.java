package com.food.exp.dto;

import org.apache.ibatis.type.Alias;

@Alias("RstDTO")
public class RstDTO {
    String rst_id;
    String rst_name;
    String rst_addr1;
    String rst_addr2;
    String rst_phone;
    String rst_cate;
    Double rev_star_avg;
    String rst_x;
    String rst_y;

    public RstDTO() {}

	public RstDTO(String rst_id, String rst_name, String rst_addr1, String rst_addr2, String rst_phone, String rst_cate,
			Double rev_star_avg, String rst_x, String rst_y) {
		super();
		this.rst_id = rst_id;
		this.rst_name = rst_name;
		this.rst_addr1 = rst_addr1;
		this.rst_addr2 = rst_addr2;
		this.rst_phone = rst_phone;
		this.rst_cate = rst_cate;
		this.rev_star_avg = rev_star_avg;
		this.rst_x = rst_x;
		this.rst_y = rst_y;
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

	public String getRst_addr2() {
		return rst_addr2;
	}

	public void setRst_addr2(String rst_addr2) {
		this.rst_addr2 = rst_addr2;
	}

	public String getRst_phone() {
		return rst_phone;
	}

	public void setRst_phone(String rst_phone) {
		this.rst_phone = rst_phone;
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

	public String getRst_x() {
		return rst_x;
	}

	public void setRst_x(String rst_x) {
		this.rst_x = rst_x;
	}

	public String getRst_y() {
		return rst_y;
	}

	public void setRst_y(String rst_y) {
		this.rst_y = rst_y;
	}

	@Override
	public String toString() {
		return "RstDTO [rst_id=" + rst_id + ", rst_name=" + rst_name + ", rst_addr1=" + rst_addr1 + ", rst_addr2="
				+ rst_addr2 + ", rst_phone=" + rst_phone + ", rst_cate=" + rst_cate + ", rev_star_avg=" + rev_star_avg
				+ ", rst_x=" + rst_x + ", rst_y=" + rst_y + "]";
	}
    
}
