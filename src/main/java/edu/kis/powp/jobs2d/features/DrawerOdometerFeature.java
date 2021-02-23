package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import java.util.logging.Logger;

public class DrawerOdometerFeature implements Job2dDriver {
    Logger logger = Logger.getLogger("global");
    private Double
            lineA,
            lineB,
            headDistance = 0.0,
            operatingDistance = 0.0;

    private void setOperatingDistance(Double operatingDistance) {
        this.operatingDistance = operatingDistance;
    }

    private void setHeadDistance(Double headDistance) {
        this.headDistance = headDistance;
    }

    public Double getHeadDistance() {
        return headDistance;
    }

    public Double getOperatingDistance() {
        return operatingDistance;
    }

    private double countDistance(int i, int y){
        lineA = Double.valueOf(i);
        lineB = Double.valueOf(y);
        return Math.sqrt(Math.pow(lineA, 2) + Math.pow(lineB, 2));
    }

    @Override
    public void setPosition(int i, int i1) {
        setOperatingDistance(getOperatingDistance() + countDistance(i, i1));

    }

    @Override
    public void operateTo(int i, int i1) {
        setHeadDistance(getHeadDistance() + countDistance(i, i1));
        logger.info("Head distance: " + getHeadDistance() +
                "\nOperating distance: " + getOperatingDistance());
    }
}
