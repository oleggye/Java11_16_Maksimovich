package by.epam.totalizator.service.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import by.epam.totalizator.bean.Locale;
import by.epam.totalizator.service.util.exception.ParserException;

public class Converter {

	private static final String RU_DATE_FORMAT_PATTERN = "dd/mm/yyyy";
	private static final String EN_DATE_FORMAT_PATTERN = "mm/dd/yyyy";

	private static final String ILLEGAL_INPUT_DATE_PARAM_MESSAGE = "Illegal input params: dateString=%s, locale=%s";
	private static final String WRONG_DATE_STRING_MESSAGE = "wrong dateString=%s";
	private static final String ILLEGAL_INPUT_DATE_TIME_PARAM_MESSAGE = "Illegal input params: dateTimeString=%s, locale=%s";
	private static final String WRONG_DATE_TIME_STRING_MESSAGE = "wrong dateTimeString=%s";
	private static final String ILLEGAL_INPUT_LOCAL_DATE_MESSAGE = "Illegal localDate=%s";
	
	
	public static LocalDate fromDateString(String dateString, Locale locale) throws ParserException {

		if (dateString == null || locale == null || dateString.isEmpty()) {
			throw new ParserException(String.format(ILLEGAL_INPUT_DATE_PARAM_MESSAGE, dateString, locale));
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
			throw new ParserException(String.format(WRONG_DATE_STRING_MESSAGE, dateString));
		}

		return localDate;
	}

	public static LocalDateTime fromDateTimeString(String dateTimeString, Locale locale) throws ParserException {

		if (dateTimeString == null || locale == null || dateTimeString.isEmpty()) {
			throw new ParserException(String.format(ILLEGAL_INPUT_DATE_TIME_PARAM_MESSAGE, dateTimeString, locale));
		}

		DateTimeFormatter formatter = null;
		LocalDateTime localDate = null;

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
			localDate = LocalDateTime.parse(dateTimeString, formatter);
		} catch (DateTimeParseException e) {
			throw new ParserException(String.format(WRONG_DATE_TIME_STRING_MESSAGE, dateTimeString));
		}

		return localDate;
	}

	public static Date fromLocalDate(LocalDate localDate)throws ParserException {
		
//		if (localDate == null){
//			throw new ParserException(String.format(ILLEGAL_INPUT_LOCAL_DATE_MESSAGE, localDate));
//		}
//		Date date = null;
//		LocalDateTime localDateTime = LocalDateTime.of(date, time)
//		try{
//			
//			date = Date.from(LocalDate);
//		}
		return null;
		
	}
}
