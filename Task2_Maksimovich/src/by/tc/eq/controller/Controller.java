package by.tc.eq.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.command.impl.AddEquipment;

public final class Controller {

	private static final Logger logger = LogManager.getLogger(AddEquipment.class.getName());

	private final CommandProvider provider = new CommandProvider();

	public String executeTask(String request) {

		String commandName = null;
		Command executionCommand;

		String response = null;

		try {

			commandName = request.substring(0, request.indexOf(Command.PARAM_DELIMETER));// такое лучше оформлять отдельным утилитным методо или даже классом с вменяемым названием
			// я про правую часть оператора
			executionCommand = provider.getCommand(commandName);

		} catch (IndexOutOfBoundsException e) {

			logger.error(response);
			logger.log(Level.ERROR, e);

		} finally {
			executionCommand = provider.getCommand(commandName);// а зачем ты повторяешь строчку 27?
			response = executionCommand.execute(request);// execute в блоке finally - это конечно креативно, но неправильно
		}
		return response;// пользователю по твоей логике можно вернуть null
	}

}
