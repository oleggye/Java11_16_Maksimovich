package by.tc.eq.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.command.impl.AddEquipment;
import by.tc.eq.controller.util.RequestParser;

public final class Controller {

	private static final Logger logger = LogManager.getLogger(AddEquipment.class.getName());

	private final CommandProvider provider = new CommandProvider();

	public String executeTask(String request) {

		String commandName = null;
		Command executionCommand;

		String response = null;

		try {

			commandName = RequestParser.getCommandNameFromRequest(request);
			executionCommand = provider.getCommand(commandName);
			response = executionCommand.execute(request);

		} catch (IndexOutOfBoundsException e) {
			logger.error(response);
			logger.log(Level.ERROR, e);

			response = "Wrong request";
		}
		return response;
	}
}
