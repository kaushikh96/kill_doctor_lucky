package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to get the turn of the next
 * player in the order.
 */
public class GetPlayerTurn implements GameController {
  private final String currentplayername;
  private String outputMessage;

  /**
   * Construct a GetPlayerTurn object that has the current player name.
   * 
   *
   * @param currentplayername name of the player whose current turn
   */
  public GetPlayerTurn(String currentplayername) throws IllegalArgumentException {
    if (currentplayername == null || "".equals(currentplayername.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    this.currentplayername = currentplayername;
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalArgumentException {
    if (b == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.outputMessage = b.getPlayerNextTurn(this.currentplayername);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
