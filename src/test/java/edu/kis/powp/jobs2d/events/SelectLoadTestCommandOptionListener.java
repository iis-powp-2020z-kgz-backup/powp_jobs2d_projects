package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.HistoryCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectLoadTestCommandOptionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		List<DriverCommand> commands = new ArrayList<DriverCommand>();
		commands.add(new SetPositionCommand(-40, -50));
		commands.add(new OperateToCommand(40, -50));
		commands.add(new SetPositionCommand(0, -50));
		commands.add(new OperateToCommand(0, 100));




		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(commands, "TestCommand");

		HistoryCommandManager historyCommandManager = CommandsFeature.getHistoryCommandManager();
		historyCommandManager.setCurrentCommand(commands, "TestCommand");
	}
}