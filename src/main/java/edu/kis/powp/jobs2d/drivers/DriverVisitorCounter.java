package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.adapter.CompositeDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;

public class DriverVisitorCounter implements IDriverVisitor {

    private int driverCount = 0;

    public int getDriversCount() {
        return driverCount;
    }

    @Override
    public void visit(LineDriverAdapter lineDriverAdapter) {
        ++driverCount;
    }

    @Override
    public void visit(VisitableLoggerDriver visitableLoggerDriver) {
        ++driverCount;
    }

    @Override
    public void visit(CompositeDriver compositeDriver) {
        compositeDriver.iterator().forEachRemaining(driver -> driver.accept(this));
    }
}