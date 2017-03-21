package by.epam.totalizator.service.util;

final public class SupportClass {
	
	private SupportClass(){};

	public static int calcPageCount(int availableCount, int recordsPerPage) {

		double result = (double) availableCount / recordsPerPage;
		// TODO: предполагается, что long многова-то
		int pageCount = (int) Math.ceil(result);
		return pageCount;
	}

}
