package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverLabelObserver implements Subscriber {

    @Override
    public void update() {
        DriverFeature.updateDriverInfo();
    }
}
