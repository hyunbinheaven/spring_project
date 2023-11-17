package com.food.exp.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.food.exp.dao.MemberDAO;
import com.food.exp.dto.MemberDTO;

@Service
public class FindServiceImpl implements FindService{

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	MemberDAO dao;
    
	//임시 비밀번호 발급
	public String tmpPw(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        //동일한 시드값을 주면 같은 값을 주는 Random을 대신하여 안전한 SecureRandom 사용
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIdx = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIdx);
            sb.append(randomChar);
        }
        return sb.toString();
    }
	
	//임시 비밀번호 발급 이메일 보내기
	public void sendMail(String user_email) {
		String temporaryPw = tmpPw(8);
		
		SimpleMailMessage simpleMailMessage = new  SimpleMailMessage();
		simpleMailMessage.setTo(user_email);
		simpleMailMessage.setSubject("먹스플로어 임시 비밀번호 발급");
		
		StringBuffer sb = new StringBuffer();
		sb.append("회원님의 임시 비밀번호는");
		sb.append(System.lineSeparator());
		
		sb.append(temporaryPw); // 임시 비밀번호 추가
        sb.append(System.lineSeparator());
        sb.append("입니다.");
        
		simpleMailMessage.setText(sb.toString());
		
		MemberDTO dto = new MemberDTO();
		dto.setUser_email(user_email);
		dto.setPw(temporaryPw);
		
		//임시 비밀번호로 변경
		int num = dao.updatePw(dto);
		
		new Thread(new Runnable() {
			public void run() {
				mailSender.send(simpleMailMessage);
			}
		}).start();
	}
}
