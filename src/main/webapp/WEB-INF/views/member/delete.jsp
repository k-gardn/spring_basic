<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<style>
	h4{coloer:red}
</style>
</head>
<body>
	<script>
		function deleteButton(){
			var formRef = document.getElementById('f');
			formRef.submit(); //submit 버튼을 누른효과를 얻을 수 있음.
		}
		function cancelButton(){
			location.href='index';
		}
	</script>
	<h3>회원 탈퇴</h3>
	<c:if test="${not empty msg }">
		<h4>${msg }</h4>
	</c:if>
	<form action="delete" method="post" id="f">
	<input type="text" name="id" id="id" readonly="readonly" value="${sessionScope.id }"><br>
		<input type="password" name="pw" id="pw" placeholder="비밀번호"><br>
		<input type="password" name="confirmPw" id="confirmPw" placeholder="비밀번호 확인"><br>
		<input type="button" value="회원 삭제" onclick="deleteButton();">
		<input type="button" value="취소" onclick="cancelButton();">
	</form>
</body>
</html>