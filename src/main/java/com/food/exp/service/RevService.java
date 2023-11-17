package com.food.exp.service;

import java.util.List;

import com.food.exp.dto.RevDTO;
import com.food.exp.dto.RevPageDTO;
import com.food.exp.dto.RevTempDTO;

public interface RevService {
	public void addReview(RevDTO review);
    public RevPageDTO getReviewById(int curPage, String user_email);
    public int updateReview(RevDTO review);
    public void deleteReview(int rev_no);
	public RevDTO selectByRev_No(int rev_no);
	public List<RevDTO> revByRst(String rst_id);
	public List<RevTempDTO> getreviewByRst(String rst_id);
	public int totalCount(String user_email);
	public void delSelect(List<String> rev_no);
	public List<RevDTO> searchRev(RevDTO revDTO);
	public int updateAvgStar(String rst_id);
}
