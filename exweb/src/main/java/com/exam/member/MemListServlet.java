package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";		//데이터베이스 서버 주소
	String user = "web";									//데이터베이스 접속 아이디
	String password = "web01";								//데이터베이스 접속 비밀번호
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		
		String sql 
		= "select mem_id, MEM_PASS, MEM_NAME, MEM_POINT from MEMBER";
		
		
		//try() 내부에 선언된 변수의 값은
		//try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try(	
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//SQL문 실행(SELECT 문 실행은 executeQuery() 메서드 사용)
				ResultSet rs = pstmt.executeQuery();		//반환값은 조회 결과 레코드(row)들
			) {
			//처음 ResultSet 객체는 첫 레코드(row) 이전을 가리키고 있음
			//.next() 메서드를 실행하면 다음 레코드를 가리키게 된다
			//.next() 메서드는 다음 레코드가 있으면 true를 반환하고, 없으면 false를 반환
			while (rs.next()) {
				//컬럼값의 데이터타입에 따라서 get타입("컬럼명") 메서드를 사용하여 컬럼값 읽기
				String memId = rs.getString("mem_id"); //현재 가리키는 레코드(row)의 "mem_id"컬럼값 읽기
				String memPass = rs.getString("mem_pass"); //현재 가리키는 레코드(row)의 "mem_pass"컬럼값 읽기
				String memName = rs.getString("mem_name"); //현재 가리키는 레코드(row)의 "mem_name"컬럼값 읽기
				int memPoint = rs.getInt("mem_point"); //현재 가리키는 레코드(row)의 "mem_point"컬럼값 읽기
				//System.out.println(memId + ":" + memPass + ":" + memName + ":" + memPoint);
				
				out.printf("<p> %6s : %6s : %6s : %5d point   ",memId,memPass,memName,memPoint);
				out.println("<a href='" + req.getContextPath() + "/member/del.do?memId=" + memId + "'><button type='button'>삭제</button></a>");
				out.print("</p>\n");
			
				
			}
			//conn.getAutoCommit();			// 커밋 상태 확인.
			//conn.setAutoCommit(false);	// JDBC는 자동으로 커밋되는데 자동커밋 안되게 변경하려면 쓰는 것.
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		//finally {
			//pstmt.close();		//명령문 객체가 사용하던 자원 반납
			//conn.close();			//데이터베이스와 연결 종료
		//}

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
