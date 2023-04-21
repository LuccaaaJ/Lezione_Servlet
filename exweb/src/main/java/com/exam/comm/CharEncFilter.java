package com.exam.comm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//필터 : 서블릿의 실행 전후에 끼어들어가서 실행
//	다수의 서블릿들이 수행라는 공통작업을 실행하도록 사용
// Filter 인터페이스를 구현하여 필터 클래스 정의
//web.xml 에 <filter> 태그로 등록하거나, 클래스에 @WebFilter 적용


public class CharEncFilter implements Filter{

	private String enc;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터가 처음 생성됐을 때 1번 실행
		System.out.println("CharEncFilter init() 실행!");
		//필터의 초기화 파라미터 값 읽기
		enc = filterConfig.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("CharEncFilter doFilter() 실행!");
		
		request.setCharacterEncoding(enc);
		
		//이후 실행될 필터 또는 서블릿들을 실행
		chain.doFilter(request, response);
		
	}
	
	@Override
	public void destroy() {
		// 필터 객체가 소멸(삭제)되기 전에 전체 1번 실행
		System.out.println("CharEncFilter destroy() 실행!");
		
	}
	
}
