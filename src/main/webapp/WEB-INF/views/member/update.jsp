<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
	<script>
		// 자바스크립트 작성
		function updateButton(){
			var id = document.getElementById('id');
			if( id.value == ''){
				alert('아이디는 필수로 입력 값이 있어야합니다.')
				return ;
			}
			//alert('아이디 : ' + id.value);
			
			var pw = document.getElementById('pw');
			if( pw.value == ''){
				alert('비밀번호는 필수로 입력 값이 있어야합니다.')
				return ;
			}
			var confirmPw = document.getElementById('confirmPw');
			if( pw.value != confirmPw.value){
				alert('두 비밀번호는 같은 비밀번호로 입력하세요.')
				return ;
			}
			
			var formRef = document.getElementById('f')
			formRef.submit();
		}
		
		function cancelButton(){
			location.href='index';
		}
	</script>
</head>
<body>
	<form action="update" method="post" id="f">
		<input type="text" name="id" id="id" value="${sessionScope.id }"><br>
		<input type="password" name="pw" id="pw" placeholder="비밀번호"><br>
		<input type="password" name="confirmPw" id="confirmPw" placeholder="비밀번호 확인"><br>
		<input type="text" name="name" id="name" value="${sessionScope.name }" ><br>
		<input type="text" name="email" id="email" value="${sessionScope.email }"><br>
		<input type="button" value="회원 수정" onclick="updateButton()">
		<input type="button" value="취소" onclick="cancelButton();">
	</form>
</body>
</html>







