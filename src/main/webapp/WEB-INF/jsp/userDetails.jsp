<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User details</title>
</head>
<body>
<%@include file="lib/header.jsp" %>
${userDetails.firstName}
<br>
${userDetails.lastName}
<br>
<fmt:formatNumber value="${userDetails.money}" type="currency" />
<br>
<p>subscriptions:</p>
<c:forEach items = "${userDetails.subscriptions}" var = "subscription">
<c:out value="${subscription.periodical.name}"/>
<c:out value="${subscription.periodical.cost}"/>
<c:if test="${subscription.updated}">
	<a href="${pageContext.request.contextPath}/controller?command=paySubscriptionFee&subscriptionId=${subscription.id}"> pay for access</a>
</c:if>
<c:if test="${subscription.active}">
	<a href="${pageContext.request.contextPath}/controller?command=getEntryPage&subscriptionId=${subscription.id}"> get entries</a>
</c:if>
<br>
</c:forEach>
<br>
<p>periodicals:</p>
<c:forEach items = "${userDetails.periodicals}" var = "periodical">
<c:out value="${periodical.name}"/>
<c:out value="${periodical.cost}"/>
<a href="${pageContext.request.contextPath}/controller?command=getPeriodicalEntryCreationPage&periodicalId=${periodical.id}"> add periodical entry</a>
<br>
</c:forEach>
<br>
<a href="${pageContext.request.contextPath}/controller?command=getPeriodicalCreationPage"> create periodical </a>
<br>
<a href="${pageContext.request.contextPath}/controller?command=getUserDetailsUpdatePage"> update user details</a>
<br>
<a href="${pageContext.request.contextPath}/controller?command=getMainPage"> main </a>
</body>
</html>