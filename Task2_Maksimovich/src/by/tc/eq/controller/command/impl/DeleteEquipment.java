package by.tc.eq.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.controller.command.Command;
import by.tc.eq.service.ShopService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class DeleteEquipment implements Command {

	private static final Logger logger = LogManager.getLogger(DeleteEquipment.class.getName());

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

			int equipment_id = 0;

			try {

				equipment_id = Integer.parseInt(params[1]);
				response = "The equipment was deleted";

			} catch (NumberFormatException e) {
				response = "Incorrect equipment parameters";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ShopService shopService = serviceFactory.getShopService();

			try {

				shopService.deleteEquipment(equipment_id);
				response = "The equipment was deleted!";

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
