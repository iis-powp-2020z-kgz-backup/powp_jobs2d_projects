package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.List;

public class LoadedCommand {
    private final List<DriverCommand> loadedDriverCommands;
    private final String loadedName;

    LoadedCommand(List<DriverCommand> loadedDriverCommands, String loadedName) {
        this.loadedDriverCommands = loadedDriverCommands;
        this.loadedName = loadedName;
    }

    public List<DriverCommand> getLoadedDriverCommands() {
        return loadedDriverCommands;
    }

    public String getLoadedName() {
        return loadedName;
    }
}
