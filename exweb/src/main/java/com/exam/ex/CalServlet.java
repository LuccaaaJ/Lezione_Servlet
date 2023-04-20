package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc.do")
public class CalServlet extends HttpServlet{
	
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
	out.println("<title>x와 y의 합</title>        ");
	out.println("<style>");
	out.println("h1 {margin: 100px; ");	
	out.println("font-family: Georgia, 'Times New Roman', Times, serif;");	
	out.println("color: rgb(78, 61, 139);");
	out.println("font-size: 100px;}");
	out.println("</style>");
	out.println("</head>                     ");
	out.println("<body>                      ");
	
	String x1 = req.getParameter("x");
	String y1 = req.getParameter("y");
	String op = req.getParameter("op");
	
	// 숫자타입 크래스명.parse숫자타입명("숫자문자열")
//	double x = Double.parseDouble(x1);
//	double y = Double.parseDouble(y1);
	
	
	double x  = Double.parseDouble(x1); 
	double y  = Double.parseDouble(y1);
	
	//문자열 값을 동등비교하는 경우, == 연산자가 아닌 .equals() 명령어 사용
	//	"문자열1" == "문자열2"		(X)
	//	"문자열1".equals("문자열2")(O)
	
	//(1) 스위치문
	// double result = 0.0;
	/*switch(op) {
		case "plu":
			out.println("<h1>" + x + "+" + y + " = " + (x+y) + "</h1>");
			break;
		case "min":
			out.println("<h1>" + x + "-" + y + " = " + (x-y) + "</h1>");
			break;
		case "mul":
			out.println("<h1>" + x + "*" + y + " = " + (x*y) + "</h1>");
			break;
		case "div":
			if (y != 0) 
			{
				out.println("<h1>" + x + "/" + y + " = " + (x/y) + "</h1>");
				break;
			}
			else
				out.println("<h1>0으로는 나눌 수 없습니다.</h1>");
				break;
		default:
			out.println("<h1>다시 입력해주세요.</h1>");
			}*/
	
	//(2) if문
//	 if (op.equals("plu"))
//		 out.println("	<h1>" + x + "+" + y + " = " + (x+y) + "</h1>");
//    else if (op.equals("min"))
//    	out.println("	<h1>" + x + "-" + y + " = " + (x-y) + "</h1>");
//    else if (op.equals("mul"))
//    	out.println("	<h1>" + x + "*" + y + " = " + (x*y) + "</h1>");
//    else 
//       {if (y != 0)
//    	   out.println("	<h1>" + x + "/" + y + " = " + (x/y) + "</h1>");
//       else
//    	   out.println("<h1>0으로는 나눌 수 없습니다.</h1>");
//       }

	//##교수님 풀이
		double result =0.0;
		String opval = null;
		switch(op) 
		{
		case "plu":
			result = x+y;
			opval = "+";
			break;
		case "min":
			result = x-y;
			opval = "-";
			break;
		case "mul":
			result = x*y;
			opval = "*";
			break;
		case "div":
			result = x/y;
			opval = "/";
				break;
		}
	out.printf("<h1> %.0f %s %.0f = %.2f </h1>", x, opval, y, result);	
	out.println("</body>                     ");
	out.println("</html>                     ");
	
	}	
	
	
}
