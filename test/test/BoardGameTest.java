package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import driver.RandomClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import theworld.BoardGameImpl;
import theworld.ItemImpl;
import theworld.PetImpl;
import theworld.PlayerImpl;
import theworld.SpaceImpl;
import theworld.TargetCharacterImpl;

/**
 * A JUnit test class for the StevensonReading class.
 */
public class BoardGameTest {

  private BoardGameImpl boardgame;
  private BoardGameImpl boardgame2;
  private BoardGameImpl boardgame3;
  private BoardGameImpl boardgame4;
  private BoardGameImpl world;
  private String itemsstring;
  private RandomClass randomref;
  private RandomClass randomref1;
  private RandomClass randomref2;

  /**
   * A setup method for initiating a constructor.
   */
  @Before
  public void setup() {
    world = BoardGameTest.readfile();
    randomref = new RandomClass(0);
    randomref1 = new RandomClass(1);
    randomref2 = new RandomClass(2);
    boardgame = boardgameconstructor(world.getTargetCharacterImpl(), world.getName(),
        world.getSpaceList(), world.getWorldCoordinates(), world.getTargetPetImpl(), randomref);
    boardgame2 = boardgameconstructor(world.getTargetCharacterImpl(), world.getName(),
        world.getSpaceList(), world.getWorldCoordinates(), world.getTargetPetImpl(), randomref);
    boardgame3 = boardgameconstructor(world.getTargetCharacterImpl(), world.getName(),
        world.getSpaceList(), world.getWorldCoordinates(), world.getTargetPetImpl(), randomref1);
    boardgame4 = boardgameconstructor(world.getTargetCharacterImpl(), world.getName() + "remo",
        world.getSpaceList(), world.getWorldCoordinates(), world.getTargetPetImpl(), randomref2);

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(1);
    coordinates.add(6);
    coordinates.add(7);
    coordinates.add(8);

    List<Integer> coordinates1 = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(4);
    coordinates.add(7);
    coordinates.add(5);

    List<ItemImpl> items = new ArrayList<>();
    items.add(new ItemImpl(2, "Crepe Pan"));
    List<ItemImpl> playeritem1 = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    playeritem1.add(itemobj);
    playeritem1.add(itemobj2);

    SpaceImpl space = new SpaceImpl(6, coordinates, "Dining Room", items);
    SpaceImpl space1 = new SpaceImpl(1, coordinates, "Music Room", items);
    SpaceImpl space2 = new SpaceImpl(18, coordinates, "Playzone", items);
    SpaceImpl space3 = new SpaceImpl(5, coordinates, "Living Room", items);
    SpaceImpl space4 = new SpaceImpl(0, coordinates, "Drawing Room", items);
    SpaceImpl space5 = new SpaceImpl(0, coordinates, "Sunroom", items);
    SpaceImpl space6 = new SpaceImpl(0, coordinates, "Sunroom", items);
    SpaceImpl space7 = new SpaceImpl(0, coordinates, "Root Cellar", items);
    randomref = new RandomClass(1);
    List<ItemImpl> playeritem = new ArrayList<>();
    boardgame.addPlayer("John", space.getName(), 2, playeritem1, false);
    boardgame.addPlayer("David", space.getName(), 2, playeritem, false);
    boardgame.addPlayer("comp1", space.getName(), 2, playeritem, true);
    boardgame.addPlayer("Rohith", space1.getName(), 2, playeritem1, false);
    boardgame.addPlayer("Anon", space2.getName(), 2, playeritem, false);
    boardgame.addPlayer("Anir", space3.getName(), 2, playeritem, false);
    boardgame.addPlayer("comp2", space4.getName(), 3, playeritem, false);
    boardgame.addPlayer("Remo", space5.getName(), 3, playeritem, false);
    boardgame.addPlayer("Jizo", space6.getName(), 3, playeritem, false);
    boardgame.addPlayer("Alzo", space7.getName(), 3, playeritem, false);

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
      List<SpaceImpl> spacelist, List<Integer> worldcoordinates, PetImpl targetpet,
      RandomClass ref) {
    return new BoardGameImpl(targetcharacter, name, spacelist, worldcoordinates, targetpet, ref,
        10);
  }

