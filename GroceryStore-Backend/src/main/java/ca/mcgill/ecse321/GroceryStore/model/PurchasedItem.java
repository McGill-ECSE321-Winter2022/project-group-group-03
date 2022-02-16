/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.*;

// line 81 "../../../../../../model.ump"
// line 241 "../../../../../../model.ump"
@Entity
public class PurchasedItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PurchasedItem Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int purchasedItemID;
  private int itemQuantity;

  //PurchasedItem Associations

  @ManyToOne
  @JoinColumn(name = "purchasedItem_name")
  private Item item;


  //------------------------
  // CONSTRUCTOR
  //------------------------


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

  public void setItem(Item item) {
    this.item = item;
  }

  public boolean setItemQuantity(int aItemQuantity)
  {
    boolean wasSet = false;
    itemQuantity = aItemQuantity;
    wasSet = true;
    return wasSet;
  }

  public Item getItem() {
    return item;
  }

  public int getPurchasedItemID()
  {
    return purchasedItemID;
  }

  public int getItemQuantity()
  {
    return itemQuantity;
  }


  public String toString()
  {
    return super.toString() + "["+
            "purchasedItemID" + ":" + getPurchasedItemID()+ "," +
            "itemQuantity" + ":" + getItemQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "item = "+(getItem()!=null?Integer.toHexString(System.identityHashCode(getItem())):"null");
  }
}