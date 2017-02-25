package by.epam.totalizator.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Competition implements Serializable {

	private static final long serialVersionUID = 3474988699943319208L;

	private int id;
	private Sport sport;
	private Tournament tournament;
	private Team homeTeam;
	private Team awayTeam;
	private Country country;
	private Date date;
	private LocalDateTime startTime;
	private BigDecimal winHomeRate;
	private BigDecimal drawRate;
	private BigDecimal winAwayRate;
	private Result result;

	public Competition() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getWinHomeRate() {
		return winHomeRate;
	}

	public void setWinHomeRate(BigDecimal winHomeRate) {
		this.winHomeRate = winHomeRate;
	}

	public BigDecimal getDrawRate() {
		return drawRate;
	}

	public void setDrawRate(BigDecimal drawRate) {
		this.drawRate = drawRate;
	}

	public BigDecimal getWinAwayRate() {
		return winAwayRate;
	}

	public void setWinAwayRate(BigDecimal winAwayRate) {
		this.winAwayRate = winAwayRate;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", sport=" + sport + ", tournament=" + tournament + ", homeTeam=" + homeTeam
				+ ", awayTeam=" + awayTeam + ", country=" + country + ", startTime=" + startTime + ", winHomeRate="
				+ winHomeRate + ", drawRate=" + drawRate + ", winAwayRate=" + winAwayRate + ", result=" + result + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((drawRate == null) ? 0 : drawRate.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + id;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((sport == null) ? 0 : sport.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((tournament == null) ? 0 : tournament.hashCode());
		result = prime * result + ((winAwayRate == null) ? 0 : winAwayRate.hashCode());
		result = prime * result + ((winHomeRate == null) ? 0 : winHomeRate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competition other = (Competition) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (drawRate == null) {
			if (other.drawRate != null)
				return false;
		} else if (!drawRate.equals(other.drawRate))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (id != other.id)
			return false;
		if (result != other.result)
			return false;
		if (sport == null) {
			if (other.sport != null)
				return false;
		} else if (!sport.equals(other.sport))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (tournament == null) {
			if (other.tournament != null)
				return false;
		} else if (!tournament.equals(other.tournament))
			return false;
		if (winAwayRate == null) {
			if (other.winAwayRate != null)
				return false;
		} else if (!winAwayRate.equals(other.winAwayRate))
			return false;
		if (winHomeRate == null) {
			if (other.winHomeRate != null)
				return false;
		} else if (!winHomeRate.equals(other.winHomeRate))
			return false;
		return true;
	}
}
