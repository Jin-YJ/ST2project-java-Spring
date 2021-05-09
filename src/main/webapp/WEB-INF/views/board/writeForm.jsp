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
		margin-left: 610px;
		
	}

</style>
</head>
<body>

<h3>글 쓰기</h3>
	<form action="writeBoard" method="get">
		제목 &nbsp;&nbsp; <input type="text" name="title"><br>
		내용 &nbsp;&nbsp; <textarea rows="4" cols="80" name ="content" class="content"></textarea> <br> 
		<input type="submit" value="작성" class="summitbutton">
		
	</form>
</body>
</html>