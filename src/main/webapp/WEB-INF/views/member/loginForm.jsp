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
	<a href="${cp}/sc2">MAIN</a><!-- 그림파일로 변경해야함 -->
		
	<h1>회원 로그인</h1>
 	
	<form:form action="${cp}/member/login" method="post" commandName="member">
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
				<td><input type="submit" value="로그인" ></td><td><a href ="/sc2/member/joinForm"><input type="button" value="회원가입"></a></td>
			</tr>
		</table>
	</form:form>

	
</body>
</html>