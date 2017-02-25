<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.header.title"
	var="title" />
<fmt:message bundle="${localBundle}" key="local.header.home" var="home" />
<fmt:message bundle="${localBundle}" key="local.header.results"
	var="results" />
<fmt:message bundle="${localBundle}" key="local.header.statistics"
	var="statistics" />
<fmt:message bundle="${localBundle}" key="local.header.information"
	var="information" />
<fmt:message bundle="${localBundle}" key="local.header.contacts"
	var="contacts" />

<header>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="jsp/home.jsp?command=home"> <c:out
						value="${title}" /></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="controller?command=home"><span
							class="glyphicon glyphicon-home"></span> <c:out value="${home}" /></a></li>
					<li><a href="controller?command=result"><c:out
								value="${results}" /></a></li>
					<li><a href="#"><c:out value="${statistics}" /></a></li>
					<li><a href="#"><c:out value="${information}" /></a></li>
					<%-- <li><a href="#"><c:out value="${contacts}" /></a></li> --%>
				</ul>

				<ul class="nav navbar-nav navbar-right">

					<c:import url="/jsp/fragment/authorizationIcon.jsp" />
					<li><c:import url="/jsp/fragment/localization.jsp" /></li>
				</ul>
			</div>
		</div>
	</nav>
</header>