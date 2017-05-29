package by.epam.totalizator.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.totalizator.bean.Betting;
import by.epam.totalizator.bean.EventType;
import by.epam.totalizator.bean.Phone;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.IValidationService;
import by.epam.totalizator.service.exception.ServiceValidationException;

/**
 * #IValidationService interface implementation for validation
 */
public class ValidationServiceImpl implements IValidationService {

	private static final Validator validator = new Validator();

	private static final String FIRST_NAME_ERROR_KEY = "local.validation.error.first_name";
	private static final String LAST_NAME_ERROR_KEY = "local.validation.error.last_name";
	private static final String BIRTH_DATE_ERROR_KEY = "local.validation.error.date_of_birth";
	private static final String EMAIL_ERROR_KEY = "local.validation.error.email";
	private static final String PASSWORD_ERROR_KEY = "local.validation.error.password";
	private static final String SECRET_QUESTION_ERROR_KEY = "local.validation.error.secret_question";
	private static final String SECRET_QUESTION_ANSWER_ERROR_KEY = "local.validation.error.secret_answer";
	private static final String COUNTRY_ERROR_KEY = "local.validation.error.country";
	private static final String PHONE_ERROR_KEY = "local.validation.error.phone";
	private static final String CURRENCY_ERROR_KEY = "local.validation.error.currency";
	private static final String CITY_ERROR_KEY = "local.validation.error.city";

	private static final String USER_ERROR = "User is null";

