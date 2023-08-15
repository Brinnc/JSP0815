<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//톰캣을 포함한 WAS는 접속 즉 요청이 들어올 때마다 해당 요청에 대한 
	//Session객체를 서버의 메모리에 생성하고, 해당 객체에는 중복되지 않는 아이디를 할당함
	//sessiom
	String sid=session.getId();
	out.print("현재 요청에 대해 생성된 세션의 아이디는 "+sid);

%>
