package by.epam.totalizator.bean.build;

import java.math.BigDecimal;
import java.util.Date;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;
import by.epam.totalizator.bean.Tournament;

/**
 * 
 * Realization of builder pattern for Competition bean
 * {@link Competition}
 *
 */
public final class CompetitionBuilder {

	private int id;
	private Sport sport;
	private Tournament tournament;
	private Team homeTeam;
	private Team awayTeam;
	private Country country;
	private Date startTime;
	private BigDecimal winHomeRate;
	private BigDecimal drawRate;
	private BigDecimal winAwayRate;
	private EventType result;

	public Competition build() {

		Competition competition = new Competition();
		competition.setId(id);
		competition.setSport(sport);
		competition.setTournament(tournament);
		competition.setHomeTeam(homeTeam);
		competition.setAwayTeam(awayTeam);
		competition.setCountry(country);
		competition.setStartTime(startTime);
		competition.setWinHomeRate(winHomeRate);
		competition.setDrawRate(drawRate);
		competition.setWinAwayRate(winAwayRate);
		competition.setResult(result);

		return competition;
	}

	public CompetitionBuilder buildId(int id) {
		this.id = id;
		return this;
	}

	public CompetitionBuilder buildSport(Sport sport) {
		this.sport = sport;
		return this;
	}

	public CompetitionBuilder buildTournament(Tournament tournament) {
		this.tournament = tournament;
		return this;
	}

	public CompetitionBuilder buildHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
		return this;
	}

	public CompetitionBuilder buildAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
		return this;
	}

	public CompetitionBuilder buildCountry(Country country) {
		this.country = country;
		return this;
	}

	public CompetitionBuilder buildStartTime(Date startTime) {
		// LocalDateTime dateTime = timestamp.toLocalDateTime();
		this.startTime = startTime;
		return this;
	}

	public CompetitionBuilder builderWinHomeRate(BigDecimal winHomeRate) {
		this.winHomeRate = winHomeRate;
		return this;
	}

	public CompetitionBuilder buildDrawRate(BigDecimal drawRate) {
		this.drawRate = drawRate;
		return this;
	}

	public CompetitionBuilder buildWinAwayRate(BigDecimal winAwayRate) {
		this.winAwayRate = winAwayRate;
		return this;
	}

	public CompetitionBuilder buildResult(EventType result) {
		this.result = result;
		return this;
	}
}
