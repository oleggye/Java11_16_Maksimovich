package by.epam.totalizator.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.controller.command.CommandName;
import by.epam.totalizator.controller.command.impl.TakeCompetitionList;
import by.epam.totalizator.controller.command.impl.Home;
import by.epam.totalizator.controller.command.impl.Result;
import by.epam.totalizator.controller.command.impl.SignIn;
import by.epam.totalizator.controller.command.impl.SignOut;
import by.epam.totalizator.controller.command.impl.SignUp;
import by.epam.totalizator.controller.command.impl.TakeUserInfo;
import by.epam.totalizator.controller.command.impl.WrongRequest;

public class CommandProvider {
	Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.SIGNUP, new SignUp());
		commands.put(CommandName.SIGNIN, new SignIn());
		commands.put(CommandName.SIGNOUT, new SignOut());
		commands.put(CommandName.WRONGREQUEST, new WrongRequest());
		commands.put(CommandName.HOME, new Home());
		commands.put(CommandName.RESULT, new Result());
		commands.put(CommandName.USERINFO, new TakeUserInfo());
		commands.put(CommandName.TAKECOMPETITION, new TakeCompetitionList());
	}

	public Command getCommand(String request) {

		CommandName commandName = null;
		Command command = null;

		if (request == null) {
			command = commands.get(CommandName.WRONGREQUEST);
		} else {

			try {
				commandName = CommandName.valueOf(request.toUpperCase());
				command = commands.get(commandName);

			} catch (IllegalArgumentException | NullPointerException e) {
				// TODO:logging
				command = commands.get(CommandName.WRONGREQUEST);
			}
		}
		return command;
	}
}
