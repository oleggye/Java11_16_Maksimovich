<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>MegaBet</title>
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

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="localization.local" var="localBundle" />


	<fmt:message bundle="${localBundle}"
		key="local.main.title.competitions" var="title" />



	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">

			<c:import url="/jsp/fragment/controlPanel/menuBar.jsp" />

			<div class="col-sm-8 text-left">

				<h2>
					<c:out value="${title}" />
				</h2>



			</div>
			<c:import url="/jsp/fragment/advertisement.jsp" />
		</div>

	</div>

	<c:import url="/jsp/fragment/footer.html" />
</body>
</html>