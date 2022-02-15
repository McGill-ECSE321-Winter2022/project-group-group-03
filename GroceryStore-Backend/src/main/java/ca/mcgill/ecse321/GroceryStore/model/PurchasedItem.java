/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.*;

// line 80 "../../../../../../model.ump"
// line 237 "../../../../../../model.ump"
@Entity
public class PurchasedItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PurchasedItem Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="PI_id")
  private int purchasedItemID;
  @Column(name="PI_quantity")
  private int itemQuantity;

  //PurchasedItem Associations
  @ManyToOne
  @JoinColumn(name = "item_name")
  private Item item;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PurchasedItem(int aPurchasedItemID, int aItemQuantity, Item aItem)
  {
    purchasedItemID = aPurchasedItemID;
    itemQuantity = aItemQuantity;
    setItem(aItem);
  }

  public PurchasedItem() {

  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPurchasedItemID(int aPurchasedItemID)
  {
    boolean wasSet = false;
    purchasedItemID = aPurchasedItemID;
    wasSet = true;
    return wasSet;
  }

  public boolean setItemQuantity(int aItemQuantity)
  {
    boolean wasSet = false;
    itemQuantity = aItemQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getPurchasedItemID()
  {
    return purchasedItemID;
  }

  public int getItemQuantity()
  {
    return itemQuantity;
  }
  /* Code from template association_GetOne */
  //@ManyToOne
  public Item getItem()
  {
    return item;
  }
  /* Code from template association_SetUnidirectionalOne */
  public void setItem(Item aNewItem)
  {
    if (aNewItem != null)
    {
      item = aNewItem;
    }
  }

  public void delete()
  {
    item = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "purchasedItemID" + ":" + getPurchasedItemID()+ "," +
            "itemQuantity" + ":" + getItemQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "item = "+(getItem()!=null?Integer.toHexString(System.identityHashCode(getItem())):"null");
  }
}