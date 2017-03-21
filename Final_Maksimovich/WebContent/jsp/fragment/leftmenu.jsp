<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.menu.football"
	var="football" />
<fmt:message bundle="${localBundle}"
	key="local.menu.football.champligue" var="champligue" />
<fmt:message bundle="${localBundle}"
	key="local.menu.football.europligue" var="europligue" />
<fmt:message bundle="${localBundle}"
	key="local.menu.football.spainprimera" var="spainprimera" />
<fmt:message bundle="${localBundle}"
	key="local.menu.football.engprimier" var="engprimier" />

<fmt:message bundle="${localBundle}" key="local.menu.tennis"
	var="tennis" />
<fmt:message bundle="${localBundle}" key="local.menu.tennis.daviscup"
	var="daviscup" />

<fmt:message bundle="${localBundle}" key="local.menu.basketball"
	var="basketball" />
<fmt:message bundle="${localBundle}" key="local.menu.basketball.nba"
	var="nba" />
<fmt:message bundle="${localBundle}"
	key="local.menu.basketball.euroliguemen" var="euroliguemen" />

<fmt:message bundle="${localBundle}" key="local.menu.hockey"
	var="hockey" />
<fmt:message bundle="${localBundle}" key="local.menu.hockey.nhl"
	var="nhl" />
<fmt:message bundle="${localBundle}" key="local.menu.hockey.khl"
	var="khl" />

<div class="col-sm-2 sidenav">
	<div class="vertical-menu">
		<p />
		<div class="dropdown">
			<button class="dropbtn">
				<c:out value="${football}" />
			</button>
			<div class="dropdown-content">
				<a href="controller?command=tournament-page&idSport=1&idTournament=1">
					<c:out value="${champligue}" />
				</a> <a href="controller?command=tournament-page&idSport=1&idTournament=2">
					<c:out value="${europligue}" />
				</a> <a href="controller?command=tournament-page&idSport=1&idTournament=3"><c:out
						value="${spainprimera}" /></a> <a
					href="controller?command=tournament-page&idSport=1&idTournament=4"><c:out
						value="${engprimier}" /></a>
			</div>
		</div>
		<p />
		<div class="dropdown">
			<button class="dropbtn">
				<c:out value="${tennis}" />
			</button>
			<div class="dropdown-content">
				<a href="controller?command=tournament-page&idSport=2&idTournament=5"><c:out
						value="${daviscup}" /></a>
			</div>
		</div>
		<p />
		<div class="dropdown">
			<button class="dropbtn">
				<c:out value="${basketball}" />
			</button>
			<div class="dropdown-content">
				<a href="controller?command=tournament-page&idSport=3&idTournament=6">
					<c:out value="${nba}" />
				</a> <a href="controller?command=tournament-page&idSport=3&idTournament=7">
					<c:out value="${euroliguemen}" />
				</a>
			</div>
		</div>
		<p />
		<div class="dropdown">
			<button class="dropbtn">
				<c:out value="${hockey}" />
			</button>
			<div class="dropdown-content">
				<a href="controller?command=tournament-page&idSport=4&idTournament=8">
					<c:out value="${nhl}" />
				</a> <a href="controller?command=tournament-page&idSport=4&idTournament=9">
					<c:out value="${khl}" />
				</a>
			</div>
		</div>
	</div>
</div>