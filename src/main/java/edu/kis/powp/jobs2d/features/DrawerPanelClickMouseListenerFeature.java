package edu.kis.powp.jobs2d.features;


        import edu.kis.legacy.drawer.panel.DrawPanelController;
        import edu.kis.legacy.drawer.shape.ILine;
        import edu.kis.powp.appbase.Application;
        import edu.kis.powp.jobs2d.drivers.DriverManager;
        import java.awt.event.*;


public class DrawerPanelClickMouseListenerFeature implements MouseListener {
    private final Application application;
    private ILine line;
    private int startX = 0, startY = 0;
    private String name;
    private boolean isClicked = false;
    private DriverManager driverManager;

    private DrawPanelController drawController;

    public DrawerPanelClickMouseListenerFeature(DrawPanelController drawController,
                                                Application application, DriverManager driverManager, ILine line, String name) {
        super();
        this.drawController = drawController;
        this.line = line;
        this.application = application;
        this.driverManager = driverManager;
        this.name = name;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        startX = mouseEvent.getX() - (application.getFreePanel().getSize().width/2);
        startY = mouseEvent.getY() - (application.getFreePanel().getSize().height/2);
        driverManager.getCurrentDriver();

        if(isClicked){
            setPosition(startX, startY);
            isClicked = false;
        }
        else {
            endPosition(startX, startY);
            isClicked = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {


    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void setPosition(int x, int y) {
        startX = x;
        startY = y;
        line.setEndCoordinates(x, y);
        drawController.drawLine(line);
    }


    public void endPosition(int x, int y) {
        line.setStartCoordinates(x, y);
    }
}