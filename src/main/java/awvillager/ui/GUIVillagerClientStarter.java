package awvillager.ui;

import javax.swing.SwingUtilities;

public class GUIVillagerClientStarter {

    static FrameVillagerClient frame;

    public static void main(String[] args){


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new FrameVillagerClient();
                frame.setVisible(true);
            }
        });


    }

}
