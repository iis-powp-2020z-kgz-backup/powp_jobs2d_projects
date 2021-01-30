package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitorInterface;

import java.io.Serializable;

/**
 * DriverCommand interface.
 */
public interface DriverCommand extends Cloneable{

	/**
	 * Execute command on driver.
	 * 
	 * @param driver driver.
	 */
	public void execute(Job2dDriver driver);

	/**
	 * Return deep copy of command by cloning.
	 */
	public DriverCommand clone();

	void accept(CommandVisitorInterface visitor);

}
