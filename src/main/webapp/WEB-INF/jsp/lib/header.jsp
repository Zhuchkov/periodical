<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%--Localization--%>
<c:if test="${sessionScope.locale == null}">
	<fmt:setLocale value="en" />
</c:if>
<c:if test="${sessionScope.locale != null}">
	<fmt:setLocale value="${sessionScope.locale}" />
</c:if>

<fmt:setBundle basename="localization" var="bundle" />
<%----%>

<fmt:setBundle basename="localization" var="bundle" />

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/controller"><fmt:message
					key="periodical" bundle="${bundle}" /></a>
		</div>
		<ul class="nav navbar-nav">
			<c:if test="${sessionScope.user != null}">
				<li><a
					href="${pageContext.request.contextPath}/controller?command=getUserDetailsPage"><fmt:message
							key="userInfoPage" bundle="${bundle}" /></a></li>
				<li><a
					href="${pageContext.request.contextPath}/controller?command=getPeriodicalCreationPage"><fmt:message
							key="createPeriodical" bundle="${bundle}" /></a></li>
			</c:if>
			<li><a
				href="${pageContext.request.contextPath}/controller?command=getPeriodicalsSearchPage"><fmt:message
						key="periodicalSearch" bundle="${bundle}" /></a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li>
				<form action="./controller" method="post" class=navbar-form>
					<input type="hidden" name="command" value="setLocale" /> <input
						type="hidden" name="previousCommand" value="${param.command }" />
					<div class="form-group">
						<select class="form-control" id="language" name="language"
							onchange="submit()">
							<option value="en"
								${sessionScope.locale == 'en' ? 'selected' : ''}>English</option>
							<option value="ru"
								${sessionScope.locale == 'ru' ? 'selected' : ''}>Russian</option>
						</select>
					</div>
				</form>
			</li>
			<c:if test="${sessionScope.user == null}">
				<li><a
					href="${pageContext.request.contextPath}/controller?command=getRegisterPage"><span
						class="glyphicon glyphicon-user"></span> <fmt:message
							key="registration" bundle="${bundle}" /> </a></li>
				<li><a href="${pageContext.request.contextPath}/controller?command=getLoginPage"><span
						class="glyphicon glyphicon-log-in"></span> <fmt:message
							key="login" bundle="${bundle}" /> </a></li>
			</c:if>
			<c:if test="${sessionScope.user != null}">
				<li><a href="#"><fmt:message key="loginAs"
							bundle="${bundle}" /> <ctg:info-user /></a></li>
				<li><a
					href="${pageContext.request.contextPath}/controller?command=logout"><span
						class="glyphicon glyphicon-user"></span> <fmt:message key="logout"
							bundle="${bundle}" /></a></li>
			</c:if>

		</ul>
	</div>
</nav>