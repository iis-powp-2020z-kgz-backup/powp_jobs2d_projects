package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class MacroDriver implements Job2dDriver {
    private List<DriverCommand> macro = new ArrayList<>();

    @Override
    public void operateTo(int x, int y) {
        macro.add(new OperateToCommand(x, y));
    }

    @Override
    public void setPosition(int x, int y) {
        macro.add((new SetPositionCommand(x, y)));
    }

    public CompoundCommand getMacro(){
        return new CompoundCommand(macro, "Macro");
    }

    public void clearMacro(){
        if(macro.size()>0) {
            macro.clear();
        }
    }

    @Override
    public String toString() {
        return "Macro";
    }
}