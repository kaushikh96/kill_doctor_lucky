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

  /**
   * Constructor for ClickEventMouse class.
   * 
   * @param listener the TicTacToeController type.
   */
  public MouseClickEvent(Features listener) {
    height = 600;
    width = 600;
    this.listener = listener;
    this.Result = "";
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    String result = listener.handleMouseClickEvent(event.getX() / 30, event.getY() / 60);
  }
}
