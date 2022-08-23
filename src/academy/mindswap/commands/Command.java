package academy.mindswap.commands;

import java.util.Arrays;

public enum Command {
	HIT("/hit", new HitHandler()),
	STAND("/stand", new StandHandler()),
	QUIT("/quit", new QuitHandler());

	private String description;
	private CommandHandler commandHandler;

	Command(String description, CommandHandler commandHandler) {
		this.description = description;
		this.commandHandler = commandHandler;
	}

	public static Command getCommandFromDescription(String description) {
		return Arrays.stream(values())
				.filter(command -> command.description.equalsIgnoreCase(description))
				.findFirst()
				.orElse(QUIT);
	}
}
