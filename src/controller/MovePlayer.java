package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to represent a turn to move a
 * player to a space.
 */
public class MovePlayer implements GameController {

  private int x_coordinate;
  private int y_coordinate;
  private String outputMessage;

  /**
   * Construct a MoveSpace object that has the playername, roomtobemovedto.
   * 
   * @param playername      name of the player
   * @param roomtobemovedto name of the room to be moved to
   */
  public MovePlayer(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("value of coordinate cannot be negative");
    }
    this.x_coordinate = x;
    this.y_coordinate = y;
    this.outputMessage = "";
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalArgumentException, IllegalStateException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.outputMessage = b.movePlayer(this.x_coordinate, this.y_coordinate);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }

}
