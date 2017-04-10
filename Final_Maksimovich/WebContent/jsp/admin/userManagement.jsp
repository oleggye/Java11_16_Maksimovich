<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="tld/custom.tld" prefix="cft"%>


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}"
	key="local.control_panel.user_management" var="userManagement" />

<!DOCTYPE html>
<html lang="en">
<head>
<title><c:out value="${userManagement}" /></title>
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
<script type="text/javascript" src="js/dropdown.js"></script>
<script type="text/javascript" src="js/modal.js"></script>

</head>
<body>

	<fmt:message bundle="${localBundle}" key="local.signup.firstname"
		var="firstName" />
	<fmt:message bundle="${localBundle}" key="local.signup.lastname"
		var="lastName" />
	<fmt:message bundle="${localBundle}" key="local.signup.email"
		var="email" />
	<fmt:message bundle="${localBundle}" key="local.user_profile.phone"
		var="phone" />
	<fmt:message bundle="${localBundle}" key="local.user_profile.balance"
		var="balance" />
	<fmt:message bundle="${localBundle}"
		key="local.user_management.user_type" var="userType" />
	<fmt:message bundle="${localBundle}" key="local.user_management.banned"
		var="banned" />

	<fmt:message bundle="${localBundle}" key="local.main.action"
		var="action" />

	<fmt:message bundle="${localBundle}"
		key="local.user_management.ban_user_question" var="banUserQuestion" />
	<fmt:message bundle="${localBundle}"
		key="local.user_management.unban_user_question"
		var="unbanUserQuestion" />



	<c:set var="message" value="${param.message}" />
	<c:set var="errorMessage" value="${param.errorMessage}" />

	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">

			<c:import url="/jsp/fragment/controlPanel/menuBar.jsp" />

			<div class="col-sm-9 text-center">

				<h1>
					<c:out value="${userManagement}" />
				</h1>
				<c:choose>

					<c:when test="${not empty message}">
						<c:out value="${message}" />
					</c:when>

				</c:choose>

				<table class="table ">
					<thead>
						<tr>

							<th>#</th>
							<th><c:out value="${firstName}" /></th>
							<th><c:out value="${lastName}" /></th>
							<th><c:out value="${email}" /></th>
							<th><c:out value="${phone}" /></th>
							<th><c:out value="${balance}" /></th>
							<th><c:out value="${userType}" /></th>
							<th><c:out value="${banned}" /></th>
							<th><c:out value="${action}" /></th>
						</tr>

					</thead>
					<tbody>
						<c:forEach var="elem" items="${requestScope.userList}">

							<tr>
								<td><c:out value="${elem.id}" /></td>
								<td><c:out value="${elem.firstName}" /></td>
								<td><c:out value="${elem.lastName}" /></td>
								<td><c:out value="${elem.email}" /></td>
								<td><c:out
										value="+(${elem.phone.code}) ${elem.phone.phoneNumber}" /></td>
								<td><cft:format-currency value="${elem.balance}"
										currency="${elem.currency}" /></td>
								<td><c:out value="${elem.userType}" /></td>
								<td><c:out value="${elem.banned}" /></td>

								<c:choose>
									<c:when test="${elem.banned eq true}">
										<td><a href="#" onclick="unBanUser(this)" id="${elem.id}">
												<span class="glyphicon glyphicon-thumbs-up"></span>
										</a></td>
									</c:when>
									<c:otherwise>
										<td><a href="#" onclick="banUser(this)" id="${elem.id}">
												<span class="glyphicon glyphicon-thumbs-down"></span>
										</a></td>
									</c:otherwise>
								</c:choose>

								<%-- 	<td class="form-inline"><a href="#"
									id="${elem.id}-${elem.sport.id}" class="btn"
									data-toggle="modal" data-target="#updateCompetitionModal">
										<span class="glyphicon glyphicon-pencil"></span>
								</a> <
								</a></td> --%>

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
	<script>
		function banUser(link) {

			if (confirm("${banUserQuestion}")) {
				var idUser = link.getAttribute("id");
				var requestParams = {
					'command' : 'ban-user',
					'idUser' : idUser
				};

				$.ajax({
					async : true,
					cache : false,
					type : 'POST',
					url : '/Totalizator/controller',
					data : requestParams,
					success : function(data) {
						alert(data);
						location.reload();
					},
					error : function(xhr, str) {
						alert(xhr.responseText);
					}
				});
			}
		};

		function unBanUser(link) {

			if (confirm("${unbanUserQuestion}")) {
				var idUser = link.getAttribute("id");
				var requestParams = {
					'command' : 'unban-user',
					'idUser' : idUser
				};

				$.ajax({
					async : true,
					cache : false,
					type : 'POST',
					url : '/Totalizator/controller',
					data : requestParams,
					success : function(data) {
						alert(data);
						location.reload();
					},
					error : function(xhr, str) {
						alert(xhr.responseText);
					}
				});
			}
		};
	</script>

	<c:import url="/jsp/fragment/footer.html" />
</body>
</html>