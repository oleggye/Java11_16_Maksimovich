<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="localBundle" />

<fmt:message bundle="${localBundle}"
	key="local.competition_management.modal.add_competition" var="title" />
<fmt:message bundle="${localBundle}"
	key="local.competition_management.modal.add_competition_button"
	var="button" />
<fmt:message bundle="${localBundle}"
	key="local.competition_management.modal.choose_sport" var="сhooseSport" />


<fmt:message bundle="${localBundle}" key="local.main.date" var="date" />
<fmt:message bundle="${localBundle}" key="local.main.sport" var="sport" />
<fmt:message bundle="${localBundle}" key="local.main.home_team"
	var="homeTeam" />
<fmt:message bundle="${localBundle}" key="local.main.away_team"
	var="awayTeam" />


<fmt:message bundle="${localBundle}" key="local.main.tournament"
	var="tournament" />
<fmt:message bundle="${localBundle}" key="local.main.place" var="place" />


<div class="container">
	<!-- Modal -->
	<div class="modal fade addCompetitionModal" id="addCompetitionModal"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-plus"></span>
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

					<form name="addCompetitionForm" method="post"
						action="javascript:void(null);" onsubmit="addCompetition(this)"
						id="addCompetitionForm">
						<input type="hidden" name="command" value="add-competition" />

						<table class="table">
							<thead>
								<tr>
									<th><c:out value="${date}" /></th>
									<th><c:out value="${sport}" /></th>
									<th><c:out value="${homeTeam}" /></th>
									<th><c:out value="${awayTeam}" /></th>
									<th><c:out value="${tournament}" /></th>
									<th><c:out value="${place}" /></th>

								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="text" class="form-control"
										id="date-field2" name="date"
										required /></td>
									<!-- "-->
									<td id="sport-td"><select class="form-control"
										name="idSport" id="sport-select"
										onchange="takeDataForCompetition()" autofocus>
											<option id="choose-sport-option" disabled selected><c:out
													value="${сhooseSport}" /></option>
											<c:forEach var="elem" items="${requestScope.sportList}">

												<option value="${elem.id}"><c:out
														value="${elem.name}" /></option>
											</c:forEach>
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
							<span class="glyphicon glyphicon-plus"></span>
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
	$('#date-field2').datetimepicker({
		format: 'Y/m/d H:i'
	});
	$.datetimepicker.setLocale("${sessionScope.local}");
	
</script>

<script>
	$('#addCompetitionModal').on(
			'show.bs.modal',
			function(e) {

				var chooseSportOptionId = "#choose-sport-option";
				var dateFieldId = "#date-field2";
				var sportSelectId = "#sport-select";
				var tournamentSelectId = "#tournament-select";
				var homeTeamSelectId = "#home-team-select";
				var awayTeamSelectId = "#away-team-select";
				var countrySelectId = "#country-select";
				var addCompetitionButtonId = "#addCompetitionBtn";
				var errorDivId = "#error-div";
				var successDivId = "#success-div";

				resetModalComponent(chooseSportOptionId, dateFieldId,
						sportSelectId, tournamentSelectId, homeTeamSelectId,
						awayTeamSelectId, countrySelectId,
						addCompetitionButtonId, errorDivId, successDivId);
			});

	function resetModalComponent(chooseSportOptionId, dateFieldId,
			sportSelectId, tournamentSelectId, homeTeamSelectId,
			awayTeamSelectId, countrySelectId, addCompetitionButtonId,
			errorDivId, successDivId) {

		setElementAttr(chooseSportOptionId, 'disabled', false);
		$(dateFieldId).val('');
		$(sportSelectId).prop('selectedIndex', 0);
		$(tournamentSelectId).empty();
		$(homeTeamSelectId).empty();
		$(awayTeamSelectId).empty();
		$(countrySelectId).empty();

		$(addCompetitionButtonId).prop('disabled', true);

		$(errorDivId).css('display', 'none');
		$(successDivId).css('display', 'none');
	};

	function setElementAttr(elemId, attrName, attrValue) {
		$(elemId).attr(attrName, attrValue);
	};

	function takeDataForCompetition() {

		var chooseSportOptionId = "#choose-sport-option";
		var sportSelectId = "#sport-select";
		var tournamentSelectId = "#tournament-select";
		var homeTeamSelectId = "#home-team-select";
		var awayTeamSelectId = "#away-team-select";
		var countrySelectId = "#country-select";
		var addCompetitionButtonId = "#addCompetitionBtn";

		var errorDivId = "#error-div";

		var idSport = $(sportSelectId).val();

		setElementAttr(chooseSportOptionId, 'disabled', true);

		if (!isNaN(idSport)) {//если выбрано поле со спортом

			var requestParams = {
				'command' : 'data-for-competition',
				'idSport' : idSport
			};

			$
					.ajax({
						cache : false,
						type : 'POST',
						dataType : 'json',
						url : '/Totalizator/controller',
						data : requestParams,
						success : function(data) {
							makeTournamentSelect(data[0], tournamentSelectId);
							makeTeamSelect(data[1], homeTeamSelectId,
									awayTeamSelectId);
							makeCountrySelect(data[2], countrySelectId);
							$(addCompetitionButtonId).prop('disabled', false);

						},
						error : function(xhr, str) {
							showMessageInDiv(errorDivId, xhr.responseText);


							$(addCompetitionButtonId).prop('disabled', true);
						}
					});
		}
	};

	function addCompetition(form) {
		var addCompetitionButtonId = "#addCompetitionBtn";
		var errorDivId = "#error-div";
		var successDivId = "#success-div";

		$(addCompetitionButtonId).attr('disabled', true);

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
			},
			error : function(xhr, str) {
				showMessageInDiv(errorDivId, xhr.responseText);
			}
		});
		$(addCompetitionButtonId).attr('disabled', false);
	};

	function showMessageInDiv(divID, data) {
		$(divID).css('display', 'block');
		$(divID).html(data);
	};
</script>