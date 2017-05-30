package awvillager.ui.panel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import awvillager.ui.Log;
import awvillager.ui.component.ComponentAddAgent;
import awvillager.ui.component.ComponentConnectAgentList;

public class MainPanel extends JPanel{

    public ComponentAddAgent componentAddAgent;
    public ComponentConnectAgentList componentAgentList;

    public MainPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(179, 177, 179)));

      //componentを追加していく
        Log.LOGGER.info("init : MainPanel : ComponentAddAgent");
        componentAddAgent = new ComponentAddAgent();
        this.add(componentAddAgent);


        Log.LOGGER.info("init : MainPanel : ComponentConnectAgentList");
        componentAgentList = new ComponentConnectAgentList();
        this.add(componentAgentList);

    }

}
