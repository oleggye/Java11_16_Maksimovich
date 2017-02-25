<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css\bootstrap.min[1].css">
<link rel="stylesheet" href="css\pagination.css">
<link rel="stylesheet" href="css\hoverdropdown.css">
<link rel="stylesheet" href="css\verticalmenu.css">
<link rel="stylesheet" href="css\style.css">
<script src="js\jquery.min.js"></script>
<script src="js\bootstrap.min.js"></script>
<script src="js\dropdown.js"></script>

</head>
<body>

	<c:import url="\fragment\header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">
			<c:import url="\fragment\leftmenu.jsp" />

			<div class="col-sm-8 text-left">
				<h2>Football. UEFA Champions League</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Date</th>
							<th>Participants</th>
							<th>Place</th>
							<th>Home</th>
							<th>Draw</th>
							<th>Away</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>12</td>
							<td>12.02 15:08</td>
							<td>Real Madrid vs Napoli</td>
							<td>Spaint</td>
							<td>1.2</td>
							<td>1.9</td>
							<td>3.7</td>
							<td><a href="#">Bet</a></td>
						</tr>

						<tr>
							<td>13</td>
							<td>12.02 15:08</td>
							<td>Arsenal vs Chelsea</td>
							<td>England</td>
							<td>1.2</td>
							<td>1.9</td>
							<td>3.7</td>
							<td><a href="#">Bet</a></td>
						</tr>

						<tr>
							<td>14</td>
							<td>12.02 15:08</td>
							<td>Manchester United vs Lester City</td>
							<td>England</td>
							<td>1.2</td>
							<td>1.9</td>
							<td>3.7</td>
							<td><a href="#">Bet</a></td>
						</tr>

						<tr>
							<td>12</td>
							<td>12.02 15:08</td>
							<td>Real Madrid vs Napoli</td>
							<td>Spaint</td>
							<td>1.2</td>
							<td>1.9</td>
							<td>3.7</td>
							<td><a href="#">Bet</a></td>
						</tr>

						<tr>
							<td>13</td>
							<td>12.02 15:08</td>
							<td>Arsenal vs Chelsea</td>
							<td>England</td>
							<td>1.2</td>
							<td>1.9</td>
							<td>3.7</td>
							<td><a href="#">Bet</a></td>
						</tr>

						<tr>
							<td>14</td>
							<td>12.02 15:08</td>
							<td>Manchester United vs Lester City</td>
							<td>England</td>
							<td>1.2</td>
							<td>1.9</td>
							<td>3.7</td>
							<td><a href="#">Bet</a></td>
						</tr>

						<tr>
							<td>12</td>
							<td>12.02 15:08</td>
							<td>Real Madrid vs Napoli</td>
							<td>Spaint</td>
							<td>1.2</td>
							<td>1.9</td>
							<td>3.7</td>
							<td><a href="#">Bet</a></td>
						</tr>

						<tr>
							<td>13</td>
							<td>12.02 15:08</td>
							<td>Arsenal vs Chelsea</td>
							<td>England</td>
							<td>1.2</td>
							<td>1.9</td>
							<td>3.7</td>
							<td><a href="#">Bet</a></td>
						</tr>

						<tr>
							<td>13</td>
							<td>12.02 15:08</td>
							<td>Arsenal vs Chelsea</td>
							<td>England</td>
							<td>1.2</td>
							<td>1.9</td>
							<td>3.7</td>
							<td><a href="#">Bet</a></td>
						</tr>

					</tbody>
				</table>
				<div class="container-fluid text-center">
					<div class="pagination">
						<a href="#">&laquo;</a> <a class="active" href="#">1</a> <a
							href="#">2</a> <a href="#">3</a> <a class="disabled" href="#">...</a>
						<a href="#">6</a> <a href="#">&raquo;</a>
					</div>
				</div>
			</div>
			<div class="col-sm-2 sidenav">
				<div class="well">
					<p>ADS</p>
				</div>
				<div class="well">
					<p>ADS</p>
				</div>
			</div>
		</div>

	</div>

	<c:import url="\fragment\footer.html" />
</body>
</html>