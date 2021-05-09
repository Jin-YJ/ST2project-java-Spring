<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
</head>
<body>
	<a href="${cp}/">MAIN</a>
	<h1>회원 정보수정 페이지</h1>
	
	<form:form action="${cp}/member/memModify" method="post" commandName="member">
		<form:hidden path="mId" value="${member.mId}"/>
		<table>
			<tr>
				<td>ID</td>
				<td>${member.mId}</td>
			</tr>
			<tr>
				<td>PW</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td>MAIL</td>
				<td><form:input path="email" value="${member.email}" /></td>
			</tr>
			<tr>
				<td>NICKNAME</td>
				<td><form:input path="nickname" value="${member.nickname}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정하기" ></td>
			</tr>
		</table>
	</form:form>
	
	
</body>
</html>