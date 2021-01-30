package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.CommandHistoryObserver;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

public class CommandHistoryFeature {

    private static DriverCommandManager commandManager;

    public static void setupCommandManager() {
        commandManager = new DriverCommandManager();

        CommandHistoryObserver commandHistoryObserver = new CommandHistoryObserver();
        commandManager.getChangePublisher().addSubscriber(commandHistoryObserver);
    }

    public static DriverCommandManager getDriverCommandManager() {
        return commandManager;
    }
}

