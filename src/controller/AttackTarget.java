package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to make an attempt to attack the target
 * character.
 */
public class AttackTarget implements GameController {

  private final String playername;
  private final String itemname;
  private String outputMessage;

  /**
   * Construct a AttackTarget object that has the name of the player and name of the item.
   *
   * @param playername the name of the current player
   * @param itemname the name of the item
   */
  public AttackTarget(String playername, String itemname) {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    if (itemname == null || "".equals(itemname.trim())) {
      throw new IllegalArgumentException("Invalid space name");
    }
    this.playername = playername;
    this.itemname = itemname;
    this.outputMessage = "";
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalStateException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.outputMessage = b.attackTarget(playername, itemname);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
