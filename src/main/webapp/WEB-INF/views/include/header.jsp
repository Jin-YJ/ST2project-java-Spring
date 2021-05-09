<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	
	.Topmenu {
		width: 100 % ;
		height: 10 % ;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	
		}
	.mainButton{
	
		margin-left: 10px ;
	
	}
	.menu {
		text-align: left;
		margin-left: 10px ;
		

	}
	
	.Topmenubutton{
		margin-top: 1px ;		
		margin-right: 5px;
	}
	
	.Topsubmenu {
		background-color: gray ;
		border-bottom : 1px solid black;
	
	}

</style>
<script type="text/javascript">
	function logoutButton.click() {
	confirm("로그아웃버튼");
}

</script>
</head>
<body>
	
	<div class="Topmenu">
	<div class="mainButton">
	<a href="/sc2"><img src="${cp}/resources/img/logo.jpg" style= "width:70px; height:70px"></a>
	</div>
		<div class="Topmenubutton">
			<c:if test="${empty member}">
				<a href="${cp}/member/joinForm">JOIN</a> <a href="${cp}/member/loginForm">Login</a>
			</c:if>
			<c:if test="${!empty member}">
				<a href="${cp}/member/modifyForm"><input type ="button" value="정보수정"></a> &nbsp;&nbsp; 
				<a href="${cp}/member/logout"><input type ="button" value="로그아웃" onclick="logoutButton.click();"></a> &nbsp;&nbsp;
				<a href="${cp}/member/removeForm"><input type ="button" value="계정삭제"  ></a> &nbsp;&nbsp; 
			</c:if>
		</div>
	</div>
	<div class = "Topsubmenu">
	<a href="/sc2" class = "menu"> HOME</a> <a href="${cp}/board/Bboard" class = "menu">build board</a>
		
	
	
	</div>
	
</body>
</html>

