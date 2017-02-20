<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Sign in</title>
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

</head>
<body>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="localization.local" var="localBundle" />

	<c:import url="\fragment\header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-4 sidenav"></div>

			<div class="col-sm-4 text-left">
				<br />
				<h1>
					<fmt:message bundle="${localBundle}" key="local.signin.title" />
				</h1>
				<br />

				<c:set var="errorMessage" value="${requestScope.errorMessage}" />

				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">
						<strong><fmt:message bundle="${localBundle}"
								key="local.signin.error" /></strong>
						<fmt:message bundle="${localBundle}"
							key="local.signin.errormessage" />
					</div>
				</c:if>

				<form action="controller" method="post">
					<input type="hidden" name="command" value="signIn" />
					<div class="form-group">
						<label for="email"><fmt:message bundle="${localBundle}"
								key="local.signin.email" /></label> <input type="email"
							class="form-control" id="email" placeholder="Enter email"
							name="login" value="">
					</div>
					<div class="form-group">
						<label for="pwd"><fmt:message bundle="${localBundle}"
								key="local.signin.password" /></label> <input type="password"
							class="form-control" id="pwd" placeholder="Enter password"
							name="password" value="">
					</div>
					<br />
					<button type="submit" class="btn btn-default">
						<fmt:message bundle="${localBundle}" key="local.signin.submit" />
					</button>
				</form>

				<br />
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