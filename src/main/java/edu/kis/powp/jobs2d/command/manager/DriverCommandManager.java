package edu.kis.powp.jobs2d.command.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.observer.Publisher;

/**
 * Driver command Manager.
 */
public class DriverCommandManager {
	private DriverCommand currentCommand = null;

	private Publisher changePublisher = new Publisher();
	ArrayList<HistoryCommandItem> commandsList = new ArrayList<>();
	int index;

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
		setCurrentCommand(new CompoundCommand(commandList, name));
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

	public String getHistoryCommandPreviousString() {
		if (this.index - 1 >= 0) this.index--;
		System.out.println(commandsList.get(this.index).getName() + commandsList.get(this.index).getDate());
		return commandsList.get(this.index).getName() + ' ' + commandsList.get(this.index).getDate();
	}

	public String getHistoryCommandNextString() {
		if (this.index + 1 < commandsList.size()) this.index++;
		System.out.println(commandsList.get(this.index).getName() + commandsList.get(this.index).getDate());
		return commandsList.get(this.index).getName() + ' ' + commandsList.get(this.index).getDate();
	}

	public void saveHistory(String command) {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		commandsList.add(new HistoryCommandItem(command, dateFormat.format(date)));
		this.index = commandsList.size()-1;
	}
}
