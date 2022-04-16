package controller;

/**
 * This is a GameConsoleController class used to handle the controller related
 * operations.
 */
public interface BoardGameController {
  /**
   * Starts the game and sends and process requests for the actions the user
   * inputs.
   * 
   * @param boardgameimpl the boardgame facade interface type
   */
  public void start() throws IllegalStateException;
}
