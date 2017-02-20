<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="controller?command=home"> <fmt:message
					bundle="${localBundle}" key="local.header.title" /></a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="controller?command=home"><span
						class="glyphicon glyphicon-home"></span> <fmt:message
							bundle="${localBundle}" key="local.header.home" /></a></li>
				<li><a href="#"><fmt:message bundle="${localBundle}"
							key="local.header.results" /></a></li>
				<li><a href="#"><fmt:message bundle="${localBundle}"
							key="local.header.statistics" /></a></li>
				<li><a href="#"><fmt:message bundle="${localBundle}"
							key="local.header.information" /></a></li>
				<li><a href="#"><fmt:message bundle="${localBundle}"
							key="local.header.contacts" /></a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">

				<c:import url="/jsp/fragment/authorizationIcon.jsp" />
				<li><c:import url="/jsp/fragment/localization.jsp" /></li>
			</ul>
		</div>
	</div>
</nav>
