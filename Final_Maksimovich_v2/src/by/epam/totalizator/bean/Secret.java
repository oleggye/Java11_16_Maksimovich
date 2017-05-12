package by.epam.totalizator.bean;

import java.io.Serializable;

public class Secret implements Serializable {

	private static final long serialVersionUID = -6219603972066705406L;

	private int secretQuestionId;
	private String secretAnswer;

	public Secret() {
	}

	public int getSecretQuestionId() {
		return secretQuestionId;
	}

	public void setSecretQuestionId(int secretQuestionId) {
		this.secretQuestionId = secretQuestionId;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretQuestionAnswer) {
		this.secretAnswer = secretQuestionAnswer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((secretAnswer == null) ? 0 : secretAnswer.hashCode());
		result = prime * result + secretQuestionId;
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
		Secret other = (Secret) obj;
		if (secretAnswer == null) {
			if (other.secretAnswer != null)
				return false;
		} else if (!secretAnswer.equals(other.secretAnswer))
			return false;
		if (secretQuestionId != other.secretQuestionId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Secret [secretQuestionId=" + secretQuestionId + ", secretAnswer=" + secretAnswer + "]";
	}
}
