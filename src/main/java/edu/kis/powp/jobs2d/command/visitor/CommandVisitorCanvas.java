package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;

public class CommandVisitorCanvas implements CommandVisitorInterface {

    ShapeChecker shapeChecker;
    boolean isBounduaryCrossed=false;

    public CommandVisitorCanvas(ShapeChecker shapeChecker){
        this.shapeChecker=shapeChecker;

    }


    @Override
    public void visit(OperateToCommand operateToCommand) {
        if(shapeChecker.checkBonduary(operateToCommand.getPosX(),operateToCommand.getPosY())){
            isBounduaryCrossed=true;
        }
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        if(shapeChecker.checkBonduary(setPositionCommand.getPosX(),setPositionCommand.getPosY())){
            isBounduaryCrossed = true;
        }
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


    public boolean getResult(){
        return isBounduaryCrossed;
    }
}
