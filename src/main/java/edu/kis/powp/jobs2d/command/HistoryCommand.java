package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.visitor.CommandVisitorInterface;

public interface HistoryCommand extends Cloneable{

	public HistoryCommand clone();

	void accept(CommandVisitorInterface visitor);

}
