package awvillager.ui.component;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import awvillager.ui.parts.ZebraJList;

public class ComponentConnectAgentList  extends JPanel{

    protected JPanel panel;

    protected TitledBorder border;

    public ComponentConnectAgentList(){

        //this.setLayout(new BorderLayout());

        //Mac
        //Border aquaBorder = UIManager.getBorder( "TitledBorder.aquaVariant" );
        //if ( aquaBorder == null );
        //aquaBorder = new EtchedBorder( );

        //border =new TitledBorder(aquaBorder,"エージェントリスト");
        //this.setBorder(border);

        //this.setBorder(new LineBorder(Color.BLACK));

        //add(BorderLayout.NORTH,new JSeparator(JSeparator.HORIZONTAL));

        /*
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 0, 0));
        add(BorderLayout.CENTER,panel);*/
        this.setLayout(new GridLayout(1, 1, 0, 0));

        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(169, 169, 169)));

        //JSeparator sp = new JSeparator(JSeparator.HORIZONTAL);
        //this.add(sp);

        ZebraJList<String> zl =new ZebraJList<String>(new String[]{"aa","bb","ss","ss","ee","ss","ss","ss","ss","ss","ss","ss"});
        zl.setVisibleRowCount(20);

        /*zl.setSelectionModel(new DefaultListSelectionModel() {
            @Override public boolean isSelectedIndex(int index) {
              return false;
            }
          });*/
        //zl.setFixedCellHeight( 20 );
        JScrollPane scrollList = new JScrollPane( zl );
        scrollList.setBorder(BorderFactory.createEmptyBorder());
        scrollList.setOpaque(false);
        scrollList.getViewport().setOpaque(false);
        scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //zl.setMaximumSize(new Dimension(10000, 10000));
        //zl.setFixedCellWidth(800);
        //scrollList.setMaximumSize(new Dimension(10000, 10000));
        //zl.setVisibleRowCount(20);
        //mainPanel.add(zl);
        this.add(scrollList);

    }

}
