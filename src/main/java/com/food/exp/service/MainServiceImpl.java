package com.food.exp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.exp.dao.MainDAO;
import com.food.exp.dto.FileDTO;
import com.food.exp.dto.MainDTO;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainDAO dao;

	public List<MainDTO> top10Rst() {
        List<MainDTO> top10Rst = dao.top10Rst();
        
        for (MainDTO restaurant : top10Rst) {
            // 리뷰 사진 정보를 MainDTO에 설정
            List<FileDTO> photos = new ArrayList<>();
            if (restaurant.getPhotos() != null && !restaurant.getPhotos().isEmpty()) {
            	FileDTO photo = restaurant.getPhotos().get(0); // 첫 번째 리뷰 사진을 가져옴
                photos.add(photo);
            }
            restaurant.setPhotos(photos);
            
            int rev_count = dao.getRevCountByRestaurantId(restaurant.getRst_id());
            restaurant.setRev_count(rev_count);
        }
        return top10Rst;
	}
}
