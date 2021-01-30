package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.LoggerDriver;

public class VisitableLoggerDriver extends LoggerDriver implements IVisitableDriver{

    @Override
    public void accept(IDriverVisitor visitor) {
        visitor.visit(this);
    }
    
}
