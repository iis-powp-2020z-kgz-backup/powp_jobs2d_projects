package edu.kis.powp.jobs2d.features;

        import edu.kis.powp.jobs2d.drivers.DriverManager;
        import javax.swing.*;
        import java.awt.event.*;


public class DrawerPanelClickMouseListenerFeature implements MouseListener {
    private JPanel freePanel;
    private int startX = 0, startY = 0;
    private boolean isClicked = true;
    private DriverManager driverManager;


    public DrawerPanelClickMouseListenerFeature(JPanel freePanel, DriverManager driverManager) {
        super();
        this.freePanel = freePanel;
        this.driverManager = driverManager;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            startX = mouseEvent.getX() - (freePanel.getSize().width / 2);
            startY = mouseEvent.getY() - (freePanel.getSize().height / 2);

            if (isClicked) {
                driverManager.getCurrentDriver().setPosition(startX, startY);
                isClicked = false;
            } else {
                driverManager.getCurrentDriver().operateTo(startX, startY);
                isClicked = true;
            }
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