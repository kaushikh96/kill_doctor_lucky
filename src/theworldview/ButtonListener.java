package theworldview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

  private BoardGameView boardgameviewimpl;

  public ButtonListener(String caption, BoardGameView view) {
    this.boardgameviewimpl = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e == null) {
      throw new IllegalArgumentException("Action Event cannot be null.\n");
    }
    if ("START".equals(e.getActionCommand())) {
      boardgameviewimpl.displayAddPlayerScreen();
    } else if ("CONTINUE".equals(e.getActionCommand())) {
      boardgameviewimpl.displayGameScreen();
    }
  }
}
