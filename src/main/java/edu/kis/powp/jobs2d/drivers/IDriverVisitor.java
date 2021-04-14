package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.adapter.CompositeDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;

public interface IDriverVisitor {
    void visit(LineDriverAdapter lineDriverAdapter);
    void visit(CompositeDriver compositeDriver);
    void visit(VisitableLoggerDriver visitableLoggerDriver);
}
