package edu.kis.powp.jobs2d.command.manager;

public interface LoaderCommand {
    public LoadedCommand loadCommandFromExternalSource(String formattedData);
}
