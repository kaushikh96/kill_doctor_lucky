package controller;

import theworld.BoardGameModel;

/**
 * This class is a part of command design pattern to represent a turn to move a
 * player to a space.
 */
public class MoveSpace implements GameController {

  private String playername;
  private String roomtobemovedto;
  private String outputMessage;

  /**
   * Construct a MoveSpace object that has the playername, roomtobemovedto.
   * 
   * @param playername      name of the player
   * @param roomtobemovedto name of the room to be moved to
   */
  public MoveSpace(String playername, String roomtobemovedto) {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    if (roomtobemovedto == null || "".equals(roomtobemovedto.trim())) {
      throw new IllegalArgumentException("Invalid space name");
    }
    this.playername = playername;
    this.roomtobemovedto = roomtobemovedto;
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalArgumentException, IllegalStateException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    String room = b.movePlayer(this.playername, this.roomtobemovedto);
    this.outputMessage = String.format(
        "Player %s has been moved to %s\nTarget Character Current Room: %s\n", playername,
        this.roomtobemovedto, room);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }

}
