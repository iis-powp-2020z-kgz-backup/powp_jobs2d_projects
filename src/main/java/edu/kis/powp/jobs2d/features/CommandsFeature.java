package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.CommandHistoryObserver;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.HistoryCommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;

public class CommandsFeature {

	private static DriverCommandManager commandManager;
	private static HistoryCommandManager historyCommandManager;

	public static void setupCommandManager() {
		commandManager = new DriverCommandManager();
		historyCommandManager = new HistoryCommandManager();

		LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
		commandManager.getChangePublisher().addSubscriber(loggerObserver);

		CommandHistoryObserver commandHistoryObserver = new CommandHistoryObserver();
		historyCommandManager.getChangePublisher().addSubscriber(commandHistoryObserver);
	}

	/**
	 * Get manager of application driver command.
	 * 
	 * @return plotterCommandManager.
	 */
	public static DriverCommandManager getDriverCommandManager() {
		return commandManager;
	}

	public static HistoryCommandManager getHistoryCommandManager() {
		return historyCommandManager;
	}
}
