<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User details </title>
</head>
<body>
<%@include file="lib/header.jsp" %>
<form action="./controller" method="post" >
	<input type="hidden" name="command" value="userDetailUpdate"/>
	<br>
	<input type="text" name="first name" value="${userDetails.firstName}"/>	
	<br>
	<input type="text" name="last name" value="${userDetails.lastName}"/>
	<br>
	<input type ="submit"/>
</form>
</body>
</html>