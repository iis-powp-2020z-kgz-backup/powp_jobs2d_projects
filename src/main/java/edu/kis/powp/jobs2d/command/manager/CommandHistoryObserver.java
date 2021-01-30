package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.features.CommandHistoryFeature;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CommandHistoryObserver implements Subscriber {

    public void update() {
        String commandString = CommandHistoryFeature.getDriverCommandManager().getCurrentCommandString();

        try {
            saveHistory(commandString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveHistory(String command) throws IOException {
        String fileName = "history.txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(command+"\n");

        writer.close();
    }

}
