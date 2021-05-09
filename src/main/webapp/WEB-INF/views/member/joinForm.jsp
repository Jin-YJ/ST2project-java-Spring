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
	<a href="${cp}">MAIN</a> <!-- 그림파일로 변경 필요 -->
	
	<h1>회원 가입</h1>
	
	<form:form action="${cp}/member/join" method="post" commandName="member">
		<table>
			<tr>
				<td>ID</td>
				<td><form:input path="mId" /></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td>MAIL</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>NickName</td>
				<td><form:input path="nickname" /></td>
			</tr>
		
			<tr>
				<td colspan="2">
					<input type="submit" value="가입하기" >
					<input type="reset" value="작성취소" >
				</td>
			</tr>
		</table>
	</form:form>
	
	
</body>
</html>