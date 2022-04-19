package theworldview;

import controller.BoardGameController;

public interface BoardGameView {

  /**
   * Set up the controller to handle click events in this view.
   * 
   * @param listener the controller
   */
  void addClickListener(BoardGameController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  public void displayAddPlayerScreen();

  public void displayGameScreen();

}
