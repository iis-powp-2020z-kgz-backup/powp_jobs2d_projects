package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.RectangleCanvas;
import edu.kis.powp.jobs2d.command.ShapeChecker;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorCanvas;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorCounter;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SelectCommandVisitorCanvasListener implements ActionListener {

    private Logger logger = Logger.getLogger("global");
    private DriverManager driverManager ;
    ShapeChecker shapeChecker;


    public SelectCommandVisitorCanvasListener(DriverManager driverManager, ShapeChecker shapeChecker) {
        this.driverManager = driverManager;
        this.shapeChecker=shapeChecker;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("Testing Visitor for CommandVisitorCanvas");

        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if(command==null){
            logger.info("No command loaded!");
        }else {
            CommandVisitorCanvas visitor = new CommandVisitorCanvas(shapeChecker);

            command.accept(visitor);

            if(visitor.getResult())
                logger.info("Boundary is crossed!");
            else
                logger.info("Boundary is not crossed");


        }
    }
}
