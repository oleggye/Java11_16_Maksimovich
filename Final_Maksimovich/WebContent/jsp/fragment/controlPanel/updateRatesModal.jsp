<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />


<fmt:message bundle="${localBundle}"
	key="local.earningManagement.modal.update_coefficient" var="title" />
<fmt:message bundle="${localBundle}"
	key="local.earningManagement.modal.update_coefficient_button"
	var="button" />


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
	<div class="modal fade updateCoefficientModal"
		id="updateCoefficientModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-pencil"></span>
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

					<form method="post" action="javascript:void(null);"
						onsubmit="updateRate(this)" id="bettingForm">
						<input type="hidden" name="command" value="set-rates" /> 
						<input type="hidden" name="idCompetition" id="id-comp-field" value="" />

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
									<td id="home-td"><input type="number"
										class="form-control currency" min="0.01" step="0.01"
										max="99.99" value="0.01" id="home-rate-field" name="homeRate"
										required /></td>
									<td id="draw-td"><input type="number"
										class="form-control currency" min="0.01" step="0.01"
										max="99.99" value="0.01" id="draw-rate-field" name="drawRate"
										required /></td>
									<td id="away-td"><input type="number"
										class="form-control currency" min="0.01" step="0.01"
										max="99.99" value="0.01" id="away-rate-field" name="awayRate"
										required /></td>
								</tr>
							</tbody>
						</table>

						<button type="submit" class="btn btn-success"
							id="update-coefficient-btn">
							<span class="glyphicon glyphicon-ok"></span>
							<c:out value="${button}" />
						</button>

					</form>

				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>
</div>

<script>
	$('#updateCoefficientModal').on(
			'show.bs.modal',
			function(e) {
				var errorDivId = "#error-div";
				var successDivId = "#success-div";
				hideMessageDiv(errorDivId, successDivId);

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
						var date = moment(new Date(data.startTime)).format(
								'MM/DD hh:mm A');

						$modal.find('#date-td').html(date);
						$modal.find('#home-team-td').html(
								data.homeTeam.club.name);
						$modal.find('#away-team-td').html(
								data.awayTeam.club.name);
						$modal.find('#tournament-td')
								.html(data.tournament.name);
						$modal.find('#country-td').html(data.country);
						$('#home-rate-field').val(data.winHomeRate);
						$('#draw-rate-field').val(data.drawRate);
						$('#away-rate-field').val(data.winAwayRate);
					},
					error : function(xhr, str) {
						showMessageInDiv(errorDivId, xhr.responseText);
						$("#updateCoefficientModal").modal('hide');
					}
				});
			});

	function updateRate(form) {
		var updateCoefficientButtonId = "#update-coefficient-btn";
		var errorDivId = "#error-div";
		var successDivId = "#success-div";

		$(updateCoefficientButtonId).attr('disabled', true);

		var msg = $(form).serialize();

		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			url : '/Totalizator/controller',
			data : msg,
			success : function(data) {
				showMessageInDiv(successDivId, data);
				location.reload();
				//window.location.replace("controller?command=competition-management");
			},
			error : function(xhr, str) {

				showMessageInDiv(errorDivId, xhr.responseText);
			}
		});
		$(updateCoefficientButtonId).attr('disabled', false);
	};

	function hideMessageDiv(errorDivId, successDivId) {
		$(errorDivId).css('display', 'none');
		$(successDivId).css('display', 'none');
	};

	function showMessageInDiv(divID, data) {
		$(divID).css('display', 'block');
		$(divID).html(data);
	};
</script>