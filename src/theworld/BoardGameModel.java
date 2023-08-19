package theworld;

import java.util.List;

/**
 * A Board Game Facade Interface represents the data of the world and contains
 * the list of methods to related to the World operation. All these methods
 * would be called directly from the controller.
 */
public interface BoardGameModel {

  /**
   * Adds a player to the Board game.
   *
   * @param name             target character name
   * @param itemcapacity     list of spaces
   * @param isComputerPlayer world coordinates
   * @param playeritems      list of items on the player
   * @param currentRoom      current room of the player
   */
  public void addPlayer(String name, String currentRoom, int itemcapacity,
      List<ItemImpl> playeritems, boolean isComputerPlayer);

  /**
   * Moves a player to a space based on the coordinates.
   *
   * @param xcoordinate the xcoordinate of the space the player needs to be move to.
   * @param ycoordinate the ycoordinate of the space the player needs to be move to.
   * 
   * @return the result of the action performed.
   */
  public String movePlayer(int xcoordinate, int ycoordinate) throws IllegalStateException;

  /**
   * Allows a player to pick an item from the space.
   *
   * @param playername name of the player
   * @param itemname   name of the item on the player
   * 
   * @return the information about the action performed.
   */
  public String pickItem(String playername, String itemname) throws IllegalStateException;

  /**
   * Allows a player to look around in the space.
   *
   * @param playername name of the player
   * 
   * @return information about current room of the player and its neighbours
   */
  public String lookAround(String playername) throws IllegalStateException;

  /**
   * Get the player information.
   *
   * @param playername name of the player
   * 
   * @return information about the player
   */
  public String getPlayerInfo(String playername) throws IllegalStateException;

  /**
   * Gets the room related information based on the given space name.
   *
   * @param spacename the name of the space
   * @return information of a room in the form of a string
   */
  public String getRoomInfo(String spacename);

  /**
   * Generates and saves the graphical representation of the world in the system.
   */
  public void createGraphicalRepresentation();

  /**
   * Method for execution of computer player turn.
   *
   * @param playername the name of the player
   * 
   * @return description about the computer player execution
   */
  public String playTurnComputerPlayer(String playername);

  /**
   * Method for movement of pet during a player's turn turn.
   *
   * @param spacename the name of the space
   * 
   * @return the result of the action performed.
   */
  public String movePet(String spacename);

  /**
   * Method for execution of attacking the target character.
   *
   * @param playername name of the player
   * @param itemname   name of the item
   * 
   * @return description about the success of failure of an attack
   */
  public String attackTarget(String playername, String itemname);

  /**
   * Method for execution of attacking the target character.
   *
   * @param currentplayername name of the player
   * 
   * @return description of the next player's turn
   */
  public String getPlayerNextTurn(String currentplayername);

  /**
   * This method creates a new world based on the uploaded text file.
   *
   * @param inputdata the input world data needed for creating the world.
   * @return the string if the new world has been created.
   */
  public String updateWorld(String inputdata);
  
}
