package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import theworld.ItemImpl;

/**
 * A JUnit test class for the ItemImpl class.
 */

public class ItemImplTest {
  private ItemImpl itemimpl;
  private ItemImpl itemimpl2;

  /**
   * A setup method for initiating an item object.
   */
  @Before
  public void setup() {
    itemimpl = itemimplconst(6, "Pesticide");
    itemimpl2 = itemimplconst(5, "Sharp Knife");
  }

  /**
   * Method to return a new object of ItemImpl type.
   * 
   * @param damage damage of the item
   * @param name   name of the item
   * @return newly created object of ItemImpl class
   */
  protected ItemImpl itemimplconst(int damage, String name) {
    return new ItemImpl(damage, name);
  }

  @Test
  public void testGetItemDamage() {
    assertEquals("Valid Item damage", 6, itemimplconst(6, "Pesticide").getItemdamage());
  }

  @Test
  public void testGetName() {
    assertEquals("Valid Item name", "Pesticide", itemimplconst(6, "Pesticide").getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidItemName() {
    itemimplconst(6, "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidItemDamage() {
    itemimplconst(-6, "Pesticide");
  }

  @Test
  public void testToString() {
    assertEquals("To String Check", "Item Name: Pesticide, Item Damage: 6", itemimpl.toString());
  }

  @Test
  public void testEquals() {
    assertTrue(itemimpl.equals(itemimpl));
    assertTrue(itemimpl.equals(new ItemImpl(6, "Pesticide")));
    assertFalse(itemimpl.equals(itemimpl2));
  }

  @Test
  public void testHashCode() {
    assertEquals(itemimpl.hashCode(), new ItemImpl(6, "Pesticide").hashCode());
  }
}
