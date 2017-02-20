<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css\bootstrap.min[1].css">
<link rel="stylesheet" href="css\hoverdropdown.css">
<link rel="stylesheet" href="css\localizationdropdown.css">
<link rel="stylesheet" href="css\style.css">
<link rel="stylesheet" href="css\bootstrap-datetimepicker.min.css" />

<script type="text/javascript" src="js\jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js\moment-with-locales.min.js"></script>
<script type="text/javascript" src="js\bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="js\jquery.maskedinput.min.js"></script>

<title>Sign up</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="localization.local" var="localBundle" />

	<fmt:message bundle="${localBundle}" key="local.signup.title"
		var="signUpTitle" />
	<fmt:message bundle="${localBundle}" key="local.signup.firstname"
		var="firstName" />
	<fmt:message bundle="${localBundle}" key="local.signup.lastname"
		var="lastName" />
	<fmt:message bundle="${localBundle}" key="local.signup.email"
		var="email" />

	<fmt:message bundle="${localBundle}" key="local.signup.countryandphone"
		var="countryAndPhone" />
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
					<c:out value="${signUpTitle}" />
				</h1>
				<br />
				<c:set var="errorMessage" value="${requestScope.errorMessage}" />

				<form action="controller" method="post">

					<input type="hidden" name="command" value="signUp" />

					<div class="form-group">
						<label><c:out value="${firstName}" /></label> <input
							class="form-control" type="text" value="" id="firstname"
							placeholder="Enter your first name">
					</div>

					<div class="form-group">
						<label><c:out value="${lastName}" /> </label> <input
							class="form-control" type="text" value="" id="lastname"
							placeholder="Enter your last name">
					</div>


					<div class="form-group">
						<label><c:out value="${email}" /> </label> <input
							class="form-control" type="email" value="" id="email"
							placeholder="Enter your email"> <small id="emailHelp"
							class="form-text text-muted">We'll never share your email
							with anyone else.</small>

					</div>

					<div class="form-group">
						<label><c:out value="${countryAndPhone}" /></label>
						<div class="form-inline">
							<select id="country" class="form-control">
								<option value="ru">Russia</option>
								<option value="ua">Ukraine</option>
								<option value="by">Belarus</option>
							</select> <input id="phone" type="text" class="form-control">
						</div>
					</div>

					<script>
						jQuery(function($) {
							$(function() {
								function maskPhone() {
									var country = $('#country option:selected')
											.val();
									switch (country) {
									case "ru":
										$("#phone").mask("+7(999) 999-99-99");
										break;
									case "ua":
										$("#phone").mask("+380(999) 999-99-99");
										break;
									case "by":
										$("#phone").mask("+375(99) 999-99-99");
										break;
									}
								}
								maskPhone();
								$('#country').change(function() {
									maskPhone();
								});
							});
						});
					</script>

					<div class="form-group">
						<label><c:out value="${dateOfBirth}" /></label>
						<div class='input-group date' id='datetimepicker1'>
							<input type='text' class="form-control" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>

					<script type="text/javascript">
						$(document).ready(
								function() {
									$('#datetimepicker1').datetimepicker({
										pickTime : false,
										constrainInput : true,
										language : 'ru',
										maxDate : "31/12/1999",
										minDate : "01/01/1929",
										defaultDate : "31/12/1999"

									});
									$("#show").click(
											function() {
												$('#datetimepicker1').data(
														"DateTimePicker")
														.show();
											});
									$("#hide").click(
											function() {
												$('#datetimepicker1').data(
														"DateTimePicker")
														.hide();
											});

								});
					</script>

					<div class="form-group">
						<label><c:out value="${password}" /></label>
						<div class="form-group">
							<input class="form-control mx-sm-3" type="password" value=""
								id="example-password-input"> <small
								id="passwordHelpInline" class="form-text text-muted">
								Must be 8-20 characters long.</small>
						</div>
					</div>

					<div class="form-group">
						<label><c:out value="${secretQuestionTitle}" /></label> <select
							class="form-control" id="secquestion">
							<option><c:out value="${secretMothername}" /></option>
							<option><c:out value="${secretFatherBirth}" /></option>
							<option><c:out value="${secretSchool}" /></option>
							<option><c:out value="${secretPin}" /></option>
						</select>
					</div>

					<div class="form-group">
						<label> <c:out value="${secretAnswer}" /></label> <input
							class="form-control" type="tel" value="" id="example-tel-input">
					</div>


					<br />
					<button type="submit" class="btn btn-default">
						<c:out value="${submitTitle}" />
					</button>
				</form>

				<br />
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