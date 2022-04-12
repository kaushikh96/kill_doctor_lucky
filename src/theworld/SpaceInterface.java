package theworld;

import java.util.List;

/**
 * A Space Interface represents the data of a space in a world and all the list
 * of items it contains.
 */
public interface SpaceInterface {

  /**
   * Gets the name of the room.
   * 
   * @return the room name
   */
  public String getName();

  /**
   * Gets the id of the room.
   * 
   * @return the room id
   */
  public int getRoomId();

  /**
   * Gets the coordinates of the room.
   * 
   * @return the room location coordinates in the form of a Integer List
   */
  public List<Integer> getRoomLocation();

  /**
   * Gets the items in the space.
   * 
   * @return the list of items in the room
   */
  public List<ItemImpl> getItems();

  /**
   * Sets the space object with the list of items in that room.
   * 
   * @param items list of items in the room
   */
  public void setItems(List<ItemImpl> items);

  /**
   * Removes the item from that space.
   * 
   * @param itemname name of the item to be removed.
   */
  public void removeItem(String itemname);
}
