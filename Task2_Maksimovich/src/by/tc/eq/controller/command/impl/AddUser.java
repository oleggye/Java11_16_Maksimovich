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

public class AddUser implements Command {

	private static final Logger logger = LogManager.getLogger(AddUser.class.getName());

	/**
	 * предполагается, что в объекте request будет строка значений такого вида
	 * "параметр с командой;name;surname;login;password"
	 */
	private static final byte EXPECTED_QUANTITY_OF_PARAMETERS = 5;

	@Override
	public String execute(String request) {

		String response = null;

		String[] params = RequestParser.getAllParamsFromRequest(request);

		if (params.length == EXPECTED_QUANTITY_OF_PARAMETERS) {

			User user = new User();

			// в данном случае, при регистрации, id у user еще нет
			user.setName(params[1]);
			user.setSurname(params[2]);
			user.setLogin(params[3]);
			user.setPassword(params[4]);
			// значения по умолчанию:
			// нет скидки, и статус - не должник
			user.setDiscount(0.00f);
			user.setStatus(Status.Available);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ClientService clientService = serviceFactory.getClientService();

			try {
				clientService.registeration(user);
				response = "The user was created";

			} catch (ServiceException e) {
				response = "Error during registration!";
				logger.error(response);
				logger.log(Level.ERROR, e);
			}
		} else {
			response = "Incorrect registration parameters";
			logger.error(response);
		}

		return response;

	}

}
