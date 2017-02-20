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
<link rel="stylesheet" href="css\bootstrap.min[1].css">
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

	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">
			<c:import url="/jsp/fragment/leftmenu.jsp" />

			<div class="col-sm-8 text-left">

				<h2>
					<fmt:message bundle="${localBundle}" key="local.main.competitions" />
				</h2>

				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th><fmt:message bundle="${localBundle}"
									key="local.main.date" /></th>
							<th><fmt:message bundle="${localBundle}"
									key="local.main.participants" /></th>
							<th><fmt:message bundle="${localBundle}"
									key="local.main.place" /></th>
							<th><fmt:message bundle="${localBundle}"
									key="local.main.home" /></th>
							<th><fmt:message bundle="${localBundle}"
									key="local.main.draw" /></th>
							<th><fmt:message bundle="${localBundle}"
									key="local.main.away" /></th>
							<th><fmt:message bundle="${localBundle}"
									key="local.main.action" /></th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="elem" items="${requestScope.competitions}">

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
			<div class="col-sm-2 sidenav">
				<div class="well">
					<p>ADS</p>
				</div>
				<div class="well">
					<p>ADS</p>
				</div>
			</div>
		</div>

	</div>

	<c:import url="/jsp/fragment/footer.html" />
</body>
</html>