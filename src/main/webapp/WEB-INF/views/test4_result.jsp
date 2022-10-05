<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test4의 결과 페이지 입니다.</h1>
	<h3>전송 결과</h3>
	<h3>user : ${mediaVO.user}</h3>
	<h3>url : ${mediaVO.url}</h3>
	
	<c:forEach items="${mediaVO.mediaFile}" var="m">
		<li>${m}</li>
	</c:forEach>
</body>
</html>