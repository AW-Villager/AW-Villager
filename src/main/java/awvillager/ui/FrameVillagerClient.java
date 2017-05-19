package awvillager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import awvillager.ui.component.ComponentAddAgent;
import awvillager.ui.component.ComponentConnectAgentList;
import awvillager.ui.component.ComponentStatus;
import awvillager.ui.component.ToolBarVillagerClient;

public class FrameVillagerClient extends JFrame {

    JPanel containerPanel;
    JPanel mainPanel;

    ComponentAddAgent componentAddAgent;
    ComponentConnectAgentList componentAgentList;
    ComponentStatus componentStatus;

    public FrameVillagerClient(){
        super();
        this.setTitle("VillagerClient");
        this.setSize(800, 640);

        //this.getRootPane().setBorder(new EmptyBorder(0, 0, 8, 0));



        this.getRootPane().putClientProperty("apple.awt.brushMetalLook", true);
        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", true);

        //this.setBackground(new Color(236, 236, 236));




        //this.setIgnoreRepaint(true);
        /*this.setUndecorated(true);
        this.addComponentListener(new ComponentAdapter() {
            @Override
             public void componentResized(ComponentEvent e) {
                 setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
             }
         });*/


        //真ん中に
        this.setLocationRelativeTo(null);

        //終了時の処理
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //toolbarの追加
        ToolBarVillagerClient toolbar = new ToolBarVillagerClient();
        //DragListener drag = new DragListener(this,toolbar);
        //toolbar.addMouseListener( drag );
        //toolbar.addMouseMotionListener( drag );
        //toolbar.putClientProperty("apple.awt.draggableWindowBackground", true);
        this.add(toolbar, BorderLayout.NORTH);


        //真ん中の部分
        containerPanel= new JPanel();
        containerPanel.setLayout(new GridLayout(1, 1, 0, 0));
        containerPanel.setBackground(new Color(236, 236, 236));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(179, 177, 179)));

        containerPanel.add(mainPanel);

        //componentを追加していく
        componentAddAgent = new ComponentAddAgent();
        mainPanel.add(componentAddAgent);

        //mainPanel.add(new JPanel());

        /*JSeparator sp = new JSeparator(JSeparator.HORIZONTAL);
        mainPanel.add(sp);
        Dimension d = sp.getPreferredSize();
        d.width = sp.getMaximumSize().width;
        sp.setMaximumSize( d );*/


        componentAgentList = new ComponentConnectAgentList();
        mainPanel.add(componentAgentList);

        //JTabbedPane tabs = new JTabbedPane( );
        //tabs.addTab( "Main", mainPanel);
        //tabs.addTab( "Log", new JPanel() );
        //add(tabs, BorderLayout.CENTER);

        add(containerPanel, BorderLayout.CENTER);
        //setLayout(new BorderLayout());
        //add(new PaintPane());

        componentStatus = new ComponentStatus();
        add(componentStatus, BorderLayout.SOUTH);

        //Focusを外す
        this.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
            }
        });

    }



    /*
    @Override
    public void paint(Graphics g){


        if(!isMac()){
            super.paint(g);
            return;
        }

        this.setBackground(new Color(236, 236, 236));

        if (!isOpaque()) {
            Graphics gg = g.create();
            try {
                if (gg instanceof Graphics2D) {

                    gg.setColor(getBackground());
                    ((Graphics2D)gg).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
                    gg.drawRoundRect(0, 0, getWidth(), getHeight(),4,4);
                }
            } finally {
                gg.dispose();
            }
        }
        this.setBackground(null);
        super.paint(g);

    }*/

    protected static boolean isMac() {
        String lcOSName = System.getProperty("os.name").toLowerCase();
        return lcOSName.startsWith("mac os x");
    }

}
