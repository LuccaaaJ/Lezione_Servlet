package com.exam.comm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.member.MemberVo;

//필터 : 서블릿의 실행 전후에 끼어들어가서 실행
//	다수의 서블릿들이 수행라는 공통작업을 실행하도록 사용
// Filter 인터페이스를 구현하여 필터 클래스 정의
//web.xml 에 <filter> 태그로 등록하거나, 클래스에 @WebFilter 적용


public class LoginFilter implements Filter{
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		System.out.println("URI:" + req.getRequestURI());
		System.out.println("URL:" + req.getRequestURL());
		
		if(!req.getRequestURI().equals(req.getContextPath()+"/member/login.do")) {

			//요청보낸 사용자의 세션을 가져옴
			HttpSession session = req.getSession(); 
			//세션에 로그인한 사용자 정보
			MemberVo vo = (MemberVo) session.getAttribute("loginUser");
			//로그인 정보가 없다면,
			if(vo==null) {
				//로그인 페이지로 이동
				resp.sendRedirect(req.getContextPath() + "/member/login.do");
				return;
			}
			
		}
		
		
		chain.doFilter(request, response);
		
	}
	
}
