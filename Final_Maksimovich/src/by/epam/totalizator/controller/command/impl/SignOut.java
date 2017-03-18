package by.epam.totalizator.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.resource.ConfigurationManager;

public class SignOut implements Command {

	private static final String HOME_PAGE_URL = "path.page.home";
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_USERID = "userId";
	private static final String PARAM_NAME_USERTYPE = "userType";

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		request.getSession().removeAttribute(PARAM_NAME_LOGIN);
		request.getSession().removeAttribute(PARAM_NAME_USERID);
		request.getSession().removeAttribute(PARAM_NAME_USERTYPE);

		// page = ConfigurationManager.getProperty(HOME_PAGE_URL);
		page = "controller?command=home";

		return page;
	}

}
