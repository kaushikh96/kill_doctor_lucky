package theworldview;

import controller.Features;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This method handles the mouse click event for performing the actions
 * accordingly.
 *
 */
public class MouseClickEvent extends MouseAdapter {
  public final int width;
  public final int height;
  public Features listener;
  public String result;

  /**
   * Constructor for ClickEventMouse class the initializes the features object for
   * set a callbacks from view and also initializes the BoardGameView.
   *
   * @param listener the features object for the set of callbacks from the view.
   */
  public MouseClickEvent(Features listener) {

    if (listener == null) {
      throw new IllegalArgumentException("Features object cannot be null\n");
    }
    height = 600;
    width = 600;
    this.listener = listener;
    this.result = "";
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    listener.handleMouseClickEvent((event.getY() - 29) / 30, (event.getX() - 59) / 60);
  }
}
