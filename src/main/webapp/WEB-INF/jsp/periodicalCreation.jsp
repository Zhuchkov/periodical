<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html lang = "en">
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Periodical creation page</title>
</head>
<body>
<%@include file="lib/header.jsp" %>
<form action="./controller" method="post" >
	<input type="hidden" name="command" value="periodicalCreation"/>
	<br>
	<input type="text" name="name" />	
	<br>
	<input type="text" name="cost"/>
	<br>
	<select name="category">
    	<c:forEach var="category" items="${categories}">
        	<option value="${category.id}">${category.name}</option>
   	 	</c:forEach>
	</select>
	<input type ="submit"/>
</form>

</body>
</html>