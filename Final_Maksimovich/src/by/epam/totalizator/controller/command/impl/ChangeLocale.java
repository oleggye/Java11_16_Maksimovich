package by.epam.totalizator.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.util.AttributeNameStore;

public class ChangeLocale implements ICommand {

	private static final String ATTRIBUTE_NAME_DEFAULT_URL = "controller?command=home";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String page = null;

		Object previousCommand = request.getSession().getAttribute(AttributeNameStore.ATTRIBUTE_PREVIOUS_PAGE_ULR);

		if (previousCommand != null) {
			page = (String) previousCommand;
		} else {
			page = ATTRIBUTE_NAME_DEFAULT_URL;
		}
		response.sendRedirect(page);
	}
}
