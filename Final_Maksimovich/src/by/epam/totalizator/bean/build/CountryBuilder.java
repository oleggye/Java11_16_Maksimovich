package by.epam.totalizator.bean.build;

import by.epam.totalizator.bean.Country;

/**
 * 
 * Realization of builder pattern for Country bean
 * {@link Country}
 *
 */
public class CountryBuilder {

	private int id;
	private String name;

	public CountryBuilder buildId(int id) {
		this.id = id;
		return this;
	}

	public CountryBuilder buildName(String name) {
		this.name = name;
		return this;
	}

	public Country build() {
		Country country = new Country();

		country.setId(id);
		country.setName(name);

		return country;
	}
}
