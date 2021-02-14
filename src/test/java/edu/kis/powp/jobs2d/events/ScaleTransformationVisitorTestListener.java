package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.transformation.ICommandTransformation;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorTransform;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class ScaleTransformationVisitorTestListener implements ActionListener {

    private Logger logger = Logger.getLogger("global");
    private ICommandTransformation transformation;

    public ScaleTransformationVisitorTestListener(ICommandTransformation transformation) {
        this.transformation = transformation;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("Testing Visitor for Scale Transformation Visitor");

        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if (command == null) {
            logger.info("No command loaded!");
        } else {
            CommandVisitorTransform visitor = new CommandVisitorTransform();
            visitor.setCurrentTransformation(transformation);
            command.accept(visitor);
            CommandsFeature.getDriverCommandManager().setCurrentCommand(command);
        }
    }
}
