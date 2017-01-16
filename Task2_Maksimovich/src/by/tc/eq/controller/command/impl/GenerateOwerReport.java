package by.tc.eq.controller.command.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.bean.Ower;
import by.tc.eq.controller.command.Command;
import by.tc.eq.service.ShopService;
import by.tc.eq.service.exception.ServiceException;
import by.tc.eq.service.factory.ServiceFactory;

public class GenerateOwerReport implements Command {

	private static final Logger logger = LogManager.getLogger(GenerateOwerReport.class.getName());

	@Override
	public String execute(String request) {

		String response = "";
		List<Ower> oweList = new ArrayList<>();

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ShopService shopService = serviceFactory.getShopService();

		try {
			oweList = shopService.getOwerReport();

			for (Ower elem : oweList) {
				response = response + elem + "\n";
			}

		} catch (ServiceException e) {
			response = "Error in the process of ower report generating !";
			logger.error(response);
			logger.log(Level.ERROR, e);
			return response;
		}

		return response;
	}

}
