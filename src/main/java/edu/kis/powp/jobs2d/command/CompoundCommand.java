package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class CompoundCommand implements ICompoundCommand {

    /**
     * Std ctor
     */
    public CompoundCommand(List<DriverCommand> commands, String name) {
        super();
        this.commandsList = new ArrayList<>();
        commands.iterator().forEachRemaining(command -> this.commandsList.add(command.clone()));
        this.name = name;
    }

    /**
     * Deep 'copy ctor' (for interface), with name (re)difinition
     * 
     * @param other other compound command (any implmementing interface).
     * @param name name of the command
     */
    public CompoundCommand(ICompoundCommand other, String name) {
        super();
        this.name = name;
        this.commandsList = new ArrayList<>();
        other.iterator().forEachRemaining(command -> this.commandsList.add(command.clone()));
    }

    /**
     * Execute command on driver.
     *
	 * @param driver driver.
     */
    public void execute(Job2dDriver driver) {
        this.iterator().forEachRemaining(command -> command.execute(driver));
    }

    /*
     * Iterator to commands collection
     * 
     * @return Iterator
     */ 
    public Iterator<DriverCommand> iterator() {
        return commandsList.iterator();
    }

    /**
	 * Return deep copy of command by cloning.
	 */
    @Override
    public CompoundCommand clone() {
        CompoundCommand command = null;
        try {
            command = (CompoundCommand) super.clone();
            command.name = this.name;
            command.commandsList = new ArrayList<>();
            for (DriverCommand cmd : this.commandsList) {
                command.commandsList.add(cmd.clone());
            }
        } catch (CloneNotSupportedException e) {
            command = new CompoundCommand(this, this.name);
        }
        return command;
    }

    /*
     * Name of compound command
     *
     * @return Name of command
     */
    @Override
    public String toString() {
        return name;
    }

    private String name;
    private List<DriverCommand> commandsList;
}
