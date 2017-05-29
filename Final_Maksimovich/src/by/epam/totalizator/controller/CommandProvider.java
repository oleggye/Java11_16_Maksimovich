package by.epam.totalizator.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.controller.command.ICommand;
import by.epam.totalizator.controller.command.CommandName;
import by.epam.totalizator.controller.command.impl.TournamentPage;
import by.epam.totalizator.controller.command.impl.UserBettingPage;
import by.epam.totalizator.controller.command.impl.UserManagementPage;
import by.epam.totalizator.controller.command.impl.ChangeLocale;
import by.epam.totalizator.controller.command.impl.CompetitionManagementPage;
import by.epam.totalizator.controller.command.impl.EarningManagement;
import by.epam.totalizator.controller.command.impl.HomePage;
import by.epam.totalizator.controller.command.impl.ResultPage;
import by.epam.totalizator.controller.command.impl.SignIn;
import by.epam.totalizator.controller.command.impl.SignInPage;
import by.epam.totalizator.controller.command.impl.SignOut;
import by.epam.totalizator.controller.command.impl.SignUp;
import by.epam.totalizator.controller.command.impl.SignUpPage;
import by.epam.totalizator.controller.command.impl.UserProfilePage;
import by.epam.totalizator.controller.command.impl.WrongRequest;
import by.epam.totalizator.controller.command.impl.ajax.AddCompetition;
import by.epam.totalizator.controller.command.impl.ajax.BanUser;
import by.epam.totalizator.controller.command.impl.ajax.ChangeUserBalance;
import by.epam.totalizator.controller.command.impl.ajax.ChangeUserType;
import by.epam.totalizator.controller.command.impl.ajax.DeleteCompetition;
import by.epam.totalizator.controller.command.impl.ajax.MakeBetting;
import by.epam.totalizator.controller.command.impl.ajax.SetRate;
import by.epam.totalizator.controller.command.impl.ajax.TakeCompetitionInfo;
import by.epam.totalizator.controller.command.impl.ajax.TakeDataForCompetition;
import by.epam.totalizator.controller.command.impl.ajax.TakeUserBalance;
import by.epam.totalizator.controller.command.impl.ajax.UnbanUser;
import by.epam.totalizator.controller.command.impl.ajax.UpdateCompetition;

/**
 * Class for handling commands and command types
 */
public class CommandProvider {

	private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class.getName());

	private static final CommandProvider INSTANCE = new CommandProvider();

	private Map<CommandName, ICommand> commandRepository = new HashMap<>();
	private static final char OLD_CHAR = '-';
	private static final char NEW_CHAR = '_';

	public static CommandProvider getInstance() {
		return INSTANCE;
	}

	private CommandProvider() {
		commandRepository.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
		commandRepository.put(CommandName.SIGNUP_PAGE, new SignUpPage());
		commandRepository.put(CommandName.SIGNUP, new SignUp());
		commandRepository.put(CommandName.SIGNIN_PAGE, new SignInPage());
		commandRepository.put(CommandName.SIGNIN, new SignIn());
		commandRepository.put(CommandName.SIGN_OUT, new SignOut());
		commandRepository.put(CommandName.HOME, new HomePage());
		commandRepository.put(CommandName.RESULT, new ResultPage());
		commandRepository.put(CommandName.USER_PROFILE, new UserProfilePage());
		commandRepository.put(CommandName.USER_BETTING, new UserBettingPage());
		commandRepository.put(CommandName.USER_BALANCE, new TakeUserBalance());
		commandRepository.put(CommandName.TOURNAMENT_PAGE, new TournamentPage());
		commandRepository.put(CommandName.COMPETITION_INFO, new TakeCompetitionInfo());
		commandRepository.put(CommandName.DATA_FOR_COMPETITION, new TakeDataForCompetition());
		commandRepository.put(CommandName.EARNING_MANAGEMENT, new EarningManagement());
		commandRepository.put(CommandName.COMPETITION_MANAGEMENT, new CompetitionManagementPage());
		commandRepository.put(CommandName.USER_MANAGEMENT, new UserManagementPage());
		commandRepository.put(CommandName.CHANGE_USER_TYPE, new ChangeUserType());
		commandRepository.put(CommandName.CHANGE_USER_BALANCE, new ChangeUserBalance());
		commandRepository.put(CommandName.SET_RATES, new SetRate());
		commandRepository.put(CommandName.MAKE_BETTING, new MakeBetting());
		commandRepository.put(CommandName.ADD_COMPETITION, new AddCompetition());
		commandRepository.put(CommandName.UPDATE_COMPETITION, new UpdateCompetition());
		commandRepository.put(CommandName.DELETE_COMPETITION, new DeleteCompetition());
		commandRepository.put(CommandName.BAN_USER, new BanUser());
		commandRepository.put(CommandName.UNBAN_USER, new UnbanUser());
		commandRepository.put(CommandName.WRONGREQUEST, new WrongRequest());
	}

	/**
	 * Method which return instance of {@link ICommand} from request param
	 *
	 * @param request
	 *            a string that contains command name
	 * @return instance of {@link ICommand} by it's name from
	 *         {@link CommandName}
	 */
	public ICommand getCommand(String request) {

		CommandName commandName = null;
		ICommand command = null;

		if (request == null) {
			command = commandRepository.get(CommandName.WRONGREQUEST);
		} else {

			try {
				commandName = CommandName.valueOf(request.toUpperCase().replace(OLD_CHAR, NEW_CHAR));
				command = commandRepository.get(commandName);

			} catch (IllegalArgumentException | NullPointerException e) {
				LOGGER.log(Level.WARN, e);
				command = commandRepository.get(CommandName.WRONGREQUEST);
			}
		}
		return command;
	}
}