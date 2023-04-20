package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
	@Override				
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8"); //문자인코딩과 데이터타입을 한번에 설정 가능
		
		
		PrintWriter out = resp.getWriter(); 
		
	
		out.println("<!DOCTYPE html>             ");
		out.println("<html>                      "); 
		out.println("<head>                      ");
		out.println("<meta charset=\"UTF-8\">    ");
		out.println("<title>HOME</title>        ");
		out.println("</head>                     ");
		out.println("<body>                      ");
		out.println("	<h1>HOME</h1>   ");
		
		//SaveServlet에서 저장한 데이터를 읽어서 출력
		HttpSession session = req.getSession();
		String nickName = (String) session.getAttribute("nick");	//세션객체에 "nick"이라는 이름으로 저장된 데이터 읽기
		out.println("세션에 저장된 닉네임 : " + nickName + "<br>");

		ServletContext context = getServletContext();
		String contextNick = (String) context.getAttribute("nick");	//서블릿컨텍스트객체에 "nick"이라는 이름으로 저장된 데이터 읽기
		out.println("서블릿컨텍스트에 저장된 닉네임 : " + contextNick + "<br>");
		
		//요청헤더(Cookie)에 포함된 쿠키값들을 읽기
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {	//쿠키들을 하나씩
			if("nick".equals(c.getName() ) ) {	//쿠키이름이 "nick"인 경우
				String v = URLDecoder.decode(c.getValue(), "UTF-8");
				out.println("쿠키에 저장된 닉네임 : " + v + "<br>");
			}
		}
		
		out.println("</body>                     ");
		out.println("</html>                     ");
		
	}
	
}
