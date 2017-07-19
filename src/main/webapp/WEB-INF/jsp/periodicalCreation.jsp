<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Periodical creation page</title>
</head>
<body>
	<%@include file="lib/header.jsp"%>
	<div class="container">
		<h1>
			<fmt:message key="periodicalCreationTitle" bundle="${bundle}" />
		</h1>

		<c:forEach var="error" items="${errors}">
			<div class="alert alert-danger">
				<strong><c:out value="${error.message}" /></strong>
			</div>
		</c:forEach>

		<form action="./controller" method="post">
			<input type="hidden" name="command" value="periodicalCreation" />

			<div
				class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, periodicalNameError ))}"> has-error</c:if>">
				<label for="name"><fmt:message key="periodicalCreationName"
						bundle="${bundle}" /></label> <input type="text" class="form-control"
					placeholder="<fmt:message key="periodicalCreationName" bundle="${bundle}"/>"
					name="name">
			</div>

			<label for="name"><fmt:message key="periodicalCreationCost"
					bundle="${bundle}" /></label>
			<div class="input-group">
				<span class="input-group-addon">$</span> <input name="cost"
					type="number" value="100" min="0" step="0.01"
					data-number-to-fixed="2" data-number-stepfactor="100"
					class="form-control currency" />
			</div>

			<div class="form-group">
				<label for="sel1"><fmt:message
						key="periodicalCreationSelectCategory" bundle="${bundle}" /></label> <select
					class="form-control" name="category">
					<c:forEach var="category" items="${categories}">
						<option value="${category.id}">${category.name}</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-default">
				<fmt:message key="submitButton" bundle="${bundle}" />
			</button>
		</form>

	</div>
</body>
</html>