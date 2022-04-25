package controllertest;

import static org.junit.Assert.assertEquals;

import controller.BoardGameController;
import controller.BoardGameControllerImpl;
import controller.Features;
import driver.RandomClass;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;
import theworld.BoardGameFacade;
import theworld.BoardGameModel;
import theworldview.BoardGameView;

/**
 * This is a Controller Test Class to generate tests for the controller methods
 * that uses the mocks of the model and the view for testing.
 */
public class ControllerClassTest {

  private BoardGameModel model;
  private BoardGameView view;
  private Features game;
  private StringBuilder viewLog;
  private StringBuilder modelLog;

  @Before
  public void setUp() {

    viewLog = new StringBuilder();
    modelLog = new StringBuilder();

    model = new GameControllerMockModel(modelLog, 1234);
    view = new BoardGameMockView(viewLog, 6789);
    game = new BoardGameControllerImpl(model, view);

  }

  @Test
  public void testAddPlayerWithMocks() {

    game.addPlayer("Sanjana", "Parlor", 2, false);

    modelLog.append(viewLog);
    assertEquals("add Player method called: Sanjana Parlor 2 [] false 1234"
        + "ifPlayerAdded method called: 6789", modelLog.toString());

  }

  @Test
  public void testGetTurnsWithMocks() {
    game.getTurns("Sanjana");
    modelLog.append(viewLog);
    assertEquals("getPlayerNextTurn method called: Sanjana"
        + "setIfTurnExecuted method called: true 6789", modelLog.toString());
  }

  @Test
  public void testhandleKeyPressEventforAttack() {
    game.handleKeyPressEvent("Attack", "Sanjana", "Knife");

    modelLog.append(viewLog);
    assertEquals("attackTarget method called: Sanjana Knife"
        + "setOutputMessage method called: 1234 6789"
        + "setIfTurnExecuted method called: true 6789", modelLog.toString());
  }

  @Test
  public void testhandleKeyPressEventforPickItem() {
    game.handleKeyPressEvent("PickItem", "Sanjana", "Billiard Cue");

    modelLog.append(viewLog);
    assertEquals("pickItem method called: Sanjana Billiard Cue 1234setOutputMessage method called: "
        + "1234 6789setIfTurnExecuted method called: true 6789", modelLog.toString());
  }

  @Test
  public void testhandleKeyPressEventforLookAround() {
    game.handleKeyPressEvent("LookAround", "Sanjana", "");

    modelLog.append(viewLog);
    assertEquals("lookAround method called: Sanjana"
        + "setOutputMessage method called: 1234 6789"
        + "setIfTurnExecuted method called: true 6789", modelLog.toString());
  }

  @Test
  public void testhandleKeyPressEventforMovePet() {
    game.handleKeyPressEvent("MovePet", "Sanjana", "Parlor");

    modelLog.append(viewLog);
    assertEquals("movePet method called: Parlor"
        + "setOutputMessage method called: 1234 6789"
        + "setIfTurnExecuted method called: true 6789", modelLog.toString());
  }

  @Test
  public void testhandleMouseClick() {

    game.handleMouseClickEvent(20, 10);

    modelLog.append(viewLog);
    assertEquals(
        "movePlayer method called: 20 10 1234setOutputMessage method called: 1234 "
        + "6789setIfTurnExecuted method called: true 6789displayGameScreen method called: 6789",
        modelLog.toString());
  }

  @Test
  public void testHandPlayerMouseClickEvent() {

    game.handlePlayerMouseClickEvent("Sanjana");

    modelLog.append(viewLog);
    assertEquals("getPlayerInfo method called: Sanjana"
        + "setPlayerInfoDialog method called: 1234 6789", modelLog.toString());
  }

  @Test
  public void testMoveToWorldSelectionScreen() {

    game.moveToWorldSelectionScreen();

    modelLog.append(viewLog);
    assertEquals("displayWorldSelectionScreen method called: 6789", modelLog.toString());
  }

  @Test
  public void testMoveToGameScreen() {
    game.moveToGameScreen();

    modelLog.append(viewLog);
    assertEquals("displayGameScreen method called: 6789", modelLog.toString());
  }

  @Test
  public void testMoveToAddPlayerScreen() {
    game.moveToAddPlayerScreen();

    modelLog.append(viewLog);
    assertEquals("displayAddPlayerScreen method called: 6789", modelLog.toString());
  }

  @Test
  public void testUpdateWorld() {
    game.updateWorld("Sanjana 30 29");

    modelLog.append(viewLog);
    assertEquals("updateWorld method called: Sanjana 30 29"
        + "setPlayerInfoDialog method called: 1234 6789"
        + "displayAddPlayerScreen method called: 6789", modelLog.toString());

  }
  
  

}
