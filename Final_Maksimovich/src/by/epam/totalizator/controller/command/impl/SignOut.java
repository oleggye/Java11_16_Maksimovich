package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.ParamNameStore;

public class SignOut implements ICommand {

	private static final String HOME_PAGE_COMMAND = "controller?command=home";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.getSession().removeAttribute(ParamNameStore.PARAM_NAME_LOGIN);
		request.getSession().removeAttribute(ParamNameStore.PARAM_NAME_ID_USER);
		request.getSession().removeAttribute(ParamNameStore.PARAM_NAME_USERTYPE);

		String page = HOME_PAGE_COMMAND;
		response.sendRedirect(page);
	}
}
