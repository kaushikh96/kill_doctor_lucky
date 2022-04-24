package theworld;

import java.util.Objects;

/**
 * Item Implementation , defines all features of Item Interface.
 */
public class ItemImpl implements ItemInterface {
  private final String name;
  private final int itemdamage;

  /**
   * Constructor for ItemImpl.
   *
   * @param itemdamage Damage of the Item
   * @param name       Name of room
   */
  public ItemImpl(int itemdamage, String name) {
    if (itemdamage < 0) {
      throw new IllegalArgumentException("Item damage cannot be negative");
    }
    if (name == null || name.trim().equals("")) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
    this.itemdamage = itemdamage;
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getItemdamage() {
    return this.itemdamage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ItemImpl)) {
      return false;
    }

    ItemImpl that = (ItemImpl) o;
    return (this.itemdamage == that.itemdamage && this.name.equals(that.name));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.itemdamage);
  }

  @Override
  public String toString() {
    return String.format("Item Name: %s, Item Damage: %d", this.getName(), this.getItemdamage());
  }
}
