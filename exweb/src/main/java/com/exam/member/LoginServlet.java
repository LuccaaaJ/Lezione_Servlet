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



@WebServlet("/member/login.do")
public class LoginServlet extends HttpServlet{
	private MemberDao memberDao = new MemberDaoBatis();
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	String memId = req.getParameter("memId");
//	MemberVo vo = memberDao.selectMember(memId);
//	req.setAttribute("mvo", vo);
	
	
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.setCharacterEncoding("UTF-8");	//필터로 이동
		MemberVo vo = new MemberVo();
	
		vo.setMemId( req.getParameter("memId") );
		vo.setMemPass( req.getParameter("memPass") );
		vo.setMemName( req.getParameter("memName") );
		
		MemberVo mvo = memberDao.selectLogin(vo);
		
		
		if(mvo==null) { //로그인실패
			resp.sendRedirect(req.getContextPath() + "/member/login.do");
		}else {//로그인성공
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", mvo);//세션에 로그인한 사용자 정보 저장
			resp.sendRedirect(req.getContextPath() + "/member/list.do");
		
		
		}

	}
}
