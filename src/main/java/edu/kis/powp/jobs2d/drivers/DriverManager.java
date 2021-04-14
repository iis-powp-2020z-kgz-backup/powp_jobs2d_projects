package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.observer.Publisher;
import edu.kis.powp.jobs2d.drivers.adapter.CompositeDriver;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {

	private IVisitableDriver currentDriver;
	private Publisher publisher = new Publisher();
	private CompositeDriver composite = new CompositeDriver();

	/**
	 * @param driver Set the driver as current.
	 */
	public synchronized void setCurrentDriver(IVisitableDriver driver) {
		composite.removeDriver(currentDriver);
		composite.addDriver(driver);
		currentDriver = driver;
		
		publisher.notifyObservers();
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public synchronized void addDriver(IVisitableDriver driver) {
		composite.addDriver(driver);
		publisher.notifyObservers();
	}

	public synchronized void removeDriver(IVisitableDriver driver) {
		composite.removeDriver(driver);
		publisher.notifyObservers();
	}

	/**
	 * @return Current driver.
	 */
	public synchronized IVisitableDriver getCurrentDriver() {
		return composite;
	}
}
