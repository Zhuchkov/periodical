<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html lang="en">
<head>
<%@include file="lib/bootstrapIncluds.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Periodical entry creation</title>
</head>
<body>
<%@include file="lib/header.jsp" %>
<h1>Periodical entry creation</h1>
<c:forEach var="error" items="${errors}">
 <div class="alert alert-danger">
 		 <strong><c:out value="${error.message}"/></strong>
 </div>
 </c:forEach>
<h3> name of periodical: <c:out value="${periodical.name}"/></h3>
<p>periodical's cost: <c:out value="${periodical.cost}"/>$</p>
  <form action="./controller" method="post">
 <input type="hidden" name="command" value="entryCreation"/>
	<input type="hidden" name="periodicalId" value="${periodical.id}"/>
    <div class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, entryNameError ))}"> has-error</c:if>">
      <label for="entryName">Entry name</label>
      <input type="text" class="form-control" id="entryName" placeholder="Entry name" name="entryName" >
    </div>
    <div class="form-group <c:if test="${errors!=null&&(ctg:contains( errors, entryTextError ))}"> has-error</c:if>">
      <label for="entryText">Entry text:</label>
      <textarea class="form-control" rows="5" id="entryText" name="entryText" placeholder="Enter text"></textarea>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>

</body>
</html>