<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="ctg" uri="customtags" %>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>
<%@include file="lib/header.jsp" %>
<div class="container">
<c:forEach var="error" items="${errors}">
 <div class="alert alert-danger">
 		 <strong><c:out value="${error.message}"/></strong>
 </div>
</c:forEach>
  <h2>
			<fmt:message key="loginFormTitle" bundle="${bundle}" />
		</h2>
  <form action="./controller" method="post">
  <input type="hidden" name="command" value="login"/>
    <div class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, emailError ))}"> has-error</c:if>">
      <label for="email"><fmt:message key="loginFormEmail" bundle="${bundle}"/></label>
      <input type="text" class="form-control" id="email" placeholder="<fmt:message key="loginFormEmail" bundle="${bundle}"/>" name="email" value = "${email}">
    </div>
    <div class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, passwordError ))}"> has-error</c:if>">
      <label for="pwd"><fmt:message key="loginFormPassword" bundle="${bundle}"/></label>
      <input type="password" class="form-control" id="pwd" placeholder="<fmt:message key="loginFormPassword" bundle="${bundle}"/>" name="password">
    </div>
    <button type="submit" class="btn btn-default"><fmt:message key="submitButton" bundle="${bundle}"/></button>
  </form>
</div>
</body>
</html>