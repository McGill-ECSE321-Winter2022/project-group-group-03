/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.util.*;

// line 71 "../../../../../../model.ump"
// line 128 "../../../../../../model.ump"
// line 176 "../../../../../../model.ump"
// line 209 "../../../../../../model.ump"
@Entity
public class Item
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Item> itemsByName = new HashMap<>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  @Id
  @Column(name="Item_id")
  private String name;
  @Column(name="Item_purchasable")
  private boolean purchasable;
  @Column(name="Item_price")
  private int price;
  @Column(name="Item_description")
  private String description;
  @Column(name="Item_stock")
  private int stock;
  @Column(name="Item_totalPurchased")
  private int totalPurchased;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aName, boolean aPurchasable, int aPrice, String aDescription, int aStock, int aTotalPurchased)
  {
    name=aName;
    purchasable = aPurchasable;
    price = aPrice;
    description = aDescription;
    stock = aStock;
    totalPurchased = aTotalPurchased;
  }
  public Item(){}

  //------------------------
  // INTERFACE
  //------------------------

  public void setName(String aName)
  {
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return;
    }
    if (hasWithName(aName)) {
      return;
    }
    name = aName;
    if (anOldName != null) {
      itemsByName.remove(anOldName);
    }
    itemsByName.put(aName, this);
  }

  public void setPurchasable(boolean aPurchasable){
    purchasable = aPurchasable;
  }

  public void setPrice(int aPrice) {
    price = aPrice;
  }

  public void setDescription(String aDescription) {
    description = aDescription;
  }

  public void setStock(int aStock) {
    stock = aStock;
  }

  public void setTotalPurchased(int aTotalPurchased) {
    totalPurchased = aTotalPurchased;
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