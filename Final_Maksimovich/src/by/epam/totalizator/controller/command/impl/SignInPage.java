package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.resource.ConfigurationManager;

public class SignInPage implements Command {

	private static final String SIGNIN_PAGE_URL = "path.page.signIn";

	@Override
	public String execute(HttpServletRequest request) {

		return ConfigurationManager.getProperty(SIGNIN_PAGE_URL);
	}
}
