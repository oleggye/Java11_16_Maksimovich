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
		<select class="form-control" name="idCountry" id="country">

			<c:forEach var="elem" items="${requestScope.countryList}">
				<option value="${elem.id}"><c:out value="${elem.name}" /></option>
			</c:forEach>

		</select> <input class="form-control" type="text" id="phoneNumber" required
			onchange="parsePhone(this)"> <input type="hidden"
			name="phone" id="phone"> <input type="hidden" name="code"
			id="code">
	</div>
</div>

<script>
	jQuery(function($) {
		$(function() {
			function maskPhone() {
				var country = $('#country option:selected').val();

				$("#phoneNumber").val('');
				switch (country) {

				case "1":
					$("#phoneNumber").mask("+34(99) 999-99-99");
					break;
				case "2":
					$("#phoneNumber").mask("+49(99) 999-99-99");
					break;
				case "3":
					$("#phoneNumber").mask("+44(99) 999-99-99");
					break;
				case "4":
					$("#phoneNumber").mask("+33(99) 999-99-99");
					break;
				case "5":
					$("#phoneNumber").mask("+39(99) 999-99-99");
					break;
				case "6":
					$("#phoneNumber").mask("+351(99) 999-99-99");
					break;
				case "7":
					$("#phoneNumber").mask("+375(99) 999-99-99");
					break;
				case "8":
					$("#phoneNumber").mask("+7(999) 999-99-99");
					break;
				case "9":
					$("#phoneNumber").mask("+380(99) 999-99-99");
					break;
				case "10":
					$("#phoneNumber").mask("+1(999) 999-9999");
					break;
				case "11":
					$("#phoneNumber").mask("+44(99) 999-99-99");
					break;
				case "12":
					$("#phoneNumber").mask("+86(99) 999-99-99");
					break;
				case "13":
					$("#phoneNumber").mask("+90(99) 999-99-99");
					break;
				case "14":
					$("#phoneNumber").mask("+30(99) 999-99-99");
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
		var phoneFirstPart, phoneFirstPartStartIndex, phoneFirstPartEndIndex, phoneSecondPart, phoneSecondPartStartIndex, phoneSecondPartEndIndex;
		var phoneSecondPartArr;
		var phone, code;

		if (checkField(phoneField)) {
			codeStartIndex = 1;
			codeEndIndex = phoneNumber.indexOf("(");
			code = phoneNumber.substring(codeStartIndex, codeEndIndex);

			phoneFirstPartStartIndex = codeEndIndex + 1;
			phoneFirstPartEndIndex = phoneNumber.indexOf(")");
			phoneFirstPart = phoneNumber.substring(phoneFirstPartStartIndex,
					phoneFirstPartEndIndex);

			phoneSecondPartStartIndex = phoneFirstPartEndIndex + 2;

			phoneSecondPartEndIndex = phoneNumber.length;
			phoneSecondPart = phoneNumber.substring(phoneSecondPartStartIndex,
					phoneSecondPartEndIndex).trim();

			phoneSecondPartArr = phoneSecondPart.split("-");

			phone = phoneFirstPart + phoneSecondPartArr[0]
					+ phoneSecondPartArr[1];

			$("#phone").val(phone);
			$("#code").val(code);
	
			return true;
		} else {
			return false;
		}
	}
</script>