<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
    <li><a href="${pageContext.request.contextPath}/controller">Home Page</a></li>
      <c:if test="${sessionScope.user != null}">
     	<li><a href="${pageContext.request.contextPath}/controller?command=getUserDetailsPage">User info page</a></li>
     	<li><a href="${pageContext.request.contextPath}/controller?command=getPeriodicalCreationPage">Create periodical</a></li>	
      </c:if>
      <li><a href="${pageContext.request.contextPath}/controller?command=getPeriodicalsSearchPage">Periodical search</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <c:if test="${sessionScope.user == null}">
      <li><a href="${pageContext.request.contextPath}/registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="${pageContext.request.contextPath}/login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </c:if>
    <c:if test="${sessionScope.user != null}">
    	<li><a href="#"></span> Login as: <ctg:info-user/></a></li>
    	<li><a href="${pageContext.request.contextPath}/controller?command=logout"><span class="glyphicon glyphicon-user"></span> Logout</a></li>
    </c:if>
    
    </ul>
  </div>
</nav>