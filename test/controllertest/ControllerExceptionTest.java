package controllertest;

import static org.junit.Assert.assertEquals;

import controller.GameConsoleController;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import test.BoardGameTest;
import theworld.BoardGameFacade;
import theworld.BoardGameImpl;
import theworld.ItemImpl;
import theworld.PetImpl;
import theworld.RandomClass;
import theworld.SpaceImpl;
import theworld.TargetCharacterImpl;

/**
 * This is a ControllerExceptionTest class to test for exceptions and invalid
 * arguments handling in the class.
 */
public class ControllerExceptionTest {

  private BoardGameImpl world;
  private BoardGameImpl boardgame;

  /**
   * A setup method for initiating a constructor.
   */
  @Before
  public void setup() {
    world = BoardGameTest.readfile();
    boardgame = boardgameconstructor(world.getTargetCharacterImpl(), world.getName(),
        world.getSpaceList(), world.getWorldCoordinates(), world.getTargetPetImpl());

  }

  /**
   * Method to return a new object of BoardGameImpl type.
   * 
   * @param targetcharacter  entity of the target character
   * @param name             name of the pet
   * @param spacelist        list of the spaces in the world
   * @param worldcoordinates coordinates of the world
   * @param targetpet        entity of the target pet
   * @return newly created object of BoardGameImpl class
   */
  protected BoardGameImpl boardgameconstructor(TargetCharacterImpl targetcharacter, String name,
      List<SpaceImpl> spacelist, List<Integer> worldcoordinates, PetImpl targetpet) {
    return new BoardGameImpl(targetcharacter, name, spacelist, worldcoordinates, targetpet);
  }

