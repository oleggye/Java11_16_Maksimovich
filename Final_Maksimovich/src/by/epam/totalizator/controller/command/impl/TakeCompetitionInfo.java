package by.epam.totalizator.controller.command.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.totalizator.bean.Competition;
import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.controller.util.SessionRequestContent;
import by.epam.totalizator.resource.ConfigurationManager;
import by.epam.totalizator.service.exception.ServiceException;
import by.epam.totalizator.service.exception.ServiceValidationException;
import by.epam.totalizator.service.factory.ServiceFactory;
import by.epam.totalizator.util.build.CompetitionBuilder;

import com.google.gson.Gson;

public class TakeCompetitionInfo implements Command {

	private static final String COMPETITION_INFO_PAGE_URL = "path.page.competitionInfo";

	@Override
	public String execute(HttpServletRequest request) {

		String page = ConfigurationManager.getProperty(COMPETITION_INFO_PAGE_URL);

		SessionRequestContent requestContent = new SessionRequestContent();
		requestContent.extractValues(request);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {
			factory.getCompetitionService().obtainCompetition(requestContent);
			requestContent.insertAttributeMap(request);

		} catch (ServiceException e) {
			// TODO: logging
			//TODO: нужно оправлять ошибку по ajax

		} catch (ServiceValidationException e) {
			// TODO logging и дописать!!!
			//TODO: нужно оправлять ошибку по ajax
		}
//		String hello = "hello world!";
//		List<Object> list = new ArrayList<>();
//
//		list.add(hello);
//
//		// List<String> list = new ArrayList<>();
//		// list.add("item1");
//		// list.add("item2");
//		// list.add("item3");
//		String json = new Gson().toJson(list);
//
//		System.out.println(json);
//
//		request.setAttribute(ATTRIBUTE_NAME_VARIABLE, json);

		return page;
	}

}
