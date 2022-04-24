package theworld;

/**
 * A Pet Interface represents the data of the pet and contains
 * the methods to related to the Pet operation.
 */
public interface PetInterface {

  /**
   * Gets the target character pet name.
   *
   * @return name of the pet
   */
  public String getName();

  /**
   * Gets the current room of the pet.
   *
   * @return current room of the pet
   */
  public SpaceInterface getCurrentRoom();

  /**
   * Method to move to the to the new space.
   *
   * @param space entity of space to which pet has to move
   * 
   * @return space name the pet has moved to
   */
  public String movepet(SpaceImpl space);
}
