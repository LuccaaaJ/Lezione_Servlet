<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--로그인이 된 경우  -->
	<c:if test="${loginUser!=null}">
		${loginUser.memName}
		<a href='${pageContext.request.contextPath}/member/logout.do'>로그아웃</a>
	</c:if>
	
	<!--로그인이 안된 경우  -->
	<c:if test="${loginUser==null}">
		<a href='${pageContext.request.contextPath}/member/login.do'>로그인</a>
		<a href='${pageContext.request.contextPath}/member/add.do'>회원추가</a>
		<a href='<c:url value="/member/add.do"/>'>회원추가</a>
	</c:if>
	<hr>
</body>
</html>