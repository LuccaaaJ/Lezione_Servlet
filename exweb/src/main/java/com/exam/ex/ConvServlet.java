package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dollar")
public class ConvServlet extends HttpServlet{
	
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
	out.println("<title>원, 달러 환전</title>        ");
	out.println("<style>");
	out.println("h1 {margin: 100px; ");	
	out.println("font-family: Georgia, 'Times New Roman', Times, serif;");	
	out.println("color: rgb(78, 61, 139);");
	out.println("font-size: 100px;}");
	out.println("</style>");
	out.println("</head>                     ");
	out.println("<body>                      ");
	
	String num1 = req.getParameter("num");
	String unitval = req.getParameter("unit");
	
	double fromMoney  = Double.parseDouble(num1); 
	
	// 스위치문
	 double result = 0.0;
	 String fromUnit = "원";
	 String toUnit = "달러";
	 switch(unitval) 
	 {
		case "won":
			result = fromMoney / 1287;	//원->달러
			break;
		case "dol":
			result = fromMoney * 1287;	//달러->원
			fromUnit = "달러";
			toUnit = "원";
			break;
	 }
	
	out.printf("<h1>%.1f %s = %.1f %s</h1>", fromMoney, fromUnit, result, toUnit);	
	out.println("</body>                     ");
	out.println("</html>                     ");
	
	}	
	
	
}
