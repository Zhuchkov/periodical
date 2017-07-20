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
		<b><fmt:message key="userFirstName" bundle="${bundle}" /></b>${userDetails.firstName}
		<br>
		<b><fmt:message key="userLastName" bundle="${bundle}" /></b>${userDetails.lastName}
		<br>
		<b><fmt:message key="userAccount" bundle="${bundle}" /></b>
		<c:out value="${userDetails.money}"></c:out>
		<br>
		 <a	href="${pageContext.request.contextPath}/controller?command=getUserDetailsUpdatePage">
		 	<button type="button" class="btn btn-primary">
		 		<fmt:message key="updateUserDetails" bundle="${bundle}" />
		 	</button>
		 </a>
		 <br> <br>
		<h3>
			<fmt:message key="userSubscription" bundle="${bundle}" />
		</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th><fmt:message key="periodicalName" bundle="${bundle}" /></th>
					<th><fmt:message key="periodicalCost" bundle="${bundle}" /></th>
					<th><fmt:message key="actions" bundle="${bundle}" /></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="items" value="0" scope="page" />
				<c:forEach items="${userDetails.subscriptions}" var="subscription">
					<c:set var="items" value="${items + 1}" scope="page" />
					<tr>
						<td><c:out value="${subscription.periodical.name}" /></td>
						<td><c:out value="${subscription.periodical.cost}" /></td>
						<td>
						<c:if test="${subscription.active}">
							<a href="${pageContext.request.contextPath}/controller?command=getEntryPage&subscriptionId=${subscription.id}">
								<button type="button" class="btn btn-primary">
								<fmt:message key="userSubscriptionGetEntries" bundle="${bundle}" />
								</button>
							</a>
						</c:if>
						</td>
						<td>
						<c:if test="${subscription.updated}">
							<a href="${pageContext.request.contextPath}/controller?command=paySubscriptionFee&subscriptionId=${subscription.id}">
								<button type="button" class="btn btn-primary">
								<fmt:message key="userSubscriptionPay" bundle="${bundle}" />
								</button>
							</a>
						</c:if>
						</td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${paginationParams.subscriptionPage>0}">
		<form class="navbar-form navbar-left" action="./controller"
			method="post">
			<input type="hidden" name="command" value="getUserDetailsPage" />
			<input type="hidden" name="subscriptionPage" value="${paginationParams.subscriptionPage-1}">
			<input type="hidden" name="periodicalPage" value="${paginationParams.periodicalPage}">
			<button type="submit" class="btn btn-default">
				<fmt:message key="previousButton" bundle="${bundle}" />(${paginationParams.subscriptionPage})
			</button>
		</form>
		</c:if>
		
		<c:if test="${items == paginationParams.subscriptionPageLength}">
		<form class="navbar-form navbar-right" action="./controller"
			method="post">
			<input type="hidden" name="command" value="getUserDetailsPage" />
			<input type="hidden" name="subscriptionPage" value="${paginationParams.subscriptionPage+1}">
			<input type="hidden" name="periodicalPage" value="${paginationParams.periodicalPage}">
			<button type="submit" class="btn btn-default">
				<fmt:message key="nextButton" bundle="${bundle}" />(${paginationParams.subscriptionPage+2})
			</button>
		</form>
		</c:if>
		<br>
		
		<h3><fmt:message key="userPeriodicals" bundle="${bundle}" /></h3>
		
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th><fmt:message key="periodicalName" bundle="${bundle}" /></th>
					<th><fmt:message key="periodicalCost" bundle="${bundle}" /></th>
					<th><fmt:message key="actions" bundle="${bundle}" /></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="items" value="0" scope="page" />
				<c:forEach items="${userDetails.periodicals}" var="periodical">
					<c:set var="items" value="${items + 1}" scope="page" />
					<tr>
						<td><c:out value="${periodical.name}"></c:out></td>
						<td><c:out value="${periodical.cost}"></c:out></td>
						<td>
						<a href="${pageContext.request.contextPath}/controller?command=getPeriodicalEntryCreationPage&periodicalId=${periodical.id}">
							<button type="button" class="btn btn-primary">
								<fmt:message key="addEntry" bundle="${bundle}" />
							</button>
						</a>
						</td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${paginationParams.subscriptionPage>0}">
		<form class="navbar-form navbar-left" action="./controller"
			method="post">
			<input type="hidden" name="command" value="getUserDetailsPage" />
			<input type="hidden" name="subscriptionPage" value="${paginationParams.subscriptionPage}">
			<input type="hidden" name="periodicalPage" value="${paginationParams.periodicalPage-1}">
			<button type="submit" class="btn btn-default">
				<fmt:message key="previousButton" bundle="${bundle}" />(${paginationParams.periodicalPage})
			</button>
		</form>
		</c:if>
		
		<c:if test="${items == paginationParams.periodicalPageLength}">
		<form class="navbar-form navbar-right" action="./controller"
			method="post">
			<input type="hidden" name="command" value="getUserDetailsPage" />
			<input type="hidden" name="subscriptionPage" value="${paginationParams.subscriptionPage}">
			<input type="hidden" name="periodicalPage" value="${paginationParams.periodicalPage+1}">
			<button type="submit" class="btn btn-default">
				<fmt:message key="nextButton" bundle="${bundle}" />(${paginationParams.periodicalPage+2})
			</button>
		</form>
		</c:if>
		
		
	</div>
</body>
</html>