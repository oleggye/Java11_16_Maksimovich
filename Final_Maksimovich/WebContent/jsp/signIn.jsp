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
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/pagination.css">
<link rel="stylesheet" href="css/hoverdropdown.css">
<link rel="stylesheet" href="css/localizationdropdown.css">
<link rel="stylesheet" href="css/verticalmenu.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/modal.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/validation.js"></script>

</head>
<body>

	<fmt:message bundle="${localBundle}" key="local.signin.title"
		var="title" />
	<fmt:message bundle="${localBundle}" key="local.signin.error"
		var="errorTitle" />
	<fmt:message bundle="${localBundle}" key="local.signin.errormessage"
		var="locErrorMessage" />
	<fmt:message bundle="${localBundle}" key="local.signin.email"
		var="email" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.email.place_holder" var="emailPlaceHolder" />

	<fmt:message bundle="${localBundle}" key="local.signin.password"
		var="password" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.password.place_holder" var="passwordPlaceHolder" />
	<fmt:message bundle="${localBundle}" key="local.signup.password.title"
		var="passwordTitle" />

	<fmt:message bundle="${localBundle}" key="local.signin.submit"
		var="submit" />
	<fmt:message bundle="${localBundle}" key="local.signup.page_title"
		var="signUp" />
	<fmt:message bundle="${localBundle}" key="local.signin.remember_me"
		var="remember" />
	<fmt:message bundle="${localBundle}" key="local.signin.forgot_password"
		var="forgot" />

	<fmt:message bundle="${localBundle}" key="local.signin.error"
		var="error" />

	<c:set var="errorMessage" value="${requestScope.errorMessage}" />

	<c:import url="\fragment\header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-4 sidenav"></div>

			<div class="col-sm-4 text-left">

				<h1>
					<c:out value="${title}" />
				</h1>

				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">
						<strong><c:out value="${errorTitle}" /></strong>
						<c:out value="${locErrorMessage}" />
					</div>
				</c:if>

				<form action="controller" method="post">
					<input type="hidden" name="command" value="signIn" />
					<div class="form-group">
						<label for="email-field"><c:out value="${email}" /></label> <input
							type="email" class="form-control"
							pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" name="login"
							id="email-field" placeholder="${emailPlaceHolder}"
							onchange="checkEmailField(this)" value="admin@example.com"
							required><span id="email-span"></span>
					</div>
					<div class="form-group">
						<label for="password-field"><c:out value="${password}" /></label>
						<input type="password" class="form-control" name="password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" id="password-field"
							placeholder="${passwordPlaceHolder}" title="${passwordTitle}"
							onchange="checkPassField(this)" value="J3dd2#.<ds" required><span
							id="password-span"></span>
					</div>
					<%-- <div class="checkbox">
						<label><input type="checkbox" value="" checked> <c:out
								value="${remember}" /></label>
					</div> --%>
					<button type="submit" class="btn btn-default ">
						<span class="glyphicon glyphicon-off"></span>
						<c:out value="${submit}" />
					</button>
					<div class="addition">
						<a href="controller?command=signUp-page"><c:out
								value="${signUp}" /></a> <br>
						<%-- <a
								href="#"><c:out value="${forgot}" /></a> --%>
					</div>

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
	<script>
		function checkPassField(passwordField) {
			var backgroundColorName = "inherit";
			var passwordSpanName = 'password-span';
			var errorMessage = '';
			var messageColor = 'inherit';

			if (passwordField.checkValidity() == false) {
				errorMessage = passwordField.title;
				messageColor = 'red';
				backgroundColorName = "rgba(255,0,0,0.3)";
			}
			passwordField.style.backgroundColor = backgroundColorName;
			setSpanText(passwordSpanName, errorMessage, messageColor);
		};
	</script>

	<c:import url="\fragment\footer.html" />
</body>
</html>