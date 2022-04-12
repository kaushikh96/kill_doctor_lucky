package theworld;

import java.util.List;

/**
 * A Player Interface represents the data of the player and contains the methods
 * to related to the Player operation.
 */
public interface PlayerInterface {

  /**
   * Moves the player to the new space.
   * 
   * @param neighbours      list of visible spaces
   * @param roomtobemovedto room name
   * @return the room name the player has moved to
   */
  public String movePlayer(List<SpaceImpl> neighbours, String roomtobemovedto)
      throws IllegalStateException;

  /**
   * Method for player to look around.
   * 
   * @param playercurrentroom current room of the player
   * @param neighbours        neighbours and its details as a string
   * @return current and visible spaces information
   */
  public String lookAround(String playercurrentroom, String neighbours);

  /**
   * Method for picking up an item from the room.
   * 
   * @param itemname name of the item the player has to pick up
   */
  public void pickItem(String itemname) throws IllegalStateException;

  /**
   * Method to determine whether playerA can see playerB.
   * 
   * @param playerB    name of the first player to whom the visibility is
   *                   determined
   * @param neighbours list of neighbours
   * @param petRoom    current space of the pet
   * 
   * @return the boolean value if player can see playerB
   */
  public boolean ifPlayerSeen(PlayerImpl playerB, List<SpaceImpl> neighbours, SpaceImpl petRoom);

  /**
   * Gets the player name.
   * 
   * @return name of the player
   */
  public String getName();

  /**
   * Gets the item capacity of the character.
   * 
   * @return get item capacity
   */
  public int getItemCapacity();

  /**
   * Gets the boolean value if the current player is a Computer Player.
   * 
   * @return the value whether the player is a Computer player
   */
  public boolean isComputerPlayer();

  /**
   * Gets the current room of the player.
   * 
   * @return current room of the player
   */
  public SpaceInterface getCurrentRoom();

  /**
   * Sets the current room of the player.
   * 
   * @param currentRoom current player room
   */
  public void setCurrentRoom(SpaceImpl currentRoom);

  /**
   * Gets the list of items on the player.
   * 
   * @return list of items on the player.
   */
  public List<ItemImpl> getItems();

}
