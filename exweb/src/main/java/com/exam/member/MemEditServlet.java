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

@WebServlet("/member/edit.do")
public class MemEditServlet extends HttpServlet{
	private MemberDao memberDao = new MemberDaoBatis();
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String memId = req.getParameter("memId");
	MemberVo vo = memberDao.selectMember(memId);
	req.setAttribute("mvo", vo);
	
	
		req.getRequestDispatcher("/WEB-INF/views/member/memEdit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.setCharacterEncoding("UTF-8");	//필터로 이동
		MemberVo vo = new MemberVo();
	
		vo.setMemId( req.getParameter("memId") );
		vo.setMemPass( req.getParameter("memPass") );
		vo.setMemName( req.getParameter("memName") );
		vo.setMemPoint( Integer.parseInt( req.getParameter("memPoint") ) );
		
//		String memId = req.getParameter("memId");
//		String memPass = req.getParameter("memPass");
//		String memName = req.getParameter("memName");
//		int memPoint = Integer.parseInt(req.getParameter("memPoint"));
	
		
		int n = memberDao.insertMember(vo);
		
		System.out.println(n + "명의 회원 추가");
		
		
		//회원목록 출력
		//MemListServlet 실행!
		//forward : 요청객체와 응답객체를 전달하면서, 지정한 주소의 서블릿을 실행
		//		현재 서블릿에서는 더 이상 응답을 출력하지 않는다
//		req.getRequestDispatcher("/member/list.do").forward(req, resp);
		//include : 요청객체와 응답객체를 전달하면서, 지정한 주소의 서블릿을 실행
		//		현재 서블릿의 출력 내용 중간에 지정한 서블릿의 출력 내용을 포함
//		req.getRequestDispatcher("/member/list.do").include(req, resp);
		//redirect : 지정한 주소로 이동하라는 명령을 담은 응답을 웹브라우저에게 전송
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
		
		
		
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		out.println("<!DOCTYPE html>             ");
//		out.println("<html>                      "); 
//		out.println("<head>                      ");
//		out.println("<meta charset=\"UTF-8\">    ");
//		out.println("<title>회원추가 결과</title>   ");
//		out.println("</head>                     ");
//		out.println("<body>              		 ");
//		out.printf("<h3>%d명의 회원 추가<h3>", n	  );
//		out.println("<a href='" + req.getContextPath() + "/member/list.do'><button type='button'>회원목록</button></a>");
//		out.println("</body>                     ");
//		out.println("</html>                     ");

	}

	
	
}
