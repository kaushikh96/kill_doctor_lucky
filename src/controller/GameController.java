package controller;

import theworld.BoardGameModel;

/**
 * This GameController is an interface has the execute method which has to be
 * overriden for controller related operations.
 */
public interface GameController {
  /**
   * Execute a game of Kill Doctor Lucky using BoardGameFacade Model. When the
   * turns reaches the maximum the execute method ends.
   *
   * @param b a non null BoardGameFacade Model
   */
  public void execute(BoardGameModel b);

  /**
   * Gets the message we get after the respective command gets executed.
   *
   * @return the message after execution of the command
   */
  public String getOutput();
}
