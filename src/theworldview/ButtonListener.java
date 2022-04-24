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

  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }

}
