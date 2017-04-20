package by.epam.totalizator.bean.build;

import java.math.BigDecimal;
import java.util.Date;

import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.bean.User;

/**
 * 
 * Realization of builder pattern for Betting bean
 * {@link Betting}
 *
 */
public class BettingBuilder {

	private int id;
	private Competition competition;
	private User user;
	private EventType betType;
	private BigDecimal betRate;
	private BigDecimal betSize;
	private Date betTime;
	private BigDecimal gain;

	public Betting build() {
		Betting betting = new Betting();

		betting.setId(id);
		betting.setCompetition(competition);
		betting.setUser(user);
		betting.setBetType(betType);
		betting.setBetRate(betRate);
		betting.setBetSize(betSize);
		betting.setBetTime(betTime);
		betting.setGain(gain);

		return betting;
	}

	public BettingBuilder buildId(int id) {
		this.id = id;
		return this;
	}

	public BettingBuilder buildCompetition(Competition competition) {
		this.competition = competition;
		return this;
	}

	public BettingBuilder buildUser(User user) {
		this.user = user;
		return this;
	}

	public BettingBuilder buildBetType(EventType betType) {
		this.betType = betType;
		return this;
	}

	public BettingBuilder buildBetRate(BigDecimal betRate) {
		this.betRate = betRate;
		return this;
	}

	public BettingBuilder buildBetSize(BigDecimal betSize) {
		this.betSize = betSize;
		return this;
	}

	public BettingBuilder buildBetTime(Date betTime) {
		this.betTime = betTime;
		return this;
	}

	public BettingBuilder buildGain(BigDecimal gain) {
		this.gain = gain;
		return this;
	}

}
