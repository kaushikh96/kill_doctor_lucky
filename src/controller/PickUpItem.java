package controller;

import theworld.BoardGameFacade;

/**
 * This class is a part of command design pattern to pick up an item from the
 * space.
 */
public class PickUpItem implements GameController {

  private String playername;
  private String itemname;
  private String outputMessage;

  /**
   * Constructor of a PickupItem command class.
   * 
   * @param playername name of the player
   * @param itemname   name of the item
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
  public void execute(BoardGameFacade b) throws IllegalStateException {
    if (b == null) {
      throw new IllegalStateException("model cannot be null");
    }
    String room = b.pickItem(playername, itemname);
    this.outputMessage = String.format(
        "Item picked up by %s and removed from Space\nTarget Character Current Room: %s\n",
        playername, room);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
