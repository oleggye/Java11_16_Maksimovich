package by.epam.totalizator.controller.command.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.bean.Country;
import by.epam.totalizator.bean.Phone;
import by.epam.totalizator.bean.User;
import by.epam.totalizator.bean.build.CountryBuilder;
import by.epam.totalizator.bean.build.UserBuilder;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageKeyStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.controller.util.SupportClass;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

/**
 * Instance of {@link ICommand}
 */
public class SignUp implements ICommand {

	private static final Logger LOGGER = LogManager.getLogger(SignUp.class.getName());
	/**
	 * url to home.jsp
	 */
	private static final String HOME_PAGE_URL = "controller?command=home";
	/**
	 * key for message in localization bundle
	 */
	private static final String PASSWORD_CONFIRMATION_ERROR_KEY = "local.validation.error.confirm_password";

	/**
	 * url to singUp.page
	 */
	private static final String SING_UP_PAGE_URL = "controller?command=signup-page";

	/**
	 * Forms {@link User} bean for registration and performs a service level
	 * call.
	 * 
	 * If success send redirect to {@value #HOME_PAGE_URL}.
	 * 
	 * If catch {@link ServiceException} than redirects to internalError.jsp.
	 * 
	 * If catch {@link ServiceValidationException} or than sets error message
	 * text as the request attribute and forwards request and response to
	 * signUp.jsp
	 * 
	 * @param request
	 *            contains a user request object from
	 *            {@link by.epam.totalizator.controller.Controller#processRequest}
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String page = null;

		String login = request.getParameter(ParamNameStore.PARAM_NAME_EMAIL);

		if (login != null) {

			ServiceFactory factory = ServiceFactory.getInstance();

			try {
				User user = parseRequestParam(request);

				boolean isLoginReserved = factory.getClientService().checkLogin(login);

				if (isLoginReserved) {
					request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_LOGIN_MESSAGE, "login is reserved");

				} else {

					factory.getClientService().registration(user);

					page = HOME_PAGE_URL;
					response.sendRedirect(page);
				}
			} catch (ServiceException e) {
				LOGGER.log(Level.ERROR, e);
				page = ConfigurationManager.getProperty(PageKeyStore.INTERNAL_ERROR_PAGE_KEY);
				response.sendRedirect(page);

			} catch (ServiceValidationException e) {
				LOGGER.log(Level.WARN, e);
				page = SING_UP_PAGE_URL;
				request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE_SET, e.getErrorSet());
				request.getRequestDispatcher(page).forward(request, response);

			} catch (IllegalArgumentException e) {
				LOGGER.log(Level.WARN, e);
				page = SING_UP_PAGE_URL;
				request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(page).forward(request, response);
			}
		}

	}

	/**
	 * Method gets {@link User} bean from request
	 */
	private User parseRequestParam(HttpServletRequest request) {
		Locale locale = getLocale(request);

		String password = request.getParameter(ParamNameStore.PARAM_NAME_PASSWORD);
		String repetedPassword = request.getParameter(ParamNameStore.PARAM_NAME_REPEATED_PASSWORD);

		if (!password.equals(repetedPassword)) {
			throw new IllegalArgumentException(
					LocalizationBundle.getInstance().getProperty(locale, PASSWORD_CONFIRMATION_ERROR_KEY));
		}

		UserBuilder userBuilder = new UserBuilder()
				.buildFirstName(request.getParameter(ParamNameStore.PARAM_NAME_FIRST_NAME))
				.buildLastName(request.getParameter(ParamNameStore.PARAM_NAME_LAST_NAME))
				.buildEmail(request.getParameter(ParamNameStore.PARAM_NAME_EMAIL));

		Country country = new CountryBuilder().buildId(getId(request, ParamNameStore.PARAM_NAME_ID_COUNTRY)).build();

		Phone phone = new Phone();
		phone.setCode(request.getParameter(ParamNameStore.PARAM_NAME_PHONE_CODE));
		phone.setPhoneNumber(request.getParameter(ParamNameStore.PARAM_NAME_PHONE_NUMBER));

		User user = userBuilder.buildCountry(country).buildPhone(phone)
				.buildCurrency(request.getParameter(ParamNameStore.PARAM_NAME_CURRENCY))
				.buildCity(request.getParameter(ParamNameStore.PARAM_NAME_CITY)).buildDateOfBirth(getDate(request))
				.buildPassword(password)
				.buildSecredQuestionId(getId(request, ParamNameStore.PARAM_NAME_SECRET_QUESTION))
				.buildSecredQuestionAnswer(request.getParameter(ParamNameStore.PARAM_NAME_SECRET_ANSWER))
				.buildLocale(locale).build();

		return user;
	}

	/**
	 * Method gets {@link java.util.Locale} from the user's session
	 */
	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}

	/**
	 * Method gets date string from the user's request and delegate safety
	 * parsing of it to
	 * {@link by.epam.totalizator.controller.util.SupportClass#parseDate}
	 */
	private Date getDate(HttpServletRequest request) {
		String dateParam = request.getParameter(ParamNameStore.PARAM_NAME_DATE);
		return SupportClass.parseDate(dateParam);
	}

	/**
	 * Method gets id string from the user's request by key and delegate safety
	 * parsing of it to
	 * {@link by.epam.totalizator.controller.util.SupportClass#parseId}
	 */
	private int getId(HttpServletRequest request, String key) {
		String idParam = request.getParameter(key);
		return SupportClass.parseId(idParam);
	}
}
