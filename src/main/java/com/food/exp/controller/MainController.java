package com.food.exp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.exp.dto.FileDTO;
import com.food.exp.dto.MainDTO;
import com.food.exp.service.MainService;
import com.food.exp.service.UploadService;

@Controller
public class MainController {

	@Autowired
	ServletContext application;
	
	@Autowired
	private MainService service;
	
	@Autowired
	UploadService UploadService;
	
	
	@GetMapping("/main")
	public String main(Model model, HttpSession session) {
		System.out.println("MainController 실행");
			
		List<MainDTO> top10Rst = service.top10Rst();
		model.addAttribute("top10Rst", top10Rst);
		
		// 리뷰 이미지 가져오기
	    String rst_id = (String) session.getAttribute("rst_id");
	    List<FileDTO> attachList = UploadService.getFilesRst(rst_id);
	    model.addAttribute("attachList", attachList);
		
		
		// 성공적인 업데이트를 나타내는 플래시 속성을 확인합니다.
        if (model.containsAttribute("delSuccess")) {
            // 알림에 표시할 메시지를 추가합니다.
        	System.out.println("삭제");
            model.addAttribute("delMessage", "회원탈퇴가 완료되었습니다.");
        }
		return "main";
	}

	@GetMapping("/template")
	public String template_thymeleaf(){
		return "sample_template/template_thymeleaf";
	}
}