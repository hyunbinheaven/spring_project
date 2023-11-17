package com.food.exp.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.crypto.dsig.keyinfo.KeyName;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.food.exp.dto.FileDTO;

@Service
//@PropertySource("classpath:application.properties")
public class FileService {

	final String endPoint = "https://kr.object.ncloudstorage.com";
	final String regionName = "kr-standard";

	// config에서 가져오는 방법 추후 구상하기!!!!!
	@Value("${spring.s3.accessKey}")
	private String accessKey;

	@Value("${spring.s3.secretKey}")
	private String secretKey;

	@Value("${spring.s3.bucket}")
	private String bucketName;

	public String getUuidFileName(String fileName) {
		String ext = fileName.substring(fileName.indexOf(".") + 1);
		return UUID.randomUUID().toString() + "." + ext;
	}

	public List<FileDTO> uploadFiles(List<MultipartFile> multipartFiles) {

//		System.out.println(accessKey);

		List<FileDTO> s3files = new ArrayList<>();

		// S3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();

		// create folder
		String folderName = "rev_img";

		for (MultipartFile multipartFile : multipartFiles) {

			String originalFileName = multipartFile.getOriginalFilename();
			String uploadFileName = getUuidFileName(originalFileName);
			String uploadFileUrl = "";

			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(multipartFile.getSize());
            objectMetadata.setContentType(multipartFile.getContentType()); 

            try (InputStream inputStream = multipartFile.getInputStream()) {  

                String keyName = folderName + "/" + uploadFileName;  

				// PutObjectRequest 객체 생성하면서 메서드를 이용한 PublicRead 권한 부여
				s3.putObject(new PutObjectRequest(bucketName, keyName, inputStream, objectMetadata)  
                        .withCannedAcl(CannedAccessControlList.PublicRead));  
				System.out.format("Object %s has been created.\n", keyName);
				
				 // S3에 업로드한 폴더 및 파일 URL  
                uploadFileUrl = "https://kr.object.ncloudstorage.com/"+ bucketName + "/" + keyName;  
  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
  
            s3files.add(  
                    FileDTO.builder()  
	                    .uploadFileName(uploadFileName)
	                    .uploadFilePath(uploadFileUrl)
	                    .build());
            System.out.println("---------"+s3files.toString());//rev_no는 나중에 리뷰 등록할 때 설정
        }  
  
        return s3files;  

	}// end uploadFiles
	
	//파일 삭제
	public void deleteFile(String objectName) {
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();

		// delete object
		try {
		    s3.deleteObject(bucketName, objectName);
		    System.out.format("Object %s has been deleted.\n", objectName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}
	
}
