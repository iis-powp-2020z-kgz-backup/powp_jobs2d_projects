package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;

public class AdditionalDriverFeature {


    private static DriverManager driverManager = new DriverManager();
    private static Application app;

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    public static void setupDriverPlugin(Application application) {
        app = application;
        app.addComponentMenu(AdditionalDriverFeature.class, "Additional Drivers");
    }
    
    public static void addDriver(String name, Job2dDriver driver) {
        SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(driver, driverManager);
        app.addComponentMenuElementWithCheckBox(AdditionalDriverFeature.class, name, listener, false);
    }

    public static void updateDriverInfo() {
        app.updateInfo(driverManager.getCurrentDriver().toString());
    }
}
