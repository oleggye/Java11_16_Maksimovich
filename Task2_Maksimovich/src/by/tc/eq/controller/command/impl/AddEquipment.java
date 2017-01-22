package by.tc.eq.controller.command.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import by.tc.eq.bean.Equipment;
import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.util.RequestParser;
import by.tc.eq.service.ShopService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class AddEquipment implements Command {

	private static final Logger logger = LogManager.getLogger(AddEquipment.class.getName());
	/**
	 * предполагается, что в объекте request будет строка значений такого вида
	 * "параметр с командой; category_id; title; price; quantity; description"
	 */
	private static final byte EXPECTED_QUANTITY_OF_PARAMETERS = 6;

	@Override
	public String execute(String request) {

		String response = null;

		String[] params = RequestParser.getAllParamsFromRequest(request);

		if (params.length == EXPECTED_QUANTITY_OF_PARAMETERS) {

			Equipment equipment = new Equipment();

			try {

				equipment.setCategory_id(Integer.valueOf(params[1]));

			} catch (NumberFormatException e) {
				response = "Wrong category id";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

			equipment.setTitle(params[2]);

			try {

				equipment.setPrice(Float.parseFloat(params[3]));

			} catch (NumberFormatException e) {
				response = "Wrong price format";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

			try {

				equipment.setQuantity(Integer.valueOf(params[4]));

			} catch (NumberFormatException e) {
				response = "Wrong quantity format";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

			equipment.setDescription(params[5]);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ShopService shopService = serviceFactory.getShopService();

			try {
				shopService.addEquipment(equipment);
				response = "The equipment was created";

			} catch (ServiceException e) {
				response = "Error during equipment addition!";
				logger.error(response);
				logger.log(Level.ERROR, e);
			}

		} else {
			response = "Incorrect equipment parameters!";
			logger.error(response);
		}

		return response;
	}

}
