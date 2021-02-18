package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorInterface;

public interface HistoryCommand extends Cloneable{

	public void execute(Job2dDriver driver);

	public HistoryCommand clone();

	void accept(CommandVisitorInterface visitor);

}
