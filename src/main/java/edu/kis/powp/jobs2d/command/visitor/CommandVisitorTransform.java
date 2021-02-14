package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.transformation.ICommandTransformation;

import java.util.Iterator;

public class CommandVisitorTransform implements CommandVisitorInterface {

    private ICommandTransformation currentTransformation;

    public ICommandTransformation getCurrentTransformation() {
        return currentTransformation;
    }

    public void setCurrentTransformation(ICommandTransformation transformation) {
        currentTransformation = transformation;
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        currentTransformation.apply(operateToCommand);
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        currentTransformation.apply(setPositionCommand);
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