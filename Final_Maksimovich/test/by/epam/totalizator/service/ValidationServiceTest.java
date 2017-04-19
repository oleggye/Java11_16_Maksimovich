package by.epam.totalizator.service;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.build.UserBuilder;
import by.epam.totalizator.service.IValidationService;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.impl.ValidationServiceImpl;

public class ValidationServiceTest {

	private IValidationService validationService;
	private User user;
	private static final Locale LOCALE = new Locale("ru");

	@Before
	public void setUp() throws Exception {

		validationService = new ValidationServiceImpl();
		user = new UserBuilder().buildLocale(LOCALE).build();
	}

	@After
	public void tearDown() throws Exception {
		validationService = null;
		user= null;
	}

	@Test(expected = ServiceValidationException.class)
	public void testValidateUser() throws ServiceValidationException {
		try {
			validationService.validateUser(user);
		} catch (ServiceValidationException e) {
			System.err.println(e.getErrorSet());
			throw e;
		}
	}
}
