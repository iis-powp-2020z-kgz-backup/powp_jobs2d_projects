package edu.kis.powp.jobs2d.command.transformation;

import java.util.Iterator;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class ScaleTransformation implements ICommandTransformation {
    private int scaleX, scaleY;

    public void setScale(int x, int y) {
        scaleX = x;
        scaleY = y;
    }

    public void apply(DriverCommand command) {
        if(command instanceof SetPositionCommand) {
            int oldX = ((SetPositionCommand) command).getPosX();
            int oldY = ((SetPositionCommand) command).getPosY();
            command = new SetPositionCommand(oldX * scaleX, oldY * scaleY);
        }
        else if (command instanceof OperateToCommand) {
            int oldX = ((OperateToCommand) command).getPosX();
            int oldY = ((OperateToCommand) command).getPosY();
            command = new OperateToCommand(oldX * scaleX, oldY * scaleY);
        }
        else if (command instanceof ICompoundCommand) {
            Iterator<DriverCommand> iterator = ((ICompoundCommand)command).iterator();
            while (iterator.hasNext())
            {
                DriverCommand subCommand = iterator.next();
                this.apply(subCommand);
            }
        }
    }
}
