<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}"
	key="local.competition_management.modal.update_competition" var="title" />
<fmt:message bundle="${localBundle}"
	key="local.competition_management.modal.update_competition_button"
	var="button" />
<fmt:message bundle="${localBundle}"
	key="local.competition_management.modal.choose_sport" var="сhooseSport" />


<fmt:message bundle="${localBundle}" key="local.main.date" var="date" />
<fmt:message bundle="${localBundle}" key="local.main.sport" var="sport" />
<fmt:message bundle="${localBundle}" key="local.main.home_team"
	var="homeTeam" />
<fmt:message bundle="${localBundle}" key="local.main.away_team"
	var="awayTeam" />
<fmt:message bundle="${localBundle}" key="local.main.draw" var="draw" />


<fmt:message bundle="${localBundle}" key="local.main.tournament"
	var="tournament" />
<fmt:message bundle="${localBundle}" key="local.main.place" var="place" />

<div class="container">
	<!-- Modal -->
	<div class="modal fade updateCompetitionModal"
		id="updateCompetitionModal" role="dialog">
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

					<div id="update-error-div"
						class="alert alert-danger falert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					</div>

					<div id="update-success-div"
						class="alert alert-success alert-dismissable">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					</div>


					<form name="updateCompetitionForm" method="post"
						action="javascript:void(null);" onsubmit="updateCompetition(this)"
						id="update-competition-form">

						<input type="hidden" name="command" value="update-competition" />
						<input type="hidden" id="id-comp-field" name="idCompetition"
							value="" />

						<table class="table">
							<thead>
								<tr>
									<th><c:out value="${date}" /></th>
									<th><c:out value="${sport}" /></th>
									<th><c:out value="${homeTeam}" /></th>
									<th><c:out value="${awayTeam}" /></th>
									<th><c:out value="${tournament}" /></th>
									<th><c:out value="${place}" /></th>
									<th>Result</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="text" class="form-control"
										id="date-field1" name="date" required value="" /></td>

									<td id="comp-sport-td"><select class="form-control"
										name="idSport" id="comp-sport-select"
										onchange="takeDataForCompetitionUpdate()" disabled>
											<c:forEach var="elem" items="${requestScope.sportList}">

												<option value="${elem.id}"><c:out
														value="${elem.name}" /></option>
											</c:forEach>
									</select></td>

									<td id="comp-home-team-td"><select class="form-control"
										name="idHomeClub" id="comp-home-team-select">
									</select></td>

									<td id="comp-away-team-td"><select class="form-control"
										name="idAwayClub" id="comp-away-team-select">
									</select></td>

									<td id="comp-tournament-td"><select class="form-control"
										name="idTournament" id="comp-tournament-select">
									</select></td>

									<td id="comp-country-td"><select class="form-control"
										name="idCountry" id="comp-country-select">
									</select></td>

									<td id="comp-result-td"><select class="form-control"
										name="result" id="comp-result-select">
											<option disabled selected>Choose result</option>
											<option value="H"><c:out value="${homeTeam}" /></option>
											<option value="D"><c:out value="${draw}" /></option>
											<option value="A"><c:out value="${awayTeam}" /></option>
									</select></td>

								</tr>
							</tbody>
						</table>

						<button type="submit" class="btn btn-default clearfix"
							id="updateCompetitionBtn">
							<span class="glyphicon glyphicon-pencil"></span>
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
	$('#date-field1').datetimepicker({
		format : 'Y/m/d H:i'
	});
</script>

