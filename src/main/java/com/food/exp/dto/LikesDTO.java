package com.food.exp.dto;

import org.apache.ibatis.type.Alias;

@Alias("LikesDTO")
public class LikesDTO {
	String user_email;
	String rst_id;
	String rst_name;
	String rst_addr1;
	String rst_cate;
	String rst_x;
	String rst_y;
	int likes_total;
	int rev_count;
	double rev_star_avg;
	int isLiked;
	
	public LikesDTO() {}

	public LikesDTO(String user_email, String rst_id, String rst_name, String rst_addr1, String rst_cate, String rst_x,
			String rst_y, int likes_total, int rev_count, double rev_star_avg, int isLiked) {
		super();
		this.user_email = user_email;
		this.rst_id = rst_id;
		this.rst_name = rst_name;
		this.rst_addr1 = rst_addr1;
		this.rst_cate = rst_cate;
		this.rst_x = rst_x;
		this.rst_y = rst_y;
		this.likes_total = likes_total;
		this.rev_count = rev_count;
		this.rev_star_avg = rev_star_avg;
		this.isLiked = isLiked;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
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

	public int getLikes_total() {
		return likes_total;
	}

	public void setLikes_total(int likes_total) {
		this.likes_total = likes_total;
	}

	public int getRev_count() {
		return rev_count;
	}

	public void setRev_count(int rev_count) {
		this.rev_count = rev_count;
	}

	public double getRev_star_avg() {
		return rev_star_avg;
	}

	public void setRev_star_avg(double rev_star_avg) {
		this.rev_star_avg = rev_star_avg;
	}

	public int getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(int isLiked) {
		this.isLiked = isLiked;
	}

	@Override
	public String toString() {
		return "LikesDTO [user_email=" + user_email + ", rst_id=" + rst_id + ", rst_name=" + rst_name + ", rst_addr1="
				+ rst_addr1 + ", rst_cate=" + rst_cate + ", rst_x=" + rst_x + ", rst_y=" + rst_y + ", likes_total="
				+ likes_total + ", rev_count=" + rev_count + ", rev_star_avg=" + rev_star_avg + ", isLiked=" + isLiked
				+ "]";
	}

}