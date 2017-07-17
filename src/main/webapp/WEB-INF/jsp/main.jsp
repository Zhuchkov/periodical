<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html lang=en>
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
</head>
<body>
<%@include file="lib/header.jsp" %>
${sessionScope.user.id}
<br>
<a href="${pageContext.request.contextPath}/controller?command=getUserDetailsPage"> User details</a>
<br>
<a href="${pageContext.request.contextPath}/controller?command=getPeriodicalsSearchPage"> List of periodicals</a>
</body>
</html>