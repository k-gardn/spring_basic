<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<%
	
	String id = (String)session.getAttribute("id");
	if(id == null){
		response.sendRedirect("login.jsp");
		return;
	}
	
	%>
	<script>
		function deleteButton(){
			
			var formRef = document.getElementById('f');
			formRef.submit(); //submit 버튼을 누른효과를 얻을 수 있음.
		}
		function cancelButton(){
			location.href='index.jsp';
		}
	</script>
	<form action="deleteService.jsp" method="post" id="f">
	<input type="text" name="id" id="id" readonly="readonly" value="<%=id %>"><br>
		<input type="password" name="pw" id="pw" placeholder="비밀번호"><br>
		<input type="password" name="confirmPw" id="confirmPw" placeholder="비밀번호 확인"><br>
		<input type="button" value="회원 삭제" onclick="deleteButton();">
		<input type="button" value="취소" onclick="cancelButton();">
	</form>
</body>
</html>