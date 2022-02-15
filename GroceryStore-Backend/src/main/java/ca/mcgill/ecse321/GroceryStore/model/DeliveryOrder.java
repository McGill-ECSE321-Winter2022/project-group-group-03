/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.*;

// line 19 "../../../../../../GroceryStoreStates.ump"
// line 56 "../../../../../../GroceryStoreStates.ump"
// line 93 "../../../../../../model.ump"
// line 180 "../../../../../../model.ump"
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
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
  private boolean inTown;

  //DeliveryOrder State Machines
  public enum ShippingStatus { InCart, Ordered, Prepared, Delivered }
  private ShippingStatus shippingStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DeliveryOrder(int aConfirmationNumber, int aTotalCost, Store aStore, Employee aEmployee, Customer aCustomer, boolean aInTown)
  {
    super(aConfirmationNumber, aTotalCost, aStore, aEmployee, aCustomer);
    inTown = aInTown;
    setShippingStatus(ShippingStatus.InCart);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInTown(boolean aInTown)
  {
    boolean wasSet = false;
    inTown = aInTown;
    wasSet = true;
    return wasSet;
  }

  public boolean getInTown()
  {
    return inTown;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isInTown()
  {
    return inTown;
  }

  public String getShippingStatusFullName()
  {
    String answer = shippingStatus.toString();
    return answer;
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

  private void setShippingStatus(ShippingStatus aShippingStatus)
  {
    shippingStatus = aShippingStatus;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "inTown" + ":" + getInTown()+ "]";
  }
}