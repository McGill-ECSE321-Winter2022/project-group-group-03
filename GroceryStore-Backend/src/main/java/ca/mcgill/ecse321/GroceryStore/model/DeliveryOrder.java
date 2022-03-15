/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;

import javax.persistence.*;

// line 19 "../../../../../../GroceryStoreStates.ump"
// line 56 "../../../../../../GroceryStoreStates.ump"
// line 96 "../../../../../../model.ump"
// line 183 "../../../../../../model.ump"
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class DeliveryOrder extends Order
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int SHIPPINGFEE = 30;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DeliveryOrder Attributes
  private String shippingAddress;
  private boolean outOfTown;
  public DeliveryOrder() {
    super();
  }

  //DeliveryOrder State Machines
  public enum ShippingStatus { InCart, Ordered, Prepared, Delivered }
  @Enumerated
  @Column(nullable = false)
  private ShippingStatus shippingStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

//  public DeliveryOrder(int aConfirmationNumber, int aTotalCost, Store aStore, String aShippingAddress)
//  {
//    super(aConfirmationNumber, aTotalCost, aStore);
//    shippingAddress = aShippingAddress;
//    setShippingStatus(ShippingStatus.InCart);
//  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setShippingAddress(String aShippingAddress)
  {
    this.shippingAddress = aShippingAddress;
  }

  public String getShippingAddress()
  {
    return shippingAddress;
  }

  public String getShippingStatusFullName()
  {
    String answer = shippingStatus.toString();
    return answer;
  }
  public boolean isOutOfTown(){
    return this.outOfTown;
  }
  public void setIsOutOfTown(boolean outOfTown1){
    this.outOfTown = outOfTown1;
  }
  public ShippingStatus getShippingStatus()
  {
    return shippingStatus;
  }

  public boolean order()
  {
    boolean wasEventProcessed = false;
    
    ShippingStatus aShippingStatus = shippingStatus;
    switch (aShippingStatus)
    {
      case InCart:
        setShippingStatus(ShippingStatus.Ordered);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pay()
  {
    boolean wasEventProcessed = false;
    
    ShippingStatus aShippingStatus = shippingStatus;
    switch (aShippingStatus)
    {
      case Ordered:
        setShippingStatus(ShippingStatus.Prepared);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean deliver()
  {
    boolean wasEventProcessed = false;
    
    ShippingStatus aShippingStatus = shippingStatus;
    switch (aShippingStatus)
    {
      case Prepared:
        setShippingStatus(ShippingStatus.Delivered);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public void setShippingStatus(ShippingStatus aShippingStatus)
  {
    shippingStatus = aShippingStatus;
  }

//  public void delete()
//  {
//    super.delete();
//  }


  public String toString()
  {
    return super.toString() + "["+
            "shippingAddress" + ":" + getShippingAddress()+ "]";
  }
}