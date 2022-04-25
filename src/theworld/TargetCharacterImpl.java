package theworld;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This is the TargetCharacterImpl class which implements methods from the
 * TargetCharacterInterface.
 */
public class TargetCharacterImpl implements TargetCharacterInterface {
  private String name;
  private int health;
  private SpaceImpl currentRoom;

  /**
   * Constructor for TargetCharacterImpl.
   *
   * @param name        name of the target character
   * @param health      health of the target character
   * @param currentRoom current space of the target character
   */
  public TargetCharacterImpl(String name, int health, SpaceImpl currentRoom) {
    if (health < 0) {
      throw new IllegalArgumentException("Health cannot be negative");
    }
    if (currentRoom == null) {
      throw new IllegalArgumentException("Target character must have a current room");
    }
    if (name == null || name.trim().equals("")) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
    this.name = name;
    this.health = health;
    this.currentRoom = currentRoom;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getHealth() {
    return health;
  }

  @Override
  public void decreaseHealth(int damage) {
    this.health = this.health - damage;
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
  public String getTargetCharacterNextRoom(List<SpaceImpl> spacelist) {
    if (spacelist == null) {
      throw new IllegalArgumentException("Not a valid space list");
    } else {
      List<SpaceImpl> currenttargetlist = spacelist.stream()
          .filter(s -> s.getName().equalsIgnoreCase(this.currentRoom.getName()))
          .collect(Collectors.toList());
      if (!currenttargetlist.isEmpty()) {
        SpaceImpl spaceobject = currenttargetlist.get(0);
        int indexvalue = spacelist.indexOf(spaceobject);
        if (indexvalue + 1 == spacelist.size()) {
          this.currentRoom = spacelist.get(0);
        } else {
          this.currentRoom = spacelist.get(indexvalue + 1);
        }
      } else {
        throw new IllegalStateException("Space doesn't exist\n");
      }
      return this.currentRoom.getName();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TargetCharacterImpl)) {
      return false;
    }

    TargetCharacterImpl that = (TargetCharacterImpl) o;
    return this.name.equals(that.name) && this.health == that.health
        && this.currentRoom.equals(that.currentRoom);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getHealth(), this.getCurrentRoom());
  }

  @Override
  public String toString() {
    return String.format("Target Name: %s, Health: %d, Current Room: %s", this.getName(),
        this.getHealth(), this.getCurrentRoom().getName());
  }
}
