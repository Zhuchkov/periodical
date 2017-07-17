<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Periodical entry creation</title>
</head>
<body>
<%@include file="lib/header.jsp" %>
<c:out value="${periodical.name}"/>
<c:out value="${periodical.cost}"/>

<form action="./controller" method="post" >
	<input type="hidden" name="command" value="entryCreation"/>
	<input type="hidden" name="periodicalId" value="${periodical.id}"/>
	<br>
	<input type="text" name="entryName" value="${entryParam.entryName }"/>	
	<br>
	<input type="text" name="entryText"value="${entryParam.entryText }"/>
	<br>
	<input type ="submit"/>
</form>
</body>
</html>