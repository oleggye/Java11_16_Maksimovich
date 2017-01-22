package by.tc.eq.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.bean.Status;
import by.tc.eq.bean.User;
import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.util.RequestParser;
import by.tc.eq.service.ClientService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class UpdateUserData implements Command {

	private static final Logger logger = LogManager.getLogger(UpdateUserData.class.getName());

	/**
	 * предполагается, что в объекте request будет строка значений такого вида
	 * "параметр с командой;id;name;surname;login;password,discount" status
	 */

	private static final byte EXPECTED_QUANTITY_OF_PARAMETERS = 8;

	@Override
	public String execute(String request) {

		String response = null;

		String[] params = RequestParser.getAllParamsFromRequest(request);

		if (params.length == EXPECTED_QUANTITY_OF_PARAMETERS) {

			User user = new User();

			// получаем id для user из строки, что чревато ошибкой
			try {
				user.setId(Integer.parseInt(params[1]));
			} catch (NumberFormatException e) {
				response = "Incorrect user's id parameter";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

			user.setName(params[2]);
			user.setSurname(params[3]);
			user.setLogin(params[4]);
			user.setPassword(params[5]);

			// получаем descount из строки, что чревато ошибкой
			try {
				user.setDiscount(Float.parseFloat(params[6]));
			} catch (NumberFormatException e) {
				response = "Incorrect user's discount parameter";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

			try {
				user.setStatus(Status.valueOf(params[7]));
			} catch (IllegalArgumentException e) {
				response = "Incorrect user's status parameter";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ClientService clientService = serviceFactory.getClientService();

			try {

				clientService.updateUserData(user);
				response = "The user's data was updated";
			} catch (ServiceException e) {
				response = "Error during updating!";
				logger.error(response);
				logger.log(Level.ERROR, e);
			}
		} else {
			response = "Incorrect user's parameters";
			logger.error(response);
		}

		return response;

	}

}
