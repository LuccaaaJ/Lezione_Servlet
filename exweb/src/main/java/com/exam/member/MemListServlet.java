package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

//회원 목록 화면에 "회원추가" 링크를 추가하고,
// 그 링크를 클릭하면, 회원정보를 입력하는 폼 화면으로 이동하도록
// MemListServlet 클래스를 변경하세요.

//회원 정보 추가 후에 화면에 "회원목록으로 이동" 링크를 추가하고,
// 그 링크를 클릭하면, 회원정보 화면으로 이동하도록
// MemAddServlet 클래스를 변경하세요.

//회원목록의 각 회원정보 옆에 "삭제" 링크를 출력하고, 
// 링크를 클릭하면 해당 회원이 삭제 되도록 
// MemListServlet 클래스를 변경하세요.

// 삭제 링크가 버튼 모양이면 더 좋을 것 같아요.

@WebServlet("/member/list.do")
public class MemListServlet extends HttpServlet{

	private MemberDao memberDao = new MemberDaoBatis();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<MemberVo> list = memberDao.selectMemberList();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>             ");
		out.println("<html>                      "); 
		out.println("<head>                      ");
		out.println("<meta charset=\"UTF-8\">    ");
		out.println("<title>회원관리</title>  	 ");
		out.println("<style>  	 ");
		out.println("a {text-decoration: none; color: black; }  ");
		out.println("button {font-weight: bolder; } ");
		out.println("</style>  	 ");
		out.println("</head>                     ");
		out.println("<body>              		 ");
		out.println("<h2>회원목록</h2>       		 ");
		
		for (MemberVo vo : list) {
			//System.out.println(memId + ":" + memPass + ":" + memName + ":" + memPoint);
			out.printf("<p> %6s : %6s : %6s : %5d point   ",vo.getMemId(), vo.getMemPass(), vo.getMemName(), vo.getMemPoint());
			out.println("<a href='" + req.getContextPath() + "/member/del.do?memId=" + vo.getMemId() + "'><button type='button'>삭제</button></a>");
			out.print("</p>\n");
		}
		
		

		//회원목록이 이클립스 콘솔이 아닌
		//웹 브라우저 화면에 출력되도록 MemListServlet을 변경하세요.
		
		//out.println("<form action=\"" + req.getContextPath() + "/member/addform.do\" method=\"post\">");
		//out.println("<input type=\"submit\" value=\"회원추가\">");
		//out.println("</form>");
		//out.println("<form action=\"" + req.getContextPath() + "/member/delform.do\" method=\"post\">");
		//out.println("<input type=\"submit\" value=\"회원삭제\">");
		//out.println("</form>");
		
		out.println("<a href='" + req.getContextPath() + "/member/addform.do'><button type='button'>회원추가</button></a>");
		out.println("<a href='" + req.getContextPath() + "/member/delform.do'><button type='button'>회원삭제</button></a>");
		out.println("</body>                     ");
		out.println("</html>                     ");

		
	}

	
	
}
