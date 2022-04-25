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
 * A JUnit test class for the PlayerImpl class.
 */
public class PlayerImplTest {
  private PlayerImpl playerimpl;
  private PlayerImpl playerimpl2;
  private PlayerImpl playerimpl3;
  private SpaceImpl currentroom;
  private List<Integer> coordinates;
  private List<ItemImpl> item;
  private List<ItemImpl> item2;
  private List<ItemImpl> playeritem;
  private SpaceImpl currentroom2;

  /**
   * Setup method that creates 2 players.
   */
  @Before
  public void setup() {
    coordinates = new ArrayList<Integer>();
    item = new ArrayList<ItemImpl>();
    item2 = new ArrayList<ItemImpl>();
    playeritem = new ArrayList<ItemImpl>();
    coordinates.add(0);
    coordinates.add(0);
    coordinates.add(5);
    coordinates.add(3);
    item.add(new ItemImpl(6, "Sofa Edge"));

    currentroom = new SpaceImpl(1, coordinates, "Living Room", item);
    currentroom2 = new SpaceImpl(1, coordinates, "Living Room", item2);
    playerimpl = playerimplconst("John", currentroom, 2, playeritem, false);
    playerimpl2 = playerimplconst("Comp", currentroom, 2, playeritem, true);
    playerimpl3 = playerimplconst("John", currentroom2, 2, playeritem, true);
  }

  /**
   * Reads file and parses it into world.
   * 
   * @return world object
   * 
   */
  public static BoardGameImpl readfile() {
    String mansiondimensions = "25 16 Max's Villa\r\n" + "70 Dr.Max\r\n" + "Fortune the cat\r\n"
        + "20\r\n" + "0 4 7 5 Drawing Room\r\n" + "6 0 11 2 Music Room\r\n" + "4 9 11 10 Garden\r\n"
        + "12 9 19 10 Laundry Room\r\n" + "20 9 24 11 Sunroom\r\n" + "0 0 5 3 Living Room\r\n"
        + "1 6 7 8 Dining Room\r\n" + "1 9 3 12 Pantry\r\n" + "12 1 16 2 Attic\r\n"
        + "0 13 3 15 Parlor\r\n" + "17 0 22 3 Powder Room\r\n" + "4 11 10 15 Library\r\n"
        + "15 4 22 8 Gym\r\n" + "8 6 14 8 Kitchen\r\n" + "11 11 19 13 Keeping Room\r\n"
        + "20 12 24 13 Root Cellar\r\n" + "23 2 24 4 Wine Cellar\r\n" + "8 3 10 5 Washroom\r\n"
        + "11 3 14 5 Playzone\r\n" + "11 14 24 15 Nursery\r\n" + "19\r\n" + "8 5 Sharp Knife\r\n"
        + "0 7 Divider\r\n" + "1 6 Pesticide\r\n" + "15 3 Axle\r\n" + "3 8 Pointed table\r\n"
        + "4 2 Drying fan\r\n" + "5 6 Sofa Edge\r\n" + "6 5 Glass cutter\r\n"
        + "14 4 Billiard Cue\r\n" + "12 2 Rat Poison\r\n" + "6 2 Trowel\r\n"
        + "2 4 Big Red Hammer\r\n" + "16 2 Pinking Shears\r\n" + "10 3 Duck Decoy\r\n"
        + "13 2 Bad Cream\r\n" + "18 2 Monkey Hand\r\n" + "11 2 Tight Hat\r\n"
        + "17 2 Piece of Rope\r\n" + "9 3 Silken Cord";
    String[] worldattributes = mansiondimensions.split("\r\n");

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
    RandomClass randomref = new RandomClass(1);
    BoardGameImpl world = new BoardGameImpl(target, worldattributes1[2], roomlist, worldcoordinates,
        targetpet, randomref, 10);
    return world;
  }

  /**
   * Method to return a new object of PlayerImpl type.
   * 
   * @param name             name of the player
   * @param currentRoom      current room of the player
   * @param itemcapacity     item capacity of the player
   * @param items            list of items on the player
   * @param isComputerPlayer boolean attribute to determine whether or not
   *                         Computer Player
   * @return newly created object of PlayerImpl class
   */
  protected PlayerImpl playerimplconst(String name, SpaceImpl currentRoom, int itemCapacity,
      List<ItemImpl> items, boolean isComputerPlayer) {
    return new PlayerImpl(name, currentRoom, itemCapacity, items, isComputerPlayer);
  }

  @Test
  public void testGetName() {
    assertEquals("Valid Name", "John", playerimpl.getName());
  }

  @Test
  public void testGetItemCapacity() {
    assertEquals("Valid Item capacity", 2, playerimpl.getItemCapacity());
  }

  @Test
  public void testGetItems() {
    assertEquals("Valid items", playeritem, playerimpl.getItems());
  }

  @Test
  public void testIsComputerPlayer() {
    assertEquals("Valid computer player", false, playerimpl.isComputerPlayer());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGetName() {
    playerimplconst("", currentroom, 2, item, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidItemCapacity() {
    playerimplconst("John", currentroom, -2, item, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidItemsList() {
    playerimplconst("John", currentroom, 2, null, false);
  }

  @Test
  public void testmoveplayer() {
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

    assertEquals("Valid movement of current player", "Music Room",
        playerimpl.movePlayer(neighbourspaces, "Music Room"));
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidmove() {
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
    playerimpl.movePlayer(neighbourspaces, "Attic");
  }

  @Test(expected = IllegalStateException.class)
  public void testpickifnoitems() {

    playerimpl3.pickItem("Sofa Edge");
  }

  @Test
  public void testLookAround() {
    assertEquals("Player Info: Name: John, Space name = Living Room, Items: []\n" + "",
        playerimpl.toString());
  }

  @Test
  public void testToString() {
    assertEquals("To String Check",
        "Player Info: Name: John, Space name = Living Room, Items: []\n" + "",
        playerimpl.toString());
  }

  @Test
  public void testEquals() {
    assertTrue(playerimpl.equals(playerimpl));
    assertTrue(playerimpl.equals(new PlayerImpl("John", currentroom, 2, playeritem, false)));
    assertFalse(playerimpl.equals(playerimpl2));
  }

  @Test
  public void testHashCode() {
    assertEquals(playerimpl.hashCode(),
        new PlayerImpl("John", currentroom, 2, playeritem, false).hashCode());
  }
}
