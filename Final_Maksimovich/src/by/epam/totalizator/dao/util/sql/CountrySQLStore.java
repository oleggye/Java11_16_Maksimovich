package by.epam.totalizator.dao.util.sql;

public class CountrySQLStore {

	public static final String GET_COUNTRY_LIST = 
			"select country.id, country.name from totalizator.country;";
}
