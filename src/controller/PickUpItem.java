package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to pick up an item from the
 * space.
 */
public class PickUpItem implements GameController {

  private final String playername;
  private final String itemname;
  private String outputMessage;

  /**
   * Constructor of a PickupItem command class that initializes the 
   * name of the player and the name of the item to be picked.
   *
   * @param playername name of the current player
   * @param itemname   name of the item selected to pick.
   */
  public PickUpItem(String playername, String itemname) {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    if (itemname == null || "".equals(itemname.trim())) {
      throw new IllegalArgumentException("Invalid item name");
    }
    this.playername = playername;
    this.itemname = itemname;
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalStateException {
    if (b == null) {
      throw new IllegalStateException("model cannot be null");
    }
    this.outputMessage = b.pickItem(playername, itemname);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
