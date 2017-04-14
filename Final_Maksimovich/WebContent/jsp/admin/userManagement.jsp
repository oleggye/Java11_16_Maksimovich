<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="tld/custom.tld" prefix="cft"%>


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}"
	key="local.control_panel.user_management" var="userManagement" />

<!DOCTYPE html>
<html lang="en">
<head>
<title><c:out value="${userManagement}" /></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/pagination.css">
<link rel="stylesheet" href="css/localizationdropdown.css">
<link rel="stylesheet" href="css/verticalmenu.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/controlPanel.css">
<link rel="stylesheet" href="css/modal.css">

<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/dropdown.js"></script>
<script type="text/javascript" src="js/modal.js"></script>

</head>
<body>

	<fmt:message bundle="${localBundle}" key="local.signup.firstname"
		var="firstName" />
	<fmt:message bundle="${localBundle}" key="local.signup.lastname"
		var="lastName" />
	<fmt:message bundle="${localBundle}" key="local.signup.email"
		var="email" />
	<fmt:message bundle="${localBundle}" key="local.user_profile.phone"
		var="phone" />
	<fmt:message bundle="${localBundle}" key="local.user_profile.balance"
		var="balance" />
	<fmt:message bundle="${localBundle}"
		key="local.user_management.user_type" var="userType" />
	<fmt:message bundle="${localBundle}" key="local.user_management.banned"
		var="banned" />

	<fmt:message bundle="${localBundle}" key="local.main.action"
		var="action" />

	<fmt:message bundle="${localBundle}"
		key="local.user_management.ban_user_question" var="banUserQuestion" />
	<fmt:message bundle="${localBundle}"
		key="local.user_management.unban_user_question"
		var="unbanUserQuestion" />

	<fmt:message bundle="${localBundle}"
		key="local.ajax.change_user_balance" var="changeUserBalanceQuestion" />
	<fmt:message bundle="${localBundle}" key="local.ajax.change_user_type"
		var="changeUserTypeQuestion" />

	<fmt:message bundle="${localBundle}" key="local.signin.error"
		var="error" />

	<c:set var="errorMessage" value="${requestScope.errorMessage}" />

	<c:import url="/jsp/fragment/header.jsp" />

	<div class="container-fluid text-center">
		<div class="row content">

			<c:import url="/jsp/fragment/controlPanel/menuBar.jsp" />

			<div class="col-sm-9 text-center">

				<h1>
					<c:out value="${userManagement}" />
				</h1>

				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger alert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong><c:out value="${error}" /></strong>
						<c:out value="${errorMessage}" />
					</div>
				</c:if>

				<table class="table ">
					<thead>
						<tr>

							<th>#</th>
							<th><c:out value="${firstName}" /></th>
							<th><c:out value="${lastName}" /></th>
							<th><c:out value="${email}" /></th>
							<th><c:out value="${phone}" /></th>
							<th><c:out value="${balance}" /></th>
							<th><c:out value="${userType}" /></th>
							<th><c:out value="${banned}" /></th>
							<th><c:out value="${action}" /></th>
						</tr>

					</thead>
					<tbody>
						<c:forEach var="elem" items="${requestScope.userList}">

							<tr>
								<td><c:out value="${elem.id}" /></td>
								<td><c:out value="${elem.firstName}" /></td>
								<td><c:out value="${elem.lastName}" /></td>
								<td><c:out value="${elem.email}" /></td>
								<td><c:out
										value="+(${elem.phone.code}) ${elem.phone.phoneNumber}" /></td>
								<td><cft:format-currency value="${elem.balance}"
										currency="${elem.currency}" /> <a href="#"
									onclick="changeBalance(this)" id="${elem.id}"><span
										class="glyphicon glyphicon-flash"></span></a></td>


								<td><select id="${elem.userType.getShortName()}"
									class="form-control userSelect"
									onchange="changeUserType(${elem.id},'${elem.userType.getShortName()}',this)">
										<option value="C">CLIENT</option>
										<option value="B">BOOKMAKER</option>
										<option value="A">ADMIN</option>
								</select></td>



								<td><c:out value="${elem.banned}" /></td>

								<c:choose>
									<c:when test="${elem.banned eq true}">
										<td><a href="#" onclick="unBanUser(this)" id="${elem.id}">
												<span class="glyphicon glyphicon-thumbs-up"></span>
										</a></td>
									</c:when>
									<c:otherwise>
										<td><a href="#" onclick="banUser(this)" id="${elem.id}">
												<span class="glyphicon glyphicon-thumbs-down"></span>
										</a></td>
									</c:otherwise>
								</c:choose>

								<%-- 	<td class="form-inline"><a href="#"
									id="${elem.id}-${elem.sport.id}" class="btn"
									data-toggle="modal" data-target="#updateCompetitionModal">
										<span class="glyphicon glyphicon-pencil"></span>
								</a> <
								</a></td> --%>

							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="container paggination_container">
					<c:import url="/jsp/fragment/pagination.jsp" />
				</div>

			</div>

			<div class="col-sm-1 sidenav"></div>
		</div>

	</div>
	<script>
		//выбирает из select нужные значения из поля id для задания выбранным поля из списка
		$(document).ready(function() {

			var selectArr = document.getElementsByClassName('userSelect');
			var i;

			for (i = 0; i < selectArr.length; i++) {
				$(selectArr[i]).val(selectArr[i].id);
			}

		});

		function banUser(link) {

			if (confirm("${banUserQuestion}")) {
				var idUser = link.getAttribute("id");
				var requestParams = {
					'command' : 'ban-user',
					'idUser' : idUser
				};

				$.ajax({
					async : true,
					cache : false,
					type : 'POST',
					url : '/Totalizator/controller',
					data : requestParams,
					success : function(data) {
						alert(data);
						location.reload();
					},
					error : function(xhr, str) {
						alert(xhr.responseText);
					}
				});
			}
		};

		function unBanUser(link) {

			if (confirm("${unbanUserQuestion}")) {
				var idUser = link.getAttribute("id");
				var requestParams = {
					'command' : 'unban-user',
					'idUser' : idUser
				};

				$.ajax({
					async : true,
					cache : false,
					type : 'POST',
					url : '/Totalizator/controller',
					data : requestParams,
					success : function(data) {
						alert(data);
						location.reload();
					},
					error : function(xhr, str) {
						alert(xhr.responseText);
					}
				});
			}
		};

		function changeBalance(link) {
			var idUser = link.getAttribute("id");

			var amount = prompt("${changeUserBalanceQuestion}", '');

			if (amount) {
				changeUserBalance(idUser, amount);
			}
		};

		function changeUserBalance(idUser, amount) {

			var requestParams = {
				'command' : 'change-user-balance',
				'idUser' : idUser,
				'amount' : amount
			};

			$.ajax({
				async : true,
				cache : false,
				type : 'POST',
				url : '/Totalizator/controller',
				data : requestParams,
				success : function(data) {
					alert(data);
					location.reload();
				},
				error : function(xhr, str) {
					alert(xhr.responseText);
				}
			});
		};

		function changeUserType(idUser, oldUserType, selectId) {

			var newUserTypeShort = $(selectId).val();
			var newUserTypeFull = $(selectId).find(":selected").text();
			
			var isConfirmed = confirm("${changeUserTypeQuestion}" + newUserTypeFull + " ?");
			
			if (isConfirmed){
				
			var requestParams = {
				'command' : 'change-user-type',
				'idUser' : idUser,
				'userType' : newUserTypeShort
			};

			$.ajax({
				async : true,
				cache : false,
				type : 'POST',
				url : '/Totalizator/controller',
				data : requestParams,
				success : function(data) {
					alert(data);
					location.reload();
				},
				error : function(xhr, str) {
					alert(xhr.responseText);
				}
			});
			}else{
				$(selectId).val(oldUserType);
			}
		};
	</script>

	<c:import url="/jsp/fragment/footer.html" />
</body>
</html>