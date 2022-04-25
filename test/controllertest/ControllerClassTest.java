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

  private BoardGameModel mockModel;
  private BoardGameView mockView;
  private Features game;
  private StringBuilder viewLog;
  private StringBuilder modelLog;
  private BoardGameModel mockModelException;
  private BoardGameView mockViewException;

  /**
   * Set's up the mock model and mock view class and passes
   * it to the controller class for testing.
   */
  @Before
  public void setUp() {

    viewLog = new StringBuilder();
    modelLog = new StringBuilder();

    mockModel = new GameControllerMockModel(modelLog, 1234);
    mockView = new BoardGameMockView(viewLog, 6789);
    game = new BoardGameControllerImpl(mockModel, mockView);

  }

  @Test
  public void testAddPlayerWithMocks() {

    game.addPlayer("Sanjana", "Parlor", 2, false);

    modelLog.append(viewLog);
    assertEquals("add Player method called: Sanjana Parlor 2 [] false 1234"
        + "ifPlayerAdded method called: 6789", modelLog.toString());

  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerWithMockExceptions() {
    
    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);

    game1.addPlayer("", "Parlor", 2, false);
    
    modelLog.append(viewLog);
    assertEquals("add Player method with Exception called:  Parlor 2 [] false 1234"
        + "ifPlayerAdded method for exception called: 6789", modelLog.toString());

  }

  @Test
  public void testGetTurnsWithMocks() {
    game.getTurns("Sanjana");
    modelLog.append(viewLog);
    assertEquals("getPlayerNextTurn method called: Sanjana"
        + "setIfTurnExecuted method called: true 6789"
        + "setTurnMessage method called: 1234 6789", modelLog.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetTurnsWithMocksException() {
    
    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.getTurns("");
    modelLog.append(viewLog);
    assertEquals("getPlayerNextTurn method foe Exception called: Sanjana"
        + "setIfTurnExecuted method for exception called: true 6789"
        + "setTurnMessage method for exception called: 1234 6789", modelLog.toString());
  }

  @Test
  public void testhandleKeyPressEventforAttack() {
    game.handleKeyPressEvent("Attack", "Sanjana", "Knife");

    modelLog.append(viewLog);
    assertEquals("attackTarget method called: Sanjana Knife"
        + "setOutputMessage method called: 1234 6789"
        + "setIfTurnExecuted method called: true 6789", modelLog.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testhandleKeyPressEventforAttackWithException() {
    
    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.handleKeyPressEvent("Attack", "", "Knife");
    
    modelLog.append(viewLog);
    assertEquals("attackTarget method for Exception called: Sanjana Knife"
        + "setOutputMessage method for exception called: 1234 6789"
        + "setIfTurnExecuted method for exception called: true 6789", modelLog.toString());
  }

  @Test
  public void testhandleKeyPressEventforPickItem() {
    game.handleKeyPressEvent("PickItem", "Sanjana", "Billiard Cue");

    modelLog.append(viewLog);
    assertEquals("pickItem method called: Sanjana Billiard Cue 1234setOutputMessage method called: "
        + "1234 6789setIfTurnExecuted method called: true 6789", modelLog.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testhandleKeyPressEventforPickWithException() {
    
    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.handleKeyPressEvent("PickItem", "", "Knife");
    
    modelLog.append(viewLog);
    assertEquals("pickItem method for Exception called: Sanjana Billiard Cue 1234"
        + "setOutputMessage method for exception called: \n"
        + "1234 6789"
        + "setIfTurnExecuted method for exception called: true 6789", modelLog.toString());
  }

  @Test
  public void testhandleKeyPressEventforLookAround() {
    game.handleKeyPressEvent("LookAround", "Sanjana", "");

    modelLog.append(viewLog);
    assertEquals("lookAround method called: Sanjana"
        + "setOutputMessage method called: 1234 6789"
        + "setIfTurnExecuted method called: true 6789", modelLog.toString());
  }

  
  @Test(expected = IllegalArgumentException.class)
  public void testhandleKeyPressEventforLookAroundWithException() {
    
    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.handleKeyPressEvent("LookAround", "", "Knife");
    assertEquals("lookAround method for Exception called: Sanjana"
        + "setOutputMessage method for exception called: 1234 6789"
        + "setIfTurnExecuted method for exception called: true 6789", modelLog.toString());
  }
  
  @Test
  public void testhandleKeyPressEventforMovePet() {
    game.handleKeyPressEvent("MovePet", "Sanjana", "Parlor");

    modelLog.append(viewLog);
    assertEquals("movePet method called: Parlor"
        + "setOutputMessage method called: 1234 6789"
        + "setIfTurnExecuted method called: true 6789", modelLog.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testhandleKeyPressEventforMovePetWithException() {

    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.handleKeyPressEvent("MovePet", "", "Knife");
    modelLog.append(viewLog);
    assertEquals("movePet method for Exception called: Parlor"
        + "setOutputMessage method for exception called: 1234 6789"
        + "setIfTurnExecuted method for exception called: true 6789", modelLog.toString());
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
  
  @Test(expected = IllegalArgumentException.class)
  public void testhandleMouseClickWithException() {

    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.handleMouseClickEvent(-20, 10);

    modelLog.append(viewLog);
    assertEquals(
        "movePlayer method for Exception called: 20 10 1234"
        + "setOutputMessage for exception method called: 1234 6789"
        + "setIfTurnExecuted for exception method called: true 6789"
        + "displayGameScreen for exception method called: 6789",
        modelLog.toString());
  }

  @Test
  public void testHandPlayerMouseClickEvent() {

    game.handleGetPlayerInfo("Sanjana");

    modelLog.append(viewLog);
    assertEquals("getPlayerInfo method called: Sanjana"
        + "setPlayerInfoDialog method called: 1234 6789", modelLog.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testHandPlayerMouseClickEventWithException() {

    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.handleGetPlayerInfo("");

    modelLog.append(viewLog);
    assertEquals("getPlayerInfo method for Exception called: Sanjana"
        + "setPlayerInfoDialog method for exception called: 1234 6789", modelLog.toString());
  }

  @Test
  public void testMoveToWorldSelectionScreen() {

    game.moveToWorldSelectionScreen();

    modelLog.append(viewLog);
    assertEquals("displayWorldSelectionScreen method called: 6789", modelLog.toString());
  }
  
  @Test
  public void testMoveToWorldSelectionScreenWithException() {

    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.moveToWorldSelectionScreen();

    modelLog.append(viewLog);
    assertEquals("displayWorldSelectionScreen method for exception called: 6789", 
        modelLog.toString());
  }

  @Test
  public void testMoveToGameScreen() {
    game.moveToGameScreen();

    modelLog.append(viewLog);
    assertEquals("displayGameScreen method called: 6789", modelLog.toString());
  }
  
  @Test
  public void testMoveToGameScreenWithException() {
    
    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.moveToGameScreen();
    
    modelLog.append(viewLog);
    assertEquals("displayGameScreen method for exception called: 6789", modelLog.toString());
  }

  @Test
  public void testMoveToAddPlayerScreen() {
    game.moveToAddPlayerScreen();

    modelLog.append(viewLog);
    assertEquals("displayAddPlayerScreen method called: 6789", modelLog.toString());
  }
  
  @Test
  public void testMoveToAddPlayerScreenWithException() {
    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.moveToAddPlayerScreen();

    modelLog.append(viewLog);
    assertEquals("displayAddPlayerScreen method for exception called: 6789", modelLog.toString());
  }

  @Test
  public void testUpdateWorld() {
    game.updateWorld("Sanjana 30 29");

    modelLog.append(viewLog);
    assertEquals("updateWorld method called: Sanjana 30 29"
        + "setPlayerInfoDialog method called: 1234 6789"
        + "displayAddPlayerScreen method called: 6789", modelLog.toString());

  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateWorldWithException() {
   
    mockModelException = new MockModelException(modelLog, 1234);
    mockViewException = new MockViewException(viewLog, 6789);
    Features game1 = new BoardGameControllerImpl(mockModelException, mockViewException);
    
    game1.updateWorld("");
    
    modelLog.append(viewLog);
    assertEquals("updateWorld method for Exception called: Sanjana 30 29"
        + "setPlayerInfoDialog method for exception called: 1234 6789"
        + "displayAddPlayerScreen method for exception called: 6789", modelLog.toString());

  }
 
  
  
  

}
