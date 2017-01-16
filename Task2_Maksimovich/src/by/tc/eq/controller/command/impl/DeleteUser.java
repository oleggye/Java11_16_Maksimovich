package by.tc.eq.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.controller.command.Command;
import by.tc.eq.service.ClientService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class DeleteUser implements Command {

	private static final Logger logger = LogManager.getLogger(DeleteUser.class.getName());

	/**
	 * предполагается, что в объекте request будет строка значений такого вида
	 * "параметр с командой; id
	 */

	private static final byte EXPECTED_QUANTITY_OF_PARAMETERS = 2;

	@Override
	public String execute(String request) {

		String response = null;

		String[] params = request.split(Command.PARAM_DELIMETER);

		if (params.length == EXPECTED_QUANTITY_OF_PARAMETERS) {

			int user_id = 0;

			try {
				
				user_id = Integer.parseInt(params[1]);
				
			} catch (NumberFormatException e) {
				response = "Incorrect user's parameters";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ClientService clientService = serviceFactory.getClientService();

			try {
				clientService.deleteUser(user_id);
				response = "The user was deleted!";

			} catch (ServiceException e) {
				response = "Error during deleting!";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

		} else {
			response = "Incorrect parameters";
			logger.error(response);
		}
		return response;
	}

}
