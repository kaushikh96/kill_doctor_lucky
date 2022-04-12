package controller;

import theworld.BoardGameFacade;

/**
 * This class is a part of command design pattern to display player information.
 */
public class DisplayPlayerInfo implements GameController {

  private String playername;
  private String outputMessage;

  /**
   * Construct a DisplayPlayerInfo object that has the playername.
   * 
   * @param playername name of the player
   */
  public DisplayPlayerInfo(String playername) {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    this.playername = playername;
  }

  @Override
  public void execute(BoardGameFacade b) throws IllegalArgumentException, IllegalStateException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.outputMessage = b.getPlayerInfo(this.playername);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
