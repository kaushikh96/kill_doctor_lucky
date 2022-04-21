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
    if (e == null) {
      throw new IllegalArgumentException("Action Event cannot be null.\n");
    }
    if ("START".equals(e.getActionCommand())) {
      boardgameviewimpl.displayWorldSelectionScreen();
    } else if ("Current World".equals(e.getActionCommand())) {
      boardgameviewimpl.displayAddPlayerScreen();
    } else if ("New World".equals(e.getActionCommand())) {
      String filename = JOptionPane.showInputDialog("Enter the file name", null);
    } else if ("Quit".equals(e.getActionCommand())) {
      boardgameviewimpl.closeWindow();

    }
  }
}
