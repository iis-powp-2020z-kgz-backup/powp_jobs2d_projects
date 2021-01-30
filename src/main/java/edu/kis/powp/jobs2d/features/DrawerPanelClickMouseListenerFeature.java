package edu.kis.powp.jobs2d.features;


        import edu.kis.legacy.drawer.panel.DrawPanelController;
        import edu.kis.legacy.drawer.shape.ILine;
        import edu.kis.powp.appbase.Application;
        import edu.kis.powp.jobs2d.drivers.DriverManager;
        import java.awt.event.*;


public class DrawerPanelClickMouseListenerFeature implements MouseListener {
    private final Application application;
    private int startX = 0, startY = 0;
    private boolean isClicked = true;
    private DriverManager driverManager;


    public DrawerPanelClickMouseListenerFeature(Application application, DriverManager driverManager) {
        super();
        this.application = application;
        this.driverManager = driverManager;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        startX = mouseEvent.getX() - (application.getFreePanel().getSize().width/2);
        startY = mouseEvent.getY() - (application.getFreePanel().getSize().height/2);

        if(isClicked){
            driverManager.getCurrentDriver().setPosition(startX, startY);
            isClicked = false;
        }
        else {
            driverManager.getCurrentDriver().operateTo(startX, startY);
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
}