  /**
   * Reads file and parses it into world.
   * 
   * @return world object
   * 
   */
  public static BoardGameImpl readfile() {
    String mansiondimensions = "25 16 Max's Villa\n" + "70 Dr.Max\n" + "Fortune the cat\n" + "20\n"
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
    int noofrooms = Integer.parseInt(worldattributes[3].trim());
    int noofweapons = Integer.parseInt(worldattributes[noofrooms + 4].trim());
    for (int i = 4; i <= noofrooms + 3; i++) {
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
      SpaceImpl remospace = new SpaceImpl(i - 4, roomcoordinates, spaceattr[4], null);
      roomlist.add(remospace);
    }

    for (int j = noofrooms + 5; j <= noofrooms + 4 + noofweapons; j++) {
      String[] itemattr = worldattributes[j].trim().split("\\s+");

      if (itemattr.length > 3) {
        for (int k = 3; k < itemattr.length; k++) {
          itemattr[2] = itemattr[2] + " " + itemattr[k];
        }
      }
      ItemImpl remoitem = new ItemImpl(Integer.parseInt(itemattr[1]), itemattr[2]);
      SpaceImpl space = roomlist.get(Integer.parseInt(itemattr[0]));
      if (space.getItems() == null) {
        space.setItems(new ArrayList<>());
        roomlist.get(Integer.parseInt(itemattr[0])).getItems().add(remoitem);
      } else {
        roomlist.get(Integer.parseInt(itemattr[0])).getItems().add(remoitem);
      }
    }
    TargetCharacterImpl target = new TargetCharacterImpl(worldattributes2[1],
        Integer.parseInt(worldattributes2[0]), roomlist.get(0));
    PetImpl targetpet = new PetImpl(worldattributes[2], roomlist.get(0));
    RandomClass randomref = new RandomClass(1);
    BoardGameImpl world = new BoardGameImpl(target, worldattributes1[2], roomlist, worldcoordinates,
        targetpet, randomref, 10);

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
  public void testGetRoomInfo() {
    List<ItemImpl> items = new ArrayList<>();

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(1);
    coordinates.add(6);
    coordinates.add(7);
    coordinates.add(8);

    SpaceImpl space = new SpaceImpl(6, coordinates, "Dining Room", items);
    StringBuilder builder = new StringBuilder();
    builder.append("");
    builder.append(itemsstring);
    assertEquals("Valid Arguments",
        "RoomInfo (Name: Dining Room;\n" + " Items: Glass cutter, Trowel;\n"
            + " Players: John, David, comp1;\n"
            + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n" + "\n" + "\n" + "",
        boardgame.getRoomInfo(space.getName()));
  }

  @Test
  public void testGetNextTargetCharacterRoom() {

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(6);
    coordinates.add(0);
    coordinates.add(11);
    coordinates.add(2);

    ItemImpl itemobj1 = new ItemImpl(4, "Big Red Hammer");
    List<ItemImpl> items1 = new ArrayList<>();
    items1.add(itemobj1);
    List<Integer> coordinates1 = new ArrayList<>();
    coordinates1.add(4);
    coordinates1.add(9);
    coordinates1.add(11);
    coordinates1.add(10);
    assertEquals("Valid Arguments", "Music Room", boardgame.getNextTargetCharacterRoom());
  }

  @Test
  public void testMovePlayer() {

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(6);
    coordinates.add(0);
    coordinates.add(11);
    coordinates.add(2);

    ItemImpl itemobj1 = new ItemImpl(4, "Big Red Hammer");
    List<ItemImpl> items1 = new ArrayList<>();
    items1.add(itemobj1);
    List<Integer> coordinates1 = new ArrayList<>();
    coordinates1.add(4);
    coordinates1.add(9);
    coordinates1.add(11);
    coordinates1.add(10);

    boardgame.movePlayer(2, 11);
    assertEquals("Garden", boardgame.getNextTargetCharacterRoom());
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidMovePlayer() {
    boardgame.movePlayer(14, 1);
  }

  @Test
  public void testPickanItem() {

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(6);
    coordinates.add(0);
    coordinates.add(11);
    coordinates.add(2);

    ItemImpl itemobj1 = new ItemImpl(4, "Big Red Hammer");
    List<ItemImpl> items1 = new ArrayList<>();
    items1.add(itemobj1);
    List<Integer> coordinates1 = new ArrayList<>();
    coordinates1.add(4);
    coordinates1.add(9);
    coordinates1.add(11);
    coordinates1.add(10);

    boardgame.pickItem("David", "glass cutter");
    assertEquals("Garden", boardgame.getNextTargetCharacterRoom());
  }

  @Test(expected = IllegalStateException.class)
  public void testPickupInvalid() {
    boardgame.pickItem("John", "Pesticide");
  }

  @Test
  public void testPlayerInfoNoItems() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    List<ItemImpl> playeritem = new ArrayList<>();

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);

    assertEquals("Valid Arguments",
        "Player Info (Name: David; Current Room: Dining Room; Items: No items on the player)\n"
            + "",
        boardgame.getPlayerInfo("David"));
  }

