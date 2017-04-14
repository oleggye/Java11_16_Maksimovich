<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Locale"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="local" value="${sessionScope.local}" />

<fmt:setLocale value="${local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.locbutton.name.en"
	var="en" />
<fmt:message bundle="${localBundle}" key="local.locbutton.name.ru"
	var="ru" />

<div class="droplocdown">

	<c:choose>

		<c:when test="${local eq Locale.ENGLISH}">

			<button class="droplocbtn">
				<span class="glyphicon glyphicon-globe"></span>
				<c:out value="${en}" />
			</button>

			<div class="droplocdown-content">
				<a href="#"><c:out value="${en}" /></a> <a
					href="controller?command=change-locale&local=ru"> <c:out
						value="${ru}" />
				</a>

			</div>
		</c:when>

		<c:otherwise>

			<button class="droplocbtn">
				<span class="glyphicon glyphicon-globe"></span>
				<c:out value="${ru}" />
			</button>

			<div class="droplocdown-content">
				<a href="controller?command=change-locale&local=en"> <c:out
						value="${en}" />
				</a> <a href="#"><c:out value="${ru}" /></a>
			</div>
		</c:otherwise>
	</c:choose>
</div>