<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.error.404.title"
	var="title" />
<fmt:message bundle="${localBundle}" key="local.error.404.message"
	var="message" />

<c:set var="login" value="${sessionScope.login}" />

<!DOCTYPE html>
<html lang="en">
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
	padding: 1em 1em 0 1em;
	font-size: 6em;
	color: #9d9d9d;
}

h2 {
	padding: 0 1em;
	font-size: 5em;
	color: #9d9d9d;
	font-size: 5em;
}
</style>
</head>

<body>

	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">

		<div class="row content">


			<div class="col-sm-2 text-center">
				<h1>404</h1>
				<h2>
					<c:out value="${message}" />
				</h2>
			</div>

			<div class="col-sm-10 text-center">
				<img class="image" src="images/hit.jpg" alt="Page Not Found (404).">
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