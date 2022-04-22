package theworldview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JOptionPane;

public class ButtonListener implements ActionListener {

  //private BoardGameView boardgameviewimpl;
  private Map<String, Runnable> buttonClickedActions;

  public ButtonListener() {
    
    this.buttonClickedActions = null;
  }

//  public ButtonListener(BoardGameView view) {
//    this.boardgameviewimpl = view;
//  }

//  @Override
//  public void actionPerformed(ActionEvent e) {
//    if (e == null) {
//      throw new IllegalArgumentException("Action Event cannot be null.\n");
//    }
//    if ("START".equals(e.getActionCommand())) {
//      boardgameviewimpl.displayWorldSelectionScreen();
//    } else if ("Current World".equals(e.getActionCommand())) {
//      boardgameviewimpl.displayAddPlayerScreen();
//    } else if ("New World".equals(e.getActionCommand())) {
//      String filename = JOptionPane.showInputDialog("Enter the file name", null);
//    } else if ("Quit".equals(e.getActionCommand())) {
//      boardgameviewimpl.closeWindow();
//
//    }
//  }
  
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
