package by.epam.totalizator.dao.util.build;

import java.math.BigDecimal;
import java.util.Date;

import by.epam.totalizator.bean.Club;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Result;
import by.epam.totalizator.bean.Sport;
import by.epam.totalizator.bean.Team;

public final class CompetitionBuilder {
	private Competition competition = new Competition();

	public Competition getCompetition() {
		return competition;
	}

//	public void buildCompetition(int id, Sport sport, Tournament tournament, Team homeTeam, Team awayTeam,
//			Country country, Date startTime, BigDecimal winHomeRate, BigDecimal drawRate, BigDecimal winAwayRate,
//			Result result) {
//
//	}

	public void buildId(int id) {
		competition.setId(id);
	}

	public void buildSport(int id, String name) {
		SportBuilder builder = new SportBuilder();
		builder.buildSport(id, name);
		competition.setSport(builder.getSport());
	}

	public void buildTournament(int id, String name, Sport sport) {
		TournamentBuilder builder = new TournamentBuilder();
		builder.buildTournament(id, name, sport);
		competition.setTournament(builder.getTournament());
	}

	public void buildHomeTeam(Club club, Country country, Sport sport) {
		Team team = buildTeam(club, country, sport);
		competition.setHomeTeam(team);
	}

	public void buildAwayTeam(Club club, Country country, Sport sport) {
		Team team = buildTeam(club, country, sport);
		competition.setAwayTeam(team);
	}

	private Team buildTeam(Club club, Country country, Sport sport) {
		TeamBuilder builder = new TeamBuilder();
		builder.buildTeam(club, country, sport);
		return builder.getTeam();
	}

	public void buildCountry(int id, String name) {
		CountryBuilder builder = new CountryBuilder();
		builder.buildCountry(id, name);
		competition.setCountry(builder.getCountry());
	}

	public void buildStartTime(Date startTime) {
		competition.setStartTime(startTime);
	}

	public void builderWinHomeRate(BigDecimal winHomeRate) {
		competition.setWinHomeRate(winHomeRate);
	}

	public void buildDrawRate(BigDecimal drawRate) {
		competition.setDrawRate(drawRate);
	}

	public void buildWinAwayRate(BigDecimal winAwayRate) {
		competition.setWinAwayRate(winAwayRate);
	}

	public void buildResult(Result result) {
		competition.setResult(result);
	}
}
