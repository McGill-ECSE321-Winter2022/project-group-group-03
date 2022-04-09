/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
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

  @Column(name = "image")
  private String image;




//------------------------
  // CONSTRUCTOR
  //------------------------

  public Item() {

  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setName(String aName)
  {
    this.name = aName;
  }

  public void setPurchasable(boolean aPurchasable)
  {

    this.purchasable = aPurchasable;
  }

  public void setPrice(int aPrice)
  {
    this.price = aPrice;

  }

  public void setDescription(String aDescription)
  {
    this.description = aDescription;
  }

  public void setStock(int aStock)
  {
    this.stock = aStock;
  }

  public void setTotalPurchased(int aTotalPurchased)
  {
    this.totalPurchased = aTotalPurchased;
  }

  public void setImage(String image) {
    this.image = image;
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

  public String getImage() {
    return image;
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