	@Override
	public void validateUser(User user) throws ServiceValidationException {
		String message = null;

		Set<String> errorSet = new HashSet<>();

		if (user != null) {

			if (validator.isFirstNameInvalid(user.getFirstName())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), FIRST_NAME_ERROR_KEY);
				errorSet.add(message);
			}
			if (validator.isLastNameInvalid(user.getLastName())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), LAST_NAME_ERROR_KEY);
				errorSet.add(message);
			}

			if (validator.isEmailInvalid(user.getEmail())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), EMAIL_ERROR_KEY);
				errorSet.add(message);
			}

			if (validator.isDateOfBirthInvalid(user.getDateOfBirth())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), BIRTH_DATE_ERROR_KEY);
				errorSet.add(message);
			}

			if (validator.isIdInvalid(user.getCountry().getId())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), COUNTRY_ERROR_KEY);
				errorSet.add(message);
			}

			if (validator.isCityInvalid(user.getCity())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), CITY_ERROR_KEY);
				errorSet.add(message);
			}

			if (validator.isPhoneInvalid(user.getPhone())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), PHONE_ERROR_KEY);
				errorSet.add(message);
			}

			if (validator.isCurrencyInvalid(user.getCurrency())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), CURRENCY_ERROR_KEY);
				errorSet.add(message);
			}

			if (validator.isPasswordInvalid(user.getPassword())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), PASSWORD_ERROR_KEY);
				errorSet.add(message);
			}

			if (validator.isSecretQuestionIdInvalid(user.getSecret().getSecretQuestionId())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), SECRET_QUESTION_ERROR_KEY);
				errorSet.add(message);
			}

			if (validator.isSecretAnswerInvalid(user.getSecret().getSecretAnswer())) {
				message = 
						LocalizationBundle.getProperty(user.getLocale(), SECRET_QUESTION_ANSWER_ERROR_KEY);
				errorSet.add(message);
			}

			if (!errorSet.isEmpty()) {
				throw new ServiceValidationException(errorSet);
			}

		} else {
			throw new ServiceValidationException(USER_ERROR);
		}

	}

	@Override
	public void validateLogin(String login) throws ServiceValidationException {
		if (validator.isEmailInvalid(login)) {
			String message = 
					LocalizationBundle.getProperty(Locale.getDefault(), EMAIL_ERROR_KEY);
			throw new ServiceValidationException(message);
		}
	}

	@Override
	public void validatePassword(String password) throws ServiceValidationException {
		if (validator.isPasswordInvalid(password)) {
			String message = 
					LocalizationBundle.getProperty(Locale.getDefault(), PASSWORD_ERROR_KEY);
			throw new ServiceValidationException(message);
		}
	}

	@Override
	public void validateIdUser(int idUser) throws ServiceValidationException {

		if (validator.isIdInvalid(idUser)) {
			throw new ServiceValidationException("Wrong param: idUser=" + idUser);
		}
	}

	@Override
	public void validateIdSport(int idSport) throws ServiceValidationException {

		if (validator.isIdInvalid(idSport)) {
			throw new ServiceValidationException("Wrong param: idSport=" + idSport);
		}
	}

	@Override
	public void validateIdTournament(int idTournament) throws ServiceValidationException {

		if (validator.isIdInvalid(idTournament)) {
			throw new ServiceValidationException("Wrong params: idTournament=" + idTournament);
		}
	}

	@Override
	public void validateIdCompetition(int idCompetition) throws ServiceValidationException {
		if (validator.isIdInvalid(idCompetition)) {
			throw new ServiceValidationException("Wrong param: idCompetition=" + idCompetition);
		}
	}

	@Override
	public void validatePageNumber(int pageNumber) throws ServiceValidationException {
		if (validator.isIdInvalid(pageNumber)) {
			throw new ServiceValidationException("Wrong param: pageNumber= " + pageNumber);
		}
	}

	@Override
	public void validateStartTime(Date startTime) throws ServiceValidationException {

		if (validator.isStartTimeInvalid(startTime)) {
			throw new ServiceValidationException("Wrong param: startTime=" + startTime);
		}
	}

	@Override
	public void validateIdClub(int id) throws ServiceValidationException {

		if (validator.isIdInvalid(id)) {
			throw new ServiceValidationException("Wrong param: id=" + id);
		}
	}

	@Override
	public void validateIdCountry(int idCountry) throws ServiceValidationException {

		if (validator.isIdInvalid(idCountry)) {
			throw new ServiceValidationException("Wrong param: idCountry=" + idCountry);
		}
	}

	@Override
	public void validateHomeRate(BigDecimal homeRate) throws ServiceValidationException {

		if (validator.isMonetaryValueInvalid(homeRate)) {
			throw new ServiceValidationException("Wrong param: homeRate=" + homeRate);
		}
	}

	@Override
	public void validateDrawRate(BigDecimal drawRate) throws ServiceValidationException {
		if (validator.isMonetaryValueInvalid(drawRate)) {
			throw new ServiceValidationException("Wrong param: drawRate=" + drawRate);
		}
	}

	@Override
	public void validateAwayRate(BigDecimal awayRate) throws ServiceValidationException {
		if (validator.isMonetaryValueInvalid(awayRate)) {
			throw new ServiceValidationException("Wrong param: awayRate=" + awayRate);
		}
	}

	@Override
	public void validateEventType(EventType eventType) throws ServiceValidationException {

		if (eventType == null) {
			throw new ServiceValidationException("Wrong param: eventType=" + eventType);
		}
	}

	@Override
	public void validateBetting(Betting betting) throws ServiceValidationException {

		if (validator.isMonetaryValueInvalid(betting.getBetRate())) {
			throw new ServiceValidationException("Wrong param: betRate=" + betting.getBetRate());
		}

		if (validator.isMonetaryValueInvalid(betting.getBetSize())) {
			throw new ServiceValidationException("Wrong param: betSize=" + betting.getBetSize());
		}

		if (validator.isEventTypeInvalid(betting.getBetType())) {
			throw new ServiceValidationException("Wrong param: betType=" + betting.getBetType());
		}
	}

	@Override
	public void validateAmount(BigDecimal amount) throws ServiceValidationException {
		if (validator.isAmountInvalid(amount)) {
			throw new ServiceValidationException("Wrong param: amount=" + amount);
		}
	}

	private static class Validator {

		private static final String EMAIL_REGEXP = 
				"^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4}$";
		
		private static final String PASSWORD_REGEXP = 
				"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

		private static final String FIRST_NAME_REGEXP = "^[A-Za-zА-Яа-я]{3,40}";
		private static final String LAST_NAME_REGEXP = "^[A-Za-zА-Яа-я]{4,40}";
		private static final String CITY_REGEXP = "^[A-Za-zА-Яа-я]{3,14}";
		private static final String CURRENCY_REGEXP = "^[A-Z]{3}";

		private static final String SECRET_ANSWER_REGEXP = "[\\w]{4,20}";
		private static final String PHONE_CODE_REGEXP = "^[\\d]{3}";
		private static final String PHONE_NUMBER_REGEXP = "^[\\d]{9,10}";

		private static final int SECRET_QUESTION_QUANTITY = 4;
		private static final int LOWER_YEAR_BOUND = 18;
		private static final int UPPER_YEAR_BOUND = 120;

		boolean isEmailInvalid(String email) {

			if (email == null || email.isEmpty()) {
				return true;
			}

			Pattern pattern = Pattern.compile(EMAIL_REGEXP);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				return false;
			}

			return true;
		}

		boolean isPasswordInvalid(String password) {

			if (password == null || password.isEmpty()) {
				return true;
			}

			Pattern pattern = Pattern.compile(PASSWORD_REGEXP);
			Matcher matcher = pattern.matcher(password);
			if (matcher.matches()) {
				return false;
			}
			return true;
		}

		boolean isFirstNameInvalid(String firstName) {

			if (firstName == null || firstName.isEmpty()) {
				return true;
			}

			Pattern pattern = Pattern.compile(FIRST_NAME_REGEXP);
			Matcher matcher = pattern.matcher(firstName);
			if (matcher.matches()) {
				return false;
			}
			return true;
		}

		boolean isLastNameInvalid(String lastName) {

			if (lastName == null || lastName.isEmpty()) {
				return true;
			}

			Pattern pattern = Pattern.compile(LAST_NAME_REGEXP);
			Matcher matcher = pattern.matcher(lastName);
			if (matcher.matches()) {
				return false;
			}
			return true;
		}

		boolean isCityInvalid(String city) {

			if (city == null) {
				return true;
			}
			Pattern pattern = Pattern.compile(CITY_REGEXP);
			Matcher matcher = pattern.matcher(city);
			if (matcher.matches()) {
				return false;
			}
			return true;
		}

		boolean isCurrencyInvalid(String currency) {

			if (currency == null || currency.isEmpty()) {
				return true;
			}

			Pattern pattern = Pattern.compile(CURRENCY_REGEXP);
			Matcher matcher = pattern.matcher(currency);
			if (matcher.matches()) {
				return false;
			}
			return true;
		}

		boolean isPhoneInvalid(Phone phone) {

			if (phone == null) {
				return true;
			}

			String code = phone.getCode();
			String phoneNumber = phone.getPhoneNumber();

			Pattern pattern = Pattern.compile(PHONE_CODE_REGEXP);
			Matcher matcher = pattern.matcher(code);
			if (matcher.matches()) {
				return false;
			}

			pattern = Pattern.compile(PHONE_NUMBER_REGEXP);
			matcher = pattern.matcher(phoneNumber);
			if (matcher.matches()) {
				return false;
			}
			return true;
		}

		boolean isSecretQuestionIdInvalid(int id) {

			if (id < 1 || id > SECRET_QUESTION_QUANTITY) {
				return true;
			}
			return false;
		}

		boolean isSecretAnswerInvalid(String secretAnswer) {

			if (secretAnswer == null || secretAnswer.isEmpty()) {
				return true;
			}

			Pattern pattern = Pattern.compile(SECRET_ANSWER_REGEXP);
			Matcher matcher = pattern.matcher(secretAnswer);
			if (matcher.matches()) {
				return false;
			}
			return true;
		}

		boolean isIdInvalid(int id) {

			if (id < 1) {
				return true;
			}
			return false;
		}

		boolean isStartTimeInvalid(Date date) {
			if (date == null) {
				return true;
			}
			return false;
		}

		boolean isDateOfBirthInvalid(Date date) {

			if (date == null) {
				return true;
			}

			Date nowDate = new Date();

			int dateFullYear = date.getYear();
			int nowDateFullYear = nowDate.getYear();
			int fullYearDifference = nowDateFullYear - dateFullYear;

			if (fullYearDifference > LOWER_YEAR_BOUND 
					&& fullYearDifference < UPPER_YEAR_BOUND) {
				return false;
			}

			int dateMonthNumber = date.getMonth();
			int nowDateMonthNumber = nowDate.getMonth();

			int dateDayNumber = date.getDay();
			int nowDateDayNumber = nowDate.getDay();

			/* если разница попадает на нижнюю границу */
			if (fullYearDifference == LOWER_YEAR_BOUND) {

				if (nowDateMonthNumber > dateMonthNumber
						|| (nowDateMonthNumber == dateMonthNumber 
							&& nowDateDayNumber >= dateDayNumber)) {
					return false;
				}
			}
			/* если разница попадает на верхнюю границу */
			if (fullYearDifference == UPPER_YEAR_BOUND) {

				if (nowDateMonthNumber < dateMonthNumber
						|| (nowDateMonthNumber == dateMonthNumber
							&& nowDateDayNumber < dateDayNumber)) {
					return false;
				}
			}

			return true;
		}

		boolean isEventTypeInvalid(EventType eventType) {
			if (eventType == null) {
				return true;
			}
			return false;
		}

		boolean isAmountInvalid(BigDecimal amount) {
			if (amount == null) {
				return true;
			}
			return false;
		}

		boolean isMonetaryValueInvalid(BigDecimal value) {
			BigDecimal standard = new BigDecimal(0);

			if (value == null || value.compareTo(standard) != 1) {
				return true;
			}
			return false;
		}
	}
}