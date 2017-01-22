package by.tc.eq.controller.command.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.bean.User;
import by.tc.eq.controller.command.Command;
import by.tc.eq.service.ShopService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class GenerateUsersReport implements Command {

	private static final Logger logger = LogManager.getLogger(GenerateUsersReport.class.getName());

	@Override
	public String execute(String request) {

		String response = "";
		List<User> userList = new ArrayList<>();

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ShopService shopService = serviceFactory.getShopService();

		try {
			userList = shopService.getUsersReport();

			for (User elem : userList) {
				response = response + elem + "\n";
			}

		} catch (ServiceException e) {
			response = "Error in the process of user report generating !";
			logger.error(response);
			logger.log(Level.ERROR, e);
		}

		return response;
	}

}
