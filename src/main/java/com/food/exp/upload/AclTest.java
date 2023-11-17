package com.food.exp.upload;

import java.io.ByteArrayInputStream;
import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

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

@Service
//@PropertySource("classpath:application.properties")
public class AclTest {

	final String endPoint = "https://kr.object.ncloudstorage.com";
	final String regionName = "kr-standard";

	
  @Value("${spring.s3.accessKey}")
  private String accessKey;  

  @Value("${spring.s3.secretKey}")  
  private String secretKey;  

  @Value("${spring.s3.bucket}")  
  private String bucketName;  

	public void uploadTest() {
		
		System.out.println(accessKey);

		// S3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
		    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
		    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
		    .build();

		String bucketName = "team3-bucket";

		// create folder
		String folderName = "test-folder/";

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(0L);
		objectMetadata.setContentType("application/x-directory");
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, 
				new ByteArrayInputStream(new byte[0]), objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);

		try {
		    s3.putObject(putObjectRequest);
		    System.out.format("Folder %s has been created.\n", folderName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}

		// upload local file
		String objectName = folderName+"sample-object";
		String filePath = "/Users/ydj48/Downloads/food.jpg";

		try {
		    //s3.putObject(bucketName, objectName, new File(filePath));
			
			// PutObjectRequest 객체 생성하면서 메서드를 이용한 PublicRead 권한 부여
			s3.putObject(new PutObjectRequest(bucketName, objectName, new File(filePath))
			            .withCannedAcl(CannedAccessControlList.PublicRead));
		    System.out.format("Object %s has been created.\n", objectName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}
}
