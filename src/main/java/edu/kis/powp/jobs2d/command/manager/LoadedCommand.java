package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.List;

public class LoadedCommand {
    private final List<DriverCommand> loadedDriverCommands;

    LoadedCommand(List<DriverCommand> loadedDriverCommands) {
        this.loadedDriverCommands = loadedDriverCommands;
    }

    public List<DriverCommand> getLoadedDriverCommands() {
        return loadedDriverCommands;
    }
}
