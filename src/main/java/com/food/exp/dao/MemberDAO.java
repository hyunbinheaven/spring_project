package com.food.exp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.food.exp.dto.MemberDTO;

@Repository("MemberDAO")
public class MemberDAO {

	@Autowired
	SqlSessionTemplate session;
	
	// 회원 가입 
	public int join(MemberDTO dto) {
		return session.insert("MemberMapper.join", dto);
	}
	// 아이디 중복 체크 
	public String idCheck(String user_email) {
		return session.selectOne("MemberMapper.idCheck", user_email);
	}
	// 닉네임 중복 체크 
	public String nameCheck(String nickname) {
		return session.selectOne("MemberMapper.nameCheck", nickname);
	}
	// 로그인 
	public MemberDTO login(MemberDTO dto) {
		return session.selectOne("MemberMapper.login",dto);
	}
	// 아이디 찾기
	public String findId(MemberDTO dto) {
		return session.selectOne("MemberMapper.findId",dto);
	}	
	// 비밀번호 찾기
	public String findPw(MemberDTO dto) {
		return session.selectOne("MemberMapper.findPw",dto);
	}	
	// 비밀번호 변경
	public int updatePw(MemberDTO dto) {
		return session.update("MemberMapper.updatePw",dto);
	}
	
	//이메일로 닉네임 가져오기
	public String getNickByEmail(String user_email) {
		return session.selectOne("MemberMapper.getNickByEmail",user_email);
	}

	
}
