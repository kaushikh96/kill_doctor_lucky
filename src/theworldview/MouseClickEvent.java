package theworldview;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.BoardGameController;

public class MouseClickEvent extends MouseAdapter{
  public final int width;
  public final int height;
  public final BoardGameController listener;

  /**
   * Constructor for ClickEventMouse class.
   * 
   * @param listener the TicTacToeController type.
   */
  public MouseClickEvent(BoardGameController listener) {
    height = 600;
    width = 600;
    this.listener = listener;
  }

  @Override
  public void mouseClicked(MouseEvent event) {
//    listener.handleCellClick(event.getX() / 200, event.getY() / 200);
  }
}