  /**
   * Reads file and parses it into world.
   * 
   * @return world object
   * 
   */
  public static BoardGameImpl readfile() {
    String mansiondimensions = "25 16 Max's Villa\n" + "1 Dr.Max\n" + "Fortune the cat\n" + "20\n"
        + "0 4 7 5 Drawing Room\n" + "6 0 11 2 Music Room\n" + "4 9 11 10 Garden\n"
        + "12 9 19 10 Laundry Room\n" + "20 9 24 11 Sunroom\n" + "0 0 5 3 Living Room\n"
        + "1 6 7 8 Dining Room\n" + "1 9 3 12 Pantry\n" + "12 1 16 2 Attic\n" + "0 13 3 15 Parlor\n"
        + "17 0 22 3 Powder Room\n" + "4 11 10 15 Library\n" + "15 4 22 8 Gym\n"
        + "8 6 14 8 Kitchen\n" + "11 11 19 13 Keeping Room\n" + "20 12 24 13 Root Cellar\n"
        + "23 2 24 4 Wine Cellar\n" + "8 3 10 5 Washroom\n" + "11 3 14 5 Playzone\n"
        + "11 14 24 15 Nursery\n" + "19\n" + "8 5 Sharp Knife\n" + "0 7 Divider\n"
        + "1 6 Pesticide\n" + "15 3 Axle\n" + "3 8 Pointed table\n" + "4 2 Drying fan\n"
        + "5 6 Sofa Edge\n" + "6 5 Glass cutter\n" + "14 4 Billiard Cue\n" + "12 2 Rat Poison\n"
        + "6 2 Trowel\n" + "2 4 Big Red Hammer\n" + "16 2 Pinking Shears\n" + "10 3 Duck Decoy\n"
        + "13 2 Bad Cream\n" + "18 2 Monkey Hand\n" + "11 2 Tight Hat\n" + "17 2 Piece of Rope\n"
        + "9 3 Silken Cord";
    String[] worldattributes = mansiondimensions.split("\n");

    String[] worldattributes1 = worldattributes[0].trim().split("\\s+");
    if (worldattributes1.length > 3) {
      for (int j = 3; j < worldattributes1.length; j++) {
        worldattributes1[2] = worldattributes1[2] + " " + worldattributes1[j];
      }
    }
    String[] worldattributes2 = worldattributes[1].trim().split("\\s+");
    if (worldattributes2.length > 2) {
      for (int j = 2; j < worldattributes2.length; j++) {
        worldattributes2[1] = worldattributes2[1] + " " + worldattributes2[j];
      }
    }

    int height = Integer.parseInt(worldattributes1[0].trim());
    int width = Integer.parseInt(worldattributes1[1].trim());
    List<Integer> worldcoordinates = new ArrayList<>();
    worldcoordinates.add(height);
    worldcoordinates.add(width);
    List<SpaceImpl> roomlist = new ArrayList<>();
    int noofrooms = Integer.parseInt(worldattributes[2].trim());
    int noofweapons = Integer.parseInt(worldattributes[noofrooms + 3].trim());
    for (int i = 3; i <= noofrooms + 2; i++) {
      String[] spaceattr = worldattributes[i].trim().split("\\s+");
      if (spaceattr.length > 5) {
        for (int j = 5; j < spaceattr.length; j++) {
          spaceattr[4] = spaceattr[4] + " " + spaceattr[j];
        }
      }
      List<Integer> roomcoordinates = new ArrayList<>();
      roomcoordinates.add(Integer.parseInt(spaceattr[0]));
      roomcoordinates.add(Integer.parseInt(spaceattr[1]));
      roomcoordinates.add(Integer.parseInt(spaceattr[2]));
      roomcoordinates.add(Integer.parseInt(spaceattr[3]));
      SpaceImpl demospace = new SpaceImpl(i - 3, roomcoordinates, spaceattr[4], null);
      roomlist.add(demospace);
    }
    for (int j = noofrooms + 4; j <= noofrooms + 3 + noofweapons; j++) {
      String[] itemattr = worldattributes[j].trim().split("\\s+");

      if (itemattr.length > 3) {
        for (int k = 3; k < itemattr.length; k++) {
          itemattr[2] = itemattr[2] + " " + itemattr[k];
        }
      }
      ItemImpl demoitem = new ItemImpl(Integer.parseInt(itemattr[1]), itemattr[2]);
      SpaceImpl space = roomlist.get(Integer.parseInt(itemattr[0]));
      if (space.getItems() == null) {
        space.setItems(new ArrayList<>());
        roomlist.get(Integer.parseInt(itemattr[0])).getItems().add(demoitem);
      } else {
        roomlist.get(Integer.parseInt(itemattr[0])).getItems().add(demoitem);
      }
    }
    TargetCharacterImpl target = new TargetCharacterImpl(worldattributes2[1],
        Integer.parseInt(worldattributes2[0]), roomlist.get(0));
    PetImpl targetpet = new PetImpl(worldattributes[2], roomlist.get(0));
    BoardGameImpl world = new BoardGameImpl(target, worldattributes1[2], roomlist, worldcoordinates,
        targetpet);

    return world;
  }

  @Test
  public void testGetAllNeighbours() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    List<ItemImpl> items4 = new ArrayList<>();
    items4.add(new ItemImpl(6, "Sofa Edge"));

    List<Integer> coordinates1 = new ArrayList<>();
    coordinates1.add(0);
    coordinates1.add(4);
    coordinates1.add(7);
    coordinates1.add(5);

    List<ItemImpl> items1 = new ArrayList<>();
    items1.add(new ItemImpl(7, "Divider"));

    List<Integer> coordinates2 = new ArrayList<>();
    coordinates2.add(6);
    coordinates2.add(0);
    coordinates2.add(11);
    coordinates2.add(2);

