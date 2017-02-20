package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.resource.ConfigurationManager;

public class SignUp implements Command {

	private final String SIGN_UP_PAGE_URL = "path.page.signUp";

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(SIGN_UP_PAGE_URL);
		return page;
	}

}
