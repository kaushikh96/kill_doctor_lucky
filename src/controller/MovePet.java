package controller;

import theworld.BoardGameFacade;

/**
 * This class is a part of command design pattern to execute of the turn moving
 * a pet.
 */
public class MovePet implements GameController {
  private String playername;
  private String roomtobemovedto;
  private String outputMessage;

  /**
   * Construct a MovePet object that has the playername, roomtobemovedto.
   * 
   * @param playername      name of the player
   * @param roomtobemovedto name of the room to be moved to
   */
  public MovePet(String playername, String roomtobemovedto) {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    if (roomtobemovedto == null || "".equals(roomtobemovedto.trim())) {
      throw new IllegalArgumentException("Invalid space name");
    }
    this.playername = playername;
    this.roomtobemovedto = roomtobemovedto;
    this.outputMessage = "";
  }

  @Override
  public void execute(BoardGameFacade b) throws IllegalArgumentException, IllegalStateException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.outputMessage = b.movePet(this.roomtobemovedto);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
