package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorCounter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class SelectICompoundCommandVistorTestListener implements ActionListener {

    private Logger logger = Logger.getLogger("global");

    @Override
    public void actionPerformed(ActionEvent e) {

        List<DriverCommand> commands = new ArrayList<>(
                Arrays.asList(
                        new OperateToCommand(3, 4),
                        new SetPositionCommand(1, 2)

                )
        );

        ICompoundCommand embeddedCompoundCommand = new ICompoundCommand() {
            private List<DriverCommand> embeddedCommands = new ArrayList<>(
                    Arrays.asList(
                            new OperateToCommand(7, 9),
                            new OperateToCommand(9, 10),
                            new SetPositionCommand(11,12)
                    )
            );

            @Override
            public Iterator<DriverCommand> iterator() {
                return embeddedCommands.iterator();
            }

            @Override
            public void execute(Job2dDriver driver) {

            }

            @Override
            public DriverCommand clone() {
                return null;
            }
        };
        commands.add(embeddedCompoundCommand);
        ICompoundCommand command = new ICompoundCommand() {
            @Override
            public Iterator<DriverCommand> iterator() {
                return commands.iterator();
            }

            @Override
            public void execute(Job2dDriver driver) {

            }

            @Override
            public DriverCommand clone(){
                return null;
            }
        };
        CommandVisitorCounter visitor = new CommandVisitorCounter();
        command.accept(visitor);

        logger.info("Counter: " + visitor.getAllCommandsCounter());

    }
}
