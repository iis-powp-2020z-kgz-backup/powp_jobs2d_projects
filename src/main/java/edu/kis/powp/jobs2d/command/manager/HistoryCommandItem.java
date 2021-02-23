package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.List;

public class HistoryCommandItem {

    public HistoryCommandItem(String name, String date, List<DriverCommand> commandList) {
        this.name = name;
        this.date = date;
        this.commandList = commandList;
    }

    private String name;
    private String date;
    private List<DriverCommand> commandList;

    public void setCommandList(List<DriverCommand> commandList) {
        this.commandList = commandList;
    }

    public List<DriverCommand> getCommandList() {
        return commandList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
