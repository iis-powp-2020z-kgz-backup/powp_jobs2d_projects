package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.MacroDriver;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadMacroOptionListener implements ActionListener {
    MacroDriver macroDriver = MacroFeature.getMacroDriver();
    DriverCommandManager manager = CommandsFeature.getDriverCommandManager();

    @Override
    public void actionPerformed(ActionEvent e) {
        manager.setCurrentCommand(macroDriver.getMacro());
    }
}
