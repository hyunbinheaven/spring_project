package com.food.exp.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.food.exp.dto.PageDTO;
import com.food.exp.dto.RevDTO;
import com.food.exp.dto.RevPageDTO;
import com.food.exp.dto.RevTempDTO;

@Repository("RevDAO")
public class RevDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	// 리뷰 추가
	public void addReview(RevDTO review) {
        session.insert("RevMapper.addReview", review);
    }

	// id에 해당하는 리뷰 가져오기
    public RevPageDTO getReviewById(int curPage, String user_email) {
    	RevPageDTO revPageDTO = new RevPageDTO();
	    int offset = (curPage - 1) * revPageDTO.getPerPage();
	    int limit = revPageDTO.getPerPage();

	    List<RevDTO> revlist = session.selectList("RevMapper.getReviewById", user_email, new RowBounds(offset, limit));

	    revPageDTO.setRevlist(revlist);
	    revPageDTO.setCurPage(curPage);
	    
        return revPageDTO;
    }
    
    // 리뷰 페이지
 	public int totalCount(String user_email) {
 		return session.selectOne("RevMapper.totalCount", user_email);
 	}
 	
    // 리뷰 자세히 보기
    public RevDTO selectByRev_No(int no) {
		return session.selectOne("RevMapper.selectByRev_No", no);
	}
    
    // 리뷰 수정
    public int updateReview(RevDTO review) {
        return session.update("RevMapper.updateReview", review);
    }

    // 리뷰 삭제
    public void deleteReview(int rev_no) {
        session.delete("RevMapper.deleteReview", rev_no);
    }
    
    // 식당별 리뷰
    public List<RevDTO> revByRst(String rst_id) {
        return session.selectList("RevMapper.revByRst", rst_id);
    }

    public List<RevTempDTO> getreviewByRst(String rst_id){
    	return session.selectList("RevMapper.getreviewByRst",rst_id);
    }
    
	public void delSelect(List<String> rev_no) {
		session.delete("RevMapper.delSelect", rev_no);
	}
	
	// 리뷰 검색
	public List<RevDTO> searchRev(RevDTO revDTO) {
	    return session.selectList("RevMapper.searchRev", revDTO);
	}

	    //리뷰 등록, 수정, 삭제 시 식당 평균 별점 업데이트
	public int updateAvgStar(String rst_id) {
		return session.update("RevMapper.updateAvgStar",rst_id);
	}
}
