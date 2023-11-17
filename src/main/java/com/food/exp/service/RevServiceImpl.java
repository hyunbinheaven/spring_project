package com.food.exp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.exp.dao.RevDAO;
import com.food.exp.dao.UploadDAO;
import com.food.exp.dto.FileDTO;
import com.food.exp.dto.RevDTO;
import com.food.exp.dto.RevPageDTO;
import com.food.exp.dto.RevTempDTO;

@Service
public class RevServiceImpl implements RevService {
	
	@Autowired
	RevDAO dao;
	
	@Autowired
	UploadDAO udao;

	@Transactional
	@Override
	public void addReview(RevDTO review) {
		dao.addReview(review);
		int rev_no = udao.currentRno();
		if(review.getAttachList() == null || review.getAttachList().size()<=0) {
			return;
		}
		
		List<FileDTO> attachList = review.getAttachList();
		System.out.println("revno is "+rev_no);

		for (int i = 0; i < attachList.size(); i++) {
			System.out.println("in!!!!");
			FileDTO attach = attachList.get(i);
		    attach.setRev_no(rev_no);
		    udao.insert(attach);
		}

	}

	@Override
	public RevPageDTO getReviewById(int curPage, String user_email) {
		 return dao.getReviewById(curPage, user_email);
	}
	@Override
	public int totalCount(String user_email) {
		return dao.totalCount(user_email);
	}

	@Override
	public int updateReview(RevDTO review) {
		return dao.updateReview(review);
	}

	@Override
	public void deleteReview(int rev_no) {
		dao.deleteReview(rev_no);
	}

	@Override
	public RevDTO selectByRev_No(int rev_no) {
		return dao.selectByRev_No(rev_no);
	}

	@Override
	public List<RevDTO> revByRst(String rst_id) {
		return dao.revByRst(rst_id);
	}
	
	@Override
	public List<RevTempDTO> getreviewByRst(String rst_id){
		return dao.getreviewByRst(rst_id);
	}

	@Override
	public void delSelect(List<String> rev_no_list) {
		dao.delSelect(rev_no_list);
	}

	@Override
	public List<RevDTO> searchRev(RevDTO revDTO) {
		return dao.searchRev(revDTO);
	}
	
	public int updateAvgStar(String rst_id) {
		return dao.updateAvgStar(rst_id);
	}
	
}
