package com.food.exp.service;

import java.util.List;

import com.food.exp.dto.LikesDTO;
import com.food.exp.dto.MemberDTO;
import com.food.exp.dto.PageDTO;
import com.food.exp.dto.RevDTO;

public interface MypageService {

	public MemberDTO getInfo(String user_email);
	public int changeInfo(MemberDTO dto);
	public int delMember(String user_email);
	public PageDTO getLikes(int curPage, String user_email);
	public int delLikes(LikesDTO dto);
	public int addLikes(LikesDTO dto);
	public int totalCount(String user_email);
	public int isLiked(LikesDTO dto);
	public List<LikesDTO> getLikesTotal(String rst_id);
	public List<LikesDTO> getLikesMap(String user_email);
	public List<RevDTO> getRev(String user_email);
}
