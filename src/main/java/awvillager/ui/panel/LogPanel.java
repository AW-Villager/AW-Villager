package awvillager.ui.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.aiwolf.common.data.Player;

import awvillager.ui.util.JTextAreaAppender;

public class LogPanel extends JPanel{

    JTabbedPane tabbedpane;

    JTextArea logText;

    public LogPanel(){

        this.setLayout(new GridLayout(1, 1, 0, 0));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(179, 177, 179)),
                new EmptyBorder(8, 12, 16, 12)
                ));

        this.setBackground(new Color(236, 236, 236));

        tabbedpane = new JTabbedPane();

        JPanel jp = new JPanel();
        jp.setOpaque(false);
        jp.setLayout(new GridLayout(1, 1, 0, 0));
        jp.setBorder(new EmptyBorder(16, 20, 20, 20));
        logText = new JTextArea();
        ScrollPane sp = new ScrollPane();
        jp.add(sp);
        sp.add(logText);

        JTextAreaAppender.addTextArea(logText);
        //TextAreaOutputStream op = new TextAreaOutputStream(logText);
        /*try {
            System.setOut(new PrintStream(op,true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        tabbedpane.addTab("Main log", jp);
        //tabbedpane.addTab("Agent1", new JButton("button2"));
        //tabbedpane.addTab("Agent2", new JButton("button3"));
        //tabbedpane.addTab("Agent3", new JButton("button4"));


        this.add(tabbedpane);

    }

    public JTextArea addAgent(Class<? extends Player> playerClass){

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1, 1, 0, 0));
        jp.setOpaque(false);
        JTextArea area =new JTextArea();
        ScrollPane sp = new ScrollPane();
        jp.setBorder(new EmptyBorder(16, 20, 20, 20));
        jp.add(sp);
        sp.add(area);
        tabbedpane.addTab(playerClass.getSimpleName(), jp);

        return area;

    }

}
