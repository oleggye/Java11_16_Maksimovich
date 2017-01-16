package by.tc.eq.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.controller.command.Command;
import by.tc.eq.service.ClientService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class SignOut implements Command {

	private static final Logger logger = LogManager.getLogger(SignOut.class.getName());

	@Override
	public String execute(String request) {
		String response = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();

		try {

			clientService.signOut();
			response = "Good bye!";

		} catch (ServiceException e) {
			response = "Error during logout procedure!";

			logger.error(response);
			logger.log(Level.ERROR, e);
			return response;
		}

		return response;
	}

}
