package theworldview;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.BoardGameController;
import controller.Features;

public class MouseClickEvent extends MouseAdapter {
  public final int width;
  public final int height;
  public final Features listener;
  public String Result;
  public BoardGameView view;

  /**
   * Constructor for ClickEventMouse class.
   * 
   * @param listener the TicTacToeController type.
   */
  public MouseClickEvent(Features listener, BoardGameView view) {
    height = 600;
    width = 600;
    this.listener = listener;
    this.view = view;
    this.Result = "";
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    try {
      String result = listener.handleMouseClickEvent((event.getY() - 29) / 30,
          (event.getX() - 59) / 60);
      view.setOutputMessage(result);
      view.displayGameScreen();
    } catch (IllegalStateException ise) {
      view.setOutputMessage(ise.getMessage());
      view.displayGameScreen();
    }

  }
}
