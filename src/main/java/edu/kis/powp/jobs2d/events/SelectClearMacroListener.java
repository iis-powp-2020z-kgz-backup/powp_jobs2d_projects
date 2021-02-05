package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.drivers.MacroDriver;

public class SelectClearMacroListener implements ActionListener {
    private MacroDriver macroDriver;

    public SelectClearMacroListener(MacroDriver macroDriver){
        this.macroDriver = macroDriver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        macroDriver.clearMacro();
    }
}
