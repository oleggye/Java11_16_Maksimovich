package by.epam.totalizator.controller.util;

import by.epam.totalizator.bean.UserType;

public final class SupportClass {

	private static final int DEFAULT_PAGE_NUMBER = 1;

	public static int parsePageNumber(String pageNumberParam) {

		int pageNumber;

		if (pageNumberParam == null) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		} else {
			pageNumber = Integer.valueOf(pageNumberParam);
		}
		return pageNumber;
	}

	public static boolean isUserTypeClient(UserType userType) {

		boolean isClient;

		if (UserType.ADMINISTRATOR.equals(userType) || UserType.BOOKMAKER.equals(userType)) {
			isClient = false;
		} else {
			isClient = true;
		}
		return isClient;
	}

	public static int calculatePageCount(int availableCount, int recordsPerPage) {

		double result = (double) availableCount / recordsPerPage;
		int pageCount = (int) Math.ceil(result);

		return pageCount;
	}
}
