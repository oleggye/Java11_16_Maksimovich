package by.epam.totalizator.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Betting implements Serializable {

	private static final long serialVersionUID = 7718548548297164978L;

	private int id;
	private Competition competition;
	private User user;
	private BetType betType;
	private BigDecimal betRate;
	private BigDecimal betSize;
	private Date betTime;
	private BigDecimal gain;

	public Betting() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public BigDecimal getBetRate() {
		return betRate;
	}

	public void setBetRate(BigDecimal betRate) {
		this.betRate = betRate;
	}

	public BigDecimal getBetSize() {
		return betSize;
	}

	public void setBetSize(BigDecimal betSize) {
		this.betSize = betSize;
	}

	public Date getBetTime() {
		return betTime;
	}

	public void setBetTime(Date betTime) {
		this.betTime = betTime;
	}

	public BigDecimal getGain() {
		return gain;
	}

	public void setGain(BigDecimal gain) {
		this.gain = gain;
	}

	@Override
	public String toString() {
		return "Betting [id=" + id + ", competition=" + competition + ", user=" + user + ", betType=" + betType
				+ "betRate=" + betRate + ", betSize=" + betSize + ", betTime=" + betTime + ", gain=" + gain + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((betSize == null) ? 0 : betSize.hashCode());
		result = prime * result + ((betRate == null) ? 0 : betRate.hashCode());
		result = prime * result + ((betTime == null) ? 0 : betTime.hashCode());
		result = prime * result + ((betType == null) ? 0 : betType.hashCode());
		result = prime * result + ((competition == null) ? 0 : competition.hashCode());
		result = prime * result + ((gain == null) ? 0 : gain.hashCode());
		result = prime * result + id;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Betting other = (Betting) obj;
		if (betSize == null) {
			if (other.betSize != null)
				return false;
		} else if (!betSize.equals(other.betSize))
			return false;
		if (betRate == null) {
			if (other.betRate != null)
				return false;
		} else if (!betRate.equals(other.betRate))
			return false;
		if (betTime == null) {
			if (other.betTime != null)
				return false;
		} else if (!betTime.equals(other.betTime))
			return false;
		if (betType != other.betType)
			return false;
		if (competition == null) {
			if (other.competition != null)
				return false;
		} else if (!competition.equals(other.competition))
			return false;
		if (gain == null) {
			if (other.gain != null)
				return false;
		} else if (!gain.equals(other.gain))
			return false;
		if (id != other.id)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
