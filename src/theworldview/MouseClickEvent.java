package theworldview;

import controller.Features;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This method handles the mouse click event for performing the 
 * actions accordingly.
 *
 */
public class MouseClickEvent extends MouseAdapter {
  public final int width;
  public final int height;
  public Features listener;
  public String result;
  public BoardGameView view;

  /**
   * Constructor for ClickEventMouse class the initializes the features object
   * for set a callbacks from view and also initializes the BoardGameView.
   *
   * @param listener the features object for the set of callbacks from the view.
   * @param view the BoardGameView to get the functionality from view panel.
   */
  public MouseClickEvent(Features listener, BoardGameView view) {
    
    if (listener == null) {
      throw new IllegalArgumentException("Features object cannot be null\n");
    }
    
    if (view == null) {
      throw new IllegalArgumentException("The View object cannot be null\n");
    }
    height = 600;
    width = 600;
    this.listener = listener;
    this.view = view;
    this.result = "";
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    try {
      String result = listener.handleMouseClickEvent((event.getY() - 29) / 30,
          (event.getX() - 59) / 60);
      view.setOutputMessage(result);
      view.setIfTurnExecuted(true);
      view.displayGameScreen();
    } catch (IllegalStateException ise) {
      view.setOutputMessage(ise.getMessage());
      view.setIfTurnExecuted(false);
      view.displayGameScreen();

    }

  }
}
