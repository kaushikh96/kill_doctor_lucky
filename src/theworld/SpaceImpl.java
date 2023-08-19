package theworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class is implementation of SpaceInterface. This class defines Space
 * features.
 */
public class SpaceImpl implements SpaceInterface {
  private final int roomid;
  private final List<Integer> location;
  private final String name;
  private List<ItemImpl> items;

  /**
   * Constructor for SpaceImpl.
   * 
   * @param roomid   id of the room
   * @param location location of the room
   * @param name     name of the room
   * @param items    Items in the room
   */
  public SpaceImpl(int roomid, List<Integer> location, String name, List<ItemImpl> items) {

    if (Math.round((float) roomid) < 0) {
      throw new IllegalArgumentException("Room id cannot be negative");
    }
    if (location.size() == 0 || location == null) {
      throw new IllegalArgumentException("Location coordinates cannot be null or empty");
    }
    if (name == null || name.trim().equals("")) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
    this.roomid = roomid;
    this.location = location;
    this.name = name;
    this.items = items;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getRoomId() {
    return this.roomid;
  }

  @Override
  public List<Integer> getRoomLocation() {
    List<Integer> copylocation = new ArrayList<>(this.location);
    return copylocation;
  }

  @Override
  public List<ItemImpl> getItems() {
    return this.items;
  }

  @Override
  public void setItems(List<ItemImpl> items) {
    List<ItemImpl> itemscopy = new ArrayList<>(items);
    this.items = itemscopy;
  }

  @Override
  public void removeItem(String itemname) {
    if (itemname == null || "".equals(itemname.trim())) {
      throw new IllegalArgumentException("Not a valid item name");
    } else {
      List<ItemImpl> itemlist = this.items.stream()
          .filter(s -> s.getName().trim().equalsIgnoreCase(itemname)).collect(Collectors.toList());
      if (!itemlist.isEmpty()) {
        this.items.remove(itemlist.get(0));
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SpaceImpl)) {
      return false;
    }

    SpaceImpl that = (SpaceImpl) o;
    return this.name.equals(that.name) && this.roomid == that.roomid
        && this.location.equals(that.location) && this.items.equals(that.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getRoomId(), this.getRoomLocation(), this.getItems());
  }

  @Override
  public String toString() {
    return String.format("Space name = %s", this.getName());
  }
}