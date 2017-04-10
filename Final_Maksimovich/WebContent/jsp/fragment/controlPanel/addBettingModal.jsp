<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}"
	key="local.user_betting.modal.betting" var="title" />
<fmt:message bundle="${localBundle}"
	key="local.user_betting.modal.add_betting_button" var="button" />
<fmt:message bundle="${localBundle}"
	key="local.user_betting.modal.bet_type" var="betType" />
<fmt:message bundle="${localBundle}"
	key="local.user_betting.modal.betting_size" var="betSize" />
<fmt:message bundle="${localBundle}"
	key="local.user_betting.modal.your_balance" var="balance" />


<fmt:message bundle="${localBundle}" key="local.main.date" var="date" />
<fmt:message bundle="${localBundle}" key="local.main.home_team"
	var="homeTeam" />
<fmt:message bundle="${localBundle}" key="local.main.away_team"
	var="awayTeam" />
<fmt:message bundle="${localBundle}" key="local.main.tournament"
	var="tournament" />
<fmt:message bundle="${localBundle}" key="local.main.place" var="place" />

<fmt:message bundle="${localBundle}" key="local.main.home" var="home" />
<fmt:message bundle="${localBundle}" key="local.main.draw" var="draw" />
<fmt:message bundle="${localBundle}" key="local.main.away" var="away" />

<div class="container">
	<!-- Modal -->
	<div class="modal fade addBettingModal" id="makeBettingModal"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-piggy-bank"></span>
						<c:out value="${title}" />
					</h4>
				</div>
				<div class="modal-body">

					<div id="error-div" class="alert alert-danger falert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					</div>

					<div id="success-div" class="alert alert-success alert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					</div>

					<table class="table">
						<thead>
							<tr>
								<th><c:out value="${date}" /></th>
								<th><c:out value="${homeTeam}" /></th>
								<th><c:out value="${awayTeam}" /></th>
								<th><c:out value="${tournament}" /></th>
								<th><c:out value="${place}" /></th>
								<th><c:out value="${home}" /></th>
								<th><c:out value="${draw}" /></th>
								<th><c:out value="${away}" /></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td id="date-td"></td>
								<td id="home-team-td"></td>
								<td id="away-team-td"></td>
								<td id="tournament-td"></td>
								<td id="country-td"></td>
								<td id="home-td"></td>
								<td id="draw-td"></td>
								<td id="away-td"></td>
							</tr>
						</tbody>
					</table>

					<form name="bettingForm" method="post"
						action="javascript:void(null);" onsubmit="makeBetting()"
						id="betting-form">
						<input type="hidden" name="command" value="make-betting" /> <input
							type="hidden" name="idCompetition" id="id-comp-field" value="" />

						<input type="hidden" name="bettingRate" id="id-bet-rate" value="" />


						<div class="form-inline">
							<div class="form-group">
								<label for="betting-type-field"><c:out
										value="${betType}" /></label> <select class="form-control"
									name="bettingType" id="betting-type-field">
									<option value="H"><c:out value="${home}" /></option>
									<option value="D"><c:out value="${draw}" /></option>
									<option value="A"><c:out value="${away}" /></option>
								</select>
							</div>

							<div class="form-group bettingAmount">
								<label for="betting-size-field"><c:out
										value="${betSize}" /></label> <input type="number"
									class="form-control currency" min="0.01" step="0.01"
									value="0.01" id="betting-size-field" name="bettingSize"
									required />
							</div>

						</div>

						<div class="addition">

							<button type="submit" class="btn btn-default"
								id="addBettingButton">
								<span class="glyphicon glyphicon-thumbs-up"></span>
								<c:out value="${button}" />
							</button>

							<h2>
								<span id="ballance"><c:out value="${balance}" /> <a
									href="#" id="balance-field"></a></span>
							</h2>
						</div>

					</form>

				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>
</div>

