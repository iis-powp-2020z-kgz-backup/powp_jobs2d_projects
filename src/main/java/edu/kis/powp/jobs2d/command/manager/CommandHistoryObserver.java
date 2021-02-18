package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

public class CommandHistoryObserver implements Subscriber {

    public void update() {
        // todo zmienic na caly obiekt a nie tylko nazwe
        String commandString = CommandsFeature.getHistoryCommandManager().getCurrentCommandString();

        CommandsFeature.getHistoryCommandManager().saveHistory(commandString);
    }

    public String toString() {
        return "Command History Observer";
    }

}
