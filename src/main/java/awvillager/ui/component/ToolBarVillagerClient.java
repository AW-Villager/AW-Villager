package awvillager.ui.component;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;

import awvillager.ui.FrameVillagerClient;

public class ToolBarVillagerClient extends JToolBar implements javax.swing.event.ChangeListener{

    private FrameVillagerClient frame;

    public JButton index;
    public JButton log;

    public JButton configuration;

    public ToolBarVillagerClient( FrameVillagerClient f){
        super();
        frame = f;
        //this.putClientProperty("apple.awt.brushMetalLook", true);
        this.setFloatable(false);

        index = new JButton("操作パネル");
        index.setSelected(true);
        index.setForeground(Color.WHITE);
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

        index.addChangeListener(this);
        log.addChangeListener(this);

        index.putClientProperty("JButton.buttonType", "segmentedTextured");
        log.putClientProperty("JButton.buttonType", "segmentedTextured");
        index.putClientProperty("JButton.segmentPosition", "first");
        log.putClientProperty("JButton.segmentPosition", "last");

        index.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

              JButton cb = (JButton)e.getSource();
              if (cb.isSelected())return;
              cb.setSelected(true);
              log.setSelected(false);
            }
          }
          );

        log.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

              JButton cb = (JButton)e.getSource();
              if (cb.isSelected())return;
              cb.setSelected(true);
              index.setSelected(false);
            }
          }
          );

        this.add(index);
        this.add(log);

        this.add(configuration);


        this.setVisible(true);

    }

    @Override
    public void stateChanged(ChangeEvent e) {

        //Log.LOGGER.info("hoge");
      JButton cb = (JButton)e.getSource();

      if (cb == index && index.isSelected()) {
          cb.setForeground(Color.WHITE);
          log.setForeground(Color.BLACK);

          frame.changeMainPanel(frame.mainPanel);


      } else if (cb == log && log.isSelected()) {
          cb.setForeground(Color.WHITE);
          index.setForeground(Color.BLACK);
          frame.changeMainPanel(frame.logPanel);
      }
    }


}
