package com.food.exp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.food.exp.dto.FileDTO;
import com.food.exp.service.FileService;

@Controller
public class UploadController {

	@Autowired
	FileService service;
	
    @PostMapping("/upload")  
    public ResponseEntity<Object> uploadFilesAjax(  
        @RequestPart(value = "files") MultipartFile[] multipartFiles) { 
    	System.out.println("upload들어옴");
        List<FileDTO> uploadedFiles = service.uploadFiles(Arrays.asList(multipartFiles));

            return ResponseEntity.ok(uploadedFiles);
    }  
}
