package com.food.exp.service;

import java.util.List;

import com.food.exp.dto.RstDTO;

public interface RstService {
    List<RstDTO> getAllRestaurants();
    void insertOrUpdateRestaurant(RstDTO restaurant);
    RstDTO selectRestaurantById(String rst_id);
    void updateAvgStar(RstDTO restaurant);
    // 다른 비즈니스 로직 메서드
}
