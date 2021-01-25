package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class CompositeDriver implements Job2dDriver {
    private List<Job2dDriver> driversToBeExecuted = new ArrayList<>();

    public CompositeDriver() {
    }

    public CompositeDriver(Job2dDriver composite, Job2dDriver driver) {
        driversToBeExecuted.add(composite);
        if (!driversToBeExecuted.contains(driver)){
            driversToBeExecuted.add(driver);
        }
    }

    public void addDriver(Job2dDriver addDriver) {
        this.driversToBeExecuted.add(addDriver);
    }
    public void removeDriver(Job2dDriver addDriver) {
        this.driversToBeExecuted.remove(addDriver);
    }

    @Override
    public void setPosition(int i, int i1) {
        for(Job2dDriver driver : driversToBeExecuted){
            driver.setPosition(i,i1);
        }
    }

    @Override
    public void operateTo(int i, int i1) {
        for(Job2dDriver driver : driversToBeExecuted){
            driver.operateTo(i,i1);
        }
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Job2dDriver driver : driversToBeExecuted){
            builder.append(driver.toString() +" ");

        }
        return builder.toString();
    }
}
