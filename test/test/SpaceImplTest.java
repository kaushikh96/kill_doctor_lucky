package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import theworld.ItemImpl;
import theworld.SpaceImpl;

/**
 * A JUnit test class for the SpaceImpl class.
 */
public class SpaceImplTest {
  private List<Integer> coordinates;
  private List<ItemImpl> item;
  private SpaceImpl spaceimpl;
  private SpaceImpl spaceimpl2;

  /**
   * Setup method that creates 2 spaces.
   */
  @Before
  public void setup() {

    coordinates = new ArrayList<Integer>();
    item = new ArrayList<ItemImpl>();
    coordinates.add(6);
    coordinates.add(0);
    coordinates.add(11);
    coordinates.add(2);

    spaceimpl = spaceimplconst(1, coordinates, "Music Room", item);
    spaceimpl2 = spaceimplconst(2, coordinates, "Garden", item);
  }

  /**
   * Method to return a new object of SpaceImpl type.
   * 
   * @param id       id of the space
   * @param location coordinates of the space
   * @param name     name of the space
   * @param items    items in the space
   * @return newly created object of SpaceImpl class
   */
  protected SpaceImpl spaceimplconst(int roomid, List<Integer> location, String name,
      List<ItemImpl> items) {
    return new SpaceImpl(roomid, location, name, items);
  }

  @Test
  public void testGetRoomid() {
    assertEquals("Valid Item damage", 1,
        spaceimplconst(1, coordinates, "Music Room", item).getRoomId());
  }

  @Test
  public void testGetName() {
    assertEquals("Valid Item name", "Music Room",
        spaceimplconst(1, coordinates, "Music Room", item).getName());
  }

  @Test
  public void testGetLocation() {
    assertEquals("Valid location", coordinates,
        spaceimplconst(1, coordinates, "Music Room", item).getRoomLocation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGetRoomId() {
    spaceimplconst(-1, coordinates, "Music Room", item);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRoomName() {
    spaceimplconst(-1, coordinates, "", item);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidLocations() {
    coordinates.set(2, -2);
    spaceimplconst(-1, coordinates, "", item);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidLocations1() {
    List<Integer> coordinates = new ArrayList<>();
    spaceimplconst(-1, coordinates, "", item);
  }

  @Test
  public void testToString() {
    assertEquals("To String Check", "Space name = Music Room", spaceimpl.toString());
  }

  @Test
  public void testEquals() {
    assertTrue(spaceimpl.equals(spaceimpl));
    assertTrue(spaceimpl.equals(new SpaceImpl(1, coordinates, "Music Room", item)));
    assertFalse(spaceimpl.equals(spaceimpl2));
  }

  @Test
  public void testHashCode() {
    assertEquals(spaceimpl.hashCode(),
        new SpaceImpl(1, coordinates, "Music Room", item).hashCode());
  }
}
