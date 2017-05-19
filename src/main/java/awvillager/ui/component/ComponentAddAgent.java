package awvillager.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ComponentAddAgent extends JPanel{

    protected TitledBorder border;

    public ComponentAddAgent(){

        this.setBackground(new Color(236, 236, 236));
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(179, 177, 179)));


        this.setMinimumSize(new Dimension(-1, 180));
        this.setPreferredSize(new Dimension(-1, 180));
        //this.setMaximumSize(new Dimension(-1, 200));
        //this.setBackground(this.getBackground());
        //this.getBackground()
        //this.putClientProperty("apple.awt.brushMetalLook", false);

        //Mac
        Border aquaBorder = UIManager.getBorder( "TitledBorder.aquaVariant" );
        if ( aquaBorder == null )aquaBorder = new EtchedBorder( );

        border =new TitledBorder(aquaBorder,"エージェントを追加");
        this.setBorder(border);


        this.setLayout(new GridLayout(1, 1, 0, 0));

    }

}
