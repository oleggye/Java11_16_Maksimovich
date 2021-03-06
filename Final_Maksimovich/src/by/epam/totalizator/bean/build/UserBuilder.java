package by.epam.totalizator.bean.build;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Phone;
import by.epam.totalizator.bean.Secret;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.UserType;

/**
 * 
 * Realization of builder pattern for User bean {@link User}
 *
 */
public final class UserBuilder {

	private int id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	private String password;
	private Secret secret;
	private Country country;
	private String city;
	private Phone phone;
	private String currency;
	private BigDecimal balance;
	private UserType userType;
	private Date registrationTime;
	private Locale locale;
	private boolean banned;

	public UserBuilder buildId(int id) {
		this.id = id;
		return this;
	}

	public UserBuilder buildFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserBuilder buildLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserBuilder buildDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public UserBuilder buildPassword(String password) {
		this.password = password;
		return this;
	}

	public UserBuilder buildEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder buildSecret(Secret secret) {
		this.secret = secret;
		return this;
	}

	public UserBuilder buildCountry(Country country) {
		this.country = country;
		return this;
	}

	public UserBuilder buildCity(String city) {
		this.city = city;
		return this;
	}

	public UserBuilder buildPhone(Phone phone) {
		this.phone = phone;
		return this;
	}

	public UserBuilder buildCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public UserBuilder buildBalance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}

	public UserBuilder buildUserType(UserType userType) {
		this.userType = userType;
		return this;
	}

	public UserBuilder buildRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
		return this;
	}

	public UserBuilder buildLocale(Locale locale) {
		this.locale = locale;
		return this;
	}

	public UserBuilder buildBanned(boolean banned) {
		this.banned = banned;
		return this;
	}

	public User build() {
		User user = new User();

		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDateOfBirth(dateOfBirth);
		user.setEmail(email);
		user.setPassword(password);
		user.setSecret(secret);
		user.setCountry(country);
		user.setCity(city);
		user.setPhone(phone);
		user.setCurrency(currency);
		user.setBalance(balance);
		user.setUserType(userType);
		user.setRegistrationTime(registrationTime);
		user.setLocale(locale);
		user.setBanned(banned);

		return user;
	}
}
