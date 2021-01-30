package edu.kis.powp.jobs2d.command.manager;

public class CommandLoaderFactory {
    public CommandLoader getInstance(String dataType) {
        switch (dataType) {
            case "json":
                return new JsonCommandLoader();
            case "xml":
                return new XMLCommandLoader();
            default:
                return null;
        }
    }
}
