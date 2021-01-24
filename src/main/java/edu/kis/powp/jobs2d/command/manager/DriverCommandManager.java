package edu.kis.powp.jobs2d.command.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.observer.Publisher;

/**
 * Driver command Manager.
 */
public class DriverCommandManager {
	private DriverCommand currentCommand = null;

	private Publisher changePublisher = new Publisher();

	/**
	 * Set current command.
	 *
	 * @param commandList Set the command as current.
	 */
	public synchronized void setCurrentCommand(DriverCommand commandList) {
		this.currentCommand = commandList;
		changePublisher.notifyObservers();
	}

	/**
	 * Set current command.
	 *
	 * @param commandList list of commands representing a compound command.
	 * @param name        name of the command.
	 */
	public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
		setCurrentCommand(new ICompoundCommand() {

			List<DriverCommand> driverCommands = commandList;

			@Override
			public void execute(Job2dDriver driver) {
				driverCommands.forEach((c) -> c.execute(driver));
			}

			@Override
			public Iterator<DriverCommand> iterator() {
				return driverCommands.iterator();
			}

			@Override
			public String toString() {
				return name;
			}
		});

	}

	/**
	 * Return current command.
	 *
	 * @return Current command.
	 */
	public synchronized DriverCommand getCurrentCommand() {
		return currentCommand;
	}

	public synchronized void clearCurrentCommand() {
		currentCommand = null;
	}

	public synchronized String getCurrentCommandString() {
		if (getCurrentCommand() == null) {
			return "No command loaded";
		} else
			return getCurrentCommand().toString();
	}

	public Publisher getChangePublisher() {
		return changePublisher;
	}

	// Class representation of Command needed to use with Gson
	class Command {
		String operation;
		int X;
		int Y;
	}

	/**
	 *
	 * @param filePath path where file with json is stored
	 * @param name name of the command
	 */
	public synchronized void loadFromJsonFile(String filePath, String name) {
		Gson gson = new Gson();
		Type myType = new TypeToken<Collection<Command>>() {}.getType();
		String jsonString = null;
		try {
			jsonString = new Scanner(new File(filePath)).useDelimiter("\\Z").next(); // 1-liner that reads whole file to string
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<Command> myCommands = gson.fromJson(jsonString, myType);
		List<DriverCommand> myDriverCommands = new ArrayList<>();
		for (Command command : myCommands) {
			switch (command.operation) {
				case "SetPositionCommand":
					myDriverCommands.add(new SetPositionCommand(command.X, command.Y));
					break;
				case "OperateToCommand"	:
					myDriverCommands.add(new OperateToCommand(command.X, command.Y));
					break;
			}
		}
		setCurrentCommand(myDriverCommands, name);
	}
}


