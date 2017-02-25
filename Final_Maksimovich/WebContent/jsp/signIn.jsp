<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.signin.page_title"
	var="pageTitle" />


<!DOCTYPE html>
<html lang="en">
<head>
<title><c:out value="${pageTitle}" /></title>
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

</head>
<body>

	<fmt:message bundle="${localBundle}" key="local.signup.title"
		var="title" />
	<fmt:message bundle="${localBundle}" key="local.signin.error"
		var="errorTitle" />
	<fmt:message bundle="${localBundle}" key="local.signin.errormessage"
		var="locErrorMessage" />
	<fmt:message bundle="${localBundle}" key="local.signup.email"
		var="email" />
	<fmt:message bundle="${localBundle}" key="local.signup.password"
		var="password" />
	<fmt:message bundle="${localBundle}" key="local.signin.submit"
		var="submit" />

	<c:import url="\fragment\header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-4 sidenav"></div>

			<div class="col-sm-4 text-left">

				<h1>
					<c:out value="${title}" />
				</h1>
				<br>

				<c:set var="errorMessage" value="${requestScope.errorMessage}" />

				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">
						<strong><c:out value="${errorTitle}" /></strong>
						<c:out value="${locErrorMessage}" />
					</div>
				</c:if>

				<form action="controller" method="post">
					<input type="hidden" name="command" value="signIn" />
					<div class="form-group">
						<label for="email"><c:out value="${email}" /></label> <input
							type="email" class="form-control" id="email"
							placeholder="Enter email" name="login" value="admin@example.com"
							required>
					</div>
					<div class="form-group">
						<label for="pwd"><c:out value="${password}" /></label> <input
							type="password" class="form-control" id="pwd"
							placeholder="Enter password" name="password" value="123" required>
					</div>
					<br>
					<button type="submit" class="btn btn-default">
						<c:out value="${submit}" />
					</button>
				</form>

				<br>
				<div class="alert alert-warning">
					<fmt:message bundle="${localBundle}"
						key="local.signin.warningmessage" />
				</div>

			</div>

			<div class="col-sm-4 sidenav"></div>

		</div>
	</div>

	<c:import url="\fragment\footer.html" />
</body>
</html>