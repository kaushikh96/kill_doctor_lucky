package theworld;

/**
 * A Item Interface represents the data related to a specific item in the room.
 */
public interface ItemInterface {

  /**
   * Gets the name of the item.
   * 
   * @return the item name
   */
  public String getName();

  /**
   * Gets the damage of the item.
   * 
   * @return the item damage.
   */
  public int getItemdamage();
}
