package theworld;

import java.util.Objects;

/**
 * This class is implementation of PetInterface. This class defines Pet
 * features.
 *
 */
public class PetImpl implements PetInterface {

  private String name;
  private SpaceImpl currentRoom;

  /**
   * Constructor for PetImpl.
   * 
   * @param name        name of the pet
   * @param currentRoom entity of the space the pet is currently in
   */
  public PetImpl(String name, SpaceImpl currentRoom) {
    if (name == null || name.trim().equals("")) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
    if (currentRoom == null) {
      throw new IllegalArgumentException("Target Pet must have a current room");
    }
    this.name = name;
    this.currentRoom = currentRoom;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public SpaceImpl getCurrentRoom() {
    return this.currentRoom;
  }

  @Override
  public String movepet(SpaceImpl space) {
    if (space == null) {
      throw new IllegalArgumentException("Invalid space");
    } else {
      this.currentRoom = space;
      return String.format("Pet has been moved to %s", space.getName());
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PetImpl)) {
      return false;
    }

    PetImpl that = (PetImpl) o;
    return this.name.equals(that.name)
        && this.currentRoom.getName().equals(that.currentRoom.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getCurrentRoom());
  }

  @Override
  public String toString() {
    return String.format("Pet name = %s, Current Room: %s", this.getName(),
        this.getCurrentRoom().getName());
  }
}
