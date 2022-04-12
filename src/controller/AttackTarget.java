package controller;

import theworld.BoardGameFacade;

/**
 * This class is a part of command design pattern to attack the target
 * character.
 */
public class AttackTarget implements GameController {

  private String playername;
  private String itemname;
  private String outputMessage;

  /**
   * Construct a AttackTarget object that has the playername, itemname.
   * 
   * @param playername name of the player
   * @param itemname   name of the item
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
  public void execute(BoardGameFacade b) throws IllegalStateException {
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
