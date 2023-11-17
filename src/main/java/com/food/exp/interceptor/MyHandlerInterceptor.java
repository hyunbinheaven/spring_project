package com.food.exp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {
	
	private void saveDest(HttpServletRequest req) {

	    String uri = req.getRequestURI();
	    String query = req.getQueryString();
	    uri = uri.replaceFirst("/foodexp", "");
	    
	    // 기존 URI에 parameter가 있을 경우, 이를 포함
	    if(query == null || query.equals("null")) {
	    	query = "";
	    } else {
	        query = "?" + query;
	    }
	    
	    if(req.getMethod().equals("GET")) {
	    	System.out.println("dest: "+ (uri + query));
	        req.getSession().setAttribute("dest", uri + query);
	    }
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MyHandlerInterceptor.preHandle");
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("login") == null) {
			System.out.println("로그인 필요");
	        saveDest(request);
			
			//context 바꿀 시 변경 필요
			//response.sendRedirect("/foodexp/member/loginform");
	        response.sendRedirect("/foodexp/member/loginform?showAlert=true");

			return false;
		}
		return true;
	}
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("MyHandlerInterceptor.postHandle");
//		HttpSession session = request.getSession();
//		
//		if(session.getAttribute("dest") != null) {
//			Object dest = session.getAttribute("dest");
//			response.sendRedirect((String)dest);
//			
//		}
//        
//		
//	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("MyHandlerInterceptor.afterCompletion");
		HttpSession session = request.getSession(false);
		if(session != null) {			
			//리다이렉트 후 남아있는 dest의 정보를 삭제해 다음 posthandle
			session.removeAttribute("dest");
		}
		
	}
}
