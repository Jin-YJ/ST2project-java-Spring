<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.content{
		margin-top: 10px;
	}
	
	.summitbutton {
		margin-left: 550px;
		
	}

</style>
</head>
<body>

<h3>글 수정</h3>
	<form action="modifyBoard" method="get">
		<input type ="hidden" name="bno" value = "${board.bno}">
		글 번호 : ${board.bno}
		제목 &nbsp;&nbsp; <input type="text" name="title" value ="${board.title}"><br>
		내용 &nbsp;&nbsp; <textarea rows="4" cols="80" name ="content" class="content">${board.content}</textarea> <br> 
		<div class="summitbutton">
		<input type="submit" value="수정" > &nbsp;
		<a href = "/index"><input type="button" value="취소"></a>
		</div>
	</form>
</body>
</html>