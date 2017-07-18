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
	<c:forEach var="error" items="${errors}">
		<div class="alert alert-danger">
			<strong><c:out value="${error.message}" /></strong>
		</div>
	</c:forEach>

	<form action="./controller" method="post">
		<input type="hidden" name="command" value="userDetailUpdate" />

		<div
			class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, firstNameError ))}"> has-error</c:if>">
			<label for="firstName">First name: <c:out
					value="${userDetails.firstName}"></c:out>
			</label> <input type="text" class="form-control" placeholder="new first name"
				name="firstName">
		</div>

		<div
			class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, lastNameError ))}"> has-error</c:if>">
			<label for="lastName">Last name: <c:out
					value="${userDetails.lastName}"></c:out>
			</label> <input type="text" class="form-control" placeholder="new last name"
				name="lastName">
		</div>

		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</body>
</html>