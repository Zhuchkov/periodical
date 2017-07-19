<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang=<c:out value=" ${sessionScope.locale}"></c:out>>
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Entry page</title>
</head>
<body>
<%@include file="lib/header.jsp" %>
<c:forEach items = "${entries}" var = "entry">
<c:out value="${entry.name}"/>
<c:out value="${entry.data}"/>
<br>
</c:forEach>

</body>
</html>