    List<ItemImpl> items2 = new ArrayList<>();
    items2.add(new ItemImpl(6, "Pesticide"));
    SpaceImpl space2 = new SpaceImpl(1, coordinates2, "Music Room", items2);
    SpaceImpl space1 = new SpaceImpl(0, coordinates1, "Drawing Room", items1);
    List<SpaceImpl> neighbourspaces = new ArrayList<>();
    neighbourspaces.add(space2);
    neighbourspaces.add(space1);
    SpaceImpl space = new SpaceImpl(5, coordinates, "Living Room", items4);
    assertEquals("Valid Arguments", neighbourspaces.toString(),
        boardgame.getAllVisibleSpaces(space).toString());
  }

  @Test
  public void testInvalidPlayerType() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "3\n" + "9\n" + "D");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Invalid value entered\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "", out.toString());
  }

  @Test
  public void testInvalidPlayerSpace() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "9\n" + "9\n" + "H\n"
        + "Pranith\n" + "Ball Room\n" + "3");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "", out.toString());
  }

  @Test
  public void testDuplicatePlayerName() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n" + "Kaushik\n" + "Gym\n" + "5");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "", out.toString());
  }

  @Test
  public void testInvalidRoomforDisplayRoomInfo() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
        + "Pran\n" + "Music Room\n" + "5\n" + "5\n" + "abcd");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testInvalidItemCapacity() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "-4");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "", out.toString());
  }

  @Test
  public void testInvalidPlayerDisplayInfo() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "H\n" + "Pran\n"
        + "Laundry Room\n" + "5\n" + "6\n" + "xyz");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "Invalid command: H\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "Invalid command: Pran\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Invalid command: Laundry Room\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testInvalidMovePlayer() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "H\n" + "Pran\n"
        + "Laundry Room\n" + "5\n" + "3\n" + "3\n" + "Gym\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "Invalid command: H\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n" + "Invalid command: Pran\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Invalid command: Laundry Room\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testTurnsofPlayers() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
        + "Pran\n" + "Laundry Room\n" + "5\n" + "3\n" + "3\n" + "Gym\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfReturnsTheTurnMoveSpace() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
        + "Pran\n" + "Laundry Room\n" + "5\n" + "3\n" + "3\n" + "Root Cellar\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfGameQuitsAfterMaxTurns() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "3\n" + "9\n" + "H\n"
        + "Pran\n" + "Attic\n" + "3\n" + "2\n" + "2\n" + "2\n" + "3\n" + "Playzone");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testItemsMoreThanCapacity() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Dining Room\n" + "1\n" + "9\n" + "H\n"
        + "Pran\n" + "Music Room\n" + "1\n" + "4\n" + "4\n" + "glass cutter\n" + "3\n"
        + "playzone\n" + "4\n" + "trowel\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfItemNotPresentInSpace() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Dining Room\n" + "1\n" + "9\n" + "H\n"
        + "Pran\n" + "Music Room\n" + "1\n" + "4\n" + "4\n" + "pesticide\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testMoveRoomNotNeighbour() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Dining Room\n" + "1\n" + "9\n" + "H\n"
        + "Pran\n" + "Music Room\n" + "1\n" + "3\n" + "3\n" + "Wine Cellar\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testMoveIfCurrentRoom() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Dining Room\n" + "1\n" + "9\n" + "H\n"
        + "Pran\n" + "Music Room\n" + "1\n" + "3\n" + "3\n" + "Dining Room\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testInvalidRoomName() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Dining Room\n" + "1\n" + "9\n" + "H\n"
        + "Pran\n" + "Music Room\n" + "1\n" + "3\n" + "3\n" + "abcd\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testInvalidItemName() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Dining Room\n" + "1\n" + "9\n" + "H\n"
        + "Pran\n" + "Music Room\n" + "1\n" + "4\n" + "4\n" + "defg\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfHumanorComputerPlayer() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Dining Room\n" + "1\n" + "9\n" + "H\n"
        + "Pran\n" + "Music Room\n" + "1\n" + "4\n" + "4\n" + "defg\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testComputerPlayerFunctioning() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "1\n" + "9\n" + "C\n"
        + "Comp1\n" + "Laundry Room\n" + "1\n" + "2\n" + "2\n" + "2\n" + "2");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass(1);
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testComputerMovingtoNeighbouringSpace() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "1\n" + "9\n" + "C\n"
        + "Comp1\n" + "Laundry Room\n" + "1\n" + "2\n" + "2\n" + "2\n" + "2");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass(1);
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testComputerPickingUpItem() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "1\n" + "9\n" + "C\n"
        + "Comp1\n" + "Laundry Room\n" + "1\n" + "2\n" + "2\n" + "2\n" + "2");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass(2);
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testComputerLookAorund() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "1\n" + "9\n" + "C\n"
        + "Comp1\n" + "Laundry Room\n" + "1\n" + "2\n" + "2\n" + "2\n" + "2");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass(0);
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testSingleComputerPlayer() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c\n" + "comp1\n" + "Music Room\n" + "1\n" + "2\n" + "2\n" + "2");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass(0);
    BoardGameFacade model = new GameControllerMockModel(log, 123456);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testTargetCharacterIfMovePlayer() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
        + "Pran\n" + "Laundry Room\n" + "4\n" + "3\n" + "3\n" + "Gym\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Music Room", boardgame.getNextTargetCharacterRoom());
  }

  @Test
  public void testTargetCharacterIfLookAround() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
        + "Pran\n" + "Laundry Room\n" + "4\n" + "3\n" + "3\n" + "Gym\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Music Room", boardgame.getNextTargetCharacterRoom());
  }

  @Test
  public void testTargetCharacterIfPickanItem() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
        + "Pran\n" + "Laundry Room\n" + "4\n" + "3\n" + "3\n" + "Gym\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Music Room", boardgame.getNextTargetCharacterRoom());
  }

  @Test
  public void testMovePlayer() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
        + "Pran\n" + "Laundry Room\n" + "4\n" + "3\n" + "3\n" + "Gym\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfTurnsReducing() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "H\n"
        + "Pran\n" + "Laundry Room\n" + "4\n" + "3\n" + "3\n" + "Gym\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass(0);
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfTwoComputerPlayers() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("C\n" + "Kaushik\n" + "Music Room\n" + "4\n" + "9\n" + "C\n"
        + "Comp1\n" + "Attic\n" + "1\n" + "2\n" + "2");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfComputerPlayerWins() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("C\n" + "comp1\n" + "Laundry Room\n" + "4\n" + "9\n" + "C\n"
        + "Comp2\n" + "Music Room\n" + "1\n" + "2\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfGameEndsAfterWin() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "kaushik\n" + "Laundry Room\n" + "4\n" + "9\n" + "H\n"
        + "pran\n" + "Music Room\n" + "1\n" + "2\n" + "2\n" + "6\n" + "poke");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 200, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfTargetCharacterEscapesAfterTurns() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "kaushik\n" + "Laundry Room\n" + "4\n" + "9\n" + "h\n"
        + "Comp2\n" + "Music Room\n" + "2\n" + "2\n" + "2\n" + "4\n" + "pesticide" + "2\n" + "2");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 3, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testIfInvalidItemNameforAttack() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("H\n" + "kaushik\n" + "Laundry Room\n" + "4\n" + "9\n" + "H\n"
        + "pran\n" + "Music Room\n" + "1\n" + "2\n" + "2\n" + "6\n" + "poke\n" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 200, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }

  @Test
  public void testDisplayTargetCharacterLocation() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
        "H\n" + "kaushik\n" + "Laundry Room\n" + "4\n" + "9\n" + "H\n" + "Pran\n" + "Attic\n"
            + "1\n" + "2\n" + "2\n" + "5\n" + "Laundry room\n" + "3\n" + "Music Room" + "q");

    StringBuilder log = new StringBuilder();
    RandomClass randomref = new RandomClass();
    BoardGameFacade model = new GameControllerMockModel(log, 1234567);
    GameConsoleController game = new GameConsoleController(in, out, 200, randomref);
    game.start(model);
    assertEquals("Welcome to my World !\n" + "Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Is it a human or a computer player ? Select C or H\n" + "Enter a player name\n"
        + "Enter a space to enter\n" + "Enter item capacity (should be a number)\n"
        + "Format specifier '%s'Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "9 -- > Add a player\n"
        + "Choose from the below options !\n" + "1 -- > Create a graphical representation\n"
        + "2 -- > Turn: Look Around\n" + "3 -- > Turn: Move to Space\n"
        + "4 -- > Turn: Pick up an item\n" + "5 -- > Turn: Move Pet\n"
        + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "Choose from the below options !\n"
        + "1 -- > Create a graphical representation\n" + "2 -- > Turn: Look Around\n"
        + "3 -- > Turn: Move to Space\n" + "4 -- > Turn: Pick up an item\n"
        + "5 -- > Turn: Move Pet\n" + "6 -- > Turn: Attack Target\n" + "7 -- > Display Room Info\n"
        + "8 -- > Display Player Info\n" + "", out.toString());
  }
}
