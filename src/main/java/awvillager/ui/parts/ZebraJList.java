package awvillager.ui.parts;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

public class ZebraJList<E> extends javax.swing.JList<E> {
    private java.awt.Color rowColors[] = new java.awt.Color[2];
    private boolean drawStripes = false;

    public ZebraJList() {
    }

    public ZebraJList(ListModel<E> dataModel) {
        super(dataModel);
    }

    public ZebraJList(E[] listData) {
        super(listData);
    }

    public ZebraJList(Vector<? extends E> listData) {
        super(listData);
    }

    /** Add zebra stripes to the background. */
    @Override
    public void paintComponent(java.awt.Graphics g) {
        drawStripes = (getLayoutOrientation() == VERTICAL) && isOpaque();
        if (!drawStripes) {
            super.paintComponent(g);
            return;
        }

        // Paint zebra background stripes
        updateZebraColors();
        final java.awt.Insets insets = getInsets();
        final int w = getWidth() - insets.left - insets.right;
        final int h = getHeight() - insets.top - insets.bottom;
        final int x = insets.left;
        int y = insets.top;
        int nRows = 0;
        int startRow = 0;
        int rowHeight = getFixedCellHeight();
        if (rowHeight > 0)
            nRows = h / rowHeight;
        else {
            // Paint non-uniform height rows first
            final int nItems = getModel().getSize();
            rowHeight = 17; // A default for empty lists
            for (int i = 0; i < nItems; i++, y += rowHeight) {
                rowHeight = getCellBounds(i, i).height;
                g.setColor(rowColors[i & 1]);
                g.fillRect(x, y, w, rowHeight);
            }
            // Use last row height for remainder of list area
            nRows = nItems + (insets.top + h - y) / rowHeight;
            startRow = nItems;
        }
        for (int i = startRow; i < nRows; i++, y += rowHeight) {
            g.setColor(rowColors[i & 1]);
            g.fillRect(x, y, w, rowHeight);
        }
        final int remainder = insets.top + h - y;
        if (remainder > 0) {
            g.setColor(rowColors[nRows & 1]);
            g.fillRect(x, y, w, remainder);
        }

        // Paint component
        setOpaque(false);
        super.paintComponent(g);
        setOpaque(true);
    }

    /** Wrap a cell renderer to add zebra stripes behind list cells. */
    private class RendererWrapper<F> implements ListCellRenderer<F> {

        public ListCellRenderer<? super F> ren = null;

        @Override
        public Component getListCellRendererComponent(
                JList<? extends F> list, F value, int index,
                boolean isSelected, boolean cellHasFocus) {

            final java.awt.Component c = ren.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            if (!isSelected && drawStripes)
                c.setBackground(rowColors[index & 1]);

            JLabel jl = (JLabel) c;
            jl.setBorder(new EmptyBorder(2, 14, 2, 0));


            return c;

        }

    }

    private RendererWrapper<E> wrapper = null;

    /** Return the wrapped cell renderer. */
    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        final ListCellRenderer<? super E> ren = super.getCellRenderer();
        if (ren == null)
            return null;
        if (wrapper == null)
            wrapper = new RendererWrapper<E>();
        wrapper.ren = ren;
        return wrapper;
    }

    /** Compute zebra background stripe colors. */
    private void updateZebraColors() {
        if ((rowColors[0] = getBackground()) == null) {
            rowColors[0] = rowColors[1] = java.awt.Color.white;
            return;
        }
        final java.awt.Color sel = getSelectionBackground();
        if (sel == null) {
            rowColors[1] = rowColors[0];
            return;
        }
        final float[] bgHSB = java.awt.Color.RGBtoHSB(
                rowColors[0].getRed(), rowColors[0].getGreen(),
                rowColors[0].getBlue(), null);
        final float[] selHSB = java.awt.Color.RGBtoHSB(
                sel.getRed(), sel.getGreen(), sel.getBlue(), null);
        rowColors[1] = java.awt.Color.getHSBColor(
                (selHSB[1] == 0.0 || selHSB[2] == 0.0) ? bgHSB[0] : selHSB[0],
                0.1f * selHSB[1] + 0.9f * bgHSB[1],
                bgHSB[2] + ((bgHSB[2] < 0.5f) ? 0.05f : -0.05f));
    }
}
