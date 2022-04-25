package theworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class is implementation of PlayerInterface. This class defines Player
 * features.
 *
 */
public class PlayerImpl implements PlayerInterface {
  private final String name;
  private SpaceImpl currentRoom;
  private List<ItemImpl> items;
  private final int itemCapacity;
  private final boolean isComputerPlayer;

  /**
   * Constructor for PlayerImpl that initializes the details of the player.
   *
   * @param name             name of the player
   * @param currentRoom      current space of the player
   * @param items            Items it carries
   * @param itemCapacity     no of items a player can carry
   * @param isComputerPlayer a check if its a computer player
   */
  public PlayerImpl(String name, SpaceImpl currentRoom, int itemCapacity, List<ItemImpl> items,
      boolean isComputerPlayer) {
    if (currentRoom == null) {
      throw new IllegalArgumentException("Target character must have a current room");
    }
    if (name == null || name.trim().equals("")) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
    if (items == null) {
      throw new IllegalArgumentException("Items cannot be null");
    }
    if (items.size() > itemCapacity) {
      throw new IllegalArgumentException("Cannot add items more than capacity");
    }
    if (itemCapacity < 0) {
      throw new IllegalArgumentException("Item capacity cannot be negative.");
    }
    this.name = name;
    this.currentRoom = currentRoom;
    this.itemCapacity = itemCapacity;
    this.items = items;
    this.isComputerPlayer = isComputerPlayer;
  }

  @Override
  public SpaceImpl getCurrentRoom() {
    return currentRoom;
  }

  @Override
  public void setCurrentRoom(SpaceImpl currentRoom) {
    this.currentRoom = currentRoom;
  }

  @Override
  public List<ItemImpl> getItems() {
    List<ItemImpl> itemCopy = new ArrayList<>(this.items);
    return itemCopy;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getItemCapacity() {
    return itemCapacity;
  }

  @Override
  public boolean isComputerPlayer() {
    return isComputerPlayer;
  }

  @Override
  public String movePlayer(List<SpaceImpl> neighbours, String roomtobemovedto)
      throws IllegalStateException {
    if (neighbours == null || roomtobemovedto == null) {
      throw new IllegalArgumentException("Not a valid list of neighbours or room name");
    } else {
      List<SpaceImpl> playerspacelist = neighbours.stream()
          .filter(s -> s.getName().trim().equalsIgnoreCase(roomtobemovedto.trim()))
          .collect(Collectors.toList());
      if (playerspacelist.size() == 0) {
        throw new IllegalStateException("Invalid Space to be moved to\n");
      } else {
        SpaceImpl playerspace = playerspacelist.get(0);
        this.setCurrentRoom(playerspace);
      }
      return this.getCurrentRoom().getName();
    }
  }

  @Override
  public void pickItem(String itemname) throws IllegalStateException {
    if (itemname == null || "".equals(itemname.trim())) {
      throw new IllegalArgumentException("Not a valid itemname");
    } else {
      if (this.getItems().size() < this.getItemCapacity()) {
        SpaceImpl playercurrspace = this.getCurrentRoom();
        List<ItemImpl> listitems = playercurrspace.getItems();
        List<ItemImpl> ifexistsitem = listitems.stream()
            .filter(s -> s.getName().trim().equalsIgnoreCase(itemname))
            .collect(Collectors.toList());
        if (ifexistsitem.size() == 0) {
          throw new IllegalStateException("Item doesn't\n exist in the space\n");
        } else {
          this.getItems().add(ifexistsitem.get(0));
          playercurrspace.removeItem(itemname);
        }
      } else {
        throw new IllegalStateException("More than the item capacity\n of the player\n");
      }
    }
  }

  @Override
  public String lookAround(String playercurrentroom, String neighbours) {
    if (playercurrentroom == null || "".equals(playercurrentroom.trim()) || neighbours == null
        || "".equals(neighbours.trim())) {
      throw new IllegalArgumentException("Not a valid room name or neighbours");
    } else {
      return String.format("%sNeighbours: %s", playercurrentroom, neighbours);
    }
  }

  @Override
  public boolean ifPlayerSeen(PlayerImpl playerB, List<SpaceImpl> neighbours, SpaceImpl petRoom) {
    if (playerB == null || neighbours == null || petRoom == null) {
      throw new IllegalArgumentException("Invalid parameters");
    } else {
      if (this.currentRoom.equals(playerB.getCurrentRoom())) {
        return true;
      } else if (this.currentRoom.equals(petRoom)) {
        return false;
      } else {
        if (!neighbours.stream().filter(t -> t.getName().equals(playerB.getName()))
            .collect(Collectors.toList()).isEmpty()) {
          if (playerB.getCurrentRoom().equals(petRoom)) {
            return false;
          } else {
            return true;
          }
        } else {
          return false;
        }
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlayerImpl)) {
      return false;
    }

    PlayerImpl that = (PlayerImpl) o;
    return this.name.equals(that.name) && this.itemCapacity == that.itemCapacity
        && this.items.equals(that.items) && this.currentRoom.equals(that.currentRoom);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getItemCapacity(), this.getItems(),
        this.getCurrentRoom());
  }

  @Override
  public String toString() {
    return String.format("Player Info: Name: %s, Space name = %s, Items: %s\n", this.getName(),
        this.getCurrentRoom().getName(), this.items);
  }
}
