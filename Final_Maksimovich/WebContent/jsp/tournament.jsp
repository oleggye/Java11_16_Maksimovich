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
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/pagination.css">
<link rel="stylesheet" href="css/hoverdropdown.css">
<link rel="stylesheet" href="css/localizationdropdown.css">
<link rel="stylesheet" href="css/verticalmenu.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/modal.css">

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dropdown.js"></script>
<script src="js/moment-with-locales.min.js"></script>
<script src="js/modal.js"></script>

</head>
<body>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="localization.local" var="localBundle" />
	<c:set var="competitons" value="${requestScope.competitions}" />

	<c:set var="login" value="${sessionScope.login}" />
	<c:set var="userType" value="${sessionScope.userType.getShortName()}" />
	<c:set var="CLIENT_CONST" value="${'C'}" />


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
	<fmt:message bundle="${localBundle}" key="local.signin.error"
		var="error" />

	<c:set var="errorMessage" value="${requestScope.errorMessage}" />

	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">
			<c:import url="/jsp/fragment/leftmenu.jsp" />

			<div class="col-sm-8 text-center">

				<h2>
					<c:out value="${competitionList[0].tournament.name}" />
				</h2>

				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger alert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong><c:out value="${error}" /></strong>
						<c:out value="${errorMessage}" />
					</div>
				</c:if>

				<table class="table table-striped mytable text-left">
					<thead>
						<tr>
							<th>#</th>
							<th><c:out value="${date}" /></th>
							<th><c:out value="${participants}" /></th>
							<th><c:out value="${place}" /></th>
							<th><c:out value="${home}" /></th>
							<th><c:out value="${draw}" /></th>
							<th><c:out value="${away}" /></th>

							<c:if test="${userType eq CLIENT_CONST}">
								<th><c:out value="${action}" /></th>
							</c:if>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="elem" items="${competitionList}">

							<fmt:formatDate type="Date" value="${elem.startTime}"
								dateStyle="short" timeStyle="short" var="date" />
							<fmt:formatDate type="Time" value="${elem.startTime}"
								dateStyle="short" timeStyle="short" var="time" />

							<tr>
								<td><c:out value="${elem.id}" /></td>
								<td><c:out value="${date}" /> <br> <c:out
										value="${time}" /></td>
								<td><c:out value="${elem.homeTeam.club.name}" /><br>
									<c:out value="${elem.awayTeam.club.name}" /></td>
								<td><c:out value="${elem.country.name}" /></td>
								<td><c:out value="${elem.winHomeRate}" /></td>
								<td><c:out value="${elem.drawRate}" /></td>
								<td><c:out value="${elem.winAwayRate}" /></td>

								<c:if test="${userType eq CLIENT_CONST}">
									<td><a href="#" id="${elem.id}"
										class="btn btn-default btn-lg" data-toggle="modal"
										data-target="#makeBettingModal">Bet</a></td>
								</c:if>

							</tr>

						</c:forEach>

					</tbody>
				</table>

				<c:import url="/jsp/fragment/pagination.jsp" />

			</div>
			<c:import url="/jsp/fragment/advertisement.jsp" />
		</div>

	</div>

	<c:import url="/jsp/fragment/footer.html" />

	<c:choose>
		<c:when test="${empty login}">
			<c:import url="/jsp/fragment/signInModal.jsp" />
			<script src="js/validation.js"></script>
		</c:when>
		<c:otherwise>
			<c:if test="${userType eq CLIENT_CONST}">
				<c:import url="/jsp/fragment/controlPanel/addBettingModal.jsp" />
			</c:if>
		</c:otherwise>
	</c:choose>

</body>
</html>