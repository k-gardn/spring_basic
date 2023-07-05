<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
	<h3>회원 등록</h3>
	<c:if test="${not empty msg }" >
		<h3>${msg }</h3>
	</c:if>
	<form action="register" method="post">
		<input type="text" name="id"  placeholder="아이디"><br>
		<input type="password" name="pw"  placeholder="비밀번호"><br>
		<input type="password" name="confirmPw"  placeholder="비밀번호 확인"><br>
		<input type="text" name="name"  placeholder="이름"><br>
		<input type="text" name="email"  placeholder="이메일"><br>
		<input type="submit" value="회원 가입" >
		<input type="button" value="취소" onclick="location.href='index'">
	</form>
</body>
</html>



