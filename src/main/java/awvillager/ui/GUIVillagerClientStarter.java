package awvillager.ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;

import awvillager.ui.util.SYSOPrintStream;

public class GUIVillagerClientStarter {



    public static FrameVillagerClient frame;

    public static void main(String[] args){

        Log.LOGGER.info("main start");

        System.setOut(new SYSOPrintStream(LogManager.getLogger("STDOUT"), System.out));

        //Log.LOGGER.info("hoge");


        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "VillagerClient");

        //Toolbar用
        //これが無いと何故かButtonとかFrameのデザインが変わる
        System.setProperty("apple.awt.brushMetalLook", "false");

        try
        {
            Log.LOGGER.info("setLookAndFeel : SystemLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.LOGGER.info("invoke : Swing");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Log.LOGGER.info("run : Swing");


                frame = new FrameVillagerClient();
                //frame.setIgnoreRepaint(true);
                Log.LOGGER.info("visible : Swing");
                frame.setVisible(true);



                //Log.LOGGER.info("FrameVillagerClient run()");
            }
        });


    }

}
