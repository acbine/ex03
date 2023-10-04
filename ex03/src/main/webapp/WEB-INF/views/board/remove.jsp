<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
삭제화면
삭제할꺼면 비밀번호1234입력
<form action="remove" method="post">
	<input type="hidden" name=bno value="${bno}">
	<input type="password" name="password">
	<input type="submit" value="삭제">
</form>

<%--footer 파일 넣기 --%>    
<%@ include file="../includes/footer.jsp" %>
<script>
var flag="${flag}" //"":삭제요청 or "fail":패스워드 오류
if(flag=="fail"){
	alert("패스워드를 확인해주세요.");
}
</script>
</body>
</html>