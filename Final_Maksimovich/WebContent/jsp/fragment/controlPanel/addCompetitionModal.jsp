<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<div class="container">
	<!-- Modal -->
	<div class="modal fade addCompetitionModal" id="addCompetitionModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-exclamation-sign"></span> Add a
						competition
					</h4>
				</div>
				<div class="modal-body">

					<form method="post" action="javascript:void(null);"
						onsubmit="addCompetition()" id="competitionForm">
						<input type="hidden" name="command" value="add-competition" />

						<table class="table">
							<thead>
								<tr>
									<th>Date</th>
									<th>Sport</th>
									<th>HomeTeam</th>
									<th>AwayTeam</th>
									<th>Tournament</th>
									<th>Country</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="text" class="form-control"
										id="date-field" name="date" /></td>

									<td id="sport-td"><select class="form-control"
										name="idSport" id="sport-select"
										onchange="takeDataForCompetition()" autofocus>
											<option selected>Choose sport</option>
											<option value="1">Football</option>
											<option value="2">Tennis</option>
											<option value="3">Basketball</option>
											<option value="4">Hockey</option>
									</select></td>

									<td id="home-team-td"><select class="form-control"
										name="idHomeClub" id="home-team-select">
									</select></td>

									<td id="away-team-td"><select class="form-control"
										name="idAwayClub" id="away-team-select">
									</select></td>

									<td id="tournament-td"><select class="form-control"
										name="idTournament" id="tournament-select">
									</select></td>

									<td id="country-td"><select class="form-control"
										name="idCountry" id="country-select">
									</select></td>

								</tr>
							</tbody>
						</table>

						<button type="submit" class="btn btn-default clearfix"
							id="addCompetitionBtn">
							<span class="glyphicon glyphicon-plus"></span> Add
						</button>

					</form>


				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>
</div>

<script>
	$('#date-field').datetimepicker();
</script>

<script>
	function addCompetition() {
		var date = moment().format($('#date-field').val(),
				'MMMM Do YYYY, hh:mm TT');
		console.log(date);

		var msg = $('#competitionForm').serialize();

		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			url : '/Totalizator/controller',
			data : msg,
			success : function(data) {
				alert('Соревнование было успешно добавлено!');

				$("#addCompetitionModal").modal('hide');
			},
			error : function(xhr, str) {
				alert('Возникла ошибка: ' + xhr.responseCode);
				$("#addCompetitionModal").modal('hide');
			}
		});

	};

	function takeDataForCompetition() {
		var idSport = document.getElementById("sport-select").value;
		var requestParams = {
			'command' : 'data-for-competition',
			'idSport' : idSport
		};

		$.ajax({
			cache : false,
			type : 'POST',
			dataType : 'json',
			url : '/Totalizator/controller',
			data : requestParams,
			success : function(data) {
				makeTournamentSelect(data[0]);
				makeTeamSelect(data[1]);
				makeCountrySelect(data[2]);
			},
			error : function(xhr, str) {
				alert('Возникла ошибка: ' + xhr.responseCode);
			}
		});

	};

	function makeCountrySelect(countryList) {

		var countrySelectComponent = $("#country-select").html('');

		for ( var index in countryList) {

			$("<option />", {
				value : countryList[index].id,
				text : countryList[index].name
			}).appendTo(countrySelectComponent);
		}
		countrySelectComponent.appendTo($("#country-td"));
	};

	function makeTeamSelect(teamList) {

		var homeTeamSelectComponent = $("#home-team-select").html('');
		var awayTeamSelectComponent = $("#away-team-select").html('');
		var temp = '';

		for ( var index in teamList) {

			$("<option />", {
				value : teamList[index].club.id,
				text : teamList[index].club.name
			}).appendTo(homeTeamSelectComponent);

			$("<option />", {
				value : teamList[index].club.id,
				text : teamList[index].club.name
			}).appendTo(awayTeamSelectComponent);
		}
		homeTeamSelectComponent.appendTo($("#home-team-td"));
		awayTeamSelectComponent.appendTo($("#away-team-td"));
	};

	function makeTournamentSelect(tournamentList) {

		var tournamentSelectComponent = $("#tournament-select").html('');

		for ( var index in tournamentList) {

			$("<option />", {
				value : tournamentList[index].id,
				text : tournamentList[index].name
			}).appendTo(tournamentSelectComponent);
		}
		tournamentSelectComponent.appendTo($("#tournament-td"));
	};
</script>