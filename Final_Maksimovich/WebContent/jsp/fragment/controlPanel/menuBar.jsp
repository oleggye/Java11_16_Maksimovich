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

<div class="col-sm-2 sidenav">
	<div class="vertical-menu">

		<div class="menuBar">
			<form name="first" action="controller" method="post">
				<input type="hidden" name="command" value="user-profile"> <input
					type="submit" class="menuBarBtn" value="${yourProfile}">
			</form>
		</div>

		<div class="menuBar">
			<form name="second" action="controller" method="post">
				<input type="hidden" name="command" value="user-betting"> <input
					type="submit" class="menuBarBtn" value="${yourBetting}">
			</form>
		</div>

		<div class="menuBar">
			<form name="third" action="controller" method="post">
				<input type="hidden" name="command" value="add-funds"> <input
					type="submit" class="menuBarBtn" value="${addFunds}">
			</form>
		</div>

	</div>
</div>