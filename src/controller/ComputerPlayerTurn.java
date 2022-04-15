package controller;

import driver.RandomClass;
import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to add a computer player to
 * the world.
 */
public class ComputerPlayerTurn implements GameController {
  private String playername;
  private RandomClass randomref;
  private String outputMessage;

  /**
   * Construct a ComputerPlayerTurn object that has the playername,randomref.
   * 
   *
   * @param playername name of the player
   * @param randomref  random class reference
   * 
   */
  public ComputerPlayerTurn(String playername) {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    if (randomref == null) {
      throw new IllegalArgumentException("Invalid random variable");
    }
    this.playername = playername;
    this.randomref = randomref;
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
