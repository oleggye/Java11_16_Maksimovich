<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.title"
	var="countryAndPhone" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.spain"
	var="spain" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.germany"
	var="germany" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.england"
	var="england" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.france"
	var="france" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.italy"
	var="italy" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.portugal"
	var="portugal" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.belarus"
	var="belarus" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.russia"
	var="russia" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.ukraine"
	var="ukraine" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.usa"
	var="usa" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.uk"
	var="uk" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.china"
	var="china" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.turkey"
	var="turkey" />
<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.greece"
	var="greece" />

<div class="form-group">
	<label><c:out value="${countryAndPhone}" /></label>
	<div class="form-inline">
		<select class="form-control" name="country" id="country">
			<option value="spain"><c:out value="${spain}" /></option>
			<option value="germany"><c:out value="${germany}" /></option>
			<option value="england"><c:out value="${england}" /></option>
			<option value="france"><c:out value="${france}" /></option>
			<option value="italy"><c:out value="${italy}" /></option>
			<option value="portugal"><c:out value="${portugal}" /></option>

			<option value="belarus"><c:out value="${belarus}" /></option>
			<option value="russia"><c:out value="${russia}" /></option>
			<option value="ukraine"><c:out value="${ukraine}" /></option>

			<option value="usa"><c:out value="${usa}" /></option>
			<option value="uk"><c:out value="${uk}" /></option>
			<option value="china"><c:out value="${china}" /></option>

			<option value="turkey"><c:out value="${turkey}" /></option>
			<option value="greece"><c:out value="${greece}" /></option>

		</select> <input class="form-control" name="phone" type="text" id="phone" required>
	</div>
</div>

<script>
	jQuery(function($) {
		$(function() {
			function maskPhone() {
				var country = $('#country option:selected').val();
				switch (country) {

				case "spain":
					$("#phone").mask("+34(99) 999-99-99");
					break;
				case "germany":
					$("#phone").mask("+49(99) 999-99-99");
					break;
				case "england":
					$("#phone").mask("+44(99) 999-99-99");
					break;
				case "france":
					$("#phone").mask("+33(99) 999-99-99");
					break;
				case "italy":
					$("#phone").mask("+39(99) 999-99-99");
					break;
				case "portugal":
					$("#phone").mask("+351(99) 999-99-99");
					break;
				case "belarus":
					$("#phone").mask("+375(99) 999-99-99");
					break;
				case "russia":
					$("#phone").mask("+7(999) 999-99-99");
					break;
				case "ukraine":
					$("#phone").mask("+380(99) 999-99-99");
					break;
				case "usa":
					$("#phone").mask("+1(999) 999-9999");
					break;
				case "uk":
					$("#phone").mask("+44(99) 999-99-99");
					break;
				case "china":
					$("#phone").mask("+86(99) 999-99-99");
					break;
				case "turkey":
					$("#phone").mask("+90(99) 999-99-99");
					break;
				case "greece":
					$("#phone").mask("+30(99) 999-99-99");
					break;
				}
			}
			maskPhone();
			$('#country').change(function() {
				maskPhone();
			});
		});
	});
</script>