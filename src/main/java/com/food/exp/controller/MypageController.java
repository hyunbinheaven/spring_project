package com.food.exp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.food.exp.dto.FileDTO;
import com.food.exp.dto.LikesDTO;
import com.food.exp.dto.MemberDTO;
import com.food.exp.dto.PageDTO;
import com.food.exp.dto.RevDTO;
import com.food.exp.service.MypageService;
import com.food.exp.service.UploadService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	@Value("${com.food.exp.mapapikey}")
	private String mapApiKey;

	@Autowired
	MypageService service;

	@Autowired
	UploadService uploadService;

	// 메인 페이지
	@GetMapping("/main")
	public String mypage(HttpSession session, Model m, PageDTO pageDTO) {
		// 회원 정보
		String user_email = (String) session.getAttribute("login");
		MemberDTO memberDTO = service.getInfo(user_email);
		m.addAttribute("MemberDTO", memberDTO);
		// 즐겨찾기 정보
		int curPage = 0;
		pageDTO = service.getLikes(curPage, user_email);
		m.addAttribute("pageDTO", pageDTO);
		// 리뷰 정보
		List<RevDTO> revDTO = service.getRev(user_email);
		m.addAttribute("RevDTO", revDTO);
		
		// 성공적인 업데이트를 나타내는 플래시 속성을 확인합니다.
        if (m.containsAttribute("updateSuccess")) {
            // 알림에 표시할 메시지를 추가합니다.
            m.addAttribute("updateMessage", "회원정보 수정이 완료되었습니다.");
        }

		return "mypage/mypage";
	}

	// 나의 즐겨찾기 페이지
	@GetMapping("/myLikes")
	public String myLikes(HttpSession session, Model m, PageDTO pageDTO) {
		// 즐겨찾기 정보
		String user_email = (String) session.getAttribute("login");
		int curPage = pageDTO.getCurPage();
		pageDTO = service.getLikes(curPage, user_email);
		int totalNum = pageDTO.getTotalCount() / pageDTO.getPerPage();
		if (pageDTO.getTotalCount() % pageDTO.getPerPage() != 0) {
			totalNum++;
		}
		pageDTO.setTotalNum(totalNum);
		m.addAttribute("pageDTO", pageDTO);
		// 이미지 가져오기
		String rst_id = (String) session.getAttribute("rst_id");
		List<FileDTO> attachList = uploadService.getFilesRst(rst_id);
		m.addAttribute("attachList", attachList);
		return "mypage/myLikes";
	}

	// 즐겨찾기 삭제
	@PostMapping("/delLikes")
	public String delLikes(HttpSession session, LikesDTO dto) {
		String user_email = (String) session.getAttribute("login");
		dto.setUser_email(user_email);
		int num = service.delLikes(dto);
		return "redirect:/mypage/myLikes";
	}

	// 나의 맛집 지도
	@GetMapping("/myLikesMap")
	public String rst_main(HttpSession session, Model m) {
		// 즐겨찾기 정보
		String user_email = (String) session.getAttribute("login");
		List<LikesDTO> likesDTO = service.getLikesMap(user_email);
		m.addAttribute("likesDTO", likesDTO);
		// api키 가지고오기
		m.addAttribute("apiKey", mapApiKey);
		return "/mypage/myLikesMap";
	}

	// 회원정보 수정 페이지로 이동
	@GetMapping("/changeInfo")
	public String changeInfo(HttpSession session, Model m) {
		String user_email = (String) session.getAttribute("login");
		MemberDTO dto = service.getInfo(user_email);
		m.addAttribute("MemberDTO", dto);
		return "member/changeInfo";
	}

	// 회원정보 수정
	@PostMapping("/updateInfo")
	public String updateInfo(MemberDTO dto, HttpSession session, RedirectAttributes redirectAttributes) {
		int num = service.changeInfo(dto);
		String nickname = dto.getNickname();
		session.setAttribute("nickname", nickname);
		
		System.out.println("수정");
		
	    redirectAttributes.addFlashAttribute("updateSuccess", true);

		return "redirect:main";
	}

	// 회원 탈퇴
	@PostMapping("/delMember")
	public String delMember(HttpSession session, RedirectAttributes redirectAttributes) {
		String user_email = (String) session.getAttribute("login");
		int num = service.delMember(user_email);
		session.invalidate();
	    redirectAttributes.addFlashAttribute("delSuccess", true);
		return "redirect:/main";
	}
}
