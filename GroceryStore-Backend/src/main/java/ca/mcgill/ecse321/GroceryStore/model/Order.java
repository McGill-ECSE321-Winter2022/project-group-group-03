/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.util.*;

// line 87 "../../../../../../model.ump"
// line 135 "../../../../../../model.ump"
// line 218 "../../../../../../model.ump"
//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes

  @Id
  //@GeneratedValue(strategy = GenerationType.TABLE)
  private int confirmationNumber;
  private int totalCost;

  //Order Associations

  @ManyToOne
  @JoinColumn(name = "order_storeID", unique = true)
  private Store store;

  @OneToMany
  @JoinColumn(name = "order_purchasedItemID")
  private List<PurchasedItem> purchasedItem;

//------------------------
  // CONSTRUCTOR
  //------------------------


  public Order() {

  }

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

  public Store getStore(){
    return store;
  }

  public List<PurchasedItem> getPurchasedItem() {
    return purchasedItem;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public void setPurchasedItem(List<PurchasedItem> purchasedItem) {
    this.purchasedItem = purchasedItem;
  }

  public int getConfirmationNumber()
  {
    return confirmationNumber;
  }

  public int getTotalCost()
  {
    return totalCost;
  }


}