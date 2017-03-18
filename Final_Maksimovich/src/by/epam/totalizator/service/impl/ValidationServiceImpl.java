package by.epam.totalizator.service.impl;

import java.util.HashSet;
import java.util.Set;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.service.ValidationService;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.util.Validator;

public class ValidationServiceImpl implements ValidationService {

	private static final String FIRST_NAME_ERROR = "firstNameError";
	private static final String LAST_NAME_ERROR = "lastNameError";
	private static final String BIRTH_DATE_ERROR = "birthDateError";
	private static final String EMAIL_ERROR = "emailError";
	private static final String SECRET_QUESTION_ERROR = "secretQuestionError";
	private static final String PASSWORD_ERROR = "passwordError";

	@Override
	public void validateUser(User user) throws ServiceValidationException {

		Set<String> errorSet = new HashSet<>();

		if (user != null) {

			if (Validator.isFirstNameInvalid(user.getFirstName())) {
				errorSet.add(FIRST_NAME_ERROR);
			}
			if (Validator.isLastNameInvalid(user.getFirstName())) {
				errorSet.add(LAST_NAME_ERROR);
			}

			if (Validator.isEmailInvalid(user.getEmail())) {
				errorSet.add(EMAIL_ERROR);
			}

			if (Validator.isSecretQuestionIdInvalid(user.getSecredQuestionId())) {
				errorSet.add(SECRET_QUESTION_ERROR);
			}

			/*
			 * if (Validator.isDateInvalid(user.getDateOfBirth().toString(),
			 * user.getLocale())) { errorSet.add(BIRTH_DATE_ERROR); }
			 */

			if (Validator.isPasswordInvalid(user.getPassword())) {
				errorSet.add(PASSWORD_ERROR);
			}
		} else {
			throw new ServiceValidationException("user= null");
		}

	}

	@Override
	public void validateLoginAndPassword(String login, String password) throws ServiceValidationException {

		Set<String> errorSet = new HashSet<>();

		if (Validator.isEmailInvalid(login)) {
			errorSet.add(EMAIL_ERROR);
		}

		if (Validator.isPasswordInvalid(password)) {
			errorSet.add(PASSWORD_ERROR);
		}

		if (errorSet.isEmpty())
			throw new ServiceValidationException(errorSet);

	}

	public void validateIdSportAndIdTournament(String idSportParam, String idTournamentParam)
			throws ServiceValidationException {

		if (Validator.isIdParamInvalid(idSportParam) && !Validator.isIdParamInvalid(idTournamentParam)) {
			throw new ServiceValidationException(
					"Wrong params: idSportParam=" + idSportParam + ", idTournamentParam=" + idTournamentParam);
		}
	}

	public void validatePageNumber(String pageNumberParam) throws ServiceValidationException {
		if (Validator.isIdParamInvalid(pageNumberParam)) {
			throw new ServiceValidationException("Wrong params: pageNumberParam= " + pageNumberParam);
		}
	}
}
