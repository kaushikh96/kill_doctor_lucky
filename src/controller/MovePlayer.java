package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to represent a turn to move a
 * player to a space.
 */
public class MovePlayer implements GameController {

  private int xcoordinate;
  private int ycoordinate;
  private String outputMessage;

  /**
   * Construct a MoveSpace object that has the name of the player, the space 
   * for the player to move.
   *
   * @param x the xcoordinate of the space the player needs to be move to.
   * @param y the ycoordinate of the space the player needs to be move to.
   */
  public MovePlayer(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("value of coordinate cannot be negative");
    }
    this.xcoordinate = x;
    this.ycoordinate = y;
    this.outputMessage = "";
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalArgumentException, IllegalStateException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.outputMessage = b.movePlayer(this.xcoordinate, this.ycoordinate);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }

}
