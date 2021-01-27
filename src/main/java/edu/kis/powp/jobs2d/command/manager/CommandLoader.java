package edu.kis.powp.jobs2d.command.manager;

public interface CommandLoader {
    public LoadedCommand loadCommandFromExternalSource(String formattedData);
}
