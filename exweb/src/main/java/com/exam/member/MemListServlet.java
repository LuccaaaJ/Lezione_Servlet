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
import javax.servlet.http.HttpSession;

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

//로그인하지않은 상태에서 회원목록 페이지에 접속하면,
//로그인 화면으로 이동하도록 구현
@WebServlet("/member/list.do")
public class MemListServlet extends HttpServlet{

	private MemberDao memberDao = new MemberDaoBatis();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<MemberVo> list = memberDao.selectMemberList();
		
		req.setAttribute("memberList", list);
		// memList에서 memberList 와 연결 list를 출력
		
		req.getRequestDispatcher("/WEB-INF/views/member/memList.jsp").forward(req, resp);
		
	}

	
	
}
