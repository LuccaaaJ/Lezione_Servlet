package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//1.브라우저 주소창에 http://localhost:8000/exweb/member/login.do 를 입력하여 접속하면, 
//	LoginServlet클래스와 login.jsp 파일이 순차적으로 실행되어 브라우저에 로그인 화면이 출력되도록 구현
//2.로그인 화면에서 submit 버튼을 클릭하면 LoginServlet클래스의 doPost가 실행되도록 구현



@WebServlet("/member/logout.do")
public class LogutServlet extends HttpServlet{
	private MemberDao memberDao = new MemberDaoBatis();
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
//		session.setAttribute("loginUser", null); //방식 1. 세션에 지정한 이름의 값을 null로 설정 (: 특정세션 삭제)
//		session.removeAttribute("loginUser"); //방식 2. 세션에서 지정한 이름의 속성을 삭제 (:특정세션 삭제)
		session.invalidate();//방식 3.세션객체를 제거(후 다시 생성), 모든 속성들도 함께 삭제 (:모든세션 삭제)
		
		resp.sendRedirect(req.getContextPath() + "/member/login.do"); // 로그아웃 후 로그인페이지 
		
	}
}
