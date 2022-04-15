package controller;

import theworld.BoardGameFacade;
import theworld.BoardGameModel;
import theworldview.BoardGameView;

/**
 * This is a GameConsoleController class used to handle the controller related
 * operations.
 */
public class BoardGameController {
  private final BoardGameView view;
  private final BoardGameModel model;

  /**
   * Constructor for the controller.
   * 
   * @param view  the BoardGameView interface
   * @param model the BoardGameModel interface
   */
  public BoardGameController(BoardGameView view, BoardGameModel model) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("BoardGame model or BoardGameView can't be null");
    }
    this.view = view;
    this.model = model;
  }

  /**
   * Starts the game and sends and process requests for the actions the user
   * inputs.
   * 
   * @param boardgameimpl the boardgame facade interface type
   */
  public void start() throws IllegalStateException {
    // implementation for starting the game
  }
}
