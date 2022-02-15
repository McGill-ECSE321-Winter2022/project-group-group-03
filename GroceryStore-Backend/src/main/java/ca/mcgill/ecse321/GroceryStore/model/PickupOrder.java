/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;
import java.util.*;

// line 1 "../../../../../../GroceryStoreStates.ump"
// line 51 "../../../../../../GroceryStoreStates.ump"
// line 66 "../../../../../../GroceryStoreStates.ump"
// line 99 "../../../../../../model.ump"
// line 185 "../../../../../../model.ump"
public class PickupOrder extends Order
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PaymentMethod { CreditCard, Cash }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PickupOrder State Machines
  public enum PickupStatus { InCart, Ordered, Prepared, PickedUp }
  private PickupStatus pickupStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PickupOrder(int aConfirmationNumber, int aTotalCost, Store aStore, Employee aEmployee, Customer aCustomer)
  {
    super(aConfirmationNumber, aTotalCost, aStore, aEmployee, aCustomer);
    setPickupStatus(PickupStatus.InCart);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getPickupStatusFullName()
  {
    String answer = pickupStatus.toString();
    return answer;
  }

  public PickupStatus getPickupStatus()
  {
    return pickupStatus;
  }

  public boolean order()
  {
    boolean wasEventProcessed = false;
    
    PickupStatus aPickupStatus = pickupStatus;
    switch (aPickupStatus)
    {
      case InCart:
        setPickupStatus(PickupStatus.Ordered);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean prepare()
  {
    boolean wasEventProcessed = false;
    
    PickupStatus aPickupStatus = pickupStatus;
    switch (aPickupStatus)
    {
      case Ordered:
        setPickupStatus(PickupStatus.Prepared);
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
    
    PickupStatus aPickupStatus = pickupStatus;
    switch (aPickupStatus)
    {
      case Prepared:
        setPickupStatus(PickupStatus.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPickupStatus(PickupStatus aPickupStatus)
  {
    pickupStatus = aPickupStatus;
  }

  public void delete()
  {
    super.delete();
  }

}