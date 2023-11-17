package com.food.exp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration  
public class BucketConfig {  
    private final String endPoint = "https://kr.object.ncloudstorage.com";  
    private final String regionName = "kr-standard";  
  
    @Value("${spring.s3.accessKey}")
    private String accessKey;  
  
    @Value("${spring.s3.secretKey}")  
    private String secretKey;  
  
    @Value("${spring.s3.bucket}")  
    private String bucketName;  
  
//    @Bean  
//    public AmazonS3Client amazonS3Client() {  
//        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);  
//        return (AmazonS3Client) AmazonS3ClientBuilder  
//                .standard()  
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))  
//                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))  
//                .build();  
//    }  

    // S3 client
//	final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//	    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
//	    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
//	    .build();  
	
	// S3 client
    @Bean  
    public AmazonS3Client amazonS3Client() {  
//    	System.out.println(accessKey);
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);  
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
        	    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
        	    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
        	    .build();  
    } 
  
}