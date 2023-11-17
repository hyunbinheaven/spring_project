package com.food.exp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.food.exp.interceptor.MyHandlerInterceptor;

@Configuration  
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	MyHandlerInterceptor interceptor;

	
	//로그인 필요한 곳 링크 추후 추가 ex: 즐겨찾기
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		        .addPathPatterns("/mypage/**");
//		.addPathPatterns("/mypage/**","/rst/revwrite");
		
//		registry.addInterceptor(loginIC)
//		.addPathPatterns("/member/login");
	}



//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/mypage")
//		        .setViewName("main");
//		
//	}


	
	
	
}
