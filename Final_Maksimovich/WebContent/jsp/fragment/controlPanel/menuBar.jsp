<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />


<fmt:message bundle="${localBundle}"
	key="local.control_panel.your_profile" var="yourProfile" />
<fmt:message bundle="${localBundle}"
	key="local.control_panel.your_betting" var="yourBetting" />
<fmt:message bundle="${localBundle}" key="local.control_panel.add_funds"
	var="addFunds" />

<fmt:message bundle="${localBundle}"
	key="local.control_panel.competition_management"
	var="competitionManagement" />
<fmt:message bundle="${localBundle}"
	key="local.control_panel.user_management" var="userManagement" />

<c:set var="userType" value="${sessionScope.userType.getShortName()}" />
<c:set var="CLIENT_CONST" value="${'C'}" />
<c:set var="ADMIN_CONST" value="${'A'}" />
<c:set var="BOOKMAKER_CONST" value="${'B'}" />

<div class="col-sm-2 sidenav">
	<div class="vertical-menu">

		<c:choose>

			<c:when test="${userType eq CLIENT_CONST}">
				<div class="menuBar">
					<form name="first" action="controller" method="post">
						<input type="hidden" name="command" value="user-profile">
						<input type="submit" class="menuBarBtn" value="${yourProfile}">
					</form>
				</div>

				<div class="menuBar">
					<form name="second" action="controller" method="post">
						<input type="hidden" name="command" value="user-betting">
						<input type="submit" class="menuBarBtn" value="${yourBetting}">
					</form>
				</div>

				<%-- <div class="menuBar">
					<form name="third" action="controller" method="post">
						<input type="hidden" name="command" value="add-funds"> <input
							type="submit" class="menuBarBtn" value="${addFunds}">
					</form>
				</div> --%>
			</c:when>

			<c:when test="${userType eq ADMIN_CONST}">
				<div class="menuBar">
					<form name="first" action="controller" method="post">
						<input type="hidden" name="command" value="competition-management">
						<button type="submit" class="menuBarBtn">
							<c:out value="${competitionManagement}" />
						</button>
					</form>
				</div>

				<div class="menuBar">
					<form name="second" action="controller" method="post">
						<input type="hidden" name="command" value="user-management">
						<button type="submit" class="menuBarBtn">
							<c:out value="${userManagement}" />
						</button>
					</form>
				</div>
			</c:when>
			
			<c:when test="${userType eq BOOKMAKER_CONST}">
				<div class="menuBar">
					<form name="first" action="controller" method="post">
						<input type="hidden" name="command" value="earning-management">
						<button type="submit" class="menuBarBtn">
							<c:out value="${competitionManagement}" />
						</button>
					</form>
				</div>

				<!-- <div class="menuBar">
					<form name="second" action="controller" method="post">
						<input type="hidden" name="command" value="statistics">
						<button type="submit" class="menuBarBtn">
							Statistics
						</button>
					</form>
				</div> -->
			</c:when>

		</c:choose>

	</div>
</div>