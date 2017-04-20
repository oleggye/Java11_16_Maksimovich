function validateCompetitionForm(dateFieldId) {
	var date = $(dateFieldId).val();

	if (isNaN(Date.parse(date))) {
		return false;
	}

	var formatedDate = moment().format(date, 'MMMM Do YYYY, hh:mm TT');
	$(dateFieldId).val(formatedDate);
	return true;
};

function makeCountrySelect(countryList, countrySelectId) {

	$(countrySelectId).empty();

	var countrySelectComponent = $(countrySelectId).html('');

	for ( var index in countryList) {

		$("<option />", {
			value : countryList[index].id,
			text : countryList[index].name
		}).appendTo(countrySelectComponent);
	}
	countrySelectComponent.appendTo($(countrySelectId));
};

function makeTeamSelect(teamList, homeTeamSelectId, awayTeamSelectId) {

	$(homeTeamSelectId).empty();
	$(awayTeamSelectId).empty();

	var homeTeamSelectComponent = $(homeTeamSelectId).html('');
	var awayTeamSelectComponent = $(awayTeamSelectId).html('');
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
	homeTeamSelectComponent.appendTo($(homeTeamSelectId));
	awayTeamSelectComponent.appendTo($(awayTeamSelectId));
};

function makeTournamentSelect(tournamentList, tournamentSelectId) {

	$(tournamentSelectId).empty();

	var tournamentSelectComponent = $(tournamentSelectId).html('');

	for ( var index in tournamentList) {

		$("<option />", {
			value : tournamentList[index].id,
			text : tournamentList[index].name
		}).appendTo(tournamentSelectComponent);
	}
	tournamentSelectComponent.appendTo($(tournamentSelectId));
};

function showMessageInDiv(divID, data) {
	$(divID).css('display', 'block');
	$(divID).html(data);
};