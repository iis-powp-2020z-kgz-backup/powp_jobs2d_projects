package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.IVisitableDriver;
import edu.kis.powp.jobs2d.drivers.SelectAdditionalDriverMenuOptionListener;

public class AdditionalDriverFeature {

    private static Application app;


    public static void setupDriverPlugin(Application application) {
        app = application;
        app.addComponentMenu(AdditionalDriverFeature.class, "Additional Drivers");
    }
    
    public static void addDriver(String name, IVisitableDriver driver) {
        SelectAdditionalDriverMenuOptionListener listener = new SelectAdditionalDriverMenuOptionListener(driver, DriverFeature.getDriverManager());
        app.addComponentMenuElementWithCheckBox(AdditionalDriverFeature.class, name, listener, false);
    }
}
