package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to add a computer player to
 * the world.
 */
public class ComputerPlayerTurn implements GameController {
  private final String playername;
  private String outputMessage;

  /**
   * Construct a ComputerPlayerTurn object that has the playername.
   * 
   *
   * @param playername name of the player
   * 
   */
  public ComputerPlayerTurn(String playername) {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    this.playername = playername;
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalArgumentException, IllegalStateException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.outputMessage = b.playTurnComputerPlayer(playername);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
