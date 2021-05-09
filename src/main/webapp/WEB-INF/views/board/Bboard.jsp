<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
		<style>
			.boardTable{
				text-align: center;
				margin-left: 300px;
			}
			
			.writeButton{
				text-align: right;
				margin-right:20px;
			}
			
			.tableButton{
				border: 0px;
			}
			
			.searchtr {
				border: 0px;
			
			
			
			}
			
			.searchButton{
				text-align: right;
				margin-right:20px;
			}
		</style>
</head>
<body>
<%@ include file ="/WEB-INF/views/include/header.jsp" %>
	<h3>Build Board</h3>
	<div class= "boardTable">
	<form action="${cp}/board/searchBoard" method = "get">	
		<table border=1 width= 70%>
			<tr>
			<th>글 번호</th><th> 제목 </th><th> 작성자 </th><th> 작성일 </th><th>조회수</th>
			</tr>
			
			<c:forEach var="row" items="${list }">
			<tr>
				<td>${row.bno}</td>
				<td><a href="${cp}/board/viewContent?bno=${row.bno}">${row.title}</a></td>
				<td>${row.writer}</td>
				<td>${row.date}</td>
				<td>${row.hit}</td>
			</tr>
			</c:forEach>
		
			<tr class = "tableButton">
			
			<td colspan="5">
				<div class ="writeButton">
				<a href="${cp}/board/writeForm"><input type="button" value="글쓰기"></a>
				</div>
				</td>
			
			</tr>
			
			<tr class = "tableButton">
			<td colspan = "5">
			
			<c:forEach var="i" begin="1" end="${pageNum}" step="1">
				<a href = "${cp}/board/Bboard?pageNum=${i}">${i}</a>&nbsp;&nbsp;
			</c:forEach>
			
			</td>
			</tr>
		
			<tr class = "searchtr">
			<td>
				<select name = "searchOption" size = "1" >
					<option value = "title" selected = "selected">제목</option>
					<option value = "writer" >작성자</option>
					<option value = "content" >내용</option>
				
				</select>
			
			</td>
			<td colspan="3"><input type ="search" name ="search" size = "40"></td>
			
			<td><input type ="submit" value = "검색"></td>
			</tr>
		
		</table>
	</form>
	</div>

<a href = "${cp}/board/boardPageNum"></a>
</body></html>

