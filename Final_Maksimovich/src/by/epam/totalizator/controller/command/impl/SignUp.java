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
import by.epam.totalizator.bean.build.UserBuilder;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.command.impl.ajax.AddCompetition;
import by.epam.totalizator.controller.util.AttributeNameStore;
import by.epam.totalizator.controller.util.PageNameStore;
import by.epam.totalizator.controller.util.ParamNameStore;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.resource.LocalizationBundle;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;

public class SignUp implements ICommand {

	private static final Logger logger = LogManager.getLogger(AddCompetition.class.getName());

	private static final String COMMAND_PATTERN = "controller?command=%s";
	private static final String ATTRIBUTE_NAME_DEFAULT_COMMAND_VALUE = "home";
	private static final String PASSWORD_CONFIRMATION_ERROR_KEY = "local.validation.error.confirm_password";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String page = null;

		String login = request.getParameter(ParamNameStore.PARAM_NAME_EMAIL);

		if (login != null) {

			ServiceFactory factory = ServiceFactory.getInstance();

			try {
				User user = parseRequestParam(request);
				System.out.println(user);

				boolean isLoginReserved = factory.getClientService().checkLogin(login);

				if (isLoginReserved) {
					request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_LOGIN_MESSAGE, "login is reserved");

				} else {

					factory.getClientService().registration(user);

					Object previousCommand = request.getSession()
							.getAttribute(AttributeNameStore.ATTRIBUTE_NAME_COMMAND);
					if (previousCommand != null) {
						page = String.format(COMMAND_PATTERN, previousCommand);
					} else {
						page = String.format(COMMAND_PATTERN, ATTRIBUTE_NAME_DEFAULT_COMMAND_VALUE);
					}
				}
			} catch (ServiceException e) {
				logger.log(Level.ERROR, e);
				request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
				page = ConfigurationManager.getProperty(PageNameStore.INTERNAL_ERROR_PAGE_URL);

			} catch (ServiceValidationException e) {
				logger.log(Level.WARN, e);
				request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
				request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE_SET, e.getErrorSet());
				page = ConfigurationManager.getProperty(PageNameStore.SIGN_UP_PAGE_URL);

			} catch (IllegalArgumentException e) {
				logger.log(Level.WARN, e);
				request.setAttribute(AttributeNameStore.ATTRIBUTE_ERROR_MESSAGE, e.getMessage());
				page = ConfigurationManager.getProperty(PageNameStore.SIGN_UP_PAGE_URL);
			}
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

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

		Country country = new Country();
		country.setName(request.getParameter(ParamNameStore.PARAM_NAME_COUNTRY));

		Phone phone = new Phone();
		phone.setCode(request.getParameter(ParamNameStore.PARAM_NAME_PHONE_CODE));
		phone.setPhoneNumber(request.getParameter(ParamNameStore.PARAM_NAME_PHONE_NUMBER));

		User user = userBuilder.buildCountry(country).buildPhone(phone)
				.buildCurrency(request.getParameter(ParamNameStore.PARAM_NAME_CURRENCY))
				.buildDateOfBirth(new Date(request.getParameter(ParamNameStore.PARAM_NAME_DATE)))
				.buildPassword(password)
				.buildSecredQuestionId(Integer.valueOf(request.getParameter(ParamNameStore.PARAM_NAME_SECRET_QUESTION)))
				.buildSecredQuestionAnswer(request.getParameter(ParamNameStore.PARAM_NAME_SECRET_ANSWER))
				.buildLocale(locale).build();

		return user;
	}

	private Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_NAME_LOCAL);
	}
}
