package edu.kis.powp.jobs2d.command.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class JsonCommandLoader implements CommandLoader {

    public JsonCommandLoader() {
    }

    @Override
    public synchronized LoadedCommand loadCommandFromExternalSource(String formattedData) {
        Gson gson = new Gson();
        Type myType = new TypeToken<Collection<Command>>() {}.getType();
        List<Command> myCommands = gson.fromJson(formattedData, myType);
        List<DriverCommand> myDriverCommands = new ArrayList<>();
        for (Command command : myCommands) {
            switch (command.operation) {
                case "SetPositionCommand":
                    myDriverCommands.add(new SetPositionCommand(command.x, command.y));
                    break;
                case "OperateToCommand"	:
                    myDriverCommands.add(new OperateToCommand(command.x, command.y));
                    break;
            }
        }
        return new LoadedCommand(myDriverCommands);
    }
}
