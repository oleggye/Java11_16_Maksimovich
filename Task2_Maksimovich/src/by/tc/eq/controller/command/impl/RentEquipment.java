package by.tc.eq.controller.command.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.controller.command.Command;
import by.tc.eq.service.ShopService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class RentEquipment implements Command {

	private static final Logger logger = LogManager.getLogger(RentEquipment.class.getName());

	/**
	 * наличие данных костант объясняется тем, что пользователю разрешено взять
	 * до 3-х единиц из проката т.е. предполагается, что в объекте request будет
	 * строка значений такого вида: "параметр с командой;id_user;id_equipment;
	 * .....
	 */

	private static final byte EXPECTED_MIN_QUANTITY_OF_PARAMETERS = 3;
	private static final byte EXPECTED_MAX_QUANTITY_OF_PARAMETERS = 5;

	// т.к. максимум 3 товара может арендовать один пользователь
	private static final byte MAX_EQUIPMENT_QUANTITY_FOR_RENT = 3;

	@Override
	public String execute(String request) {

		String response = null;

		String[] params = request.split(Command.PARAM_DELIMETER);

		if (params.length >= EXPECTED_MIN_QUANTITY_OF_PARAMETERS
				&& params.length <= EXPECTED_MAX_QUANTITY_OF_PARAMETERS) {

			int id_user = 0;
			// размер массива рассчитывается по формуле
			// [ 3 - (5 - длина_массива_параметров) ]
			// размер_массива - количество арендуемых товаров
			int[] ids_equipment = new int[MAX_EQUIPMENT_QUANTITY_FOR_RENT
					- (EXPECTED_MAX_QUANTITY_OF_PARAMETERS - params.length)];

			try {

				id_user = Integer.parseInt(params[1]);

				// т.к. нулевым элементов в массиве params является команда, а
				// вторым - id_user, то начинаем со 2-ого
				for (int i = 0; i < ids_equipment.length; i++) {

					ids_equipment[i] = Integer.parseInt(params[i + 2]);
				}
			} catch (NumberFormatException e) {
				response = "Wrong equipment id";
				logger.error(response);
				logger.log(Level.ERROR, e);
				return response;
			}

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ShopService shopService = serviceFactory.getShopService();

			try {

				shopService.rentEquipment(id_user, ids_equipment);
				response = "The equipment was rented";

			} catch (ServiceException e) {
				response = "Error during equipment renting!";
				logger.error(response);
				logger.log(Level.ERROR, e);
			}

		} else {
			response = "Incorrect parameters for renting!";
			logger.error(response);
		}

		return response;
	}

}
