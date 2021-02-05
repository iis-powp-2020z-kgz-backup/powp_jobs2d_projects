package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

public class CommandHistoryObserver implements Subscriber {

    public void update() {
        String commandString = CommandsFeature.getDriverCommandManager().getCurrentCommandString();

        CommandsFeature.getDriverCommandManager().saveHistory(commandString);
    }

    public String toString() {
        return "Command History Observer";
    }

}
