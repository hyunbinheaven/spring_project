package com.food.exp.service;

import com.food.exp.dto.MemberDTO;

public interface MemberService {

	public int join(MemberDTO dto);
	public String idCheck(String user_email);
	public String nameCheck(String nickname);
	public MemberDTO login(MemberDTO dto);
	public String findId(MemberDTO dto);
	public String findPw(MemberDTO dto);
	public int updatePw(MemberDTO dto);
	public String getNickByEmail(String user_email);
}
