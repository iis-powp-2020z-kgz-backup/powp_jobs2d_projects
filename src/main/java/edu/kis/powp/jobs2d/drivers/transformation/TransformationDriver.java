package edu.kis.powp.jobs2d.drivers.transformation;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class TransformationDriver implements Job2dDriver {
    private final List<Transformation> transformationsList = new ArrayList<>();
    private final Job2dDriver driver;

    public TransformationDriver(Job2dDriver job2dDriver) {
        this.driver = job2dDriver;
    }

    public void addNewTransformation(Transformation transformation) {
        transformationsList.add(transformation);
    }

    private Point newPoint(Point point) {
        for (Transformation transformation : transformationsList) {
            point = transformation.use(point);
        }

        return point;
    }

    @Override
    public void setPosition(int x, int y) {
        Point point = newPoint(new Point(x, y));
        driver.setPosition(point.getX(), point.getY());
    }

    @Override
    public void operateTo(int x, int y) {
        Point point = newPoint(new Point(x, y));
        driver.operateTo(point.getX(), point.getY());
    }
}
