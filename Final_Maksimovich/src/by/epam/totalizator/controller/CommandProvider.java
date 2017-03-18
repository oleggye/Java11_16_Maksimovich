package by.epam.totalizator.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.controller.command.CommandName;
import by.epam.totalizator.controller.command.impl.TournamentPage;
import by.epam.totalizator.controller.command.impl.UserBettingPage;
import by.epam.totalizator.controller.command.impl.HomePage;
import by.epam.totalizator.controller.command.impl.Result;
import by.epam.totalizator.controller.command.impl.SignIn;
import by.epam.totalizator.controller.command.impl.SignInPage;
import by.epam.totalizator.controller.command.impl.SignOut;
import by.epam.totalizator.controller.command.impl.SignUp;
import by.epam.totalizator.controller.command.impl.SignUpPage;
import by.epam.totalizator.controller.command.impl.UserProfilePage;
import by.epam.totalizator.controller.command.impl.WrongRequest;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();
	private static final char OLD_CHAR = '-';
	private static final char NEW_CHAR = '_';

	public CommandProvider() {
		commands.put(CommandName.SIGNUP_PAGE, new SignUpPage());
		commands.put(CommandName.SIGNUP, new SignUp());
		commands.put(CommandName.SIGNIN_PAGE, new SignInPage());
		commands.put(CommandName.SIGNIN, new SignIn());
		commands.put(CommandName.SIGNOUT, new SignOut());
		commands.put(CommandName.WRONGREQUEST, new WrongRequest());
		commands.put(CommandName.HOME, new HomePage());
		commands.put(CommandName.RESULT, new Result());
		commands.put(CommandName.USER_PROFILE, new UserProfilePage());
		commands.put(CommandName.USER_BETTING, new UserBettingPage());
		commands.put(CommandName.TOURNAMENT_PAGE, new TournamentPage());
	}

	public Command getCommand(String request) {

		CommandName commandName = null;
		Command command = null;

		if (request == null) {
			command = commands.get(CommandName.WRONGREQUEST);
		} else {

			try {
				commandName = CommandName.valueOf(request.toUpperCase().replace(OLD_CHAR, NEW_CHAR));
				command = commands.get(commandName);

			} catch (IllegalArgumentException | NullPointerException e) {
				// TODO:logging
				command = commands.get(CommandName.WRONGREQUEST);
			}
		}
		return command;
	}
}
