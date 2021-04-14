package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.DriverVisitorCounter;
import edu.kis.powp.jobs2d.drivers.IVisitableDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Logger;

public class SelectDriverVisitorCounterListener implements ActionListener {

    private Logger logger = Logger.getLogger("global");
    private DriverManager driverManager;

    public SelectDriverVisitorCounterListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IVisitableDriver driver = driverManager.getCurrentDriver();

        if(driver == null){
            logger.info("Driver visitor: No driver loaded!");
        } 
        else {
            DriverVisitorCounter visitor = new DriverVisitorCounter();

            driver.accept(visitor);

            logger.info("Driver visitor: Counter: " + visitor.getDriversCount());
        }
    }
}
