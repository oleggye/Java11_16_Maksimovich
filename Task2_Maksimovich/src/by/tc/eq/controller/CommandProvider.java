package by.tc.eq.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.command.CommandName;
import by.tc.eq.controller.command.impl.*;

public class CommandProvider {

	private static final Logger logger = LogManager.getLogger(AddEquipment.class.getName());

	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.SIGN_IN, new SignIn());
		repository.put(CommandName.SIGN_OUT, new SignOut());

		repository.put(CommandName.ADD_USER, new AddUser());
		repository.put(CommandName.UPDATE_USER_DATA, new UpdateUserData());
		repository.put(CommandName.DELETE_USER, new DeleteUser());

		repository.put(CommandName.ADD_EQUIPMENT, new AddEquipment());
		repository.put(CommandName.UPDATE_EQUIPMENT, new UpdateEquipment());
		repository.put(CommandName.DELETE_EQUIPMENT, new DeleteEquipment());

		repository.put(CommandName.RENT_EQUIPMENT, new RentEquipment());
		repository.put(CommandName.RETURN_EQUIPMENT, new ReturnEquipment());

		repository.put(CommandName.GENERATE_AVAILABLE_EQUIPMENT_REPORT, new GenerateAvailableEquipmentReport());
		repository.put(CommandName.GENERATE_RENTED_EQUIPMENT_REPORT, new GenerateRentedEquipmentReport());
		repository.put(CommandName.GENERATE_OWER_REPORT, new GenerateOwerReport());
		repository.put(CommandName.GENERATE_USER_REPORT, new GenerateUsersReport());

		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
	}

	Command getCommand(String name) {

		CommandName commandName = null;
		Command command = null;

		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
			
		} catch (IllegalArgumentException | NullPointerException e) {
			logger.error("Wrong request");
			logger.log(Level.ERROR, e);
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		return command;
	}

}
