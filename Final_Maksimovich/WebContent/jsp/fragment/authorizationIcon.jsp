<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.header.signout"
	var="signout" />
<fmt:message bundle="${localBundle}" key="local.header.signin"
	var="signin" />
<fmt:message bundle="${localBundle}" key="local.header.signup"
	var="signup" />


<c:set var="login" value="${sessionScope.login }" />

<c:choose>
	<c:when test="${not empty login}">

		<li><a href="controller?command=userInfo"> <span
				class="glyphicon glyphicon-user"></span> <c:out value="${login}" />
		</a></li>
		<li><a href="controller?command=signOut"><span
				class="glyphicon glyphicon-log-out"></span> <c:out
					value="${signout}" /></a></li>


	</c:when>

	<c:otherwise>
		<li><a href="controller?command=signIn"> <c:out
					value="${signin}" /></a></li>
		<li><a href="controller?command=signUp"> <c:out
					value="${signup}" /></a></li>

	</c:otherwise>
</c:choose>

