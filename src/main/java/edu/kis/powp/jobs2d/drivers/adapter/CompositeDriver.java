package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.drivers.IDriverVisitor;
import edu.kis.powp.jobs2d.drivers.IVisitableDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeDriver implements IVisitableDriver {
    private List<IVisitableDriver> driversToBeExecuted = new ArrayList<>();

    public CompositeDriver() {
    }

    public CompositeDriver(IVisitableDriver composite, IVisitableDriver driver) {
        if (!driversToBeExecuted.contains(composite)) {
            driversToBeExecuted.add(composite);
        }
               
        if (!driversToBeExecuted.contains(driver)) {
            driversToBeExecuted.add(driver);
        }
    }

    public void addDriver(IVisitableDriver addDriver) {
        this.driversToBeExecuted.add(addDriver);
    }
    public void removeDriver(IVisitableDriver addDriver) {
        this.driversToBeExecuted.remove(addDriver);
    }

    public Iterator<IVisitableDriver> iterator() {
        return driversToBeExecuted.iterator();
    }

    public void accept(IDriverVisitor visitor) {
		visitor.visit(this);
	}

    @Override
    public void setPosition(int x, int y) {
        for(IVisitableDriver driver : driversToBeExecuted) {
            driver.setPosition(x, y);
        }
    }

    @Override
    public void operateTo(int x, int y) {
        for(IVisitableDriver driver : driversToBeExecuted) {
            driver.operateTo(x, y);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(IVisitableDriver driver : driversToBeExecuted) {
            builder.append(driver.toString() + " ");

        }
        return builder.toString();
    }
}
