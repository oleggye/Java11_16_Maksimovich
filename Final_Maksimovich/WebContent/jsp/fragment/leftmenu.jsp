<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<div class="col-sm-2 sidenav">
	<div class="vertical-menu">
		<p />
		<div class="dropdown">
			<button class="dropbtn">
				<fmt:message bundle="${localBundle}" key="local.menu.football" />
			</button>
			<div class="dropdown-content">
				<a href="#"><fmt:message bundle="${localBundle}"
						key="local.menu.football.champligue" /></a> <a href="#"><fmt:message
						bundle="${localBundle}" key="local.menu.football.europigue" /></a> <a
					href="#"><fmt:message bundle="${localBundle}"
						key="local.menu.football.spainprimera" /></a> <a href="#"><fmt:message
						bundle="${localBundle}" key="local.menu.football.engprimier" /></a>
			</div>
		</div>
		<p />
		<div class="dropdown">
			<button class="dropbtn">
				<fmt:message bundle="${localBundle}" key="local.menu.tennis" />
			</button>
			<div class="dropdown-content">
				<a href="#"><fmt:message bundle="${localBundle}"
						key="local.menu.tennis.daviscup" /></a>
			</div>
		</div>
		<p />
		<div class="dropdown">
			<button class="dropbtn">
				<fmt:message bundle="${localBundle}" key="local.menu.basketball" />
			</button>
			<div class="dropdown-content">
				<a href="#"><fmt:message bundle="${localBundle}"
						key="local.menu.basketball.nba" /></a> <a href="#"><fmt:message
						bundle="${localBundle}" key="local.menu.basketball.euroliguemen" /></a>
			</div>
		</div>
		<p />
		<div class="dropdown">
			<button class="dropbtn">
				<fmt:message bundle="${localBundle}" key="local.menu.hockey" />
			</button>
			<div class="dropdown-content">
				<a href="#"><fmt:message bundle="${localBundle}"
						key="local.menu.hockey.nhl" /></a> <a href="#"> <fmt:message
						bundle="${localBundle}" key="local.menu.hockey.khl" /></a>
			</div>
		</div>
	</div>
</div>