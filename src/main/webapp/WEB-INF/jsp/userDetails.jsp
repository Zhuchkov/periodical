<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User details</title>
</head>
<body>
	<%@include file="lib/header.jsp"%>
	<div class="container">
		<fmt:message key="userFirstName" bundle="${bundle}" />${userDetails.firstName}
		<br>
		<fmt:message key="userLastName" bundle="${bundle}" />${userDetails.lastName}
		<br>
		<fmt:message key="userAccount" bundle="${bundle}" />
		<c:out value="${userDetails.money}"></c:out>
		<br>
		<p>
			<fmt:message key="userSubscription" bundle="${bundle}" />
		</p>
		<c:forEach items="${userDetails.subscriptions}" var="subscription">
			<c:out value="${subscription.periodical.name}" />
			<c:out value="${subscription.periodical.cost}" />
			<c:if test="${subscription.updated}">
				<a
					href="${pageContext.request.contextPath}/controller?command=paySubscriptionFee&subscriptionId=${subscription.id}"><fmt:message
						key="userSubscriptionPay" bundle="${bundle}" /></a>
			</c:if>
			<c:if test="${subscription.active}">
				<a
					href="${pageContext.request.contextPath}/controller?command=getEntryPage&subscriptionId=${subscription.id}"><fmt:message
						key="userSubscriptionGetEntries" bundle="${bundle}" /></a>
			</c:if>
			<br>
		</c:forEach>
		<br>
		<p>
			<fmt:message key="userPeriodicals" bundle="${bundle}" />
		</p>
		<c:forEach items="${userDetails.periodicals}" var="periodical">
			<c:out value="${periodical.name}" />
			<c:out value="${periodical.cost}" />
			<a
				href="${pageContext.request.contextPath}/controller?command=getPeriodicalEntryCreationPage&periodicalId=${periodical.id}"><fmt:message
					key="addEntry" bundle="${bundle}" /></a>
			<br>
		</c:forEach>
		<br> <a
			href="${pageContext.request.contextPath}/controller?command=getUserDetailsUpdatePage"><fmt:message
				key="updateUserDetails" bundle="${bundle}" /></a>
	</div>
</body>
</html>