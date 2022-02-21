/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

// line 72 "../../../../../../model.ump"
// line 130 "../../../../../../model.ump"
// line 178 "../../../../../../model.ump"
// line 213 "../../../../../../model.ump"
@Entity
public class Item
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Item> itemsByName = new HashMap<String, Item>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  @Id
  private String name;
  private boolean purchasable;
  private int price;
  private String description;
  private int stock;
  private int totalPurchased;


  //------------------------
  // CONSTRUCTOR
  //------------------------

   public Item() {

  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      itemsByName.remove(anOldName);
    }
    itemsByName.put(aName, this);
    return wasSet;
  }

  public boolean setPurchasable(boolean aPurchasable)
  {
    boolean wasSet = false;
    purchasable = aPurchasable;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setStock(int aStock)
  {
    boolean wasSet = false;
    stock = aStock;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalPurchased(int aTotalPurchased)
  {
    boolean wasSet = false;
    totalPurchased = aTotalPurchased;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Item getWithName(String aName)
  {
    return itemsByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  /**
   * stuff that can be bought online, if false only available in person
   */
  public boolean getPurchasable()
  {
    return purchasable;
  }

  public int getPrice()
  {
    return price;
  }

  public String getDescription()
  {
    return description;
  }

  public int getStock()
  {
    return stock;
  }

  public int getTotalPurchased()
  {
    return totalPurchased;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isPurchasable()
  {
    return purchasable;
  }

  public void delete()
  {
    itemsByName.remove(getName());
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "purchasable" + ":" + getPurchasable()+ "," +
            "price" + ":" + getPrice()+ "," +
            "description" + ":" + getDescription()+ "," +
            "stock" + ":" + getStock()+ "," +
            "totalPurchased" + ":" + getTotalPurchased()+ "]";
  }
}