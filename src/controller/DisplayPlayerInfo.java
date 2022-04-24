package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to display player information.
 */
public class DisplayPlayerInfo implements GameController {

  private final String playername;
  private String outputMessage;

  /**
   * Construct a DisplayPlayerInfo object that initializes the player name for which
   * the player information needs to be displayes.
   *
   * @param playername the name of the player for displayer information.
   */
  public DisplayPlayerInfo(String playername) {
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
    this.outputMessage = b.getPlayerInfo(this.playername);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
