package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import theworld.ItemImpl;
import theworld.PetImpl;
import theworld.SpaceImpl;

/**
 * A JUnit test class for the PetImpl class.
 */
public class PetImplTest {
  private PetImpl petimpl;
  private PetImpl petimpl2;
  private List<Integer> coordinates;
  private SpaceImpl space1;
  private List<ItemImpl> item;

  /**
   * A setup method for initiating a pet object.
   */
  @Before
  public void setup() {
    coordinates = new ArrayList<Integer>();
    item = new ArrayList<ItemImpl>();
    coordinates.add(0);
    coordinates.add(4);
    coordinates.add(7);
    coordinates.add(5);

    space1 = new SpaceImpl(1, coordinates, "Drawing Room", item);

    petimpl = petimplconst("Fortune the cat", space1);
    petimpl2 = petimplconst("Tommy the dog", space1);
  }

  /**
   * Method to return a new object of PetImpl type.
   * 
   * @param name        name of the pet
   * @param currentRoom current room of the pet
   * @return newly created object of PetImpl class
   */
  protected PetImpl petimplconst(String name, SpaceImpl currentRoom) {
    return new PetImpl(name, currentRoom);
  }

  @Test
  public void testGetCurrentRoom() {
    assertEquals("Valid Pet Current room", space1,
        petimplconst("Fortune the cat", space1).getCurrentRoom());
  }

  @Test
  public void testGetName() {
    assertEquals("Valid Pet name", "Fortune the cat",
        petimplconst("Fortune the cat", space1).getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidItemName() {
    petimplconst("", space1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidItemDamage() {
    petimplconst("Fortune the cat", null);
  }

  @Test
  public void testToString() {
    assertEquals("To String Check", "Pet name = Fortune the cat, Current Room: Drawing Room",
        petimpl.toString());
  }

  @Test
  public void testMovePet() {
    List<Integer> coordinates1 = new ArrayList<Integer>();
    coordinates1.add(4);
    coordinates1.add(9);
    coordinates1.add(11);
    coordinates1.add(10);
    List<ItemImpl> item = new ArrayList<ItemImpl>();
    item.add(new ItemImpl(4, "Big Red Hammer"));

    SpaceImpl space2 = new SpaceImpl(2, coordinates1, "Garden", item);

    assertEquals("Test Move Pet", "Pet has been moved to Garden", petimpl.movepet(space2));
  }

  @Test
  public void testEquals() {
    assertTrue(petimpl.equals(petimpl));
    assertTrue(petimpl.equals(new PetImpl("Fortune the cat", space1)));
    assertFalse(petimpl.equals(petimpl2));
  }

  @Test
  public void testHashCode() {
    assertEquals(petimpl.hashCode(), new PetImpl("Fortune the cat", space1).hashCode());
  }
}
