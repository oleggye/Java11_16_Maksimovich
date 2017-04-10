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

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/hoverdropdown.css">
<link rel="stylesheet" href="css/localizationdropdown.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/validation.css" />
<link rel="stylesheet" href="css/modal.css">
<link rel="stylesheet" href="css/jquery.datetimepicker.min.css" />


<script src="js/jquery.min.js"></script>
<script src="js/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/moment-with-locales.min.js"></script>
<script type="text/javascript" src="js/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="js/validation.js"></script>

</head>
<body>


	<fmt:message bundle="${localBundle}" key="local.signup.title"
		var="title" />

	<fmt:message bundle="${localBundle}" key="local.signup.firstname"
		var="firstName" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.firstname.place_holder" var="firstNamePlaceHolder" />
	<fmt:message bundle="${localBundle}" key="local.signup.firstname.title"
		var="firstNameTitle" />


	<fmt:message bundle="${localBundle}" key="local.signup.lastname"
		var="lastName" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.lastname.place_holder" var="lastNamePlaceHolder" />
	<fmt:message bundle="${localBundle}" key="local.signup.lastname.title"
		var="lastNameTitle" />

	<fmt:message bundle="${localBundle}" key="local.signup.email"
		var="email" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.email.place_holder" var="emailPlaceHolder" />


	<fmt:message bundle="${localBundle}" key="local.signup.currency"
		var="currency" />

	<fmt:message bundle="${localBundle}" key="local.signup.dateofbirth"
		var="dateOfBirth" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.dateofbirth.place_holder"
		var="dateOfBirthPlaceHolder" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.dateofbirth.title" var="dateOfBirthTitle" />


	<fmt:message bundle="${localBundle}" key="local.signup.password"
		var="password" />

	<fmt:message bundle="${localBundle}"
		key="local.signup.password.place_holder" var="passwordPlaceHolder" />
	<fmt:message bundle="${localBundle}" key="local.signup.password.title"
		var="passwordTitle" />

	<fmt:message bundle="${localBundle}"
		key="local.signup.confirm_password" var="confirm_password" />
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
	<fmt:message bundle="${localBundle}"
		key="local.signup.secret.answer.place_holder"
		var="secretAnswerPlaceHolder" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.secret.answer.title" var="secretAnswerTitle" />

	<fmt:message bundle="${localBundle}" key="local.signup.email"
		var="email" />
	<fmt:message bundle="${localBundle}" key="local.signup.submit"
		var="submitTitle" />

	<fmt:message bundle="${localBundle}"
		key="local.signup.dateofbirh.age_between" var="ageBetween" />
	<fmt:message bundle="${localBundle}"
		key="local.signup.dateofbirh.age_and" var="ageAnd" />


	<c:import url="\fragment\header.jsp" />

	<div class="container-fluid text-center"
		style="background-color: #f1f1f1;">
		<div class="row content">
			<div class="col-sm-4 sidenav"></div>

			<div class="col-sm-4 text-left signupmain"
				style="background-color: #fff;">

				<h1>
					<c:out value="${title}" />
				</h1>
				<br>
				<c:set var="errorMessage" value="${requestScope.errorMessage}" />

				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">
						<strong><c:out value="${errorTitle}" /></strong>
						<c:out value="${errorMessage}" />
					</div>
				</c:if>

				<c:set var="errorMessageSet" value="${requestScope.errorMessageSet}" />

				<c:if test="${not empty errorMessageSet}">
					<div class="alert alert-danger">
						<c:forEach var="elem" items="${errorMessageSet}">
							<p>
								<strong><c:out value="${elem}" /></strong>
							</p>
						</c:forEach>
					</div>
				</c:if>

				<form name="signInForm" action="controller"
					onsubmit="return validateForm()" method="post">

					<input type="hidden" name="command" value="signUp" />

					<div class="form-group">
						<label for="firstname-field"><c:out value="${firstName}" /></label>
						<span class="req-symbol">*</span> <input class="form-control"
							type="text" name="firstName" id="first-name-field" value=""
							pattern="^[A-Za-zА-Яа-я]{4,40}"
							placeholder="${firstNamePlaceHolder}" title="${firstNameTitle}"
							required onchange="checkFirstNameField(this)"> <span
							id="first-name-span"></span>
					</div>

					<div class="form-group">
						<label for="lastname-field"><c:out value="${lastName}" /></label>
						<span class="req-symbol">*</span> <input class="form-control"
							type="text" name="lastName" id="last-name-field" value=""
							placeholder="${lastNamePlaceHolder}"
							pattern="^[A-Za-zА-Яа-я]{4,40}" title="${lastNameTitle}" required
							onchange="checkLastNameField(this)"> <span
							id="last-name-span"></span>
					</div>

					<div class="form-group">
						<label for="email-field"><c:out value="${email}" /></label> <span
							class="req-symbol">*</span> <input class="form-control"
							type="email" name="email" id="email-field" value="" size="40"
							pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
							placeholder="${emailPlaceHolder}" required
							onchange="checkEmailField(this)"> <span
							id="user-email-span"></span>
					</div>


					<c:import url="/jsp/fragment/signup/countryandphone.jsp" />


					<div class="form-group">
						<label for="currency-field"><c:out value="${currency}" /></label>
						<span class="req-symbol">*</span> <select class="form-control"
							name="currency" id="currency-field">
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
						<label for="date-field"><c:out value="${dateOfBirth}" /></label>
						<span class="req-symbol">*</span> <input
							class="form-control mx-sm-3" type="date" name="date"
							id="date-field" placeholder="yyyy-mm-dd"
							title="${dateOfBirthTitle}" required
							onchange="validateDateField(this)"> <span id="date-span"></span>
					</div>

					<div class="form-group">
						<label for="password-field"><c:out value="${password}" /></label>
						<span class="req-symbol">*</span>

						<div class="form-group">
							<input class="form-control mx-sm-3" type="password"
								name="password" id="password-field" value=""
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
								placeholder="${passwordPlaceHolder}" title="${passwordTitle}"
								required onchange="checkPasswordField(this)"> <span
								id="password-span"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="rep-password-field"><c:out
								value="${confirm_password}" /></label> <span class="req-symbol">*</span>

						<div class="form-group">
							<input class="form-control mx-sm-3" type="password"
								name="repPassword" id="rep-password-field" value=""
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
								placeholder="${passwordPlaceHolder}" title="${passwordTitle}"
								required onchange="checkPassword()"> <span
								id="rep-password-span"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="secquestion-field"><c:out
								value="${secretQuestionTitle}" /></label> <span class="req-symbol">*</span>
						<select class="form-control" name="secquestion"
							id="secquestion-field">
							<option value="1"><c:out value="${secretMothername}" /></option>
							<option value="2"><c:out value="${secretFatherBirth}" /></option>
							<option value="3"><c:out value="${secretSchool}" /></option>
							<option value="4"><c:out value="${secretPin}" /></option>
						</select>
					</div>


					<div class="form-group">
						<label for="secanswer-field"><c:out
								value="${secretAnswer}" /></label> <span class="req-symbol">*</span> <input
							class="form-control" type="text" name="secanswer"
							id="secanswer-field" value="" pattern="[\w]{4,20}"
							placeholder="${secretAnswerPlaceHolder }"
							title="${secretAnswerTitle}" required
							onchange="checkSecAnswerField(this)"> <span
							id="secanswer-span"></span>
					</div>

					<br> <input type="submit" class="btn btn-default"
						value="${submitTitle}">

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

	<c:import url="/jsp/fragment/footer.html" />

	<c:if test="${empty login}">
		<c:import url="/jsp/fragment/signInModal.jsp" />
	</c:if>

	<script>
		$('#date-field').datetimepicker({
			timepicker : false,
			format : 'Y/m/d'
		});
		$.datetimepicker.setLocale("${sessionScope.local}");

		/*валидным считается возраст [18,120)*/
		function validateDateField(dateField) {
			var LOWER_YEAR_BOUND = 18;
			var UPPER_YEAR_BOUND = 120;
			var date = new Date(Date.parse(dateField.value));
			var now = new Date();
			var dateFullYear, dateMonth, dateDay = 0;
			var nowFullYear, nowMonth, nowDay = 0;
			var fullYearDifference = 0;
			var errorMessage = '';

			setSpanText("date-span", '', 'inherit');

			if (!isNaN(date)) {
				dateFullYear = date.getFullYear();
				nowFullYear = now.getFullYear();
				fullYearDifference = nowFullYear - dateFullYear;

				/*если разница попадает внутрь границ*/
				if (fullYearDifference > LOWER_YEAR_BOUND
						&& fullYearDifference < UPPER_YEAR_BOUND) {
					dateField.style.backgroundColor = 'inherit';
					return true;
				}

				dateMonth = date.getMonth();
				dateDay = date.getDate();

				nowMonth = now.getMonth();
				nowDay = now.getDate();
				/*если разница попадает на нижнюю границу*/
				if (fullYearDifference === LOWER_YEAR_BOUND) {

					if (nowMonth > dateMonth
							|| (nowMonth === dateMonth && nowDay >= dateDay)) {
						dateField.style.backgroundColor = 'inherit';
						return true;
					}
				}
				/*если разница попадает на верхнюю границу*/
				if (fullYearDifference === UPPER_YEAR_BOUND) {

					if (nowMonth < dateMonth
							|| (nowMonth === dateMonth && nowDay < dateDay)) {
						dateField.style.backgroundColor = 'inherit';
						return true;
					}
				}
				errorMessage = '${ageBetween} ' + LOWER_YEAR_BOUND
						+ ' ${ageAnd} ' + UPPER_YEAR_BOUND;
			} else {
				errorMessage = dateField.title;
			}
			dateField.style.backgroundColor = 'rgba(255,0,0,0.3)';
			setSpanText("date-span", errorMessage, 'red');

			return false;
		}
	</script>
</body>
</html>