<script>
	$('#makeBettingModal').on('show.bs.modal', function(e) {

		var $modal = $(this), idCompetition = e.relatedTarget.id;
		var idCompetitionField = "#id-comp-field";
		var errorDivId = "#error-div";
		var successDivId = "#success-div";
		var addBettingButtonId = "#addBettingButton";

		$(errorDivId).css('display', 'none');
		$(successDivId).css('display', 'none');
		$(addBettingButtonId).attr('disabled', false);

		$(idCompetitionField).val(idCompetition);
		takeCompetitionInfo(idCompetition);
		takeUserBalance();
	});

	function takeCompetitionInfo(idCompetition) {

		var errorDivId = "#error-div";

		var requestParams = {
			'command' : 'competition-info',
			'idCompetition' : idCompetition
		};

		$.ajax({
			cache : false,
			type : 'POST',
			dataType : 'json',
			url : '/Totalizator/controller',
			data : requestParams,
			success : function(data) {
				moment().locale('ru');
				var date = moment(new Date(data.startTime)).format(
						'MM/DD hh:mm A');

				$('#date-td').html(date);
				$('#home-team-td').html(data.homeTeam.club.name);
				$('#away-team-td').html(data.awayTeam.club.name);
				$('#tournament-td').html(data.tournament.name);
				$('#country-td').html(data.country.name);
				$('#home-td').html(data.winHomeRate);
				$('#draw-td').html(data.drawRate);
				$('#away-td').html(data.winAwayRate);

			},
			error : function(xhr, str) {
				showMessageInDiv(errorDivId, xhr.responseText);
				$(addBettingButtonId).attr('disabled', true);
			}
		})
	};

	function takeUserBalance() {

		var errorDivId = "#error-div";

		var requestParams = {
			'command' : 'user-balance'
		};

		$.ajax({
			cache : false,
			type : 'POST',
			dataType : 'json',
			url : '/Totalizator/controller',
			data : requestParams,
			success : function(data) {
				var balance = data[0];
				var currency = data[1];
				var balanceAndCurrency = currencyFormatter(balance, currency);

				$('#balance-field').html(balanceAndCurrency);
				$('#betting-size-field').attr('max', balance);

			},
			error : function(xhr, str) {
				showMessageInDiv(errorDivId, xhr.responseText);
				$(addBettingButtonId).attr('disabled', true);

			}
		})

	};

	function makeBetting() {

		var addBettingButtonId = "#addBettingButton";
		var errorDivId = "#error-div";
		var successDivId = "#success-div";
		$(addBettingButtonId).attr('disabled', true);

		var betRateFieldId = "#id-bet-rate";
		var betRate = getBetRate();
		$(betRateFieldId).val(betRate);

		var msg = $('#betting-form').serialize();

		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			url : '/Totalizator/controller',
			data : msg,
			success : function(data) {
				showMessageInDiv(successDivId, data);
				location.reload();
			},
			error : function(xhr, str) {
				showMessageInDiv(errorDivId, xhr.responseText);
			}
		})
		$(addBettingButtonId).attr('disabled', false);
	};

	function currencyFormatter(value, currency) {

		var BYN_CONST = {
			currency : "BYN",
			symbol : "Br"
		};
		var RUB_CONST = {
			currency : "RUB",
			symbol : "₽"
		};
		var UAH_CONST = {
			currency : "UAH",
			symbol : "₴"
		};
		var USD_CONST = {
			currency : "USD",
			symbol : "$"
		};
		var EUR_CONST = {
			currency : "EUR",
			symbol : "€"
		};
		var GBP_CONST = {
			currency : "GBP",
			symbol : "£"
		};
		var CNY_CONST = {
			currency : "CNY",
			symbol : "元"
		};
		var DEFAULT_CONST_SYMBOL = "¤";

		var result;
		var currencySymbol;

		switch (currency) {
		case BYN_CONST.currency:
			currencySymbol = BYN_CONST.symbol;
			break;

		case RUB_CONST.currency:
			currencySymbol = RUB_CONST.symbol;
			break;

		case UAH_CONST.currency:
			currencySymbol = UAH_CONST.symbol;
			break;

		case USD_CONST.currency:
			currencySymbol = USD_CONST.symbol;
			break;

		case EUR_CONST.currency:
			currencySymbol = EUR_CONST.symbol;
			break;

		case GBP_CONST.currency:
			currencySymbol = GBP_CONST.symbol;
			break;

		case CNY_CONST.currency:
			currencySymbol = CNY_CONST.symbol;
			break;

		default:
			currencySymbol = DEFAULT_CONST_SYMBOL;
			break;
		}
		result = value + " " + currencySymbol;
		return result;
	};

	function getBetRate() {

		var betRate;

		var bettingTypeField = "#betting-type-field";
		var bettingType = $(bettingTypeField).val();
		var homeTdId = "#home-td";
		var drawTdId = "#draw-td";
		var awayTdId = "#away-td";

		var HOME_VALUE_CONST = 'H';
		var DRAW_VALUE_CONST = 'D';
		var AWAY_VALUE_CONST = 'A';

		switch ($(bettingTypeField).val()) {

		case HOME_VALUE_CONST:
			betRate = $(homeTdId).html();
			break;

		case DRAW_VALUE_CONST:
			betRate = $(drawTdId).html();
			break;

		case AWAY_VALUE_CONST:
			betRate = $(awayTdId).html();
			break;
		default:
			betRate = 0.0;
		}
		return betRate;
	};
</script>
