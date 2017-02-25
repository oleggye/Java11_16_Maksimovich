<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>MegaBet</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css\bootstrap.min.css">
<link rel="stylesheet" href="css\pagination.css">
<link rel="stylesheet" href="css\hoverdropdown.css">
<link rel="stylesheet" href="css\localizationdropdown.css">
<link rel="stylesheet" href="css\verticalmenu.css">
<link rel="stylesheet" href="css\style.css">
<script src="js\jquery.min.js"></script>
<script src="js\bootstrap.min.js"></script>
<script src="js\dropdown.js"></script>

</head>
<body>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="localization.local" var="localBundle" />
	<c:set var="competitons" value="${requestScope.competitions}" />


	<fmt:message bundle="${localBundle}" key="local.main.date" var="date" />
	<fmt:message bundle="${localBundle}" key="local.main.participants"
		var="participants" />
	<fmt:message bundle="${localBundle}" key="local.main.place" var="place" />
	<fmt:message bundle="${localBundle}" key="local.main.home" var="home" />
	<fmt:message bundle="${localBundle}" key="local.main.draw" var="draw" />
	<fmt:message bundle="${localBundle}" key="local.main.away" var="away" />
	<fmt:message bundle="${localBundle}" key="local.main.action"
		var="action" />
	<fmt:message bundle="${localBundle}" key="local.main.tournament"
		var="tournament" />
	<fmt:message bundle="${localBundle}" key="local.main.tournament"
		var="tournament" />


	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">
			<c:import url="/jsp/fragment/leftmenu.jsp" />

			<div class="col-sm-8 text-left">

				<h2>
					<c:out value="${competitons[0].tournament.name}" />
				</h2>

				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th><c:out value="${date}" /></th>
							<th><c:out value="${participants}" /></th>
							<th><c:out value="${place}" /></th>
							<th><c:out value="${home}" /></th>
							<th><c:out value="${draw}" /></th>
							<th><c:out value="${away}" /></th>
							<th><c:out value="${action}" /></th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="elem" items="${competitons}">

							<tr>
								<td><c:out value="${ elem.id}" /></td>
								<td><c:out value="${ elem.startTime}" /></td>

								<c:set var="delim" value=" vs " />
								<c:set var="participants"
									value="${elem.homeTeam.club.name}${delim}${elem.awayTeam.club.name}" />

								<td><c:out value="${ participants}" /></td>
								<td><c:out value="${ elem.country.name}" /></td>
								<td><c:out value="${ elem.winHomeRate}" /></td>
								<td><c:out value="${ elem.drawRate}" /></td>
								<td><c:out value="${ elem.winAwayRate}" /></td>
								<td><a href="#">Bet</a></td>
							</tr>

						</c:forEach>

					</tbody>
				</table>

				<c:import url="/jsp/fragment/pagination.jsp" />

			</div>
			<c:import url="/jsp/fragment/advirtisement.jsp" />
		</div>

	</div>

	<c:import url="/jsp/fragment/footer.html" />
</body>
</html>