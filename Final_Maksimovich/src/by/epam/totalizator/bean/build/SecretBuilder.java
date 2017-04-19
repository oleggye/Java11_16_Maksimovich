package by.epam.totalizator.bean.build;

import by.epam.totalizator.bean.Secret;

/**
 * 
 * Realization of builder pattern for Betting bean {@link Secret}
 *
 */
public class SecretBuilder {

	private int secretQuestionId;
	private String secretAnswer;

	public SecretBuilder buildSecretQuestionId(int secretQuestionId) {
		this.secretQuestionId = secretQuestionId;
		return this;
	}

	public SecretBuilder buidSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
		return this;
	}

	public Secret build() {
		Secret secret = new Secret();
		secret.setSecretQuestionId(secretQuestionId);
		secret.setSecretAnswer(secretAnswer);

		return secret;
	}
}