<script>
	$('#updateCompetitionModal').on('show.bs.modal', function(e) {

		var sportSelectId = "#comp-sport-select";
		var idCompetitionFieldId = "#id-comp-field";

		var $modal = $(this), elemId = e.relatedTarget.id;
		var paramArr = elemId.split("-");

		idCompetition = paramArr[0];
		idSport = paramArr[1];

		/*сбрасываем все поля для ввода*/
		resetUpdateModalComponent();

		/*задаем скрытому полю значение id соревнования*/
		$(idCompetitionFieldId).val(idCompetition);
		/*выбираем option, с value = idSport */
		$(sportSelectId).val(idSport);
		/*метод получения данных для обновления выбранного соревнования*/
		takeDataForCompetitionUpdate(idSport, idCompetition);
	});
	function resetUpdateModalComponent() {

		var dateFieldId = "#date-field1";
		var sportSelectId = "#comp-sport-select";
		var tournamentSelectId = "#comp-tournament-select";
		var homeTeamSelectId = "#comp-home-team-select";
		var awayTeamSelectId = "#comp-away-team-select";
		var countrySelectId = "#comp-country-select";
		var addCompetitionButtonId = "#updateCompetitionBtn";
		var updateErrorDivId = "#update-error-div";
		var updateSuccessDivId = "#update-success-div";
		var competititonResultSelect = "#comp-result-select";

		resetModalComponent(null, dateFieldId, sportSelectId,
				tournamentSelectId, homeTeamSelectId, awayTeamSelectId,
				countrySelectId, addCompetitionButtonId, updateErrorDivId,
				updateSuccessDivId);

		$(competititonResultSelect).prop('selectedIndex', 0);
	}

	function takeDataForCompetitionUpdate(idSport) {

		var tournamentSelectId = "#comp-tournament-select";
		var homeTeamSelectId = "#comp-home-team-select";
		var awayTeamSelectId = "#comp-away-team-select";
		var countrySelectId = "#comp-country-select";

		var updateCompetitionButtonId = "#updateCompetitionBtn";
		var errorDivId = "#error-div";

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

				makeTournamentSelect(data[0], tournamentSelectId);
				makeTeamSelect(data[1], homeTeamSelectId, awayTeamSelectId);
				makeCountrySelect(data[2], countrySelectId);
				
				//take data for the competition by it's id
				takeCompetitionInfo(idCompetition)
			},
			error : function(xhr, str) {
				showMessageInDiv(errorDivId, xhr.responseText);
				$(updateCompetitionButtonId).prop('disabled', true);
				return false;
			}
		});
	};

	function takeCompetitionInfo(idCompetition) {

		var dateFieldId = '#date-field1';
		var updateCompetitionButtonId = "#updateCompetitionBtn";
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

				var date = moment(new Date(data.startTime)).format(
						'YYYY/MM/DD H:mm');

				$(dateFieldId).val(date);
				chooseElementInAllSelect(data)
				$(updateCompetitionButtonId).prop('disabled', false);
			},
			error : function(xhr, str) {
				showMessageInDiv(errorDivId, xhr.responseText);
				$(updateCompetitionButtonId).prop('disabled', true);
			}
		});

	};

	function chooseElementInAllSelect(competitionData) {

		var sportSelectId = "#comp-sport-select";
		var tournamentSelectId = "#comp-tournament-select";
		var homeTeamSelectId = "#comp-home-team-select";
		var awayTeamSelectId = "#comp-away-team-select";
		var countrySelectId = "#comp-country-select";

		var competititonResultSelect = "#comp-result-select";
		var resultSelectValue = '';
		var WIN_HOME_VALUE_CONST = 'H';
		var DRAW_VALUE_CONST = 'D';
		var WIN_AWAY_VALUE_CONST = 'A';

		$(tournamentSelectId).val(competitionData.tournament.id);
		$(homeTeamSelectId).val(competitionData.homeTeam.club.id);
		$(awayTeamSelectId).val(competitionData.awayTeam.club.id);
		$(countrySelectId).val(competitionData.country.id);

		if (competitionData.result) {
			switch (competitionData.result) {
			case "HOME_WIN":
				resultSelectValue = WIN_HOME_VALUE_CONST;
				break;
			case "DRAW":
				resultSelectValue = DRAW_VALUE_CONST;
				break;
			case "AWAY_WIN":
				resultSelectValue = WIN_AWAY_VALUE_CONST;
				break;
			default:
				resultSelectValue = '';
			}
		}
		$(competititonResultSelect).val(resultSelectValue);
	};

	function updateCompetition(form) {
		var updateCompetitionButtonId = "#updateCompetitionBtn";
		var updateErrorDivId = "#update-error-div";
		var updateSuccessDivId = "#update-success-div";

		$(updateCompetitionButtonId).attr('disabled', true);

		var msg = $(form).serialize();

		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			url : '/Totalizator/controller',
			data : msg,
			success : function(data) {
				showMessageInDiv(updateSuccessDivId, data);
				location.reload();
			},
			error : function(xhr, str) {
				showMessageInDiv(updateErrorDivId, xhr.responseText);
			}
		});
		$(updateCompetitionButtonId).attr('disabled', false);
	};
</script>