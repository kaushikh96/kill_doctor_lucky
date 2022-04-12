package controllertest;

import java.util.List;
import theworld.BoardGameFacade;
import theworld.ItemImpl;
import theworld.RandomClass;

/**
 * This is GameControllerMockMode class which acts as a mock model for
 * controller testing.
 */
public class GameControllerMockModel implements BoardGameFacade {
  private StringBuilder log;
  private final int uniquecode;

  /**
   * Constructor of GameControllerMockModel.
   * 
   * @param log        to log the output
   * @param uniquecode to show the unique code of the test
   */
  public GameControllerMockModel(StringBuilder log, int uniquecode) {
    this.log = log;
    this.uniquecode = uniquecode;
  }

  @Override
  public void addPlayer(String name, int health, String currentRoom, int itemcapacity,
      List<ItemImpl> playeritems, boolean isComputerPlayer) {
    log.append(String.format("%s %d %s %d %s %s %b %s", name, health, currentRoom, itemcapacity,
        playeritems.toString(), isComputerPlayer, uniquecode));
  }

  @Override
  public String movePlayer(String playername, String roomtobemovedto) {
    log.append(String.format("%s %s %s", playername, roomtobemovedto, uniquecode));
    return Integer.toString(uniquecode);

  }

  @Override
  public String pickItem(String playername, String itemname) {
    log.append(String.format("%s %s %s", playername, itemname, uniquecode));
    return Integer.toString(uniquecode);
  }

  @Override
  public String lookAround(String playername) {
    log.append(String.format("%s", playername));
    return Integer.toString(uniquecode);
  }

  @Override
  public String getPlayerInfo(String playername) {
    log.append(String.format("%s", playername));
    return Integer.toString(uniquecode);
  }

  @Override
  public String getRoomInfo(String spacename) {
    log.append(String.format("%s", spacename));
    return Integer.toString(uniquecode);
  }

  @Override
  public void createGraphicalRepresentation() {
    log.append(String.format("%d", uniquecode));
  }

  @Override
  public String playTurnComputerPlayer(String playername, RandomClass randomref) {
    log.append(String.format("%s %s", playername, randomref));
    return Integer.toString(uniquecode);
  }

  @Override
  public String movePet(String spacename) {
    log.append(String.format("%s", spacename));
    return Integer.toString(uniquecode);
  }

  @Override
  public String attackTarget(String playername, String itemname) {
    log.append(String.format("%s %s", playername, itemname));
    return Integer.toString(uniquecode);
  }

  @Override
  public String getPlayerNextTurn(String currentplayername) {
    log.append(String.format("%s", currentplayername));
    return Integer.toString(uniquecode);
  }
}
