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
    // assertEquals("6789", viewLog.toString());

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

//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidInput() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("h\n" + "Kaushik\n" + "Music Room\n" + "3");
//
//    StringBuilder log = new StringBuilder();
//    BoardGameModel model = new GameControllerMockModel(log, 1234);
//    BoardGameView view = new BoardGameMockView(log, 6789);
//    BoardGameController game = new BoardGameControllerImpl(model, view);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidOutput() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("h\n" + "Kaushik\n" + "Music Room\n" + "3");
//
//    StringBuilder log = new StringBuilder();
//    BoardGameModel model = new GameControllerMockModel(log, 1234);
//    BoardGameView view = new BoardGameMockView(log, 6789);
//    BoardGameController game = new BoardGameControllerImpl(model, view);
//  }
//  
//  @Test
//  public void testAddplayerClass() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("h\n" + "Kaushik\n" + "Music Room\n" + "3");
//
//    StringBuilder log = new StringBuilder();
//    BoardGameModel model = new GameControllerMockModel(log, 1234);
//    BoardGameView view = new BoardGameMockView(log, 6789);
//    BoardGameController game = new BoardGameControllerImpl(model, view);
//    game.start();
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "", out.toString());
//  }
//
//  @Test
//  public void testAddTwoPlayers() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "3\n" + "9\n" + "C\n"
//        + "COMP2\n" + "Laundry Room\n" + "6\n" + "q");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 123456);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "Invalid command: q\n"
//        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "", out.toString());
//  }
//
//  @Test
//  public void testCreateGraphicalRepresentation() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("H\n" + "Kaushik\n" + "Garden\n" + "5\n" + "1");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 123456);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Graphical representation created\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "", out.toString());
//  }
//
//  @Test
//  public void testDisplayPlayerInfo() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader(
//        "H\n" + "Kaushik\n" + "Music Room\n" + "3\n" + "8\n" + "Kaushik\n" + "q");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 123456);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Enter the Player name for information to be displayed\n"
//        + "123456Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "Invalid command: q\n"
//        + "123456Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "", out.toString());
//  }
//
//  @Test
//  public void testDisplayRoomInfo() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader(
//        "H\n" + "Kaushik\n" + "Music Room\n" + "3\n" + "7\n" + "Laundry Room");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 123456);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Enter the Room name for information has to be displayed\n"
//        + "123456Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "", out.toString());
//  }
//
//  @Test
//  public void testLookAround() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
//        + "Pran\n" + "Laundry Room\n" + "4\n" + "2\n" + "2\n" + "q");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 123456);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "", out.toString());
//  }
//
//  @Test
//  public void testPickupItem() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
//        + "Pran\n" + "Laundry Room\n" + "4\n" + "4\n" + "4\n" + "Pesticide\n" + "q");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "", out.toString());
//  }
//
//  @Test
//  public void testMovePlayer() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
//        + "Pran\n" + "Laundry Room\n" + "4\n" + "3\n" + "3\n" + "Attic\n" + "q");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "", out.toString());
//  }
//
//  @Test
//  public void testMovePet() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
//        + "Pran\n" + "Laundry Room\n" + "4\n" + "5\n" + "5\n" + "Gym\n" + "q");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "", out.toString());
//  }
//
//  @Test
//  public void testAttackTarget() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("H\n" + "Kaushik\n" + "Drawing Room\n" + "4\n" + "9\n" + "H\n"
//        + "Pran\n" + "Laundry Room\n" + "4\n" + "6\n" + "6\n" + "poke\n" + "q");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "", out.toString());
//  }
//
//  @Test
//  public void testPlayerNextTurn() {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
//        + "Pran\n" + "Laundry Room\n" + "4\n" + "3\n" + "3\n" + "Gym\n" + "q");
//
//    StringBuilder log = new StringBuilder();
//    RandomClass randomref = new RandomClass();
//    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
//    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
//    game.start(model);
//    assertEquals("Welcome to my World !\n" + "Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
//        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
//        + "Format specifier '%s'Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
//        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
//        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
//        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
//        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
//        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
//        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
//        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
//        + "8 -- > Display Player Info\n" + "", out.toString());
//  }
}
