package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.observer.Publisher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class HistoryCommandManager {
    private DriverCommand currentCommand = null;

    private Publisher changePublisher = new Publisher();
    private List<DriverCommand> commandsList = new ArrayList<>();
    private List<HistoryCommandItem> historyCommandList = new ArrayList<>();
    private String name;
    private int index;
    private Logger logger = Logger.getLogger("global");

    public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
        this.name = name;
        this.commandsList = commandList;
        changePublisher.notifyObservers();
    }

    public synchronized DriverCommand getCurrentCommand() {
        return currentCommand;
    }

    public synchronized void clearCurrentCommand() {
        currentCommand = null;
    }

    public synchronized String getCurrentCommandString() {
        if (getCurrentCommand() == null) {
            return "No command loaded";
        } else
            return getCurrentCommand().toString();
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }

    public String getHistoryCommandPreviousString() {
        if (this.index - 1 >= 0) this.index--;

        logger.info(historyCommandList.get(this.index).getName() + historyCommandList.get(this.index).getDate());
        return historyCommandList.get(this.index).getName() + ' ' + historyCommandList.get(this.index).getDate();
    }

    public String getHistoryCommandNextString() {
        if (this.index + 1 < historyCommandList.size()) this.index++;
        logger.info(historyCommandList.get(this.index).getName() + historyCommandList.get(this.index).getDate());
        return historyCommandList.get(this.index).getName() + ' ' + historyCommandList.get(this.index).getDate();
    }

    public void saveHistory() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        historyCommandList.add(new HistoryCommandItem(name, dateFormat.format(date), commandsList));
        this.index = historyCommandList.size() - 1;
    }
}
