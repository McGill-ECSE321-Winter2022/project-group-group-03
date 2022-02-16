/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.util.*;

// line 87 "../../../../../../model.ump"
// line 135 "../../../../../../model.ump"
// line 218 "../../../../../../model.ump"
@MappedSuperclass
public abstract class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int confirmationNumber;
  private int totalCost;

  //Order Associations

//  @ManyToOne(cascade = CascadeType.ALL)
//  @JoinColumn(name = "store_store_id", unique = true)
//  private Store store;

//  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//  @JoinColumn(name = "order_confirmation_number")
//  private List<PurchasedItem> purchasedItem = new ArrayList<>();

//------------------------
  // CONSTRUCTOR
  //------------------------

//  public Order(int aConfirmationNumber, int aTotalCost, Store aStore)
//  {
//    confirmationNumber = aConfirmationNumber;
//    totalCost = aTotalCost;
//    if (!setStore(aStore))
//    {
//      throw new RuntimeException("Unable to create Order due to aStore. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
//    }
//    purchasedItem = new ArrayList<PurchasedItem>();
//  }
//
//  public Order() {
//
//  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setConfirmationNumber(int aConfirmationNumber)
  {
    boolean wasSet = false;
    confirmationNumber = aConfirmationNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalCost(int aTotalCost)
  {
    boolean wasSet = false;
    totalCost = aTotalCost;
    wasSet = true;
    return wasSet;
  }

  public int getConfirmationNumber()
  {
    return confirmationNumber;
  }

  public int getTotalCost()
  {
    return totalCost;
  }
  /* Code from template association_GetOne */
//  public Store getStore()
//  {
//    return store;
//  }
//  /* Code from template association_GetMany */
//  public PurchasedItem getPurchasedItem(int index)
//  {
//    PurchasedItem aPurchasedItem = purchasedItem.get(index);
//    return aPurchasedItem;
//  }

//  public List<PurchasedItem> getPurchasedItem()
//  {
//    List<PurchasedItem> newPurchasedItem = Collections.unmodifiableList(purchasedItem);
//    return newPurchasedItem;
//  }
//
//  public int numberOfPurchasedItem()
//  {
//    int number = purchasedItem.size();
//    return number;
//  }
//
//  public boolean hasPurchasedItem()
//  {
//    boolean has = purchasedItem.size() > 0;
//    return has;
//  }

//  public int indexOfPurchasedItem(PurchasedItem aPurchasedItem)
//  {
//    int index = purchasedItem.indexOf(aPurchasedItem);
//    return index;
//  }
//  /* Code from template association_SetUnidirectionalOne */
//  public boolean setStore(Store aNewStore)
//  {
//    boolean wasSet = false;
//    if (aNewStore != null)
//    {
//      store = aNewStore;
//      wasSet = true;
//    }
//    return wasSet;
//  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPurchasedItem()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
//  public boolean addPurchasedItem(PurchasedItem aPurchasedItem)
//  {
//    boolean wasAdded = false;
//    if (purchasedItem.contains(aPurchasedItem)) { return false; }
//    purchasedItem.add(aPurchasedItem);
//    wasAdded = true;
//    return wasAdded;
//  }

//  public boolean removePurchasedItem(PurchasedItem aPurchasedItem)
//  {
//    boolean wasRemoved = false;
//    if (purchasedItem.contains(aPurchasedItem))
//    {
//      purchasedItem.remove(aPurchasedItem);
//      wasRemoved = true;
//    }
//    return wasRemoved;
//  }
  /* Code from template association_AddIndexControlFunctions */
//  public boolean addPurchasedItemAt(PurchasedItem aPurchasedItem, int index)
//  {
//    boolean wasAdded = false;
//    if(addPurchasedItem(aPurchasedItem))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfPurchasedItem()) { index = numberOfPurchasedItem() - 1; }
//      purchasedItem.remove(aPurchasedItem);
//      purchasedItem.add(index, aPurchasedItem);
//      wasAdded = true;
//    }
//    return wasAdded;
//  }

//  public boolean addOrMovePurchasedItemAt(PurchasedItem aPurchasedItem, int index)
//  {
//    boolean wasAdded = false;
//    if(purchasedItem.contains(aPurchasedItem))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfPurchasedItem()) { index = numberOfPurchasedItem() - 1; }
//      purchasedItem.remove(aPurchasedItem);
//      purchasedItem.add(index, aPurchasedItem);
//      wasAdded = true;
//    }
//    else
//    {
//      wasAdded = addPurchasedItemAt(aPurchasedItem, index);
//    }
//    return wasAdded;
//  }

//  public void delete()
//  {
//    store = null;
//    purchasedItem.clear();
//  }


//  public String toString()
//  {
//    return super.toString() + "["+
//            "confirmationNumber" + ":" + getConfirmationNumber()+ "," +
//            "totalCost" + ":" + getTotalCost()+ "]" + System.getProperties().getProperty("line.separator") +
//            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null");
//  }
}