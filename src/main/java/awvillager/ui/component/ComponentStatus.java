package awvillager.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ComponentStatus extends JPanel{

    private final Font FONT = new JLabel().getFont();

    JLabel label;

    public ComponentStatus(){
        this.setPreferredSize(new Dimension(-1, 26));

        //this.setBackground(new Color(236, 236, 236));

        //this.setBackground(null);
        Border lineB = BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(169, 169, 169));
        LineBorder border = new LineBorder(Color.red, 2, true);
        this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 0, 0, 0), lineB));

        FlowLayout footerlayout = new FlowLayout(); //左詰め
        footerlayout.setAlignment(FlowLayout.LEFT);
        this.setLayout(footerlayout);

        label = new JLabel("起動しました");
        //label.setPreferredSize(new Dimension(-1, 20));
        //label.setBorder(new EmptyBorder(2,10,2,10));
        Font f = new Font(FONT.getFontName(),Font.PLAIN,12);
        label.setFont(f);
        this.add(label);

    }

}
