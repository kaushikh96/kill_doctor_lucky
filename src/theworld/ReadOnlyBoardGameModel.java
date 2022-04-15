package theworld;

import java.util.List;

/**
 * A Board Game Interface represents the data of the world and contains the list
 * of methods to related to the World operation.
 */
public interface ReadOnlyBoardGameModel extends BoardGameModel {

  /**
   * Gets the target character entity.
   * 
   * @return the object of the target character
   */
  public TargetCharacterInterface getTargetCharacterImpl();

  /**
   * Gets the target pet entity.
   * 
   * @return the object of the target pet
   */
  public PetInterface getTargetPetImpl();

  /**
   * Gets the world name.
   * 
   * 
   * @return the world name.
   */
  public String getName();

  /**
   * Gets the list of spaces in the world.
   * 
   * @return the List of spaces
   */
  public List<SpaceImpl> getSpaceList();

  /**
   * Gets the world coordinates in the form of a list.
   * 
   * @return the List of Integers
   */
  public List<Integer> getWorldCoordinates();

  /**
   * Gets the neighbours of the room in the space.
   * 
   * @param space the space object
   * @return list of spaces in the world
   */
  public List<SpaceImpl> getAllVisibleSpaces(SpaceImpl space);

  /**
   * Gets the players of the room in the space.
   * 
   * @return list of players in the world
   */
  public List<PlayerImpl> getPlayerList();

  /**
   * Gets the next location for the target character to move.
   * 
   * @return string of the room the target character has moved to
   */
  public String getNextTargetCharacterRoom() throws IllegalStateException;

  /**
   * Method to determine whether playerA can see playerB.
   * 
   * @param playerA name of the first player
   * @param playerB name of the second whose visibility is to be determined
   * 
   * @return the boolean value if playerA can be playerB
   */
  public boolean ifPlayerSeen(String playerA, String playerB);

  /**
   * Method to decrease the target character's health.
   * 
   * @param playercurrent data of the current player
   * @param itemname      name of the item
   * 
   * @return the health data of the target character
   */
  public String decreaseTargetHealth(PlayerImpl playercurrent, String itemname);

  /**
   * Method to play computerplayer attack target.
   * 
   * @param playername name of the player
   * @param itemname   name of the item
   * 
   * @return the health data of the target character
   */
  public String playComputerPlayerAttackTarget(String playername, String itemname);

  /**
   * Method to move pet using Depth First Traversal logic.
   * 
   * @param petcurrentroom name of the player
   * 
   * @return the next room of the pet movement in DFS.
   */
  public String petMovementDfs(String petcurrentroom);

}
