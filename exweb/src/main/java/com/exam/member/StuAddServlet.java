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

@WebServlet("/student/add.do")
public class StuAddServlet extends HttpServlet{

	{
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
		
		String stuId = req.getParameter("stuId");
		String stuName = req.getParameter("stuName");
		int stuScore = Integer.parseInt(req.getParameter("stuScore"));
	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		//데이터베이스 서버 주소
		String user = "web";									//데이터베이스 접속 아이디
		String password = "web01";								//데이터베이스 접속 비밀번호
		
		String sql 
		= "INSERT INTO STUDENT (stu_id, stu_name, stu_score) "
				+ "VALUES (?, ?, ?)";
		
		int n = 0;	
		//try() 내부에 선언된 변수의 값은
		//try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try(	
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			//pstmt 명령문 객체에 담겨 있는 SQL문의 ?에 값을 채워넣기
			//채워넣는 값의 타입에 따라서 set타입명() 메서드 사용
			pstmt.setString(1, stuId);		//1번째 ?에 stuId 값을 넣기
			pstmt.setString(2, stuName);	//2번째 ?에 stuName 값을 넣기
			pstmt.setInt(3, stuScore);		//3번째 ?에 stuScore 값을 넣기
			
			//SQL문 실행(INSERT, UPDATE, DELETE 문 실행은 executeUpdate() 메서드 사용)
			n = pstmt.executeUpdate();	//반환값은 영향받은 레코드(row) 수
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println("<!DOCTYPE html>             ");
		out.println("<html>                      "); 
		out.println("<head>                      ");
		out.println("<meta charset=\"UTF-8\">    ");
		out.println("<title>학생 추가 결과</title>   ");
		out.println("</head>                     ");
		out.println("<body>              		 ");
		out.printf("<h1>%d명의 학생 추가</h1>", n	  );
		out.println("</body>                     ");
		out.println("</html>                     ");

	}
	
}
