package by.epam.totalizator.controller.command.impl;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.resource.ConfigurationManager;

public class SignUp implements Command {

	private final String SIGN_UP_PAGE_URL = "path.page.signUp";

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty(SIGN_UP_PAGE_URL);

		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "= " + Arrays.deepToString(entry.getValue()));
		}

		return page;
	}

}
