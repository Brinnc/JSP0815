<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*1) 클라이언트가 전송한 id, pass 파라미터를 넘겨받자*/
	/*2) 파라미터를 이용하여 오라클 회원 DB에 존재하는 회원인지 조회
		-존재함: 회원정보를 세션에 담기
		-존재하지 않음: 로그인 정보가 틀렸다는 메세지 전송
	*/
%>