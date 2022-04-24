package controller;

/**
 * This is a BoardGameController class which is used to provide a communication with the model
 * as well as the view.
 */
public interface BoardGameController {
  /**
   * Starts the game and sends and process requests for the actions the user
   * inputs.
   */
  public void start() throws IllegalStateException;
}
