package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

public class CommandHistoryObserver implements Subscriber {

    public void update() {
        CommandsFeature.getHistoryCommandManager().saveHistory();
    }

    public String toString() {
        return "Command History Observer";
    }

}
