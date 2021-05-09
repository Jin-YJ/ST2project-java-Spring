<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
</title>

<style>
	.title {
		width: 400px;
		text-align: center;
	
	}

	.boardButton {
		
		align-items: center;
	}

</style>
</head>
<body>
	<div class="title">
	<h1>게시물 보기</h1>
	</div>
	<table>
	<tr>
	<td>글번호 : </td><td>${board.bno} </td>
	</tr>
	<tr>
	<td>작성일 : </td>  <td>${board.date}</td>
	</tr>
	<tr>  
	<td>작성자 : </td> <td>  ${board.writer}</td>
	</tr>
	<tr>
	<td>제목 : </td> <td>  ${board.title}</td>
	</tr>

	</table>
	<div>
	<textarea id="content" name="content" rows="4" cols="50" >${board.content}</textarea><br>
	<a href="/sc2/board/Bboard"><input type="button" value="목록" class="boardButton"></a> 
	<c:if test = "${board.writer eq mid }">
			<a href="/sc2/board/modifyForm?bno=${board.bno}"><input type="button" value="수정"></a> 
			
			<a href="/sc2/board/deleteBoard?bno=${board.bno}"><input type="button" value="삭제"></a>
	</c:if>
	
	</div>
</body>
</html>