package edu.kis.powp.jobs2d.drivers.transformation;

public class Rotate implements Transformation {
    private final double angle;

    public Rotate(double angle) {
        this.angle = angle;
    }

    @Override
    public Point use(Point point) {
        return new Point(
                (int) (point.getX() * Math.cos(angle) - point.getY() * Math.sin(angle)),
                (int) (point.getY() * Math.cos(angle) + point.getX() * Math.sin(angle))
        );
    }
}
