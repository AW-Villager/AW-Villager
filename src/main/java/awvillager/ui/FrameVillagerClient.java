package awvillager.ui;

import javax.swing.JFrame;

public class FrameVillagerClient extends JFrame {


    public FrameVillagerClient(){
        super();
        this.setTitle("VillagerClient");
        this.setSize(800, 640);

        //真ん中に
        this.setLocationRelativeTo(null);

        //終了時の処理
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
