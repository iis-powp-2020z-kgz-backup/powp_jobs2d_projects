package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CopyCommandVisitor implements CommandVisitorInterface{

    private DriverCommand driverCommand;

    private DriverCommand copyCommand(){
        return driverCommand;
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        driverCommand = new OperateToCommand(operateToCommand.getPosX(),operateToCommand.getPosY());
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        driverCommand = new SetPositionCommand(setPositionCommand.getPosX(),setPositionCommand.getPosY());
    }

    @Override
    public void visit(ICompoundCommand iCompoundCommand) {
        List<DriverCommand> driverCommands = new ArrayList<>();
        Iterator<DriverCommand> iterator = iCompoundCommand.iterator();
        while (iterator.hasNext()){
            DriverCommand subCommand = iterator.next();
            subCommand.accept(this);
            driverCommands.add(driverCommand);
        }
        driverCommand = new CompoundCommand(driverCommands, iCompoundCommand.toString());
    }
}
