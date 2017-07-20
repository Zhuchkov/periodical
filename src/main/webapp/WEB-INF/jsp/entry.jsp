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
<div class="container">
<h1><fmt:message key="entryTitle" bundle="${bundle}" /></h1>
<c:set var="items" value="0" scope="page" />
<c:forEach items = "${entries}" var = "entry">
<c:set var="items" value="${items + 1}" scope="page" />
<b><fmt:message key="entryName" bundle="${bundle}" /></b><c:out value="${entry.name}"/>
<br>
<b><fmt:message key="entryDate" bundle="${bundle}" /></b><c:out value="${entry.creationTime}"/>
<br>
<b><fmt:message key="entryText" bundle="${bundle}" /></b><p><c:out value="${entry.data}"/></p>
<br>
</c:forEach>
		<c:if test="${items == inputParams.entryPageLength}">
		<form class="navbar-form navbar-left" action="./controller"
			method="post">
			<input type="hidden" name="command" value="getEntryPage" />
			<input type="hidden" name="subscriptionId" value="${inputParams.subscriptionId}">
			<input type="hidden" name="entryPage" value="${inputParams.entryPage+1}">
			<button type="submit" class="btn btn-default">
				<fmt:message key="oldButton" bundle="${bundle}" />
			</button>
		</form>
		</c:if>
		
		<c:if test="${inputParams.entryPage>0}">
		<form class="navbar-form navbar-right" action="./controller"
			method="post">
			<input type="hidden" name="command" value="getEntryPage" />
			<input type="hidden" name="subscriptionId" value="${inputParams.subscriptionId}">
			<input type="hidden" name="entryPage" value="${inputParams.entryPage-1}">
			<button type="submit" class="btn btn-default">
				<fmt:message key="newButton" bundle="${bundle}" />
			</button>
		</form>
		</c:if>
</div>
</body>
</html>