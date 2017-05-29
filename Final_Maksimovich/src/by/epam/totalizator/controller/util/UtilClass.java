package by.epam.totalizator.controller.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.UserType;

/**
 * 
 * Class with utility methods
 *
 */
public final class UtilClass {

	private static final Logger LOGGER = LogManager.getLogger(UtilClass.class.getName());

	private static final int DEFAULT_PAGE_NUMBER = 1;
	private static final int DEFAULT_VALUE = 0;

	/**
	 * Method for parsing page number from string param
	 * 
	 * @param pageNumberParam
	 *            a string that contains page number
	 * @return page number from the string or {@value #DEFAULT_PAGE_NUMBER}
	 */
	public static int parsePageNumber(String pageNumberParam) {

		int pageNumber;

		if (pageNumberParam == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		} else {
			try{
			pageNumber = Integer.valueOf(pageNumberParam);
			}catch(NumberFormatException e){
				LOGGER.log(Level.WARN, e);
				pageNumber = DEFAULT_VALUE;
			}
		}
		return pageNumber;
	}

	/**
	 * Method for safety {@link java.util.Date} parsing from string param
	 * 
	 * @param dateParam
	 *            a string that contains date
	 * @return parsed date or if happened {@link IllegalArgumentException} - 
	 *         returns null
	 */
	public static Date parseDate(String dateParam) {
		Date date;

		try {
			date = new Date(dateParam);
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.WARN, e);
			date = null;
		}
		return date;
	}

	/**
	 * Method for safety {@link java.math.BigDecimal} parsing from string param
	 * 
	 * @param valueParam
	 *            a string that contains value
	 * @return parsed #BigDecimal value or if happened
	 *         {@link NumberFormatException} - returns null
	 */
	public static BigDecimal parseBigDecimal(String valueParam) {
		BigDecimal value;
		try {
			value = new BigDecimal(valueParam);
		} catch (NumberFormatException e) {
			LOGGER.log(Level.WARN, e);
			value = null;
		}
		return value;
	}

	/**
	 * Method for safety id parsing from string param
	 * 
	 * @param idParam
	 *            a string that contains id
	 * @return parsed id value or if happened {@link NumberFormatException} - returns
	 *         {@link #DEFAULT_VALUE}
	 */
	public static int parseId(String idParam) {
		int id;

		try {
			id = Integer.valueOf(idParam);
		} catch (NumberFormatException e) {
			LOGGER.log(Level.WARN, e);
			id = DEFAULT_VALUE;
		}
		return id;
	}

	/**
	 * Method to check that {@link UserType} userType is not
	 * {@link UserType#ADMINISTRATOR} or {@link UserType#BOOKMAKER}
	 * 
	 * @param contains
	 *            instance of {@link UserType}
	 * @return result of checking
	 */
	public static boolean isUserTypeClient(UserType userType) {

		boolean isClient;

		if (UserType.ADMINISTRATOR.equals(userType) || UserType.BOOKMAKER.equals(userType)) {
			isClient = false;
		} else {
			isClient = true;
		}
		return isClient;
	}

	/**
	 * Method for calculating quantity of pages
	 * 
	 * @param availableCount
	 *            all record count
	 * @param recordsPerPage
	 * @return page count
	 */
	public static int calculatePageCount(int availableCount, int recordsPerPage) {

		double result = (double) availableCount / recordsPerPage;
		int pageCount = (int) Math.ceil(result);

		return pageCount;
	}
}