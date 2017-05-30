package awvillager.ui.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;

public class UtilMac {

 // Create a Layout component that will ensure the buttons abut each other
    public static JComponent createLayoutComponent(List<JButton> segmentButtons) {
      Box layoutBox = Box.createHorizontalBox();
      for(JButton button : segmentButtons) {
        layoutBox.add(button);
      }
      return layoutBox;
    }

    public static JButton createSegmentButton(String style, String position, ButtonGroup buttonGrp) {
      JButton button = new JButton();
      button.putClientProperty("JButton.buttonType", style);
      button.putClientProperty("JButton.segmentPosition", position);
      buttonGrp.add(button);
      return button;
    }

    // Bottleneck for creating the buttons for the button group
    public static List<JButton> createSegmentButtonsWithStyle(int numButtons, ButtonGroup buttonGrp, String style){
      // Allocate a list of JButtons
      List<JButton> buttons = new ArrayList<JButton>();
      if(numButtons == 1) {
        // If 1 button is requested, then it gets the "only" segment position
        buttons.add(createSegmentButton(style, "only", buttonGrp));
      } else {
        // If more than 1 button is requested, then
        // the first one gets "first" the last one gets "last" and the rest get "middle"
        buttons.add(createSegmentButton(style, "first", buttonGrp));
        for(int i = 0; i < buttons.size() - 2; ++i) {
          buttons.add(createSegmentButton(style, "middle", buttonGrp));
        }
        buttons.add(createSegmentButton(style, "last", buttonGrp));
      }
      return buttons;
    }

    // Convenience methods that pass in the correct button style for each segmented button style
    public static List<JButton> createSegmentedButtons(int numButtons, ButtonGroup buttonGroup) {
      return createSegmentButtonsWithStyle(numButtons, buttonGroup, "segmented");
    }

    public static List<JButton> createSegmentedRoundRectButtons(int numButtons, ButtonGroup buttonGroup) {
      return createSegmentButtonsWithStyle(numButtons, buttonGroup, "segmentedRoundRect");
    }

    public static List<JButton> createSegmentedCapsuleButtons(int numButtons, ButtonGroup buttonGroup) {
      return createSegmentButtonsWithStyle(numButtons, buttonGroup, "segmentedCapsule");
    }

    public static List<JButton> createSegmentedTexturedButtons(int numButtons, ButtonGroup buttonGroup) {
      return createSegmentButtonsWithStyle(numButtons, buttonGroup, "segmentedTextured");
    }

}
