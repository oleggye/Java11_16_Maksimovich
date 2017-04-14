package by.epam.totalizator.dao.util.sql;

public final class CountrySQLStore {

	public static final String GET_COUNTRY_LIST_EN = 
			"select country.id, country.name from totalizator.country;";
	
	public static final String GET_COUNTRY_LIST_RU = 
			"select country.id, country.name_ru from totalizator.country;";
}
