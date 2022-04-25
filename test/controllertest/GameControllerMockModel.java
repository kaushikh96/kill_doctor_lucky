package controllertest;

import java.util.List;

import driver.RandomClass;
import theworld.BoardGameFacade;
import theworld.BoardGameModel;
import theworld.ItemImpl;

/**
 * This is GameControllerMockMode class which acts as a mock model for
 * controller testing.
 */
public class GameControllerMockModel implements BoardGameModel {
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
  public String pickItem(String playername, String itemname) {
    log.append(String.format("pickItem method called: %s %s %d", playername, itemname, uniquecode));
    return Integer.toString(uniquecode);
  }

  @Override
  public String lookAround(String playername) {
    log.append(String.format("lookAround method called: %s", playername));
    return Integer.toString(uniquecode);
  }

  @Override
  public String getPlayerInfo(String playername) {
    log.append(String.format("getPlayerInfo method called: %s", playername));
    return Integer.toString(uniquecode);
  }

  @Override
  public String getRoomInfo(String spacename) {
    log.append(String.format("getRoomInfo method called: %s", spacename));
    return Integer.toString(uniquecode);
  }

  @Override
  public void createGraphicalRepresentation() {
    log.append(String.format("createGraphicalRepresentation method called: %d", uniquecode));
  }

  @Override
  public String playTurnComputerPlayer(String playername) {
    log.append(String.format("playTurnComputerPlayer method called: %s", playername));
    return Integer.toString(uniquecode);
  }

  @Override
  public String movePet(String spacename) {
    log.append(String.format("movePet method called: %s", spacename));
    return Integer.toString(uniquecode);
  }

  @Override
  public String attackTarget(String playername, String itemname) {
    log.append(String.format("attackTarget method called: %s %s", playername, itemname));
    return Integer.toString(uniquecode);
  }

  @Override
  public String getPlayerNextTurn(String currentplayername) {
    log.append(String.format("getPlayerNextTurn method called: %s", currentplayername));
    return Integer.toString(uniquecode);
  }

  @Override
  public void addPlayer(String name, String currentRoom, int itemcapacity,
      List<ItemImpl> playeritems, boolean isComputerPlayer) {
    
    log.append(String.format("add Player method called: %s %s %d %s %b %d", name, currentRoom, 
        itemcapacity, playeritems.toString(), isComputerPlayer, uniquecode));
  }

  @Override
  public String movePlayer(int xcoordinate, int ycoordinate) throws IllegalStateException {
    log.append(String.format("movePlayer method called: %d %d %d", xcoordinate, 
        ycoordinate, uniquecode));
    return Integer.toString(uniquecode);
  }

  @Override
  public String updateWorld(String inputdata) {
    
    log.append(String.format("updateWorld method called: %s", inputdata));
    return Integer.toString(uniquecode);
  }
}
