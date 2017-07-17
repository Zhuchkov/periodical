<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>

<form action="./controller" method="post" >
	<input type="hidden" name="command" value="registration"/>
	<br>
	<input type="text" name="email" />	
	<br>
	<input type="password" name="password"/>
	<br>
	<input type ="submit"/>
</form>
</body>
</html>