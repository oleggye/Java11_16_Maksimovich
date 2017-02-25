package by.epam.totalizator.dao.util.build;

import by.epam.totalizator.bean.Country;

public final class CountryBuilder {

	private Country country = new Country();

	public Country getCountry() {
		return country;
	}

	public void buildCountry(int id, String name) {
		country.setId(id);
		country.setName(name);
	}

}
