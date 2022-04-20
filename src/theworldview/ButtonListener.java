package theworldview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ButtonListener implements ActionListener {

  private BoardGameView boardgameviewimpl;

  public ButtonListener(BoardGameView view) {
    this.boardgameviewimpl = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if ("START".equals(e.getActionCommand())) {
      boardgameviewimpl.displayWorldSelectionScreen();
    } else if ("CurrentWorld".equals(e.getActionCommand())) {
      boardgameviewimpl.displayAddPlayerScreen();
    } else if ("CONTINUE".equals(e.getActionCommand())) {
      boardgameviewimpl.displayGameScreen();
    } else if ("NewWorld".equals(e.getActionCommand())) {
      
      String filename = JOptionPane.showInputDialog("Enter the file name", null);
          
    }
  }
}
