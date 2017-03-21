<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

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
						<span class="glyphicon glyphicon-piggy-bank"></span> Make your Bet
					</h4>
				</div>
				<div class="modal-body">

					<table class="table">
						<thead>
							<tr>
								<th>Date</th>
								<th>HomeTeam</th>
								<th>AwayTeam</th>
								<th>Tournament</th>
								<th>Place</th>
								<th>Home</th>
								<th>Draw</th>
								<th>Away</th>
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

					<form method="post" action="javascript:void(null);"
						onsubmit="call()" id="bettingForm">
						<input type="hidden" name="command" value="make-betting" /> <input
							type="hidden" name="idCompetition" id="id-comp-field" value="" />

						<div class="form-inline">
							<div class="form-group">
								<label for="betting-type-field">Choose bet type</label> <select
									class="form-control" name="bettingType" id="betting-type-field">
									<option value="H">Home</option>
									<option value="D">Draw</option>
									<option value="A">Away</option>
								</select>
							</div>

							<div class="form-group bettingAmount">
								<label for="betting-amount-field"> Betting Amount </label> <input
									type="number" class="form-control currency" min="0.01"
									step="0.01" value="0.01" id="betting-amount-field"
									name="betting-amount" required />
							</div>


						</div>

						<button type="submit" class="btn btn-default" id="addBetting">
							<span class="glyphicon glyphicon-thumbs-up"></span> Make
						</button>

					</form>

					<div class="addition">
						<h2>
							<span id="ballance">Your Balance: <a href="#"
								id="balance-field"></a></span>
						</h2>
					</div>

				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>
</div>

<script>
	$('#makeBettingModal').on(
			'show.bs.modal',
			function(e) {

				var $modal = $(this), elemId = e.relatedTarget.id;
				$modal.find('#id-comp-field').attr('value', elemId);

				var requestParams = {
					'command' : 'competition-info',
					'idCompetition' : elemId
				};

				$.ajax({
					cache : false,
					type : 'POST',
					dataType : 'json',
					url : '/Totalizator/controller',
					data : requestParams,
					success : function(data) {
						moment().locale('ru');
						var date = moment(new Date(data[0].startTime)).format(
								'MM/DD hh:mm A');

						var balance = data[1];

						$modal.find('#date-td').html(date);
						$modal.find('#home-team-td').html(
								data[0].homeTeam.club.name);
						$modal.find('#away-team-td').html(
								data[0].awayTeam.club.name);
						$modal.find('#tournament-td').html(
								data[0].tournament.name);
						$modal.find('#country-td').html(data[0].country.name);
						$modal.find('#home-td').html(data[0].winHomeRate);
						$modal.find('#draw-td').html(data[0].drawRate);
						$modal.find('#away-td').html(data[0].winAwayRate);

						$modal.find('#balance-field').html(balance + "$");
						$modal.find('#betting-amount-field').attr('max',
								balance);

					},
					error : function(xhr, str) {
						alert('Возникла ошибка: ' + xhr.statuss);
						$("#makeBettingModal").modal('hide');
					}
				});
			});

	function call() {
		var msg = $('#bettingForm').serialize();

		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			url : '/Totalizator/controller',
			data : msg,
			success : function(data) {
				alert('Ваша ставка принята!');
				//$('.modal-footer').html(data);
				$("#makeBettingModal").modal('hide');
			},
			error : function(xhr, str) {
				var errorCode = xhr.status;

				switch (errorCode) {
				case 502:
					alert('502');
					break;
				case 302:
					alert('302');
					break;
				default:
					alert('First case');
				}

				alert('Возникла ошибка: ' + xhr.status);
				$("#makeBettingModal").modal('hide');
			}
		});

	};
</script>
