package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.MacroDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadMacroOptionListener implements ActionListener {
    private MacroDriver macroDriver;
    private DriverCommandManager manager;

    public SelectLoadMacroOptionListener(MacroDriver macroDriver, DriverCommandManager manager){
        this.macroDriver = macroDriver;
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        manager.setCurrentCommand(macroDriver.getMacro());
    }
}
