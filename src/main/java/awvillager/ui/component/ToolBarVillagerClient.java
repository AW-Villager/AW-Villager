package awvillager.ui.component;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBarVillagerClient extends JToolBar{

    public JButton index;
    public JButton log;

    public JButton configuration;

    public ToolBarVillagerClient(){
        super();
        //this.putClientProperty("apple.awt.brushMetalLook", true);
        this.setFloatable(false);

        index = new JButton("操作パネル");
        log = new JButton("ログ");

        configuration = new JButton("設定");
        //ButtonGroup buttons = new ButtonGroup();
        //buttons.add(index);
        //buttons.add(log);

        //this.add(UtilMac.createLayoutComponent(UtilMac.createSegmentedTexturedButtons(2, buttons)));

        configuration.putClientProperty("JButton.buttonType", "segmentedTextured");
        configuration.putClientProperty("JButton.segmentPosition", "only");
        //configuration.putClientProperty("JComponent.sizeVariant", "mini");

        ButtonGroup bg = new ButtonGroup();
        bg.add(index);
        bg.add(log);

        index.putClientProperty("JButton.buttonType", "segmentedTextured");
        log.putClientProperty("JButton.buttonType", "segmentedTextured");
        index.putClientProperty("JButton.segmentPosition", "first");
        log.putClientProperty("JButton.segmentPosition", "last");


        this.add(index);
        this.add(log);

        this.add(configuration);


        this.setVisible(true);

    }

}
