package controller;

/**
 * Represents a action in the game of tic-tac-toe.
 */
public enum Action {
  ATTACK("Attack"), PICK_ITEM("PickItem"), MOVE_PET("MovePet"), LOOK_AORUND("LookAround");

  private final String disp;

  private Action(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }
}