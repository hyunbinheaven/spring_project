package com.food.exp.controller;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.food.exp.dto.FileDTO;
import com.food.exp.dto.RevDTO;
import com.food.exp.dto.RevPageDTO;
import com.food.exp.service.FileService;
import com.food.exp.service.RevService;
import com.food.exp.service.UploadService;

@Controller
@RequestMapping("/mypage")
public class RevController {

	@Autowired
	RevService service;

	@Autowired
	UploadService uService;

	@Autowired
	FileService fService;
	
	// 내 리뷰 목록
	@GetMapping("/rev")
	public String rev(Model model, HttpSession session, RevPageDTO revPageDTO) {
		String user_email = (String) session.getAttribute("login");
		int curPage = revPageDTO.getCurPage();
		revPageDTO = service.getReviewById(curPage, user_email);
		
		int totalCount = service.totalCount(user_email); // 총 개수
	    int totalNum = totalCount / revPageDTO.getPerPage();
	    if (totalCount % revPageDTO.getPerPage() != 0) {
	        totalNum++; // 나머지가 있는 경우 1을 추가
	    }
	    revPageDTO.setTotalCount(totalCount);
	    revPageDTO.setTotalNum(totalNum);

	    System.out.println(revPageDTO.toString());
	    model.addAttribute("revPageDTO", revPageDTO);
	    return "/rev/rev";
	}
	
	// 글 자세히 보기
	@RequestMapping(value = "/revedit", method = RequestMethod.GET)
	public String retrieve(@RequestParam("rev_no") int rev_no, Model model) {
	    RevDTO revDTO = service.selectByRev_No(rev_no);
	    
	    //파일 불러오기
	    List<FileDTO> attachList = uService.getFiles(rev_no);
	    if(attachList != null && attachList.size()>0) {
	    	System.out.println("사진 있는 리뷰");
	    	revDTO.setAttachList(attachList);
	    }
	    System.out.println(attachList.toString());
	    
	    model.addAttribute("review", revDTO);
	    return "/rev/rev_edit";
	}
	
	// 리뷰 수정
	@PostMapping("/update")
	public String updateReview(@ModelAttribute("review") RevDTO revDTO, HttpSession session,
						@RequestParam(value = "delfile", required = false) String[] delFiles) {
		String user_email = (String) session.getAttribute("login");
		revDTO.setUser_email(user_email);
		service.updateReview(revDTO);
		System.out.println(revDTO.toString());
		
		//삭제한 파일 처리
        if (delFiles != null) {
        	System.out.println("delfile있다");
            for (String delFile : delFiles) {
            	System.out.println("del실행");
                int num = uService.delete(delFile);
                System.out.println("DB삭제끝"+num);
                fService.deleteFile(delFile);
            }
        }
		//리뷰 별점 업데이트 하기
		String rst_id = revDTO.getRst_id();
		int num = service.updateAvgStar(rst_id);
		
		return "redirect:/mypage/rev";
	}
	
	// 리뷰 삭제
	@PostMapping("/delete")
	public String deleteReview(@RequestParam("rev_no") int rev_no,@RequestParam("rst_id") String rst_id,
			@RequestParam(value = "delfile", required = false) String[] delFiles) {
	    service.deleteReview(rev_no);
	    
		//삭제할 파일 처리
        if (delFiles != null) {
        	System.out.println("삭제 사진");
            for (String delFile : delFiles) {
                fService.deleteFile(delFile);
                System.out.println("finish");
            }
        }
        
		//리뷰 별점 업데이트 하기
        System.out.println(rst_id);
		int num = service.updateAvgStar(rst_id);
        
        return "redirect:/mypage/rev";
	}
	
	// 리뷰 검색
	@PostMapping("/searchRev")  
    public ResponseEntity<Object> searchRev(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
		System.out.println(keyword);
		RevDTO revDTO = new RevDTO();
		String user_email = (String) session.getAttribute("login");
		revDTO.setUser_email(user_email);
		revDTO.setKeyword(keyword);
		System.out.println(revDTO.toString());
		List<RevDTO> result = service.searchRev(revDTO);
            return ResponseEntity.ok(result);
    }
	
//	// 선택된 리뷰 삭제
//    @PostMapping("/delSelect")
//    @ResponseBody
//    public String delSelect(@RequestParam("rev_no") int rev_no) {
//            service.delSelect(rev_no);
//        return "redirect:/mypage/rev";
//    }
	
	//게시물 선택삭제
	@PostMapping("/delSelect")  
	public String delSelect(HttpServletRequest request) {            
	    String[] rev_no = request.getParameterValues("valueArr");
	    List<String> rev_no_list = Arrays.asList(rev_no);
	    service.delSelect(rev_no_list);
	    return "redirect:/mypage/rev";
	}

}
