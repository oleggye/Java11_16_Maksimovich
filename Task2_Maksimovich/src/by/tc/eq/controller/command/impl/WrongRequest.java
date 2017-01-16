package by.tc.eq.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.controller.command.Command;

public class WrongRequest implements Command {

	private static final Logger logger = LogManager.getLogger(WrongRequest.class.getName());

	@Override
	public String execute(String request) {

		String response = "Wrong request: " + request;
		logger.info(response);

		return response;
	}

}
