package com.food.exp.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.food.exp.dto.FileDTO;

@Repository("UploadDAO")
public class UploadDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	// DB에 파일 업로드
	public int insert(FileDTO dto) {
		return session.insert("UploadMapper.insert", dto);
	}
	// 파일 삭제
	public int delete(String delFile) {
		return session.insert("UploadMapper.delete", delFile);
	}
	// rev_no로 파일 불러오기
	public List<FileDTO> getFiles(int rev_no) {
		return session.selectList("UploadMapper.getFiles", rev_no);
	}
	//현재 시퀀스 값 가져오기
	public int currentRno() {
		return session.selectOne("UploadMapper.currentRno");
	}
	// 식당테이블과 연결해 이미지 가져오기
	public List<FileDTO> getFilesRst(String rst_id) {
		return session.selectList("UploadMapper.getFilesRst", rst_id);
	}
	//rst_id로 식당 사진에 쓰일 사진 불러오기
	public List<FileDTO> thumbnail(String rst_id) {
		return session.selectList("UploadMapper.thumbnail",rst_id);
	}
}
