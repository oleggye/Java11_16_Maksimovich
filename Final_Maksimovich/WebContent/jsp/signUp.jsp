<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.signup.page_title"
	var="pageTitle" />

<!DOCTYPE html>
<html lang="en">
<head>
<title><c:out value="${pageTitle}" /></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css\bootstrap.min.css">
<link rel="stylesheet" href="css\hoverdropdown.css">
<link rel="stylesheet" href="css\localizationdropdown.css">
<link rel="stylesheet" href="css\style.css">
<link rel="stylesheet" href="css\bootstrap-datetimepicker.min.css" />

<script type="text/javascript" src="js\jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js\moment-with-locales.min.js"></script>
<script type="text/javascript" src="js\bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="js\jquery.maskedinput.min.js"></script>

</head>
<body>


	<fmt:message bundle="${localBundle}" key="local.signup.title"
		var="title" />
	<fmt:message bundle="${localBundle}" key="local.signup.firstname"
		var="firstName" />
	<fmt:message bundle="${localBundle}" key="local.signup.lastname"
		var="lastName" />
	<fmt:message bundle="${localBundle}" key="local.signup.email"
		var="email" />
	<fmt:message bundle="${localBundle}" key="local.signup.currency"
		var="currency" />
	<fmt:message bundle="${localBundle}" key="local.signup.dateofbirth"
		var="dateOfBirth" />
	<fmt:message bundle="${localBundle}" key="local.signup.password"
		var="password" />
	<fmt:message bundle="${localBundle}" key="local.signup.secret.title"
		var="secretQuestionTitle" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.secret.mothername" var="secretMothername" />

	<fmt:message bundle="${localBundle}"
		key="local.signup.secret.fatherbirth" var="secretFatherBirth" />
	<fmt:message bundle="${localBundle}" key="local.signup.secret.school"
		var="secretSchool" />
	<fmt:message bundle="${localBundle}" key="local.signup.secret.pin"
		var="secretPin" />
	<fmt:message bundle="${localBundle}" key="local.signup.secret.answer"
		var="secretAnswer" />
	<fmt:message bundle="${localBundle}" key="local.signup.email"
		var="email" />
	<fmt:message bundle="${localBundle}" key="local.signup.submit"
		var="submitTitle" />


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

				<form action="controller" method="post">

					<input type="hidden" name="command" value="signUp" />

					<div class="form-group">
						<label><c:out value="${firstName}" /></label> <input
							class="form-control" type="text" name="firstname" value="Олег"
							placeholder="Enter your first name"
							pattern="^[A-Za-zА-Яа-я]{4,40}" title="Latin latters only"
							required>
					</div>

					<div class="form-group">
						<label><c:out value="${lastName}" /> </label> <input
							class="form-control" type="text" name="lastname" value="Петров"
							placeholder="Enter your last name"
							pattern="^[A-Za-zА-Яа-я]{4,40}" title="Latin latters only"
							required>
					</div>


					<div class="form-group">
						<label><c:out value="${email}" /> </label> <input
							class="form-control" type="email" name="email"
							value="badboy@mail.ru" placeholder="Enter your email" required>
						<small id="emailHelp" class="form-text text-muted">We'll
							never share your email with anyone else.</small>

					</div>

					<c:import url="\fragment\signup\countryandphone.jsp" />


					<div class="form-group">
						<label><c:out value="${currency}" /> </label> <select
							name="currency" class="form-control">
							<option value="USD">USD</option>
							<option value="EUR">EUR</option>
							<option value="BYN">BYN</option>
							<option value="RUB">RUB</option>
							<option value="UAH">UAH</option>
							<option value="CNY">CNY</option>
							<option value="GBP">GBP</option>
						</select>
					</div>

					<div class="form-group">
						<label><c:out value="${dateOfBirth}" /></label>
						<div class='input-group date' id='datetimepicker1'>
							<input type='date' class="form-control" name="date" value=""
								readonly /> <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>

					<c:choose>
						<c:when test="${sessionScope.local eq 'EN' }">
							<script type="text/javascript" src="js\signup\endatepicker.js"></script>
						</c:when>
						<c:otherwise>
							<script type="text/javascript" src="js\signup\rudatepicker.js"></script>
						</c:otherwise>
					</c:choose>


					<div class="form-group">
						<label><c:out value="${password}" /></label>
						<div class="form-group">
							<input class="form-control mx-sm-3" type="password"
								name="password" value="A2a4$212D"
								pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$"
								title="[A-z, 0-9, @,!,?,#]" required>
						</div>
					</div>

					<div class="form-group">
						<label><c:out value="${secretQuestionTitle}" /></label> <select
							class="form-control" name="secquestion">
							<option><c:out value="${secretMothername}" /></option>
							<option><c:out value="${secretFatherBirth}" /></option>
							<option><c:out value="${secretSchool}" /></option>
							<option><c:out value="${secretPin}" /></option>
						</select>
					</div>

					<div class="form-group">
						<label> <c:out value="${secretAnswer}" /></label> <input
							class="form-control" type="text" name="secanswer" value="asdb"
							pattern="[\w]{4,20}" required>
					</div>


					<br>
					<button type="submit" class="btn btn-default">
						<c:out value="${submitTitle}" />
					</button>
				</form>

				<br>
				<div class="alert alert-warning">
					<fmt:message bundle="${localBundle}"
						key="local.signup.message.warning" />
				</div>

			</div>

			<div class="col-sm-4 sidenav"></div>

		</div>
	</div>

	<c:import url="\fragment\footer.html" />
</body>
</html>