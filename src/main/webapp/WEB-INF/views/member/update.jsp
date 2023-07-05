<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
	<%
		String id = (String)session.getAttribute("id");
		if(id == null){ // 로그인이 안 되어있을 때,
			response.sendRedirect("login.jsp"); //로그인 페이지로 이동하게함.
			return;
		}
	%>
	
	<script>
		// 자바스크립트 작성
		function updateButton(){
			var id = document.getElementById('id');
			if(id.value == ''){
				alert('아이디는 필수로 입력 값이 있어야합니다.')
				return;
			}
			//alert('아이디 : ' + id.value);
			
			var pw = document.getElementById('pw');
			if(pw.value == ''){
				alert('비밀번호는 필수로 입력 값이 있어야합니다.')
				return;
			}
			var confirmPw = document.getElementById('confirmPw');
			if(pw.value != confirmPw.value){
				alert('두 비밀번호는 같은 비밀번호로 입력하세요.')
				return;
			}
			var formRef = document.getElementById('f');
			formRef.submit(); //submit 버튼을 누른효과를 얻을 수 있음.
		}
		
		function cancelButton(){
			location.href='index.jsp';
		}
	</script>
	
	<form action="updateService.jsp" method="post" id="f">
		<input type="text" name="id" id="id" value="<%=session.getAttribute("id") %>"><br>
		<input type="password" name="pw" id="pw" placeholder="비밀번호"><br>
		<input type="password" name="confirmPw" id="confirmPw" placeholder="비밀번호 확인"><br>
		<input type="text" name="name" id="name" value="<%=session.getAttribute("name") %>"><br>
		<input type="text" name="email" id="email"value="<%=session.getAttribute("email") %>"><br>
		<input type="button" value="회원 수정" onclick="updateButton();">
		<input type="button" value="취소" onclick="cancelButton();">
	</form>
</body>
</html>



