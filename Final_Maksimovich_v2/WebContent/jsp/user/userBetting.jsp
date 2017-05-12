<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="tld/custom.tld" prefix="cft"%>


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.user_profile.title"
	var="title" />

<!DOCTYPE html>
<html lang="en">
<head>
<title><c:out value="${title}" /></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/pagination.css">
<link rel="stylesheet" href="css/hoverdropdown.css">
<link rel="stylesheet" href="css/localizationdropdown.css">
<link rel="stylesheet" href="css/verticalmenu.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/controlPanel.css">

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dropdown.js"></script>

</head>
<body>

	<c:set var="currency" value="${sessionScope.currency}" />

	<fmt:message bundle="${localBundle}"
		key="local.control_panel.your_betting" var="caption" />
	<fmt:message bundle="${localBundle}" key="local.user_betting.bet_time"
		var="bettingTime" />
	<fmt:message bundle="${localBundle}" key="local.user_betting.sport"
		var="sport" />
	<fmt:message bundle="${localBundle}" key="local.main.tournament"
		var="tournament" />
	<fmt:message bundle="${localBundle}" key="local.main.participants"
		var="participants" />
	<fmt:message bundle="${localBundle}" key="local.user_betting.bet_type"
		var="bettingType" />
	<fmt:message bundle="${localBundle}"
		key="local.user_betting.bet_amount" var="bettingAmount" />
	<fmt:message bundle="${localBundle}"
		key="local.user_betting.coefficient" var="coefficient" />
	<fmt:message bundle="${localBundle}" key="local.user_betting.expected"
		var="expected" />
	<fmt:message bundle="${localBundle}" key="local.user_betting.gain"
		var="gain" />

	<fmt:message bundle="${localBundle}" key="local.signin.error"
		var="error" />


	<c:set var="errorMessage" value="${requestScope.errorMessage}" />

	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">

			<c:import url="/jsp/fragment/controlPanel/menuBar.jsp" />

			<div class="col-sm-8 text-center">

				<h1>
					<c:out value="${caption}" />
				</h1>

				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger alert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong><c:out value="${error}" /></strong>
						<c:out value="${errorMessage}" />
					</div>
				</c:if>

				<table class="table mytable text-left">
					<thead>
						<tr>
							<th>#</th>
							<th><c:out value="${bettingTime}" /></th>
							<th><c:out value="${sport}" /></th>
							<th><c:out value="${tournament}" /></th>
							<th><c:out value="${participants}" /></th>
							<th><c:out value="${bettingType}" /></th>
							<th><c:out value="${bettingAmount}" /></th>
							<th><c:out value="${coefficient}" /></th>
							<th><c:out value="${expected}" /></th>
							<th><c:out value="${gain}" /></th>
						</tr>
					</thead>
					<tbody>


						<c:forEach var="elem" items="${bettingList}">

							<fmt:formatDate type="Date" value="${elem.betTime}"
								dateStyle="short" timeStyle="short" var="date" />
							<fmt:formatDate type="Time" value="${elem.betTime}"
								dateStyle="short" timeStyle="short" var="time" />

							<tr>
								<td><c:out value="${elem.id}" /></td>
								<td><c:out value="${date}" /> <br> <c:out
										value="${time}" /></td>
								<td><c:out value="${elem.competition.sport.name}" /></td>
								<td><c:out value="${elem.competition.tournament.name}" /></td>
								<td><c:out value="${elem.competition.homeTeam.club.name}" /><br>
									<c:out value="${elem.competition.awayTeam.club.name}" /></td>
								<td><c:out value="${elem.betType.getShortName()}" /></td>

								<td><cft:format-currency value="${elem.betSize}"
										currency="${currency}" /></td>
								<td><c:out value="${elem.betRate}" /></td>
								<td><cft:format-currency
										value="${elem.betSize * elem.betRate}" currency="${currency}" />
								</td>
								<td><cft:format-currency value="${elem.gain}"
										currency="${currency}" /></td>

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
</body>
</html>