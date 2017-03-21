package by.epam.totalizator.service.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.totalizator.bean.Locale;

final public class Validator {

	private static final String EMAIL_REGEXP = "(\\w{6,})@(\\w+\\.)([a-z]{2,4})";
	private static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
	private static final String FIRST_NAME_REGEXP = "^[A-Za-zА-Яа-я]{4,40}";
	private static final String LAST_NAME_REGEXP = "^[A-Za-zА-Яа-я]{4,40}";
	private static final String CURRENCY_REGEXP = "^[A-Z]{3}";
	private static final String SECRET_ANSWER_REGEXP = "[\\w]{4,20}";
	private static final String RU_DATE_FORMAT_PATTERN = "dd/mm/yyyy";
	private static final String EN_DATE_FORMAT_PATTERN = "mm/dd/yyyy";

	private static final int SECRET_QUESTION_QUANTITY = 4;
	private static final int ADULT_AGE = 18;

	public static boolean isEmailInvalid(String email) {

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

	public static boolean isPasswordInvalid(String password) {

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

	public static boolean isFirstNameInvalid(String firstName) {

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

	public static boolean isLastNameInvalid(String lastName) {

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

	public static boolean isCountryInvalid(String country) {

		if (country == null || country.isEmpty()) {
			return true;
		}
		return true;
	}

	public static boolean isCurrencyInvalid(String currency) {

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

	public static boolean isDateInvalid(String dateString, Locale locale) {

		if (dateString == null || locale == null || dateString.isEmpty()) {
			return true;
		}

		DateTimeFormatter formatter = null;
		LocalDate localDate = null;

		switch (locale) {

		case en_EN:
			formatter = DateTimeFormatter.ofPattern(EN_DATE_FORMAT_PATTERN);
			break;
		case ru_RU:
			formatter = DateTimeFormatter.ofPattern(RU_DATE_FORMAT_PATTERN);
			break;
		default:
			formatter = DateTimeFormatter.ofPattern(EN_DATE_FORMAT_PATTERN);
			break;
		}

		try {
			localDate = LocalDate.parse(dateString, formatter);
		} catch (DateTimeParseException e) {
			return true;
		}

		LocalDate boundaryDate = LocalDate.now().minusYears(ADULT_AGE);

		if (localDate.compareTo(boundaryDate) != -1) {
			return true;
		}
		return false;
	}

	public static boolean isSecretQuestionIdInvalid(int id) {

		if (id < 1 || id > SECRET_QUESTION_QUANTITY) {
			return true;
		}
		return false;
	}

	public static boolean isSecretAnswerInvalid(String secretAnswer) {

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

	public static boolean isIdParamInvalid(String idParam) {

		int id;

		if (idParam == null) {
			return true;
		} else {
			try {
				id = Integer.valueOf(idParam);

			} catch (NumberFormatException e) {
				return true;
			}
		}

		if (id < 1) {
			return true;
		}

		return false;
	}

	public static boolean isDateInvalid(String dateParam){
		
		if (dateParam == null || dateParam.isEmpty()) {
			return true;
		}
		
		try{
			Date date = new Date(Date.parse(dateParam));
		}catch(IllegalArgumentException e){
			return true;
		}
		return false;
	}
}
