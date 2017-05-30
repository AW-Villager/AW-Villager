package awvillager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import awvillager.ui.component.ComponentStatus;
import awvillager.ui.component.ToolBarVillagerClient;
import awvillager.ui.panel.LogPanel;
import awvillager.ui.panel.MainPanel;
import awvillager.ui.util.DragListener;

public class FrameVillagerClient extends JFrame {

    JPanel containerPanel;

    //Mainパネル
    public MainPanel mainPanel;

    public LogPanel logPanel;

    JPanel currentPanel;

    ComponentStatus componentStatus;

    public FrameVillagerClient(){
        super();
        this.setTitle("VillagerClient");
        this.setSize(600, 680);

        //this.getRootPane().setBorder(new EmptyBorder(0, 0, 8, 0));

        this.getRootPane().putClientProperty("apple.awt.brushMetalLook", true);
        //this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", true);

        this.getRootPane().setOpaque(false);

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
        Log.LOGGER.info("init : Toolbar");
        ToolBarVillagerClient toolbar = new ToolBarVillagerClient(this);
        DragListener drag = new DragListener(this,toolbar);
        toolbar.addMouseListener( drag );
        toolbar.addMouseMotionListener( drag );
        //toolbar.putClientProperty("apple.awt.draggableWindowBackground", true);
        this.add(toolbar, BorderLayout.NORTH);


        //真ん中の部分
        containerPanel= new JPanel();
        containerPanel.setLayout(new GridLayout(1, 1, 0, 0));
        containerPanel.setBackground(new Color(236, 236, 236));

        //Mainパネル
        Log.LOGGER.info("init : MainPanel");
        mainPanel = new MainPanel();

        Log.LOGGER.info("init : LogPanel");
        logPanel = new LogPanel();

        currentPanel = mainPanel;
        containerPanel.add(mainPanel);

        add(containerPanel, BorderLayout.CENTER);
        //setLayout(new BorderLayout());
        //add(new PaintPane());

        Log.LOGGER.info("init : ComponentStatus");
        componentStatus = new ComponentStatus();
        add(componentStatus, BorderLayout.SOUTH);

        //Focusを外す
        this.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
            }
        });

    }


    public void addMessage(String m){
        this.mainPanel.componentAgentList.addMessage(m);
    }

    public void changeMainPanel(JPanel setPanel){


        if(setPanel == logPanel){
            containerPanel.remove(currentPanel);
            currentPanel = logPanel;
            containerPanel.add(currentPanel);
            //containerPanel.repaint();
            printAll(getGraphics());

        }else if(setPanel == mainPanel){
            containerPanel.remove(currentPanel);
            currentPanel = mainPanel;
            containerPanel.add(currentPanel);
            //containerPanel.repaint();
            printAll(getGraphics());

        }

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
