package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to execute of the turn looking
 * around.
 */
public class LookAround implements GameController {
  private String playername;
  private String outputmessage;

  /**
   * Construct a LookAround object that has the playername.
   * 
   * @param playername name of the player
   */
  public LookAround(String playername) throws IllegalArgumentException {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    this.playername = playername;
    this.outputmessage = "";
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalArgumentException {
    if (b == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.outputmessage = b.lookAround(this.playername);
  }

  @Override
  public String getOutput() {
    return this.outputmessage;
  }
}
