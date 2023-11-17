package com.food.exp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.exp.dao.MemberDAO;
import com.food.exp.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDAO dao;
	
	@Override
	public int join(MemberDTO dto) {
		return dao.join(dto);
	}

	@Override
	public String idCheck(String user_email) {
		return dao.idCheck(user_email);
	}

	@Override
	public String nameCheck(String nickname) {
		return dao.nameCheck(nickname);
	}

	@Override
	public MemberDTO login(MemberDTO dto) {
		return dao.login(dto);
	}

	@Override
	public String findId(MemberDTO dto) {
		return dao.findId(dto);
	}

	@Override
	public String findPw(MemberDTO dto) {
		return dao.findPw(dto);
	}

	@Override
	public int updatePw(MemberDTO dto) {
		return dao.updatePw(dto);
	}

	@Override
	public String getNickByEmail(String user_email) {
		return dao.getNickByEmail(user_email);
	}


}
