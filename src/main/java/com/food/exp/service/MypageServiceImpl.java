package com.food.exp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.exp.dao.MypageDAO;
import com.food.exp.dto.LikesDTO;
import com.food.exp.dto.MemberDTO;
import com.food.exp.dto.PageDTO;
import com.food.exp.dto.RevDTO;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageDAO dao;

	@Override
	public MemberDTO getInfo(String user_email) {
		return dao.getInfo(user_email);
	}

	@Override
	public int changeInfo(MemberDTO dto) {
		return dao.changeInfo(dto);
	}

	@Override
	public int delMember(String user_email) {
		return dao.delMember(user_email);
	}

	@Override
	public int delLikes(LikesDTO dto) {
		return dao.delLikes(dto);
	}
	
	@Override
	public int addLikes(LikesDTO dto) {
		return dao.addLikes(dto);
	}

	@Override
	public int totalCount(String user_email) {
		return dao.totalCount(user_email);
	}

	@Override
	public PageDTO getLikes(int curPage, String user_email) {
		return dao.getLikes(curPage, user_email);
	}

	@Override
	public int isLiked(LikesDTO dto) {
		return dao.isLiked(dto);
	}
	
	@Override
	public List<LikesDTO> getLikesTotal(String rst_id) {
		return dao.getLikesTotal(rst_id);
	}

	@Override
	public List<LikesDTO> getLikesMap(String user_email) {
		return dao.getLikesMap(user_email);
	}
	
	@Override
	public List<RevDTO> getRev(String user_email) {
		return dao.getRev(user_email);
	}

}
