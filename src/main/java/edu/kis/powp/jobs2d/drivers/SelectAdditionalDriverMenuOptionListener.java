package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectAdditionalDriverMenuOptionListener implements ActionListener {
    private DriverManager driverManager;
    private Job2dDriver driver = null;
    private boolean state =false;


    public SelectAdditionalDriverMenuOptionListener(Job2dDriver driver, DriverManager driverManager) {
        this.driverManager = driverManager;
        this.driver = driver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!state){
            driverManager.addDriver(driver);
        }else {
            driverManager.removeDriver(driver);
        }
        state = !state;
    }
}
