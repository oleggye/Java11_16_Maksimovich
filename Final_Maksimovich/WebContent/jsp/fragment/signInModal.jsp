<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.signin.title"
	var="title" />
<fmt:message bundle="${localBundle}" key="local.signin.error"
	var="errorTitle" />
<fmt:message bundle="${localBundle}" key="local.signin.errormessage"
	var="locErrorMessage" />
<fmt:message bundle="${localBundle}" key="local.signin.email"
	var="email" />
<fmt:message bundle="${localBundle}" key="local.signin.password"
	var="password" />
<fmt:message bundle="${localBundle}" key="local.signin.submit"
	var="submit" />

<fmt:message bundle="${localBundle}" key="local.signup.page_title"
	var="signUp" />
<fmt:message bundle="${localBundle}" key="local.signin.remember_me"
	var="remember" />
<fmt:message bundle="${localBundle}" key="local.signin.forgot_password"
	var="forgot" />


<div class="container">
	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-lock"></span>
						<c:out value="${title}" />
					</h4>
				</div>

				<div class="modal-body">

					<form action="controller" method="post">
						<input type="hidden" name="command" value="signIn" />
						<div class="form-group">
							<label for="login-field"><span
								class="glyphicon glyphicon-envelope"></span> <c:out
									value="${email}" /></label> <input type="email" class="form-control"
								name="login" id="login-field" placeholder="Enter email"
								value="admin@example.com" required>
						</div>
						<div class="form-group">
							<label for="pass-field"><c:out value="${password}" /></label>
							<input type="password" class="form-control" name="password"
								id="pass-field" placeholder="Enter password" value="123"
								required>
						</div>
						<div class="checkbox">
							<label><input type="checkbox" value="" checked>
							<c:out value="${remember}" /></label>
						</div>
						<button type="submit" class="btn btn-default ">
							<span class="glyphicon glyphicon-off"></span>
							<c:out value="${submit}" />
						</button>
						<div class="addition">
							<a href="controller?command=signUp-page"><c:out value="${signUp}" /></a> <br> <a
								href="#"><c:out value="${forgot}" /></a>
						</div>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>
</div>