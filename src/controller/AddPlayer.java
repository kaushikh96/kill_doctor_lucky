package controller;

import java.util.ArrayList;
import java.util.List;
import theworld.BoardGameModel;
import theworld.ItemImpl;

/**
 * This class is a part of command design pattern to add a human player with player details to the
 * world.
 */
public class AddPlayer implements GameController {

  private final String name;
  private final String currentRoom;
  private final int itemcapacity;
  private final boolean isComputerPlayer;
  private List<ItemImpl> playeritems;
  private String outputMessage;

  /**
   * Construct a AddHumanPlayer object that has the provided name , currentRoom,
   * itemcapacity, isComputerPlayer.
   *
   * @param name             name of the player
   * @param currentRoom      name of the space the player needs to be added.
   * @param itemcapacity     number of items the player can carry
   * @param isComputerPlayer the boolean value true if computer player else false for human player.
   */
  public AddPlayer(String name, String currentRoom, int itemcapacity, boolean isComputerPlayer) {
    if (name == null || "".equals(name.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    
    if (currentRoom == null || "".equals(currentRoom.trim())) {
      throw new IllegalArgumentException("Invalid space name");
    }
    
    if (itemcapacity < 0) {
      throw new IllegalArgumentException("Item Capacity for a player cannot be negative \n");
    }
    
    
    this.name = name;
    this.currentRoom = currentRoom;
    this.itemcapacity = itemcapacity;
    this.isComputerPlayer = isComputerPlayer;
    this.playeritems = new ArrayList<>();
    this.outputMessage = "";
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalArgumentException, IllegalStateException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    b.addPlayer(this.name, this.currentRoom, this.itemcapacity, this.playeritems,
        this.isComputerPlayer);
    this.outputMessage = String.format("Player %s has been added into the space - %s\n", this.name,
        this.currentRoom);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
