package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to display room information.
 */
public class DisplayRoomInfo implements GameController {
  private String spacename;
  private String outputMessage;

  /**
   * Construct a DisplayRoomInfo object that has the spacename.
   * 
   *
   * @param spacename name of the name
   */
  public DisplayRoomInfo(String spacename) {
    if (spacename == null || "".equals(spacename.trim())) {
      throw new IllegalArgumentException("Invalid space name");
    }
    this.spacename = spacename;
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalArgumentException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.outputMessage = b.getRoomInfo(this.spacename);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }

}
