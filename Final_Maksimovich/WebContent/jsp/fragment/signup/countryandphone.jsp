<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}" key="local.signup.c_and_p.title"
	var="countryAndPhone" />

<div class="form-group">
	<label><c:out value="${countryAndPhone}" /></label> <span
		class="req-symbol">*</span>

	<div class="form-inline">
		<select class="form-control" name="idCountry" id="country"
			style="width: 11em;">

			<c:forEach var="elem" items="${requestScope.countryList}">
				<option value="${elem.id}"><c:out value="${elem.name}" /></option>
			</c:forEach>

		</select> <select class="form-control" id="code">

			<option value="1">+34</option>
			<option value="2">+49</option>
			<option value="3">+44</option>
			<option value="4">+33</option>
			<option value="5">+39</option>
			<option value="6">+351</option>
			<option value="7">+375</option>
			<option value="8">+7</option>
			<option value="9">+380</option>
			<option value="10">+1</option>
			<option value="11">+44</option>
			<option value="12">+86</option>
			<option value="13">+90</option>
			<option value="14">+30</option>
		</select> <input class="form-control" type="text" id="phoneNumber" required
			onchange="parsePhone(this)"> <input type="hidden"
			name="phone" id="phone"> <input type="hidden" name="code"
			id="phoneCode">
	</div>
</div>

<script>
	jQuery(function($) {
		$(function() {
			function maskPhone() {
				var country = $('#code option:selected').val();

				$("#phoneNumber").val('');
				switch (country) {

				case "1":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "2":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "3":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "4":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "5":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "6":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "7":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "8":
					$("#phoneNumber").mask("(999) 999-99-99");
					break;
				case "9":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "10":
					$("#phoneNumber").mask("(999) 999-9999");
					break;
				case "11":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "12":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "13":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				case "14":
					$("#phoneNumber").mask("(99) 999-99-99");
					break;
				}
			}
			maskPhone();
			$('#country').change(function() {
				maskPhone();
			});
		});
	});

	function parsePhone(phoneField) {
		var phoneNumber = $(phoneField).val();
		var codeStartIndex, codeEndIndex;
		var phoneFirstPartStartIndex;
		var phoneSecondPartArr;
		var phone = "";

		if (checkField(phoneField)) {

			phoneFirstPartStartIndex = codeEndIndex;

			var patt1 = /\d/g;
			var result = phoneNumber.match(patt1);

			for (var i = 0; i < result.length; i++) {
				phone += result[i];
			}
			var code = $('#code option:selected').html();
			code = code.substring(1, code.length);

			$("#phoneCode").val(code);
			$("#phone").val(phone);

			return true;
		} else {
			return false;
		}
	}
</script>