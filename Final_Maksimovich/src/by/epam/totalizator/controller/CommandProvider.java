package by.epam.totalizator.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.totalizator.controller.command.Command;
import by.epam.totalizator.controller.command.CommandName;
import by.epam.totalizator.controller.command.impl.TournamentPage;
import by.epam.totalizator.controller.command.impl.UserBettingPage;
import by.epam.totalizator.controller.command.impl.UserManagement;
import by.epam.totalizator.controller.command.impl.AddCompetition;
import by.epam.totalizator.controller.command.impl.CompetitionManagement;
import by.epam.totalizator.controller.command.impl.TakeCompetitionInfo;
import by.epam.totalizator.controller.command.impl.HomePage;
import by.epam.totalizator.controller.command.impl.MakeBetting;
import by.epam.totalizator.controller.command.impl.Result;
import by.epam.totalizator.controller.command.impl.SetRate;
import by.epam.totalizator.controller.command.impl.SignIn;
import by.epam.totalizator.controller.command.impl.SignInPage;
import by.epam.totalizator.controller.command.impl.SignOut;
import by.epam.totalizator.controller.command.impl.SignUp;
import by.epam.totalizator.controller.command.impl.SignUpPage;
import by.epam.totalizator.controller.command.impl.TakeDataForCompetition;
import by.epam.totalizator.controller.command.impl.UserProfilePage;
import by.epam.totalizator.controller.command.impl.WrongRequest;

public class CommandProvider {

	private static final CommandProvider instance = new CommandProvider();

	private Map<CommandName, Command> commands = new HashMap<>();
	private static final char OLD_CHAR = '-';
	private static final char NEW_CHAR = '_';

	public static CommandProvider getInstance() {
		return instance;
	}

	private CommandProvider() {
		commands.put(CommandName.SIGNUP_PAGE, new SignUpPage());
		commands.put(CommandName.SIGNUP, new SignUp());
		commands.put(CommandName.SIGNIN_PAGE, new SignInPage());
		commands.put(CommandName.SIGNIN, new SignIn());
		commands.put(CommandName.SIGNOUT, new SignOut());
		commands.put(CommandName.HOME, new HomePage());
		commands.put(CommandName.RESULT, new Result());
		commands.put(CommandName.USER_PROFILE, new UserProfilePage());
		commands.put(CommandName.USER_BETTING, new UserBettingPage());
		commands.put(CommandName.TOURNAMENT_PAGE, new TournamentPage());
		commands.put(CommandName.COMPETITION_INFO, new TakeCompetitionInfo());
		commands.put(CommandName.DATA_FOR_COMPETITION, new TakeDataForCompetition());
		commands.put(CommandName.COMPETITION_MANAGEMENT, new CompetitionManagement());
		commands.put(CommandName.USER_MANAGEMENT, new UserManagement());
		commands.put(CommandName.SET_RATES, new SetRate());
		commands.put(CommandName.MAKE_BETTING, new MakeBetting());
		commands.put(CommandName.ADD_COMPETITION, new AddCompetition());
		commands.put(CommandName.WRONGREQUEST, new WrongRequest());
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
