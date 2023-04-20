package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿 URL패턴(주소, 경로) 지정 규칙
//- 반드시 "/" 또는 "*." 으로 시작
//- "*"은 0개 이상의 모든 문자열과 일치

@WebServlet("/hi.do") // 모든 파일이라면 /foo.*, 주소 끝이 .act 이면 / *.act
public class HiServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user = req.getParameter("user");
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOCTYPE html>             ");
		out.println("<html>                      "); 
		out.println("<head>                      ");
		out.println("<meta charset=\"UTF-8\">    ");
		out.println("<title>환영</title>        ");
		out.println("<style>");
		out.println("h1 {margin: 200px;}");		
		out.println("</style>");
		out.println("</head>                     ");
		out.println("<body>                      ");
		out.println("	<h1>오늘은 금요일!!!!!</h1>   ");
		out.println("	<h1>" + user + "님 환영합니다!!!</h1>   ");		
		out.println("</body>                     ");
		out.println("</html>                     ");
		
	}

}

//"/hi.do?user=둘리" 로 요청을 보내면, 화면에 "둘리님 환영합니다"라고 출력되고,
//"/hi.do?user=고길동" 로 요청을 보내면, 화면에 "고길동님 환영합니다"라고 출력되도록
//HiServlet의 내용을 변경하세요.




//이클립스의 다이나믹휍프로젝트(톰캣)가 실행 중인 상태에서
// *.java 파일을 변경하면, 이클립스가 톰캣을 자동 재시작
// src/main/webapp 폴더의 정적 리소스(*.html, *.css...) 파일들을 변경하면
// 즉시 톰캣에 반영되므로 톰캣 재시작 없이 웹브라우저에서 새로고침만 필요
// web.wml 등 설정파일 변경시에는, 수동으로 톰캣 재시작 필요





