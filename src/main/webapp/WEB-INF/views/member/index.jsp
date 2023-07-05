<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<style>
	a{ text-decoration: none; coloer:red}
	h4{coloer:red}
</style>
</head>
<body>
	<h3>인텍스 페이지</h3>
	<h4>${msg }</h4>
	
	<a href="register">회원 등록</a> |
	<a href="list">회원 목록</a> |
	<a href="update">회원 수정</a> |
	<a href="delete">회원 삭제</a> |
	<a href="login">로그인</a> |
	<a href="logout">로그아웃</a> |
</body>
</html>