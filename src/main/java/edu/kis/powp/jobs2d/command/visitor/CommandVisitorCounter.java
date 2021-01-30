package edu.kis.powp.jobs2d.command.visitor;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import java.util.Iterator;

public class CommandVisitorCounter implements CommandVisitorInterface {

    private int commandCount=0;

    public int getAllCommandsCounter() {
        return commandCount;
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        commandCount++;
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        commandCount++;
    }

    @Override
    public void visit(ICompoundCommand compoundCommand) {
        Iterator<DriverCommand> iterator = compoundCommand.iterator();
        while (iterator.hasNext())
        {
            DriverCommand driverCommand = iterator.next();
            driverCommand.accept(this);
        }
    }
}