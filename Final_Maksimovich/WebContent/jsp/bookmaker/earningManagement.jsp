<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}"
	key="local.control_panel.competition_management" var="title" />

<!DOCTYPE html>
<html lang="en">
<head>
<title><c:out value="${title}" /></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/pagination.css">
<link rel="stylesheet" href="css/localizationdropdown.css">
<link rel="stylesheet" href="css/verticalmenu.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/controlPanel.css">
<link rel="stylesheet" href="css/modal.css">

<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/moment-with-locales.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="js/dropdown.js"></script>

</head>
<body>

	<fmt:message bundle="${localBundle}"
		key="local.control_panel.competition_management" var="caption" />

	<fmt:message bundle="${localBundle}" key="local.main.date" var="date" />
	<fmt:message bundle="${localBundle}" key="local.user_betting.sport"
		var="sport" />
	<fmt:message bundle="${localBundle}" key="local.main.tournament"
		var="tournament" />
	<fmt:message bundle="${localBundle}" key="local.main.participants"
		var="participants" />
	<fmt:message bundle="${localBundle}" key="local.main.place" var="place" />
	<fmt:message bundle="${localBundle}" key="local.main.home" var="home" />
	<fmt:message bundle="${localBundle}" key="local.main.draw" var="draw" />
	<fmt:message bundle="${localBundle}" key="local.main.away" var="away" />
	<fmt:message bundle="${localBundle}" key="local.main.result"
		var="result" />
	<fmt:message bundle="${localBundle}" key="local.main.action"
		var="action" />



	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">

			<c:import url="/jsp/fragment/controlPanel/menuBar.jsp" />

			<div class="col-sm-9 text-center">

				<h1>
					<c:out value="${caption}" />
				</h1>

				<table class="table ">
					<thead>
						<tr>

							<th>#</th>
							<th><c:out value="${date}" /></th>
							<th><c:out value="${sport}" /></th>
							<th><c:out value="${tournament}" /></th>
							<th><c:out value="${participants}" /></th>
							<th><c:out value="${place}" /></th>
							<th><c:out value="${home}" /></th>
							<th><c:out value="${draw}" /></th>
							<th><c:out value="${away}" /></th>
							<th><c:out value="${result}" /></th>
							<th><c:out value="${action}" /></th>
						</tr>

					</thead>
					<tbody>
						<c:forEach var="elem" items="${requestScope.competitionList}">

							<fmt:formatDate type="Date" value="${elem.startTime}"
								dateStyle="short" timeStyle="short" var="date" />
							<fmt:formatDate type="Time" value="${elem.startTime}"
								dateStyle="short" timeStyle="short" var="time" />

							<tr>
								<td><c:out value="${elem.id}" /></td>
								<td><c:out value="${date}" /> <br> <c:out
										value="${time}" /></td>
								<td><c:out value="${elem.sport.name}" /></td>
								<td><c:out value="${elem.tournament.name}" /></td>
								<td><c:out value="${elem.homeTeam.club.name}" /><br>
									<c:out value="${elem.awayTeam.club.name}" /></td>
								<td><c:out value="${elem.country.name}" /></td>
								<td><c:out value="${elem.winHomeRate}" /></td>
								<td><c:out value="${elem.drawRate}" /></td>
								<td><c:out value="${elem.winAwayRate}" /></td>

								<c:set value="${elem.result}" var="result" />

								<td><c:out value="${elem.result.getShortName()}" /></td>

								<td><a href="#" id="${elem.id}" class="btn"
									data-toggle="modal" data-target="#updateCoefficientModal">
										<span class="glyphicon glyphicon-pencil"></span>
								</a></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="container paggination_container">
					<c:import url="/jsp/fragment/pagination.jsp" />
				</div>

			</div>

			<div class="col-sm-1 sidenav"></div>
		</div>

	</div>
	<script type="text/javascript">


		function createHiddenField(name, value) {
			var hiddenField = document.createElement("input");

			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", name);
			hiddenField.setAttribute("value", value);

			return hiddenField;
		}
	</script>

	<c:import url="/jsp/fragment/footer.html" />
	<c:import url="/jsp/fragment/controlPanel/updateRatesModal.jsp" />
</body>
</html>