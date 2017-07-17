<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Periodical search</title>
</head>
<body>
<%@include file="lib/header.jsp" %>
<form action="./controller" method="post" >
	<input type="hidden" name="command" value="getPeriodicalsSearchPage"/>
	<input type="hidden" name="searchParam"/>
	<br>
	<input type="text" name="publisherName" />	
	<br>
	<input type="text" name="periodicalName" />	
	<br>
	<input type="text" name="maxPrice" value = "0"/>
	<br>
	<select name="category">
		<c:set var="count" value="0" scope="page" />
    	<c:forEach var="category" items="${categories}">
        	<option value="${count}">${category.name}</option>
    	    <c:set var="count" value="${count + 1}" scope="page"/>
   	 	</c:forEach>
	</select>
	<Br>
	<p>sort</p>
	<input type="radio" name="sortParam" value="NAME" checked>by name<Br>
 	<input type="radio" name="sortParam" value="PRICE">by cost<Br>
 	<input type="checkbox" name="order" value="descending">descending order<br>
	<input type ="submit"/>
</form>
<c:forEach items = "${foundPeriodicals}" var = "periodical">
<c:out value="${periodical.name}"></c:out>
<c:out value="${periodical.cost}"></c:out>

<c:if test="${(sessionScope.user != null)&&(periodical.publisher.id != sessionScope.user.id)}">
	<a href="${pageContext.request.contextPath}/controller?command=subscribe&periodicalId=${periodical.id}"> subscribe </a>
</c:if>
<br>
</c:forEach>
</body>
</html>