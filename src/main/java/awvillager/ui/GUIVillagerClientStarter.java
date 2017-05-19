package awvillager.ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class GUIVillagerClientStarter {

    static FrameVillagerClient frame;

    public static void main(String[] args){

        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "VillagerClient");

        //Toolbar用
        //これが無いと何故かButtonとかFrameのデザインが変わる
        System.setProperty("apple.awt.brushMetalLook", "false");

        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {




                frame = new FrameVillagerClient();
                //frame.setIgnoreRepaint(true);
                frame.setVisible(true);
            }
        });


    }

}
