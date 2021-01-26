package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public final class ImmutableCompoundCommand implements ICompoundCommand {
    
    private List<DriverCommand> commandsList;
    
    public Iterator<DriverCommand> iterator() {
        return commandsList.iterator();
    }
           
    public ImmutableCompoundCommand(List<DriverCommand> commands, String name) {
        super();
        this.commandsList = new ArrayList<>();
        commands.iterator().forEachRemaining(command -> this.commandsList.add(command.clone()));
        this.name = name;
    }

    public ImmutableCompoundCommand(ICompoundCommand other, String name) {
        super();
        this.name = name;
        this.commandsList = new ArrayList<>();
        other.iterator().forEachRemaining(command -> this.commandsList.add(command.clone()));
    }

    public ImmutableCompoundCommand clone(){    
        ImmutableCompoundCommand command = (ImmutableCompoundCommand) super.clone();
    
        command = (ImmutableCompoundCommand) super.clone();
        command.name = this.name;
        command.commandsList = new ArrayList<>();
        for (DriverCommand cmd : this.commandsList) {
            command.commandsList.add(cmd.clone());
        }
        return command;
    }

    public void execute(Job2dDriver driver) {
        this.iterator().forEachRemaining(command -> ImmutableCompoundCommand.execute(driver));
    }
}