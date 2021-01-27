package edu.kis.powp.jobs2d.command.manager;

public class CommandLoaderFactory {
    public CommandLoader getInstance(String dataType, String formattedData, String name) {
        switch (dataType) {
            case "json":
                return new JsonCommandLoader(formattedData, name);
            case "xml":
                return new XMLCommandLoader(formattedData, name);
            default:
                return null;
        }
    }
}
