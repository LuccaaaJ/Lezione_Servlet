package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/form.do")
public class FormServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.setCharacterEncoding("UTF-8");
	
	resp.setCharacterEncoding("UTF-8");
	resp.setContentType("text/html");
	
	PrintWriter out = resp.getWriter();
	
	out.println("<!DOCTYPE html>             ");
	out.println("<html>                      "); 
	out.println("<head>                      ");
	out.println("<meta charset=\"UTF-8\">    ");
	out.println("<title>주문</title>        ");
	out.println("<body>                      ");
	String pval = req.getParameter("prod");
	String fval = req.getParameter("fruit");
	String[] dval = req.getParameterValues("drink");
	
	
	
	out.println("<h1>요청주소 : " + req.getRequestURL() + "</h>");
	out.println("<h1>요청주소 : " + req.getRequestURI() + "</h>");
	out.println("<h1>애플리케이션 고유경로 : " + req.getContextPath() + "</h>");
	out.println("<h1>요청방식 : " + req.getMethod() + "</h>");
	out.println("<h1>User-Agent 요청헤더 : " + req.getHeader("User-Agent") + "</h>");
	out.println("<h1>사용자IP주소 : " + req.getRemoteAddr() + "</h>");
	
	out.println("<h1>선택한 상품의 사진 : " + pval + "<h1>");
	out.println("<img src=\"https://api.lorem.space/image/" + pval + "?w=150&h=150\"");
	 
	out.println("<br>");
	
	if (fval != null)
		{out.println("<h1>선택한 과일의 이름<h1>");
		out.println("<h2>(\""+fval+"\")</h2>");}	
	else
		{out.println("");}
	
	if (dval.length > 0) {
		out.println("<h1>선택한 음료의 이름들<h1>");
		out.print("<h2>(");
		
		//for (int i=0; i < dval.length-1; i++)
		//{out.print("\""+dval[i]+"\",");}
		//out.print("\""+ dval[dval.length-1]+"\"");
	
		for (int i=0; i < dval.length; i++)
			{out.print("\""+dval[i]+"\""+(i<dval.length-1?",":""));}
		
		out.print(")</h2>");}
	else
		out.print("");
	
	out.println("</body>                     ");
	out.println("</html>                     ");
	
	}
}
