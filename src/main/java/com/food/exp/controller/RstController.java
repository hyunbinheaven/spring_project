package com.food.exp.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.food.exp.dto.FileDTO;
import com.food.exp.dto.LikesDTO;
import com.food.exp.dto.RevDTO;
import com.food.exp.dto.RevTempDTO;
import com.food.exp.dto.RstDTO;
import com.food.exp.dto.RstTempDTO;
import com.food.exp.service.FileService;
import com.food.exp.service.MypageService;
import com.food.exp.service.RevService;
import com.food.exp.service.RstService;
import com.food.exp.service.UploadService;

@Controller
public class RstController {    
	@Value("${com.food.exp.mapapikey}")
	private String mapApiKey;

	@Autowired
	ServletContext application;

	@Autowired
	RstService rstService;
	
	@Autowired
	RevService revService;
	
	@Autowired
	MypageService mypageService;

	@Autowired
	UploadService uService;

	@Autowired
	FileService fService;

	@GetMapping("/maintest")
	public String maintest(){
		
		return "/maintest";
	}
	
	@GetMapping("/region_data")
	@ResponseBody
	public String getRegion_data(){
		 try {
	            // region_data.js 파일을 읽어와서 반환합니다.
	            Path path = Paths.get("src/main/resources/static/js/region_data.js");
	            byte[] bytes = Files.readAllBytes(path);
	            return new String(bytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "console.error('Error loading dynamic JavaScript.');";
	        }
	}

	
	@GetMapping("/rst")
	public String rst_main(HttpSession session,Model model) {

		// 이 위치에서 현재위치로 검색할지, 다른위치로 검색할지 구분
		model.addAttribute("inputLoadSelector","myLocation");
		// api키 가지고오기
		model.addAttribute("apiKey", mapApiKey);
		model.addAttribute("searchinput", "");
		System.out.println("RstController 실행-GET 현재위치, 다른위치 검색할 함수 만들어야함");
		return "/rst/rst";
	}

	@PostMapping("/rst")
	public String rst_post(@RequestParam("query") String rst_query, Model model,HttpSession session) {
		// 이 위치에서 현재위치로 검색할지, 다른위치로 검색할지 구분
		model.addAttribute("inputLoadSelector","nationwide");
		
		model.addAttribute("apiKey", mapApiKey);

		if (rst_query != "") {
			model.addAttribute("searchinput", rst_query);
			session.setAttribute("searchinput", rst_query);
		} else {
			model.addAttribute("searchinput", "");
			session.setAttribute("searchinput", "");
		}
		System.out.println("RstController 실행-POST 키워드로 검색, 키워드는"+rst_query);
		return "/rst/rst";
	}
	
	@PostMapping("/reg_post")
	public String reg_post(@RequestParam("h_area1")String h_area1,@RequestParam("h_area2")String h_area2,@RequestParam("h_area3")String h_area3,Model model) {
		// 이 위치에서 현재위치로 검색할지, 다른위치로 검색할지 구분
		model.addAttribute("inputLoadSelector","nationwide");
		model.addAttribute("apiKey", mapApiKey);
		
		model.addAttribute("h_area1",h_area1);
		model.addAttribute("h_area2",h_area2);
		model.addAttribute("h_area3",h_area3);
		
		return "/rst/rst";
	}

	@PostMapping("/htmltodb")
	public String htmltodb(@RequestBody List<RstTempDTO> rsttempList) {
		List<RstDTO> rstDTOList = new ArrayList<>();
			for (RstTempDTO rstTempDTO : rsttempList) {
			// 01. rstDTO에 rsttempList값을 재전달
				
				
			// 01-1. null값 에러처리를 위한 공백처리
			RstDTO rstDTO = new RstDTO();
			rstDTO.setRst_id((rstTempDTO.getId() != null) ? rstTempDTO.getId() : " ");
			rstDTO.setRst_name((rstTempDTO.getPlace_name() != null) ? rstTempDTO.getPlace_name() : " ");
			rstDTO.setRst_cate((rstTempDTO.getCategory_name() != null) ? rstTempDTO.getCategory_name() : " ");
			rstDTO.setRst_phone((rstTempDTO.getPhone() != null) ? rstTempDTO.getPhone() : " ");
			rstDTO.setRst_addr1((rstTempDTO.getRoad_address_name() != null) ? rstTempDTO.getRoad_address_name() : " ");
			rstDTO.setRst_addr2((rstTempDTO.getAddress_name() != null) ? rstTempDTO.getAddress_name() : " ");
			rstDTO.setRst_x((rstTempDTO.getX() != null) ? rstTempDTO.getX() : " ");
			rstDTO.setRst_y((rstTempDTO.getY() != null) ? rstTempDTO.getY() : " ");
			// rstDTO.setPlaceUrl(rstTempDTO.getPlace_url());
			// rstDTO.setDistance(rstTempDTO.getDistance());
			// rstDTO.setCategoryGroupCode(rstTempDTO.getCategory_group_code());
			// rstDTO.setCategoryGroupName(rstTempDTO.getCategory_group_name());
			
			
			//01-2. rstDTOList에 rstDTO값 넣기 
			rstDTOList.add(rstDTO);
		}
		for (RstDTO dto : rstDTOList) {
			//02. rstService, rstServiceImpl, rstDAO, rstMapper
			//    위치에 정의된 서비스 실행하여 데이터 삽입 or 수정 
			rstService.insertOrUpdateRestaurant(dto);
		}
		return "/rst/rst";
	}
	
	//00. 식당정보(rst_id) > 세부정보(rst_id)를 GET방식으로 가지고옵니다.
	@GetMapping("/rst/rst_detail")
	public String rst_detail(@RequestParam("rst_id") String rst_id,Model model, HttpSession session, LikesDTO dto) {
		
		//01. GET으로 받은 rst_id로 DB에서 가져오기
		RstDTO rstDTO=rstService.selectRestaurantById(rst_id);
//		List<RevDTO> revDTOList = revService.getreviewByRst(rst_id);
		List<RevTempDTO> revTempDTOList = revService.getreviewByRst(rst_id);

		//각 리뷰의 파일 불러오기
		for (RevTempDTO revTempDTO : revTempDTOList) {
		    int rev_no = revTempDTO.getRev_no();
		    List<FileDTO> attachList = uService.getFiles(rev_no);
		    if(attachList != null && attachList.size()>0) {
		    	System.out.println("사진 있는 리뷰");
		    	revTempDTO.setAttachList(attachList);
		    }
		}
		System.out.println("-----"+rst_id);
		
		//식당 썸네일 사진 가져오기 (최대 7개)
		List<FileDTO> thumbnails = uService.thumbnail(rst_id);
		System.out.println("썸네일 "+thumbnails.toString());
		if(thumbnails != null) {
			model.addAttribute("thumbnails", thumbnails);
		}
		
		//02. DB에서 가져온 데이터 html로 쏴주기
        model.addAttribute("rst_addr1", rstDTO.getRst_addr1());
        model.addAttribute("rst_addr2", rstDTO.getRst_addr2());
        model.addAttribute("rst_cate", rstDTO.getRst_cate());
        model.addAttribute("rst_id", rstDTO.getRst_id());
        model.addAttribute("rst_name", rstDTO.getRst_name());
        model.addAttribute("rst_phone", rstDTO.getRst_phone());
        model.addAttribute("revTempDTOList",revTempDTOList);
        
        
        //03. 리뷰평균, 리뷰갯수를 count하기위한 작업
        double rev_star_avg=0;
        int rev_count=0;
        double rev_star_hop=0;
        
        rev_count=revTempDTOList.size();
        for(RevTempDTO tmp:revTempDTOList) {
        	rev_star_hop += Double.valueOf(tmp.getRev_star());
        }
        rev_star_avg = Math.floor(rev_star_hop / rev_count * 10) / 10.0;

        
        if (Double.isNaN(rev_star_avg)) {
        	rev_star_avg = 0.0; // null 또는 NaN 값을 0으로 처리
        }
        
        
        
        //04. Controller에서 계산한 리뷰평균, 리뷰갯수를 html로 쏴주기
        model.addAttribute("rev_all_star_avg",rev_star_avg);
        model.addAttribute("rev_all_count",rev_count);
  
        //05. 리뷰평균 DB로 Update하기
        //필요한정보: rev_star_avg, rst_id를 담은 rstDTO
        //넣는 위치: restaurant 테이블
        rstDTO.setRev_star_avg(rev_star_avg);
        rstService.updateAvgStar(rstDTO);
        
        // 즐겨찾기 개수
		List<LikesDTO> likesTotal = mypageService.getLikesTotal(rst_id);
		model.addAttribute("likesTotal", likesTotal);
		
		// 즐겨찾기 상태 가져오기
		String user_email = (String) session.getAttribute("login");
		model.addAttribute("user_email", user_email);
		if (user_email != null) {
			dto.setUser_email(user_email);
			int isLiked = mypageService.isLiked(dto);
			model.addAttribute("rst_id",rst_id);
			model.addAttribute("isLiked", isLiked);
		}
		
		return "/rst/rst_detail";
	}

	// 리뷰 작성 페이지
	@GetMapping("/rst/revwrite")
	public String addReview(@RequestParam("rst_id") String rst_id, Model model) {
		RstDTO rstDTO = rstService.selectRestaurantById(rst_id);
		model.addAttribute("rst_id",rst_id);
	    model.addAttribute("rst_name", rstDTO.getRst_name());
		return "/rst/rev_write";
	}
	
	// 글쓰기
	@RequestMapping(value = "/rst/write", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String write(RevDTO revDTO, HttpSession session) {
		String user_email = (String) session.getAttribute("login");
	    revDTO.setUser_email(user_email);
	    System.out.println("리뷰 dto는  "+revDTO.toString());
	    //파일 스토리지에 업로드 후 리뷰 등록
//	    if(multipartFiles!= null) {
//	    	System.out.println("파일 있어요");
//	        List<FileDTO> uploadedFiles = fService.uploadFiles(Arrays.asList(multipartFiles));
//	        System.out.println("fffff"+uploadedFiles.toString());
//	        revDTO.setAttachList(uploadedFiles);
//	    }
        
		revService.addReview(revDTO);
		
		//리뷰 별점 업데이트 하기
		String rst_id = revDTO.getRst_id();
		int num = revService.updateAvgStar(rst_id);
		
		return "redirect:/rst/rst_detail?rst_id=" + revDTO.getRst_id();
	}
	
	
	// 즐겨찾기 삭제
	@PostMapping("/rst/delLikes")
	public String delLikes(HttpSession session, LikesDTO dto, Model model) {
		String user_email = (String) session.getAttribute("login");
		dto.setUser_email(user_email);
		mypageService.delLikes(dto);
		List<LikesDTO> updatedLikesTotal = mypageService.getLikesTotal(dto.getRst_id());
	    model.addAttribute("likesTotal", updatedLikesTotal);
        return "redirect:/rst/rst_detail?rst_id=" + dto.getRst_id();
	}

	// 즐겨찾기 추가
	@PostMapping(value = "/rst/addLikes")
	public String addLikes(HttpSession session, LikesDTO dto) {
		String user_email = (String) session.getAttribute("login");
		dto.setUser_email(user_email);
		mypageService.addLikes(dto);
		List<LikesDTO> likesTotal = mypageService.getLikesTotal(dto.getRst_id());
		return "redirect:/rst/rst_detail?rst_id=" + dto.getRst_id();
	}
	
}
