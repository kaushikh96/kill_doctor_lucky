package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import driver.RandomClass;
import theworld.BoardGameImpl;
import theworld.ItemImpl;
import theworld.PetImpl;
import theworld.SpaceImpl;
import theworld.TargetCharacterImpl;

/**
 * A JUnit test class for the TargetCharacterImpl class.
 */

public class TargetCharacterImplTest {
  private TargetCharacterImpl targetimpl;
  private TargetCharacterImpl targetimpl2;
  private SpaceImpl spaceimpl;
  private List<Integer> coordinates;
  private List<ItemImpl> targetitems;
  private BoardGameImpl boardgameimpl;

  /**
   * Setup method that creates 2 target character object to compare.
   */
  @Before
  public void setup() {

    coordinates = new ArrayList<Integer>();
    coordinates.add(0);
    coordinates.add(4);
    coordinates.add(7);
    coordinates.add(5);
    ItemImpl spaceitem = new ItemImpl(7, "Divider");
    List<ItemImpl> spaceitemlist = new ArrayList<>();
    spaceitemlist.add(spaceitem);
    spaceimpl = new SpaceImpl(0, coordinates, "Drawing Room", spaceitemlist);
    targetimpl = targetimplconst("Dr. Max", 70, spaceimpl);
    targetimpl2 = targetimplconst("Dr. Max", 75, spaceimpl);
    boardgameimpl = TargetCharacterImplTest.readfile();
  }

  /**
   * Reads file and parses it into world.
   * 
   * @return world object
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
      SpaceImpl demospace = new SpaceImpl(i - 4, roomcoordinates, spaceattr[4], null);
      roomlist.add(demospace);
    }

    for (int j = noofrooms + 5; j <= noofrooms + 4 + noofweapons; j++) {
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
   * Method to return a new object of TargetCharacterImpl type.
   * 
   * @param name        name of the target character
   * @param health      health of the target character
   * @param currentRoom current room of the target character
   * @param items       items on the target character
   * @return newly created object of TargetCharacterImpl class
   */
  protected TargetCharacterImpl targetimplconst(String name, int health, SpaceImpl currentRoom) {
    return new TargetCharacterImpl(name, health, currentRoom);
  }

  @Test
  public void testGetTargetName() {
    assertEquals("Valid Target Name", "Dr. Max",
        targetimplconst("Dr. Max", 70, spaceimpl).getName());
  }

  @Test
  public void testGetTargetHealth() {
    assertEquals("Valid Target Health", 70, targetimplconst("Dr. Max", 70, spaceimpl).getHealth());
  }

  @Test
  public void testGetSpace() {
    assertEquals("Valid Space", spaceimpl,
        targetimplconst("Dr. Max", 70, spaceimpl).getCurrentRoom());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTargetHealth() {
    targetimplconst("Dr. Max", -75, spaceimpl);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTargetName() {
    targetimplconst(null, 70, spaceimpl);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSpace() {
    targetimplconst("Dr. Max", 70, null);
  }

  @Test
  public void testToString() {
    assertEquals("To String Check", "Target Name: Dr. Max, Health: 70, Current Room: Drawing Room",
        targetimpl.toString());
  }

  @Test
  public void testEquals() {
    assertTrue(targetimpl.equals(targetimpl));
    assertTrue(targetimpl.equals(new TargetCharacterImpl("Dr. Max", 70, spaceimpl)));
    assertFalse(targetimpl.equals(targetimpl2));
  }

  @Test
  public void testHashCode() {
    assertEquals(targetimpl.hashCode(),
        new TargetCharacterImpl("Dr. Max", 70, spaceimpl).hashCode());
  }

  @Test
  public void testGetTargetCharacterNextRoom() {
    assertEquals("Valid Target Next Move", "Music Room", targetimplconst("Dr. Max", 70, spaceimpl)
        .getTargetCharacterNextRoom(boardgameimpl.getSpaceList()));
  }
}
