<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

		
<!DOCTYPE html>            
<html>                     
<head>                     
<meta charset=\"UTF-8\">   
<title>회원관리</title>  
</head>                    
<body>              	
<h1>회원정보변경</h1>
<form action='${pageContext.request.contextPath}/member/list.do' method='post'>
		아이디 : <input type='text' name='memId' value='${mvo.memId}'/><br>
	<!-- 	비밀번호 : <input type='password' name='memPass' value=''/><br> -->
		이름 : <input type='text' name='memName' value='${mvo.memName}'/><br>
		포인트 : <input type='number' name='memPoint' value='${mvo.memPoint}'/><br>
		<input type='submit'/>
</form>
</body> 
</html>  