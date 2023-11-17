package com.food.exp.dto;

import java.util.List;

public class RstTempDTO {
    private String id;
    private String place_name;
    private String category_name;
    private String category_group_code;
    private String category_group_name;
    private String phone;
    private String address_name;
    private String road_address_name;
    private String x;
    private String y;
    private String place_url;
    private String distance;
    
    List<FileDTO> attachList;

    public RstTempDTO(String id, String place_name, String category_name, String category_group_code,
			String category_group_name, String phone, String address_name, String road_address_name, String x, String y,
			String place_url, String distance, List<FileDTO> attachList) {
		super();
		this.id = id;
		this.place_name = place_name;
		this.category_name = category_name;
		this.category_group_code = category_group_code;
		this.category_group_name = category_group_name;
		this.phone = phone;
		this.address_name = address_name;
		this.road_address_name = road_address_name;
		this.x = x;
		this.y = y;
		this.place_url = place_url;
		this.distance = distance;
		this.attachList = attachList;
	}

	public List<FileDTO> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<FileDTO> attachList) {
		this.attachList = attachList;
	}

	// 생성자
    public RstTempDTO() {
    }

    // Getter 및 Setter 메서드
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }
    
    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_group_code() {
        return category_group_code;
    }

    public void setCategory_group_code(String category_group_code) {
        this.category_group_code = category_group_code;
    }

    public String getCategory_group_name() {
        return category_group_name;
    }

    public void setCategory_group_name(String category_group_name) {
        this.category_group_name = category_group_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getRoad_address_name() {
        return road_address_name;
    }

    public void setRoad_address_name(String road_address_name) {
        this.road_address_name = road_address_name;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getPlace_url() {
        return place_url;
    }

    public void setPlace_url(String place_url) {
        this.place_url = place_url;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
    
    public String getAll() {
        return "id: " + id + ", " +
               "place_name: " + place_name + ", " +
               "category_name: " + category_name + ", " +
               "category_group_code: " + category_group_code + ", " +
               "category_group_name: " + category_group_name + ", " +
               "phone: " + phone + ", " +
               "address_name: " + address_name + ", " +
               "road_address_name: " + road_address_name + ", " +
               "x: " + x + ", " +
               "y: " + y + ", " +
               "place_url: " + place_url + ", " +
               "distance: " + distance + 
               "attachList: " + attachList;
    }
    
}
