package com.food.exp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.exp.dao.RstDAO;
import com.food.exp.dto.RstDTO;

@Service
public class RstServiceImpl implements RstService {
    
	@Autowired
    RstDAO rstDAO;
    
	@Override
	public List<RstDTO> getAllRestaurants() {
		return rstDAO.getAllRestaurants();
	}
	@Override
	public void insertOrUpdateRestaurant(RstDTO restaurant) {
		rstDAO.insertOrUpdateRestaurant(restaurant);
	}
	@Override
	public RstDTO selectRestaurantById(String rst_id) {
		
		return rstDAO.selectRestaurantById(rst_id);
	}
	@Override
	public void updateAvgStar(RstDTO restaurant) {
		rstDAO.updateAvgStar(restaurant);
	}
	

    // 다른 비즈니스 로직 메서드의 구현
}
