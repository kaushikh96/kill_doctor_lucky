package controller;

import java.util.ArrayList;
import java.util.List;
import theworld.BoardGameModel;
import theworld.ItemImpl;

/**
 * This class is a part of command design pattern to add a human player to the
 * world.
 */
public class AddPlayer implements GameController {

  private String name;
  private String currentRoom;
  private int itemcapacity;
  private boolean isComputerPlayer;
  private List<ItemImpl> playeritems;
  private String outputMessage;

  /**
   * Construct a AddHumanPlayer object that has the provided name , currentRoom,
   * itemcapacity, isComputerPlayer.
   * 
   *
   * @param name             target character name
   * @param currentRoom      name of the world
   * @param itemcapacity     list of spaces
   * @param isComputerPlayer world coordinates
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