  @Test
  public void testPlayerInfoItemExists() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(5, "crepe pan");
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);

    assertEquals("Valid Arguments",
        "Player Info (Name: John; Current Room: Dining Room; Items: Pesticide, Crepe Pan)\n" + "",
        boardgame.getPlayerInfo(player.getName()));
  }

  @Test
  public void testComputerPlayerLookAround() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    RandomClass rclass = new RandomClass(0);
    assertEquals("Valid Arguments", "LookAround (Name: Dining Room;\n"
        + " Items: Glass cutter, Trowel;\n" + " Players: John, David, comp1;\n"
        + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n" + "\n" + "\n" + "Neighbours: \n"
        + "(Name: Kitchen; Items: Bad Cream; Players: No players available)\n"
        + "(Name: Garden; Items: Big Red Hammer; Players: No players available)\n"
        + "(Name: Pantry; Items: No items available; Players: No players available)\n"
        + "Target Character Current Room: Music Room\n" + "",
        boardgame.playTurnComputerPlayer("comp1"));
  }

  @Test
  public void testComputerPlayerMovePlayer() {
    RandomClass rclass = new RandomClass(1);
    assertEquals(
        "Executed Move: comp1 has moved to Kitchen\n"
            + "Target Character Current Room: Music Room\n" + "",
        boardgame.playTurnComputerPlayer("comp1"));
  }

  @Test
  public void testComputerPlayerPickItem() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    RandomClass rclass = new RandomClass(2);
    assertEquals("Valid Arguments",
        "Executed Move Pet: Pet has been moved to Garden\n"
            + "Target Character Current Room: Music Room\n" + "",
        boardgame.playTurnComputerPlayer("comp1"));
  }

  @Test
  public void testComputerPlayerUsesHighestDamageItem() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    RandomClass rclass = new RandomClass(2);
    assertEquals("Valid Arguments",
        "Executed Move Pet: Pet has been moved to Garden\n"
            + "Target Character Current Room: Music Room\n" + "",
        boardgame.playTurnComputerPlayer("comp1"));
  }

  @Test
  public void testLookAroundifTargetExists() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    TargetCharacterImpl target = new TargetCharacterImpl("John", 30, space);
    assertEquals(
        "LookAround (Name: Dining Room;\n" + " Items: Glass cutter, Trowel;\n"
            + " Players: John, David, comp1;\n"
            + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n" + "\n" + "Neighbours: \n"
            + "(Name: Kitchen; Items: Bad Cream; Players: No players available)\n"
            + "(Name: Garden; Items: Big Red Hammer; Players: No players available)\n"
            + "(Name: Pantry; Items: No items available; Players: No players available)\n" + "",
        boardgame.lookAround("John"));
  }

  @Test
  public void testLookAroundifTargetExistsNeighbours() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);

    assertEquals("LookAround (Name: Music Room;\n" + " Items: Pesticide;\n" + " Players: Rohith;\n"
        + " Neighbours: Living Room, Attic, Washroom, Playzone)\n" + "\n" + "\n" + "Neighbours: \n"
        + "(Name: Living Room; Items: Sofa Edge; Players: Anir)\n"
        + "(Name: Attic; Items: Sharp Knife; Players: No players available)\n"
        + "(Name: Washroom; Items: Piece of Rope; Players: No players available)\n"
        + "(Name: Playzone; Items: Monkey Hand; Players: Anon)\n"
        + "Target Character Current Room: Music Room\n" + "", boardgame.lookAround("Rohith"));
  }

  @Test
  public void testLookAroundifTargetNotNeighbours() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    TargetCharacterImpl target = new TargetCharacterImpl("John", 30, space);
    assertEquals("LookAround (Name: Dining Room;\n" + " Items: Glass cutter, Trowel;\n"
        + " Players: John, David, comp1;\n"
        + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n" + "\n" + "\n" + "Neighbours: \n"
        + "(Name: Kitchen; Items: Bad Cream; Players: No players available)\n"
        + "(Name: Garden; Items: Big Red Hammer; Players: No players available)\n"
        + "(Name: Pantry; Items: No items available; Players: No players available)\n"
        + "Target Character Current Room: Music Room\n" + "", boardgame.lookAround("John"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPickupmorethanCapacity() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    ItemImpl itemobj2 = new ItemImpl(6, "Crepe pan");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);
    items.add(itemobj2);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    new PlayerImpl("John", space, 1, playeritem, false);
  }

  @Test
  public void testPetInitialLocation() {
    assertEquals("Drawing Room", boardgame.getTargetPetImpl().getCurrentRoom().getName());
  }

  @Test
  public void testLookAroundifPlayerExists() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    boardgame.addPlayer("Bob", "Music Room", 2, playeritem, false);
    boardgame.addPlayer("Alan", "Music Room", 2, playeritem, false);
    assertEquals("LookAround (Name: Music Room;\n" + " Items: Pesticide;\n"
        + " Players: Rohith, Bob, Alan;\n"
        + " Neighbours: Living Room, Attic, Washroom, Playzone)\n" + "\n" + "\n" + "Neighbours: \n"
        + "(Name: Living Room; Items: Sofa Edge; Players: Anir)\n"
        + "(Name: Attic; Items: Sharp Knife; Players: No players available)\n"
        + "(Name: Washroom; Items: Piece of Rope; Players: No players available)\n"
        + "(Name: Playzone; Items: Monkey Hand; Players: Anon)\n"
        + "Target Character Current Room: Music Room\n" + "", boardgame.lookAround("Bob"));
  }

  @Test
  public void testLookAroundifNoPlayersNeighbours() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    boardgame.addPlayer("Bob", "Music Room", 2, playeritem, false);
    boardgame.addPlayer("Alan", "Music Room", 2, playeritem, false);
    assertEquals(
        "LookAround (Name: Sunroom;\n" + " Items: Drying fan;\n" + " Players: Remo, Jizo;\n"
            + " Neighbours: Laundry Room, Keeping Room, Gym, Root Cellar)\n" + "\n"
            + "Neighbours: \n"
            + "(Name: Laundry Room; Items: Pointed table; Players: No players available)\n"
            + "(Name: Keeping Room; Items: Billiard Cue; Players: No players available)\n"
            + "(Name: Gym; Items: Rat Poison; Players: No players available)\n"
            + "(Name: Root Cellar; Items: Axle; Players: Alzo)\n" + "",
        boardgame.lookAround("Jizo"));
  }

  @Test
  public void testLookAroundIfNoItems() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    assertEquals("Valid Arguments", "LookAround (Name: Dining Room;\n"
        + " Items: Glass cutter, Trowel;\n" + " Players: John, David, comp1;\n"
        + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n" + "\n" + "\n" + "Neighbours: \n"
        + "(Name: Kitchen; Items: Bad Cream; Players: No players available)\n"
        + "(Name: Garden; Items: Big Red Hammer; Players: No players available)\n"
        + "(Name: Pantry; Items: No items available; Players: No players available)\n"
        + "Target Character Current Room: Music Room\n" + "", boardgame.lookAround("John"));
  }

  @Test
  public void testLookAroundIfNoItemsInNeighbours() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);
    List<ItemImpl> playeritem = new ArrayList<>();

    SpaceImpl space = new SpaceImpl(1, coordinates, "Dining Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);

    assertEquals("Valid Arguments",
        "LookAround (Name: Dining Room;\n" + " Items: Glass cutter, Trowel;\n"
            + " Players: John, David, comp1;\n"
            + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n" + "\n" + "Neighbours: \n"
            + "(Name: Kitchen; Items: Bad Cream; Players: No players available)\n"
            + "(Name: Garden; Items: Big Red Hammer; Players: No players available)\n"
            + "(Name: Pantry; Items: No items available; Players: No players available)\n" + "",
        boardgame.lookAround("John"));
  }

  @Test
  public void testLookAroundIfItems() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    assertEquals("Valid Arguments",
        "LookAround (Name: Dining Room;\n" + " Items: Glass cutter, Trowel;\n"
            + " Players: John, David, comp1;\n"
            + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n" + "\n" + "Neighbours: \n"
            + "(Name: Kitchen; Items: Bad Cream; Players: No players available)\n"
            + "(Name: Garden; Items: Big Red Hammer; Players: No players available)\n"
            + "(Name: Pantry; Items: No items available; Players: No players available)\n" + "",
        boardgame.lookAround("John"));
  }

  @Test
  public void testLookAroundIfItemsInNeighbours() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    assertEquals("Valid Arguments",
        "LookAround (Name: Dining Room;\n" + " Items: Glass cutter, Trowel;\n"
            + " Players: John, David, comp1;\n"
            + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n" + "\n" + "Neighbours: \n"
            + "(Name: Kitchen; Items: Bad Cream; Players: No players available)\n"
            + "(Name: Garden; Items: Big Red Hammer; Players: No players available)\n"
            + "(Name: Pantry; Items: No items available; Players: No players available)\n" + "",
        boardgame.lookAround("John"));
  }

  @Test
  public void testLookAroundIfPetInNeighbour() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    assertEquals("Valid Arguments", "LookAround (Name: Dining Room;\n"
        + " Items: Glass cutter, Trowel;\n" + " Players: John, David, comp1;\n"
        + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n" + "\n" + "\n" + "Neighbours: \n"
        + "(Name: Kitchen; Items: Bad Cream; Players: No players available)\n"
        + "(Name: Garden; Items: Big Red Hammer; Players: No players available)\n"
        + "(Name: Pantry; Items: No items available; Players: No players available)\n"
        + "Target Character Current Room: Music Room\n" + "", boardgame.lookAround("John"));
  }

  @Test(expected = IllegalStateException.class)
  public void testAttackTargetIfTargetPlayerNotInSameSpace() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);

    boardgame.attackTarget("John", "Crepe Pan");
  }

  @Test
  public void testAttackTargetIfTargetPlayerInSameSpace() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.add(itemobj);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Drawing Room", items);
    assertEquals("Target Character hit successfully.\n" + "Health got decreased by 1.\n"
        + "Current Health: 69\n" + "\n" + "", boardgame.attackTarget("comp2", "Poke"));
  }

  @Test
  public void testAttackIfTargetPetInCurrentRoom() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Garden", items);
    boardgame.movePet("Music room");
    assertEquals(
        "Target Character hit successfully.\n" + "Health got decreased by 2.\n"
            + "Current Health: 68\n" + "\n" + "Target Character Current Room: Garden\n" + "",
        boardgame.attackTarget("Rohith", "Crepe Pan"));
  }

  @Test
  public void testAttackIfPlayerInNeighbouringRoom() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(11);
    coordinates.add(3);
    coordinates.add(14);
    coordinates.add(5);

    ItemImpl itemobj = new ItemImpl(2, "Monkey hand");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);
    boardgame.movePet("Dining Room");
    assertEquals("Target Character attack stopped as the \n"
        + "attack is being seen by other players.\n" + "",
        boardgame.attackTarget("Rohith", "Pesticide"));
  }

  @Test
  public void testAttackIfPlayerPetInNeighbouringRoom() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(11);
    coordinates.add(3);
    coordinates.add(14);
    coordinates.add(5);

    ItemImpl itemobj = new ItemImpl(2, "Monkey hand");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);
    boardgame.movePet("Dining Room");
    assertEquals(
        "Target Character attack stopped as the attack is being seen by other players.\n"
            + "Target Character Current Room: Garden\n" + "",
        boardgame.attackTarget("Rohith", "Pesticide"));
  }

  @Test
  public void testAttackIfPlayersTargetPetInSameRoom() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);
    boardgame.movePet("Music Room");
    assertEquals("Target Character hit successfully.\n" + "Health got decreased by 6.\n"
        + "Current Health: 64\n" + "\n" + "", boardgame.attackTarget("Rohith", "Pesticide"));
  }

  @Test(expected = IllegalStateException.class)
  public void testAttackIfTargetInDifferentSpace() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    boardgame.attackTarget("Rohith", "Pesticide");
  }

  @Test(expected = IllegalStateException.class)
  public void testMovePetIfSameSpace() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    this.testGetNextTargetCharacterRoom();
    assertEquals("Target Character hit successfully.\n" + "Health got decreased by 6.\n"
        + "Current Health: 64\n" + "", boardgame.movePet("Drawing Room"));
  }

  @Test
  public void testMovePet() {
    assertEquals("Move pet testing",
        "Pet has been moved to Attic\n" + "Target Character Current Room: Music Room\n" + "",
        boardgame.movePet("Attic"));
  }

  @Test
  public void testLookAroundIfPetExists() {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    boardgame.movePet("Living Room");
    assertEquals("LookAround (Name: Music Room;\n" + " Items: Pesticide;\n" + " Players: Rohith;\n"
        + " Neighbours: Living Room, Attic, Washroom, Playzone)\n" + "\n" + "\n" + "Neighbours: \n"
        + "(Name: Attic; Items: Sharp Knife; Players: No players available)\n"
        + "(Name: Washroom; Items: Piece of Rope; Players: No players available)\n"
        + "(Name: Playzone; Items: Monkey Hand; Players: Anon)\n"
        + "Target Character Current Room: Garden\n" + "", boardgame.lookAround("Rohith"));
  }

  @Test
  public void testLookAroundIfPetNotExists() {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    boardgame.movePet("Library");
    assertEquals(
        "LookAround (Name: Music Room;\n" + " Items: Pesticide;\n" + " Players: Rohith;\n"
            + " Neighbours: Living Room, Attic, Washroom, Playzone)\n" + "\n" + "Neighbours: \n"
            + "(Name: Living Room; Items: Sofa Edge; Players: Anir)\n"
            + "(Name: Attic; Items: Sharp Knife; Players: No players available)\n"
            + "(Name: Washroom; Items: Piece of Rope; Players: No players available)\n"
            + "(Name: Playzone; Items: Monkey Hand; Players: Anon)\n" + "",
        boardgame.lookAround("Rohith"));
  }

  @Test
  public void testNotVisibleforPlayerWithPet() {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    boardgame.movePet("Living Room");
    assertEquals(
        "LookAround (Name: Music Room;\n" + " Items: Pesticide;\n" + " Players: Rohith;\n"
            + " Neighbours: Living Room, Attic, Washroom, Playzone)\n" + "\n" + "Neighbours: \n"
            + "(Name: Attic; Items: Sharp Knife; Players: No players available)\n"
            + "(Name: Washroom; Items: Piece of Rope; Players: No players available)\n"
            + "(Name: Playzone; Items: Monkey Hand; Players: Anon)\n" + "",
        boardgame.lookAround("Rohith"));
  }

  @Test
  public void testVisibleforPlayerNoPet() {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    boardgame.movePet("Library");
    assertEquals(
        "LookAround (Name: Sunroom;\n" + " Items: Drying fan;\n" + " Players: Remo, Jizo;\n"
            + " Neighbours: Laundry Room, Keeping Room, Gym, Root Cellar)\n" + "\n"
            + "Neighbours: \n"
            + "(Name: Laundry Room; Items: Pointed table; Players: No players available)\n"
            + "(Name: Keeping Room; Items: Billiard Cue; Players: No players available)\n"
            + "(Name: Gym; Items: Rat Poison; Players: No players available)\n"
            + "(Name: Root Cellar; Items: Axle; Players: Alzo)\n" + "",
        boardgame.lookAround("Jizo"));
  }

  @Test
  public void testVisibleforPlayerWithoutPet() {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    assertEquals(
        "LookAround (Name: Sunroom;\n" + " Items: Drying fan;\n" + " Players: Remo, Jizo;\n"
            + " Neighbours: Laundry Room, Keeping Room, Gym, Root Cellar)\n" + "\n"
            + "Neighbours: \n"
            + "(Name: Laundry Room; Items: Pointed table; Players: No players available)\n"
            + "(Name: Keeping Room; Items: Billiard Cue; Players: No players available)\n"
            + "(Name: Gym; Items: Rat Poison; Players: No players available)\n"
            + "(Name: Root Cellar; Items: Axle; Players: Alzo)\n" + "",
        boardgame.lookAround("Jizo"));
  }

  @Test
  public void testLookAroundforPlayerVisible() {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");

    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    List<ItemImpl> playeritem = new ArrayList<>();
    ItemImpl itemobj2 = new ItemImpl(2, "Crepe Pan");
    playeritem.add(itemobj);
    playeritem.add(itemobj2);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    boardgame.movePet("Library");
    assertEquals("LookAround (Name: Music Room;\n" + " Items: Pesticide;\n" + " Players: Rohith;\n"
        + " Neighbours: Living Room, Attic, Washroom, Playzone)\n" + "\n" + "\n" + "Neighbours: \n"
        + "(Name: Living Room; Items: Sofa Edge; Players: Anir)\n"
        + "(Name: Attic; Items: Sharp Knife; Players: No players available)\n"
        + "(Name: Washroom; Items: Piece of Rope; Players: No players available)\n"
        + "(Name: Playzone; Items: Monkey Hand; Players: Anon)\n"
        + "Target Character Current Room: Garden\n" + "", boardgame.lookAround("Rohith"));
  }

  @Test
  public void testPlayerTurns() {
    assertEquals(
        "Turn of: Anon; Current Room: Playzone; Items: No items on the player)\n"
            + ", PlayerType: false; Target Current Location: Drawing Room",
        boardgame.getPlayerNextTurn("Rohith"));
  }

  @Test
  public void testPetEnteringSpaceAsTarget() {
    assertEquals(world.getTargetCharacterImpl().getCurrentRoom().getName(),
        world.getTargetPetImpl().getCurrentRoom().getName());
  }

  @Test
  public void testComputerPlayerMovePet() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    items.stream().forEach(s -> itemsstring = itemsstring + ", " + s.getName());
    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    PlayerImpl player = new PlayerImpl("John", space, 2, playeritem, false);
    RandomClass rclass = new RandomClass(2);
    assertEquals("Valid Arguments",
        "Executed Move Pet: Pet has been moved to Garden\n"
            + "Target Character Current Room: Music Room\n" + "",
        boardgame.playTurnComputerPlayer("comp1"));
  }

  @Test
  public void testComputerPlayerAttackFirst() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    RandomClass rclass = new RandomClass(2);
    assertEquals(
        "Valid Arguments", "Turn of comp2\n" + "Target Character hit successfully.\n"
            + "Health got decreased by 1.\n" + "Current Health: 69\n" + "\n" + "",
        boardgame.playTurnComputerPlayer("comp2"));
  }

  @Test
  public void testDisplaytargetLocationforEachturn() {
    assertEquals("Valid Arguments", "Music Room", boardgame.movePlayer(5, 9));

    assertEquals("Valid Arguments", "Garden", boardgame.pickItem("David", "Big Red Hammer"));

    assertEquals("Valid Arguments",
        "Pet has been moved to Library\nTarget Character Current Room: Laundry Room\n",
        boardgame.movePet("Library"));

    assertEquals("Valid Arguments",
        "LookAround (Name: Dining Room;\n" + " Items: Glass cutter, Trowel;\n"
            + " Players: John, comp1;\n" + " Neighbours: Drawing Room, Kitchen, Garden, Pantry)\n"
            + "\n" + "\n" + "Neighbours: \n"
            + "(Name: Drawing Room; Items: Divider; Players: comp2)\n"
            + "(Name: Kitchen; Items: Bad Cream; Players: No players available)\n"
            + "(Name: Garden; Items: No items available; Players: David)\n"
            + "(Name: Pantry; Items: No items available; Players: No players available)\n"
            + "Target Character Current Room: Sunroom\n" + "",
        boardgame.lookAround("John"));

    assertEquals("Valid Arguments",
        "Target Character attack stopped as the attack is being seen by other players.\n"
            + "Target Character Current Room: Living Room\n" + "",
        boardgame.attackTarget("remo", "poke"));
  }

  @Test
  public void testComputerPlayerAttackFail() {

    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);

    ItemImpl itemobj = new ItemImpl(6, "Pesticide");
    List<ItemImpl> items = new ArrayList<>();
    items.add(itemobj);

    List<ItemImpl> playeritem = new ArrayList<>();
    playeritem.addAll(items);

    SpaceImpl space = new SpaceImpl(1, coordinates, "Music Room", items);
    boardgame.movePet("Library");
    boardgame.addPlayer("comp3", space.getName(), 3, playeritem, false);
    RandomClass rclass = new RandomClass();
    assertEquals("Valid Arguments",
        "Turn of comp3\n" + "Target Character attack stopped as the \n"
            + "attack is being seen by other players.\n" + "",
        boardgame.playTurnComputerPlayer("comp3"));
  }

  @Test
  public void testMovePetDfs() {
    assertEquals("Valid Next Room", "Living Room", boardgame.petMovementDfs("Drawing Room"));
    assertEquals("Valid Next Room", "Music Room", boardgame.petMovementDfs("Living Room"));
    assertEquals("Valid Next Room", "Attic", boardgame.petMovementDfs("Music Room"));
    assertEquals("Valid Next Room", "Playzone", boardgame.petMovementDfs("Attic"));
    assertEquals("Valid Next Room", "Gym", boardgame.petMovementDfs("Playzone"));
    assertEquals("Valid Next Room", "Laundry Room", boardgame.petMovementDfs("Gym"));
    assertEquals("Valid Next Room", "Garden", boardgame.petMovementDfs("Laundry Room"));
    assertEquals("Valid Next Room", "Dining Room", boardgame.petMovementDfs("Garden"));
    assertEquals("Valid Next Room", "Pantry", boardgame.petMovementDfs("Dining Room"));
    assertEquals("Valid Next Room", "Parlor", boardgame.petMovementDfs("Pantry"));
  }

  @Test
  public void testDfsBackTrackIfAllNeighboursVisited() {
    assertEquals("Valid Next Room", "Living Room", boardgame.petMovementDfs("Drawing Room"));
    assertEquals("Valid Next Room", "Music Room", boardgame.petMovementDfs("Living Room"));
    assertEquals("Valid Next Room", "Attic", boardgame.petMovementDfs("Music Room"));
    assertEquals("Valid Next Room", "Playzone", boardgame.petMovementDfs("Attic"));
    assertEquals("Valid Next Room", "Gym", boardgame.petMovementDfs("Playzone"));
    assertEquals("Valid Next Room", "Laundry Room", boardgame.petMovementDfs("Gym"));
    assertEquals("Valid Next Room", "Garden", boardgame.petMovementDfs("Laundry Room"));
    assertEquals("Valid Next Room", "Dining Room", boardgame.petMovementDfs("Garden"));
    assertEquals("Valid Next Room", "Pantry", boardgame.petMovementDfs("Dining Room"));
    assertEquals("Valid Next Room", "Parlor", boardgame.petMovementDfs("Pantry"));
    assertEquals("Valid Next Room", "Library", boardgame.petMovementDfs("Parlor"));
    assertEquals("Valid Next Room", "Keeping Room", boardgame.petMovementDfs("Library"));
    assertEquals("Valid Next Room", "Sunroom", boardgame.petMovementDfs("Keeping Room"));
    assertEquals("Valid Next Room", "Root Cellar", boardgame.petMovementDfs("Sunroom"));
    assertEquals("Valid Next Room", "Nursery", boardgame.petMovementDfs("Root Cellar"));
    assertEquals("Valid Next Room", "Root Cellar", boardgame.petMovementDfs("Nursery"));
    assertEquals("Valid Next Room", "Sunroom", boardgame.petMovementDfs("Library"));
  }

  @Test
  public void testMovePetDfsMoveSpace() {
    boardgame.movePlayer(4, 3);
    assertEquals("Valid Arguments", "Garden", boardgame.petMovementDfs("Library"));
  }

  @Test
  public void testMovePetDfsPickItem() {
    boardgame.pickItem("Anir", "Sofa Edge");
    assertEquals("Valid Arguments", "Garden", boardgame.petMovementDfs("Keeping Room"));
  }

  @Test
  public void testMovePetDfsLookAround() {
    boardgame.lookAround("John");
    assertEquals("Valid Arguments", "Attic", boardgame.petMovementDfs("Music Room"));
  }

  @Test
  public void testMovePetDfsAttackTarget() {
    boardgame.getNextTargetCharacterRoom();
    boardgame.attackTarget("Rohith", "Crepe Pan");
    assertEquals("Valid Arguments", "Laundry Room", boardgame.petMovementDfs("Sunroom"));
  }

  @Test
  public void testTargetHealthReductionforPoke() {
    boardgame.movePet("Music Room");
    assertEquals("Valid Arguments",
        "Target Character hit successfully.\n" + "Health got decreased by 1.\n"
            + "Current Health: 69\n" + "\n" + "Target Character Current Room: Garden\n" + "",
        boardgame.attackTarget("Rohith", "poke"));
  }

  @Test
  public void testTargetHealthReductionforItemDamage() {
    boardgame.movePet("Music Room");
    assertEquals("Valid Arguments", "Target Character hit successfully.\n"
        + "Health got decreased by 2.\n" + "Current Health: 68\n" + "\n" + "",
        boardgame.attackTarget("Rohith", "Crepe Pan"));
  }

  @Test(expected = IllegalStateException.class)
  public void testTargetHealthReductionforInvalidItem() {
    boardgame.movePet("Music Room");
    boardgame.attackTarget("Rohith", "Happyeasygo");
  }

  @Test
  public void testItemRemovedAfterUsed() {
    boardgame.movePet("Music Room");
    boardgame.attackTarget("Rohith", "Crepe Pan");
    assertEquals("Valid Arguments",
        "Player Info (Name: Rohith; Current Room: Music Room; Items: Pesticide)\n" + "",
        boardgame.getPlayerInfo("Rohith"));
  }

  @Test
  public void testcreateGraphicalRepresentation() {
    boardgame.createGraphicalRepresentation();
  }

  @Test
  public void testGetName() {
    assertEquals("World Name", "Max's Villa", boardgame.getName());
  }

  @Test
  public void testEquals() {
    assertTrue(boardgame.equals(boardgame));
    assertTrue(boardgame.equals(new BoardGameImpl(world.getTargetCharacterImpl(), world.getName(),
        world.getSpaceList(), world.getWorldCoordinates(), world.getTargetPetImpl(),
        world.getRandomClassRef(), world.getTurns())));
    assertFalse(boardgame.equals(boardgame2));
  }

  @Test
  public void testHashCode() {
    assertEquals(boardgame.hashCode(),
        new BoardGameImpl(world.getTargetCharacterImpl(), world.getName(), world.getSpaceList(),
            world.getWorldCoordinates(), world.getTargetPetImpl(), world.getRandomClassRef(),
            world.getTurns()).hashCode());
  }
}
