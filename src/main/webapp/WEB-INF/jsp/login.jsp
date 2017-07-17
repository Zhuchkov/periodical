<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>
<div class="container">
  <h2>Vertical (basic) form</h2>
  <c:if test="${userNotFound != null}">
 	 <div class="alert alert-danger">
 		 <strong>Email not found!</strong> .
	</div>
  </c:if>
    <c:if test="${wrongPassword != null}">
 	 <div class="alert alert-danger">
 		 <strong>Password incorrect!</strong> .
	</div>
  </c:if>
  <form action="./controller" method="post">
  <input type="hidden" name="command" value="login"/>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" value = "${email}">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>
<!-- 
<form action="./controller" method="post" >
	<input type="hidden" name="command" value="login"/>
	<br>
	<input type="text" name="email" value = "${email}" />${userNotFound}
	<br>
	<input type="password" name="password"/> ${wrongPassword}
	<br>
	<input type ="submit"/>
</form>
 -->
</body>
</html>