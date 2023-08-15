<%@page import="domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		//내장 객체인 session에서 심어놓은 데이터를 꺼내기
		Member member=(Member)session.getAttribute("member");
	%>
	[회원 정보] <p>
	
	님의 ID: <%=member.getId()%> <p>
	님의 Name: <%=member.getName()%> <p>
	님의 Email: <%=member.getEmail()%> <p>
</body>
</html>