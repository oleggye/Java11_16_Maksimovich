package by.epam.totalizator.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Phone;
import by.epam.totalizator.bean.Secret;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.build.CountryBuilder;
import by.epam.totalizator.bean.build.PhoneBuilder;
import by.epam.totalizator.bean.build.SecretBuilder;
import by.epam.totalizator.bean.build.UserBuilder;
import by.epam.totalizator.dao.exception.DAOException;
import by.epam.totalizator.dao.factory.DAOFactory;
import by.epam.totalizator.dao.pool.IConnectionPool;
import by.epam.totalizator.dao.pool.impl.ConnectionPoolImpl;

public class UserDAOTest {

	private IUserDAO userDao;
	private IConnectionPool pool;

	private static final int ID_USER = 1;
	private static final BigDecimal AMOUNT = new BigDecimal(-1);

	private User user;
	private static final String firstName = "Test";
	private static final String lastName = "Test";
	private static final Date dateOfBirth = new Date("1981/08/12");
	private static final String email = "test_email@example.com";
	private static final String password = "6$%gRwws@123";
	private static final int secretQuestionId = 1;
	private static final String secretAnswer = "Test";
	private static final int idCountry = 1;
	private static final String city = "Test";
	private static final String phoneCode = "1";
	private static final String phoneNumber = "4568738123";
	private static final String currency = "CNY";
	private static final Locale locale = new Locale("ru");

	@Before
	public void setUp() throws Exception {
		userDao = DAOFactory.getInstance().getUserDAO();
		pool = ConnectionPoolImpl.getInstance();
		pool.init();

		user = buildUser();
	}

	@After
	public void tearDown() throws Exception {
		userDao = null;

		pool.dispose();
		pool = null;

		user = null;
	}

	@Test
	public void testIncreaseUserBalance() throws DAOException {
		userDao.changeUserBalance(ID_USER, AMOUNT);
	}

	@Test
	public void testRegistration() throws DAOException {
		userDao.registration(user);
	}

	private User buildUser() {
		Country country = new CountryBuilder().buildId(idCountry).build();
		Phone phone = new PhoneBuilder().buildCode(phoneCode).buildPhoneNumber(phoneNumber).build();
		Secret secret = new SecretBuilder().buildSecretQuestionId(secretQuestionId).buidSecretAnswer(secretAnswer)
				.build();

		User user = new UserBuilder().buildFirstName(firstName).buildLastName(lastName).buildDateOfBirth(dateOfBirth)
				.buildEmail(email).buildPassword(password).buildSecret(secret).buildCountry(country).buildCity(city)
				.buildPhone(phone).buildCurrency(currency).buildLocale(locale).build();
		return user;
	}
}