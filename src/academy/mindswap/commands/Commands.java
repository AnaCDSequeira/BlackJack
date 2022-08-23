package academy.mindswap.commands;

import java.util.Arrays;

public enum Commands {
	HIT("/hit", new HitHandler()),
	STAND("/stand", new StandHandler()),
	QUIT("/quit", new QuitHandler());

	private String description;
	private CommandHandler commandHandler;

	Commands(String description, CommandHandler commandHandler) {
		this.description = description;
		this.commandHandler = commandHandler;
	}

	public static Commands getCommandFromDescription(String description) {
		return Arrays.stream(values())
				.filter(Commands -> Commands.description.equalsIgnoreCase(description))
				.findFirst()
				.orElse(QUIT); // TODO: create NoSuchCommand
	}
}
