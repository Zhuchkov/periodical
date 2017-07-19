<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Periodical search</title>
</head>
<body>
	<%@include file="lib/header.jsp"%>
	<div class="container">
		<c:forEach var="error" items="${errors}">
			<div class="alert alert-danger">
				<strong><c:out value="${error.message}" /></strong>
			</div>
		</c:forEach>

		<form action="./controller" method="post">
			<input type="hidden" name="command" value="getPeriodicalsSearchPage" />
			<input type="hidden" name="searchParam" /> <br> <input
				type="text" name="publisherName" /> <br> <input type="text"
				name="periodicalName" /> <br> <input type="text"
				name="maxPrice" value="0" /> <br> <select name="category">
				<c:set var="count" value="0" scope="page" />
				<c:forEach var="category" items="${categories}">
					<option value="${count}">${category.name}</option>
					<c:set var="count" value="${count + 1}" scope="page" />
				</c:forEach>
			</select> <Br>
			<p>
				<fmt:message key="periodicalSearchSort" bundle="${bundle}" />
			</p>
			<input type="radio" name="sortParam" value="NAME" checked>
			<fmt:message key="periodicalSearchByName" bundle="${bundle}" />
			<Br> <input type="radio" name="sortParam" value="PRICE">
			<fmt:message key="periodicalSearchByCost" bundle="${bundle}" />
			<Br> <input type="checkbox" name="order" value="descending">
			<fmt:message key="periodicalSearchOrder" bundle="${bundle}" />
			<br> <input type="submit"
				value=<fmt:message key="submitButton" bundle="${bundle}"/>>
		</form>
		<c:forEach items="${foundPeriodicals}" var="periodical">
			<c:out value="${periodical.name}"></c:out>
			<c:out value="${periodical.cost}"></c:out>

			<c:if
				test="${(sessionScope.user != null)&&(periodical.publisher.id != sessionScope.user.id)}">
				<a
					href="${pageContext.request.contextPath}/controller?command=subscribe&periodicalId=${periodical.id}">
					subscribe </a>
			</c:if>
			<br>
		</c:forEach>
	</div>
</body>
</html>