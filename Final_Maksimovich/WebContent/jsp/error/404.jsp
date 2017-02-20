<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>404</title>
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

<style>
.text {
	font-family: cursive;
	font-size: 40pt; /* Размер шрифта в пунктах */
	color: #408e41;
	text-shadow: 2px 2px 3px #000;
}

.image {
	padding-top: 25px;
}
</style>
</head>

<body>

	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">

			<div class="col-sm-12">
				<img src="images/hit.jpg" alt="Page Not Found (404).">
			</div>


		</div>
	</div>




	<c:import url="/jsp/fragment/footer.html" />
</body>

</html>