package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.HistoryCommand;
import edu.kis.powp.observer.Publisher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryCommandManager {
	private HistoryCommand currentCommand = null;

	private Publisher changePublisher = new Publisher();
	ArrayList<HistoryCommandItem> commandsList = new ArrayList<>();
	int index;

	public synchronized void setCurrentCommand(HistoryCommand commandList) {
		this.currentCommand = commandList;
		changePublisher.notifyObservers();
	}

	public synchronized void setCurrentCommand(List<HistoryCommand> commandList, String name) {
		setCurrentCommand(new CompoundCommand(commandList, name));
	}

	public synchronized HistoryCommand getCurrentCommand() {
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
