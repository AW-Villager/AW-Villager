package awvillager.ui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.aiwolf.common.data.Role;

import awvillager.loader.AgentLoader;
import awvillager.loader.AgentWorkspaceLoader;
import awvillager.loader.ComboBoxModelAgent;
import awvillager.loader.ComboBoxModelRole;
import awvillager.net.NetUtil;
import awvillager.player.IPrayerStatus;

public class ComponentAddAgent extends JPanel {

    protected TitledBorder border;

    JComboBox<ComboBoxModelDomain> domains;
    JComboBox<ComboBoxModelAgent> agents;
    List<ComboBoxModelAgent> agentList;

    JComboBox<ComboBoxModelRole> roles;

    JTextField parameter;

    protected JButton addAgentButton;

    final static private ComboBoxModelRole[] roleAry = new ComboBoxModelRole[]{
            new ComboBoxModelRole(Role.BODYGUARD,"狩人 (BODYGUARD) "),
            new ComboBoxModelRole(Role.FREEMASON,"共有者 (FREEMASON)"),
            new ComboBoxModelRole(Role.MEDIUM,"霊媒師 (MEDIUM)"),
            new ComboBoxModelRole(Role.POSSESSED,"狂人 (POSSESSED)"),
            new ComboBoxModelRole(Role.SEER,"占い師 (SEER)"),
            new ComboBoxModelRole(Role.VILLAGER,"村人 (VILLAGER)"),
            new ComboBoxModelRole(Role.WEREWOLF,"人狼 (WEREWOLF)"),
            new ComboBoxModelRole(Role.FOX,"妖狐 (FOX)")
        };

    public ComponentAddAgent() {

        this.setBackground(new Color(236, 236, 236));
        this.setBorder(new EmptyBorder(12, 12, 16, 12));

        this.setMinimumSize(new Dimension(-1, 240));
        this.setPreferredSize(new Dimension(-1, 240));
        //this.setMaximumSize(new Dimension(-1, 200));
        //this.setBackground(this.getBackground());
        //this.getBackground()
        //this.putClientProperty("apple.awt.brushMetalLook", false);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1, 1, 0, 0));
        jp.setOpaque(false);

        //Mac
        Border aquaBorder = UIManager.getBorder("TitledBorder.aquaVariant");
        if (aquaBorder == null)
            aquaBorder = new EtchedBorder();

        border = new TitledBorder(aquaBorder, "エージェントを追加");
        jp.setBorder(border);

        this.setLayout(new GridLayout(1, 1, 0, 0));


        initJComboBoxData();
        initJComboBox();


        jp.add(this.buildPanel());

        this.add(jp);

    }

    private void initJComboBoxData(){



    }

    private void initJComboBox(){

        domains = new JComboBox();

        ComboBoxModelDomain cd = new ComboBoxModelDomain("サンプル");
        cd.setAgentLoader(new AgentLoader());

        domains.addItem(cd);

        //ClassLoad
        cd.initLoader();

        //Workspace
        ComboBoxModelDomain cdW = new ComboBoxModelDomain("ワークスペース");
        cdW.setAgentLoader(new AgentWorkspaceLoader());
        cdW.initLoader();

        domains.addItem(cdW);

        //domains.addItem("Workspace");
        //domains.addItem("Hoge");

        //Domainが選択された処理
        domains.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<ComboBoxModelDomain> jcb = (JComboBox) e.getSource();
                ComboBoxModelDomain cbd = (ComboBoxModelDomain) jcb.getSelectedItem();

                agents.removeAllItems();
                for(ComboBoxModelAgent ca: cbd.getAgentList()){
                    agents.addItem(ca);
                }

                jcb.updateUI();
            }
        });

        agents = new JComboBox<>();
        agents.removeAllItems();
        for(ComboBoxModelAgent ca: cd.getAgentList()){
            agents.addItem(ca);
        }

        //Agentが選択された時
        agents.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<ComboBoxModelAgent> jcb = (JComboBox) e.getSource();
                ComboBoxModelAgent cbd = (ComboBoxModelAgent) jcb.getSelectedItem();




                if(cbd != null && cbd.getPlayerInstance() instanceof IPrayerStatus){
                    parameter.setEnabled(true);
                }else{
                    parameter.setEnabled(false);
                }

            }
        });

        /*AccessibleContext ac = domains.getAccessibleContext(); //実体はAccessibleJComboBox
        BasicComboPopup popup = (BasicComboPopup) ac.getAccessibleChild(0);

        JList list = popup.getList();
        list.setOpaque(false);*/


        roles= new JComboBox<>(roleAry);

        parameter = new JTextField();
        parameter.setEnabled(false);

    }

    private JComponent buildPanel() {

        JPanel m = new JPanel(new BorderLayout());
        m.setOpaque(false);
        m.setBorder(new EmptyBorder(12, 0, 0, 12));

        String[] labels = { "Domain: ", "Agent: ", "Rule: ","Parameter: "};
        int numPairs = labels.length;



        //作成
        JPanel p = new JPanel(new SpringLayout());
        p.setOpaque(false);
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);
            JComponent combo;
            if(i==0){
                combo = domains;
            }else if(i==1){
                combo = agents;
            }else if(i==2){
                combo = roles;
            }else if(i==3){
                combo = parameter;
            }else {
                combo = new JComboBox();
            }

            l.setLabelFor(combo);
            p.add(combo);
        }

        //SpringLayoutの設定
        makeCompactGrid(p,
                numPairs, 2, //rows, cols
                6, 6, //initX, initY
                6, 6); //xPad, yPad*/

        m.add(p, BorderLayout.CENTER);

        //追加ボタン
        JPanel addBP = new JPanel(new BorderLayout());
        addBP.setOpaque(false);
        addAgentButton = new JButton("接続");
        addAgentButton.setOpaque(false);
        addBP.setPreferredSize(new Dimension(-1, 28));
        addBP.add(addAgentButton, BorderLayout.EAST);

        addAgentButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //接続
                System.out.println("接続");
                ComboBoxModelAgent c = (ComboBoxModelAgent) agents.getSelectedItem();
                ComboBoxModelRole r = (ComboBoxModelRole) roles.getSelectedItem();

                //GUIVillagerClientStarter.frame.addMessage("接続 : " + c.getAgentClass());
                NetUtil.startClient(c.getAgentClass(), r.getRole());

            }
        });

        m.add(addBP,BorderLayout.SOUTH);

        return m;

    }



    private static SpringLayout.Constraints getConstraintsForCell(
            int row, int col,
            Container parent,
            int cols) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }

    public static void makeCompactGrid(Container parent,
            int rows, int cols,
            int initialX, int initialY,
            int xPad, int yPad) {

        SpringLayout layout;

        layout = (SpringLayout) parent.getLayout();


        //Align all cells in each column and make them the same width.
        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            for (int r = 0; r < rows; r++) {
                width = Spring.max(width,
                        getConstraintsForCell(r, c, parent, cols).getWidth());
            }
            for (int r = 0; r < rows; r++) {
                SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }

        //Align all cells in each row and make them the same height.
        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; r++) {
            Spring height = Spring.constant(0);
            for (int c = 0; c < cols; c++) {
                height = Spring.max(height,
                        getConstraintsForCell(r, c, parent, cols).getHeight());
            }
            for (int c = 0; c < cols; c++) {
                SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }

        //Set the parent's size.
        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH, y);
        pCons.setConstraint(SpringLayout.EAST, x);
    }

}
