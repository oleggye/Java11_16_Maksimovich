<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


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
<link rel="stylesheet" href="css\bootstrap.min.css">
<link rel="stylesheet" href="css\pagination.css">
<link rel="stylesheet" href="css\hoverdropdown.css">
<link rel="stylesheet" href="css\localizationdropdown.css">
<link rel="stylesheet" href="css\verticalmenu.css">
<link rel="stylesheet" href="css\style.css">
<link rel="stylesheet" href="css\controlPanel.css">
<script src="js\jquery.min.js"></script>
<script src="js\bootstrap.min.js"></script>
<script src="js\dropdown.js"></script>

</head>
<body>

	<c:set var="user" value="${requestScope.user}" />

	<fmt:message bundle="${localBundle}" key="local.control_panel.your_profile"
		var="caption" />
	<fmt:message bundle="${localBundle}" key="local.signup.firstname"
		var="firstname" />
	<fmt:message bundle="${localBundle}" key="local.signup.lastname"
		var="lastname" />
	<fmt:message bundle="${localBundle}" key="local.signup.dateofbirth"
		var="dateofbirth" />
	<fmt:message bundle="${localBundle}" key="local.signup.email"
		var="email" />
	<fmt:message bundle="${localBundle}" key="local.user_profile.country"
		var="country" />
	<fmt:message bundle="${localBundle}" key="local.user_profile.city"
		var="city" />
	<fmt:message bundle="${localBundle}" key="local.user_profile.phone"
		var="phone" />
	<fmt:message bundle="${localBundle}" key="local.signup.currency"
		var="currency" />
	<fmt:message bundle="${localBundle}" key="local.user_profile.balance"
		var="balance" />
	<fmt:message bundle="${localBundle}" key="local.user_profile.locale"
		var="locale" />



	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">

			<c:import url="/jsp/fragment/controlPanel/menuBar.jsp" />

			<div class="col-sm-8 text-center">

				<h1>
					<c:out value="${caption}" />
				</h1>

				<div class="container userProfile">
					<table class="table userProfileTable">
						<thead>

						</thead>
						<tbody>
							<tr>
								<th><c:out value="${firstname}" /></th>
								<td><c:out value="${user.firstName}" /></td>
							</tr>
							<tr>
								<th><c:out value="${lastname}" /></th>
								<td><c:out value="${user.lastName}" /></td>
							</tr>
							<fmt:formatDate type="date" value="${ user.dateOfBirth}"
								dateStyle="long" timeStyle="short" var="date" />
							<tr>
								<th><c:out value="${dateofbirth}" /></th>
								<td><c:out value="${date}" /></td>
							</tr>
							<tr>
								<th><c:out value="${email}" /></th>
								<td><c:out value="${user.email}" /></td>
							</tr>
							<tr>
								<th><c:out value="${country}" /></th>
								<td><c:out value="${user.country}" /></td>
							</tr>
							<tr>
								<th><c:out value="${city}" /></th>
								<td><c:out value="${user.city}" /></td>
							</tr>
							<tr>
								<th><c:out value="${phone}" /></th>
								<c:set var="userPhone"
									value="+${user.phone.code} ${user.phone.phoneNumber}" />
								<td><c:out value="${userPhone}" /></td>
							</tr>
							<tr>
								<th><c:out value="${currency}" /></th>
								<td><c:out value="${user.currency}" /></td>
							</tr>
							<tr>
								<th><c:out value="${balance}" /></th>
								<td><c:out value="${user.balance}" /></td>
							</tr>
							<tr>
								<th><c:out value="${locale}" /></th>
								<td><c:out value="${user.locale.getShortName()}" /></td>
							</tr>

						</tbody>
					</table>

				</div>
			</div>

			<c:import url="/jsp/fragment/advertisement.jsp" />
		</div>

	</div>

	<c:import url="/jsp/fragment/footer.html" />
</body>
</html>