<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.error.500.title"
	var="title" />

<c:set var="login" value="${sessionScope.login}" />
<c:set var="errorMessage" value="${requestScope.errorMessage}" />

<!DOCTYPE html>
<html>
<head>
<title><c:out value="${title}" /></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/localizationdropdown.css">
<link rel="stylesheet" href="css/verticalmenu.css">
<link rel="stylesheet" href="css/modal.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<style>
.text {
	font-family: cursive;
	font-size: 40pt; /* Размер шрифта в пунктах */
	color: #408e41;
	text-shadow: 2px 2px 3px #000;
}

.image {
	text-align: center;
	display: block;
	margin: auto;
	width: auto;
	height: auto;
	display: block;
	padding: 15px;
}

h1 {
	padding: 1em;
	font-size: 6em;
	color: #9d9d9d;
}
</style>
</head>

<body>

	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">

		<div class="row content">

			<div class="col-sm-2 text-center">
				<h1>Internal server error</h1>
			</div>

			<div class="col-sm-10 text-center">
				<img class="image" src="images/500.png" alt="Status 500.">
			</div>



		</div>
	</div>

	<c:import url="/jsp/fragment/footer.html" />

	<c:if test="${empty login}">
		<c:import url="/jsp/fragment/signInModal.jsp" />
		<script src="js/validation.js"></script>
	</c:if>
</body>

</html>