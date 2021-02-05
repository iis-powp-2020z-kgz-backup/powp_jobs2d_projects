package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CommandHistoryObserver implements Subscriber {

    ArrayList<HistoryCommandItem> commandsList = new ArrayList<>();

    public void update() {
        String commandString = CommandsFeature.getDriverCommandManager().getCurrentCommandString();

        saveHistory(commandString);
    }

    private void saveHistory(String command) {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        commandsList.add(new HistoryCommandItem(command, dateFormat.format(date)));
    }

}
