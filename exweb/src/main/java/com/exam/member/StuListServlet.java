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

@WebServlet("/student/list.do")
public class StuListServlet extends HttpServlet{

	{
		// MemAddServlet 보다 먼저 실행될 지도 모르니 그냥 둠
		// 애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요
				// try, catch로 오류를 예외처리
				try {
					Class.forName("oracle.jdbc.OracleDriver");
				} 
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	}
	
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
		out.println("<title>학생 추가 결과</title>   ");
		out.println("</head>                     ");
		out.println("<body>              		 ");
		
		

		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		//데이터베이스 서버 주소
		String user = "web";									//데이터베이스 접속 아이디
		String password = "web01";								//데이터베이스 접속 비밀번호
		
		String sql 
		= "select stu_id, stu_name, stu_score from student";
		
		//try() 내부에 선언된 변수의 값은
		//try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try(	
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			//SQL문 실행(SELECT 문 실행은 executeQuery() 메서드 사용)
			ResultSet rs = pstmt.executeQuery();		//반환값은 조회 결과 레코드(row)들
			
			//처음 ResultSet 객체는 첫 레코드(row) 이전을 가리키고 있음
			//.next() 메서드를 실행하면 다음 레코드를 가리키게 된다
			//.next() 메서드는 다음 레코드가 있으면 true를 반환하고, 없으면 false를 반환
			while (rs.next()) {
				//컬럼값의 데이터타입에 따라서 get타입("컬럼명") 메서드를 사용하여 컬럼값 읽기
				String stuId = rs.getString("stu_id"); //현재 가리키는 레코드(row)의 "stu_id"컬럼값 읽기
				String stuPass = rs.getString("stu_name"); //현재 가리키는 레코드(row)의 "stu_name"컬럼값 읽기
				int stuPoint = rs.getInt("stu_score"); //현재 가리키는 레코드(row)의 "stu_score"컬럼값 읽기
				out.println("<h2>" + stuId + ":" + stuPass + ":" + stuPoint + "</h2>");
			}
			//conn.getAutoCommit();			// 커밋 상태 확인.
			//conn.setAutoCommit(false);	// JDBC는 자동으로 커밋되는데 자동커밋 안되게 변경하려면 쓰는 것.
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println("</body>                     ");
		out.println("</html>                     ");

	}
	
}
