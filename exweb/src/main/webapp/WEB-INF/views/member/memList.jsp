<%-- <%@page import="com.exam.member.MemberVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.member.MemberDaoBatis"%>
<%@page import="com.exam.member.MemberDao"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%-- DBMS에서 데이터를 가져온다. but DBMS가 아닌 memListServlet에서 폴더를 직접 지정했기 때문에 필요없으므로 삭제처리 ->  import 삭제 (==)
	<%!
	private MemberDao memberDao = new MemberDaoBatis();
	%>
	<%
	List<MemberVo> list = memberDao.selectMemberList();
	request.setAttribute("memberList", list);
	%>
	 --%>
	<!DOCTYPE html>             
	<html>                       
	<head>                      
	<meta charset=\"UTF-8\">    
	<title>회원관리</title>  	
	</head>                 
	<body>
	<jsp:include page="/WEB-INF/views/menu.jsp"></jsp:include>
	<h2>회원목록</h2>
	<%-- <a href='<%=request.getContextPath()%>/member/add.do'>회원추가</a> --%>
	<a href='${pageContext.request.contextPath}/member/add.do'>회원추가</a>
	<a href='<c:url value="/member/add.do"/>'>회원추가</a>
	<% 
	/* for (MemberVo vo : list) { */
	%>	
	<c:forEach var="vo" items='${memberList}'>	
	<p>
	<%-- ${vo.memId}, ${vo.memPass}, ${vo.memName}, ${vo.memPoint} --%>
	
	<!-- contextPath방식으로의 연결  -->
	<%-- <a href='${pageContext.request.contextPath}/member/edit.do?memId=<c:out value="${vo.memId}"/>'><c:out value="${vo.memId}"/> :</a> --%>

	<!-- url 방식으로의 연결  **아래의 형식에 유의할 것  -->
	<c:url value="/member/edit.do" var="editUrl">
		<c:param name="memId">${vo.memId}</c:param>
	</c:url>
	<a href='${editUrl}'><c:out value="${vo.memId}"/></a> :
	
	<c:out value="${vo.memName}"/> :
	${vo.memPoint}
	<a href='${pageContext.request.contextPath}/member/del.do?memId=<c:out value="${vo.memId}"/>'><button type='button'>삭제</button></a>
<!-- 	JSTL태그의 scrop와 var 속성을 사용하면,
	JSTL 태그 실행 결과를 현재 위치에 출력하지 않고,
	지정한 scope에 지정한 이름(var)의 속성을 저장한 후,
	EL에서 읽어서 사용 가능
 -->	
 	<c:url value="/member/del.do" var="delUrl">
		<c:param name="memId">${vo.memId}</c:param>
	</c:url>
	<a href='${delUrl}'><button type='button'>삭제</button></a>
	
	</p>
</c:forEach>	
<%-- 
<%
}
%>
 --%>
</body>
</html>


