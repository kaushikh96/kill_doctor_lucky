package controllertest;

import java.util.List;
import theworld.BoardGameModel;
import theworld.ItemImpl;
import theworld.SpaceImpl;
import theworld.SpaceInterface;

/**
 * This is the class that handles model exception for controller
 * testing.
 *
 */
public class MockModelException implements BoardGameModel {

  private StringBuilder log;
  private final int uniquecode;

  /**
   * Constructor of MockModelException that initializes the
   * string builder object and the uniquecode.
   *
   * @param log        to log the output
   * @param uniquecode to show the unique code of the test
   */
  public MockModelException(StringBuilder log, int uniquecode) {
    this.log = log;
    this.uniquecode = uniquecode;
  }
  
  @Override
  public String pickItem(String playername, String itemname) throws IllegalArgumentException, 
      IllegalStateException {
    
    log.append(String.format("pickItem method check for Exception called: %s %s %d", 
        playername, itemname, uniquecode));
        
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for add player");
    } else {
      throw new IllegalStateException("IllegalStateException called for add player");
    }
    
  }

  @Override
  public String lookAround(String playername) {
    log.append(String.format("lookAround method for Exception called: %s", playername));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for look around");
    } else {
      throw new IllegalStateException("IllegalStateException called for look around");
    }
    
  }

  @Override
  public String getPlayerInfo(String playername) {
    log.append(String.format("getPlayerInfo method for Exception called: %s", playername));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for getPlayerInfo");
    } else {
      throw new IllegalStateException("IllegalStateException called for getPlayerInfo");
    }
    
  }

  @Override
  public String getRoomInfo(String spacename) {
    log.append(String.format("getRoomInfo method for Exception called: %s", spacename));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for getRoomInfo");
    } else {
      throw new IllegalStateException("IllegalStateException called for getRoomInfo");
    }
    
  }

  @Override
  public void createGraphicalRepresentation() {
    log.append(String.format("createGraphicalRepresentation method for Exception called: %d", 
        uniquecode));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for"
          + " createGrraphicalRepresentation");
    } else {
      throw new IllegalStateException("IllegalStateException called for "
          + "createGrraphicalRepresentation");
    }
    
  }

  @Override
  public String playTurnComputerPlayer(String playername) {
    log.append(String.format("playTurnComputerPlayer method for Exception called: %s", playername));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for"
          + " playTurnComputerPlayer");
    } else {
      throw new IllegalStateException("IllegalStateException called for playTurnComputerPlayer");
    }
        
  }

  @Override
  public String movePet(String spacename) {
    log.append(String.format("movePet method for Exception called: %s", spacename));
   
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for"
          + " movePet");
    } else {
      throw new IllegalStateException("IllegalStateException called for movePet");
    }
  }

  @Override
  public String attackTarget(String playername, String itemname) {
    log.append(String.format("attackTarget method for Exception called: %s %s", 
        playername, itemname));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for"
          + " movePet");
    } else {
      throw new IllegalStateException("IllegalStateException called for movePet");
    }
  }

  @Override
  public String getPlayerNextTurn(String currentplayername) {
    log.append(String.format("getPlayerNextTurn method for Exception called: %s", 
        currentplayername));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for"
          + " getPlayerNextTurn");
    } else {
      throw new IllegalStateException("IllegalStateException called for getPlayerNextTurn");
    }
  }

  @Override
  public void addPlayer(String name, String currentRoom, int itemcapacity,
      List<ItemImpl> playeritems, boolean isComputerPlayer) {
    
    log.append(String.format("add Player method for Exception called: %s %s %d %s %b %d", 
        name, currentRoom, 
        itemcapacity, playeritems.toString(), isComputerPlayer, uniquecode));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for"
          + " addPlayer");
    } else {
      throw new IllegalStateException("IllegalStateException called for addPlayer");
    }
  }

  @Override
  public String movePlayer(int xcoordinate, int ycoordinate) throws IllegalStateException {
    log.append(String.format("movePlayer method for Exception called: %d %d %d", xcoordinate, 
        ycoordinate, uniquecode));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for"
          + " movePlayer");
    } else {
      throw new IllegalStateException("IllegalStateException called for movePlayer");
    }
  }

  @Override
  public String updateWorld(String inputdata) {
    
    log.append(String.format("updateWorld method for Exception called: %s", inputdata));
    
    if ((uniquecode / 2) == 0) {
      throw new IllegalArgumentException("IllegalArgumentException called for"
          + " updateWorld");
    } else {
      throw new IllegalStateException("IllegalStateException called for updateWorld");
    }
  }

}
