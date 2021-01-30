package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.adapter.CompositeDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;

public class DriverVisitorCopy implements IDriverVisitor {

    private IVisitableDriver savedDriver;

    public IVisitableDriver getSavedDriver() {
        return savedDriver;
    }

    @Override
    public void visit(LineDriverAdapter lineDriverAdapter) {
        savedDriver = lineDriverAdapter;
    }

    @Override
    public void visit(VisitableLoggerDriver visitableLoggerDriver) {
        savedDriver = visitableLoggerDriver;
    }

    @Override
    public void visit(CompositeDriver compositeDriver) {
        final CompositeDriver copiedCompositeDriver = new CompositeDriver();
        compositeDriver.iterator().forEachRemaining(driver -> {
            driver.accept(this);
            copiedCompositeDriver.addDriver(savedDriver);
        });
        savedDriver = copiedCompositeDriver;
    }
}