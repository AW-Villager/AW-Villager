package awvillager.ui.util;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

public class DragListener extends MouseInputAdapter
{
    private final Component drag;
    private final JFrame frame;
    private Point mouseDownCompCoords = null;

    public DragListener(JFrame frame,Component drag) {
        this.frame = frame;
        this.drag = drag;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDownCompCoords = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDownCompCoords = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currCoords = e.getLocationOnScreen();
        int h = drag.getLocationOnScreen().y - frame.getLocationOnScreen().y;
        frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y - h);// + drag.getHeight());
    }
}