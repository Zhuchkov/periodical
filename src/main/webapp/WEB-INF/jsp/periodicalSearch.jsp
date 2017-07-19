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
			<input type="hidden" name="searchParam" />

			<div
				class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, periodicalNameError ))}"> has-error</c:if>">
				<label for="publisherName"><fmt:message key="publisherName"
						bundle="${bundle}" /></label> <input id="publisherName" type="text"
					class="form-control"
					placeholder="<fmt:message key="publisherName" bundle="${bundle}"/>"
					name="publisherName">
			</div>

			<div
				class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, periodicalNameError ))}"> has-error</c:if>">
				<label for="periodicalName"><fmt:message
						key="periodicalName" bundle="${bundle}" /></label> <input
					id="periodicalName" type="text" class="form-control"
					placeholder="<fmt:message key="periodicalName" bundle="${bundle}"/>"
					name="periodicalName">
			</div>


			<label for="cost"><fmt:message
					key="periodicalCreationMaxCost" bundle="${bundle}" /></label>
			<div class="input-group">
				<span class="input-group-addon">$</span> <input id="cost"
					name="maxPrice" type="number" value="0" min="0" step="0.01"
					data-number-to-fixed="2" data-number-stepfactor="100"
					class="form-control currency" />
			</div>

			<div class="form-group">
				<label for="sel1"><fmt:message
						key="periodicalCreationSelectCategory" bundle="${bundle}" /></label> <select
					class="form-control" name="category">
					<c:set var="count" value="0" scope="page" />
					<c:forEach var="category" items="${categories}">
						<option value="${count}" 
						${searchParameters.sortParam ==null? "selected":(searchParameters.category.id == category.id ?"selected":"")}>
						${category.name}</option>
						<c:set var="count" value="${count + 1}" scope="page" />
					</c:forEach>
				</select>
			</div>
			<div class="radio">
				<label><input type="radio" name="sortParam" value="NAME"
				${searchParameters.sortParam ==null? "checked":(searchParameters.sortParam eq "NAME"?"checked":"")}>
				<fmt:message key="periodicalSearchByName" bundle="${bundle}" /></label>
			</div>
			<div class="radio">
				<label><input type="radio" name="sortParam" value="COST" ${searchParameters.sortParam eq "COST" ? "checked":""}>
				<fmt:message key="periodicalSearchByCost" bundle="${bundle}" /></label>
			</div>
			
			<div class="checkbox">
				<label><input type="checkbox" name="order" value="descending" ${searchParameters.descending ==true ?"checked":""}>
				<fmt:message key="periodicalSearchOrder" bundle="${bundle}" /></label>
			</div>

			<button type="submit" class="btn btn-default">
				<fmt:message key="submitButton" bundle="${bundle}" />
			</button>
		</form>
		<table class="table table-hover">
			<thead>
				<tr>
					<th><fmt:message key="periodicalName" bundle="${bundle}" /></th>
					<th><fmt:message key="periodicalCost" bundle="${bundle}" /></th>
					<th><fmt:message key="subscriptionButton" bundle="${bundle}" /></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="items" value="0" scope="page" />
				<c:forEach items="${foundPeriodicals}" var="periodical">
					<c:set var="items" value="${items + 1}" scope="page" />
					<tr>
						<td><c:out value="${periodical.name}"></c:out></td>
						<td><c:out value="${periodical.cost}"></c:out></td>
						<td> <c:if test="${(sessionScope.user != null)&&(periodical.publisher.id != sessionScope.user.id)}">
								<a href="${pageContext.request.contextPath}/controller?command=subscribe&periodicalId=${periodical.id}">
									<button type="button" class="btn btn-success">
									<fmt:message key="subscriptionButton" bundle="${bundle}" />
									</button>
									 </a>
							</c:if>
							<c:if test="${(sessionScope.user != null)&&(periodical.publisher.id == sessionScope.user.id)}">
									<button type="button" class="btn btn-primary">
									<fmt:message key="ownPeriodical" bundle="${bundle}" />
									</button>
							</c:if>
						</td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${searchParameters.pageNumber>0}">
		<form class="navbar-form navbar-left" action="./controller"
			method="post">
			
			<input type="hidden" name="command" value="getPeriodicalsSearchPage" />
			<input type="hidden" name="searchParam" />
			<input type="hidden" name="publisherName" value="${searchParameters.publisherName}">
			<input type="hidden" name="periodicalName" value="${searchParameters.periodicalName}">
			<input type="hidden" name="maxPrice" value="${searchParameters.maxCost}">
			<input type="hidden" name="category" value="${categoryOrigrn}">
			<input type="hidden" name="sortParam" value="${searchParameters.sortParam}">
			<input type="hidden" name="order" value=${searchParameters.descending?"descending":"" }>
			<input type="hidden" name="pageNumber" value="${searchParameters.pageNumber-1}">
			
			<button type="submit" class="btn btn-default">
				<fmt:message key="previousButton" bundle="${bundle}" />${searchParameters.pageNumber}
			</button>
		</form>
		</c:if>
		
		<c:if test="${items == searchParameters.pageLength}">
		<form class="navbar-form navbar-right" action="./controller"
			method="post">
			<input type="hidden" name="command" value="getPeriodicalsSearchPage" />
			<input type="hidden" name="searchParam" />
			<input type="hidden" name="publisherName" value="${searchParameters.publisherName}">
			<input type="hidden" name="periodicalName" value="${searchParameters.periodicalName}">
			<input type="hidden" name="maxPrice" value="${searchParameters.maxCost}">
			<input type="hidden" name="category" value="${categoryOrigrn}">
			<input type="hidden" name="sortParam" value="${searchParameters.sortParam}">
			<input type="hidden" name="order" value=${searchParameters.descending?"descending":"" }>
			<input type="hidden" name="pageNumber" value="${searchParameters.pageNumber+1}">
			<button type="submit" class="btn btn-default">
				<fmt:message key="nextButton" bundle="${bundle}" />${searchParameters.pageNumber+2}
			</button>
		</form>
		</c:if>
	</div>
</body>
</html>