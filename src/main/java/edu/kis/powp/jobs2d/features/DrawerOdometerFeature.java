package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import java.util.logging.Logger;

public class DrawerOdometerFeature implements Job2dDriver {
    Logger logger = Logger.getLogger("global");
    private Double
            lineA,
            lineB,
            positionDistance = 0.0,
            operateDistance = 0.0;

    @Override
    public void setPosition(int i, int i1) {
        lineA = Double.valueOf(i);
        lineB = Double.valueOf(i1);
        double hypotenuse = Math.sqrt(Math.pow(lineA, 2) + Math.pow(lineB, 2));
        this.positionDistance = this.positionDistance + hypotenuse;
        logger.info("Head distance: " + this.positionDistance);
    }

    @Override
    public void operateTo(int i, int i1) {
        lineA = Double.valueOf(i);
        lineB = Double.valueOf(i1);
        double hypotenuse = Math.sqrt(Math.pow(lineA, 2) + Math.pow(lineB, 2));
        this.operateDistance = this.operateDistance + hypotenuse;
        logger.info("Op. distance: " + this.operateDistance);
    }
}
