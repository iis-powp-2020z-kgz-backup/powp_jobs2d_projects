package edu.kis.powp.jobs2d.drivers.transformation;

public class Scale implements Transformation {
    private final double xScale;
    private final double yScale;

    public Scale(double xScale, double yScale) {
        this.xScale = xScale;
        this.yScale = yScale;
    }

    @Override
    public Point use(Point point) {
        return new Point((int) (point.getX() * xScale), (int) (point.getY() * yScale));
    }
}
