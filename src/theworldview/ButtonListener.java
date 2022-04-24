package theworldview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * This method listens to button click events and performs actions according to the 
 * respective button click.
 */
public class ButtonListener implements ActionListener {

  private Map<String, Runnable> buttonClickedActions;

  public ButtonListener() {
    this.buttonClickedActions = null;
  }

  /**
   * This method sets the map with buttons strings and the actions to be perfromed 
   * in the form of Runnable interface.
   *
   * @param map the map that consists of the Strings and actions.
   */
  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    
    if (map == null) {
      throw new IllegalArgumentException("The ButtionClicked map is null\n");
    }
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    
    if (e == null) {
      throw new IllegalArgumentException("Event cannot be null\n");
    }
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }

}
