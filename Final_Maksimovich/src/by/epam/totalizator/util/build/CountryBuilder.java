package by.epam.totalizator.util.build;

import by.epam.totalizator.bean.Country;

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
