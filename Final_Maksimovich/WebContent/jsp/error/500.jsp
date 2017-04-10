<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.error.500.title"
	var="title" />

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
</style>
</head>

<body>

	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="container">
			<h1>
				<c:out value="${errorMessage}" />
			</h1>
		</div>
		<div class="row content">


			<div class="col-sm-12">
				<img class="image" src="images/500.png" alt="Page Not Found (404).">
			</div>


		</div>
	</div>




	<c:import url="/jsp/fragment/footer.html" />
</body>

</html>