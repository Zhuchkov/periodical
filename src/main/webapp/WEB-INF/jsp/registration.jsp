<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
	<%@include file="lib/header.jsp"%>
	<div class="container">
		<c:forEach var="error" items="${errors}">
			<div class="alert alert-danger">
				<strong><c:out value="${error.message}" /></strong>
			</div>
		</c:forEach>
		<h2>
			<fmt:message key="registrationFormTitle" bundle="${bundle}" />
		</h2>
		<form action="./controller" method="post">
			<input type="hidden" name="command" value="registration" />
			<div
				class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, emailError ))}"> has-error</c:if>">
				<label for="email"><fmt:message key="registrationFormEmail"
						bundle="${bundle}" /></label> <input type="text" class="form-control"
					id="email"
					placeholder="<fmt:message key="registrationFormEmail" bundle="${bundle}"/>"
					name="email" value="${email}">
			</div>
			<div
				class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, passwordEqualityError )||ctg:contains( errors, passwordError ))}"> has-error</c:if>">
				<label for="pwd1"><fmt:message
						key="registrationFormFirstPassword" bundle="${bundle}" /></label> <input
					type="password" class="form-control" id="pwd1"
					placeholder="<fmt:message key="registrationFormFirstPassword" bundle="${bundle}"/>"
					name="password">
			</div>
			<div
				class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, passwordEqualityError )||ctg:contains( errors, passwordError ))}"> has-error</c:if>">
				<label for="pwd2"><fmt:message
						key="registrationFormSecondPassword" bundle="${bundle}" /></label> <input
					type="password" class="form-control" id="pwd2"
					placeholder="<fmt:message key="registrationFormSecondPassword" bundle="${bundle}"/>"
					name="secondPassword">
			</div>
			<button type="submit" class="btn btn-default">
				<fmt:message key="submitButton" bundle="${bundle}" />
			</button>
		</form>
	</div>
</body>
</html>