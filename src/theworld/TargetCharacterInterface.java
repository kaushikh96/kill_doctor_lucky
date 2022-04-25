package theworld;

import java.util.List;

/**
 * A Target Interface represents the data of the target character and contains
 * the methods to related to the Target Character operation.
 */
public interface TargetCharacterInterface {

  /**
   * Gets the target character name.
   *
   * @return the name of the target character
   */
  public String getName();

  /**
   * Gets the target character health.
   *
   * @return the target health
   */
  public int getHealth();

  /**
   * decreases the target character health.
   *
   * @param damage target health
   */
  public void decreaseHealth(int damage);

  /**
   * Gets the target character current room.
   *
   * @return the target current room
   */
  public SpaceInterface getCurrentRoom();

  /**
   * Sets the current room of the target character.
   *
   * @param currentRoom a space object
   */
  public void setCurrentRoom(SpaceImpl currentRoom);

  /**
   * Gets the next movement of the target character.
   *
   * @param spacelist list of items
   * @return the current room of the target character
   */
  public String getTargetCharacterNextRoom(List<SpaceImpl> spacelist);
}
