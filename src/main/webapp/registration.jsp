<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="WEB-INF/jsp/lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
 <div class="alert alert-danger">
 		 <strong><c:out value="${error.message}"/></strong>
 </div>
</c:forEach>
 <h2>Registration</h2>
<form action="./controller" method="post">
  <input type="hidden" name="command" value="registration"/>
  	<div class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, emailError ))}"> has-error</c:if>" >
      <label for="email">Email:</label>
      <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" value = "${email}">
    </div>
    <div class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, passwordEqualityError )||ctg:contains( errors, passwordError ))}"> has-error</c:if>">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
    </div>
     <div class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, passwordEqualityError )||ctg:contains( errors, passwordError ))}"> has-error</c:if>">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="secondPassword">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</body>
</html>