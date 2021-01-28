package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.jobs2d.drivers.adapter.CompositeDriver;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {

	private Job2dDriver currentDriver = new LoggerDriver();
	private Publisher publisher = new Publisher();
	private CompositeDriver composite = new CompositeDriver();

	/**
	 * @param driver Set the driver as current.
	 */
	public synchronized void setCurrentDriver(Job2dDriver driver) {
		currentDriver = driver;
		publisher.notifyObservers();
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public synchronized void addDriver(Job2dDriver driver){
		composite.addDriver(driver);
		System.out.println(composite);
	}

	public synchronized void removeDriver(Job2dDriver driver){
		composite.removeDriver(driver);
	}

	/**
	 * @return Current driver.
	 */
	public synchronized Job2dDriver getCurrentDriver() {
		return new CompositeDriver(composite, currentDriver);
	}
}
