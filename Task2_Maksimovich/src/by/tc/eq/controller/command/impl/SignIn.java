package by.tc.eq.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.controller.command.Command;
import by.tc.eq.service.ClientService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class SignIn implements Command {

	private static final Logger logger = LogManager.getLogger(SignIn.class.getName());

	/**
	 * предполагается, что в объекте request будет строка значений такого вида
	 * "параметр с командой;логин;пароль"
	 */
	private static final byte EXPECTED_QUANTITY_OF_PARAMETERS = 3;

	@Override
	public String execute(String request) {

		String login = null;
		String password = null;

		String response = null;

		String[] params = request.split(Command.PARAM_DELIMETER);

		if (params.length == EXPECTED_QUANTITY_OF_PARAMETERS) {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ClientService clientService = serviceFactory.getClientService();

			login = params[1];
			password = params[2];

			try {

				clientService.singIn(login, password);
				response = "Welcome!";

			} catch (ServiceException e) {
				response = "Error during login procedure!";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

		} else {
			response = "Incorrect login parameters";
			logger.error(response);
		}

		return response;
	}

}
