package controller;

/**
 * Represents an action in the game.
 */
public enum Action {
  ATTACK("Attack"), PICK_ITEM("PickItem"), MOVE_PET("MovePet"), LOOK_AORUND("LookAround");

  private final String disp;

  /**
   * Constructor that initializes the action string.
   *
   * @param disp the action name in the form of string.
   */
  private Action(String disp) {
    
    if (disp == null) {
      throw new IllegalArgumentException("Action string passed is null\n");
    }
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }
}