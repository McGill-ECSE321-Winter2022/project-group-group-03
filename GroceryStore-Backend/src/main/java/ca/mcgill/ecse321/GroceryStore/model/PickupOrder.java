///*PLEASE DO NOT EDIT THIS CODE*/
///*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
//
//package ca.mcgill.ecse321.GroceryStore.model;
//import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;
//
//import javax.persistence.*;
//import java.util.*;
//
//// line 1 "../../../../../../GroceryStoreStates.ump"
//// line 51 "../../../../../../GroceryStoreStates.ump"
//// line 66 "../../../../../../GroceryStoreStates.ump"
//// line 102 "../../../../../../model.ump"
//// line 188 "../../../../../../model.ump"
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Entity
//public class PickupOrder extends Order
//{
//  public PickupOrder() {
//
//  }
//
//  //------------------------
//  // ENUMERATIONS
//  //------------------------
//
//  public enum PaymentMethod { CreditCard, Cash }
//
//  //------------------------
//  // MEMBER VARIABLES
//  //------------------------
//
//  //PickupOrder Attributes
//  @Enumerated
//  @Column(name = "paymentMethod", nullable = false)
//  private PaymentMethod paymentMethod;
//
//  //PickupOrder State Machines
//  public enum PickupStatus { InCart, Ordered, Prepared, PickedUp }
//  @Enumerated
//  @Column(name = "pickupStatus", nullable = false)
//  private PickupStatus pickupStatus;
//
//  //------------------------
//  // CONSTRUCTOR
//  //------------------------
//
//  public PickupOrder(int aConfirmationNumber, int aTotalCost, Store aStore, PaymentMethod aPaymentMethod)
//  {
//    super(aConfirmationNumber, aTotalCost, aStore);
//    paymentMethod = aPaymentMethod;
//    setPickupStatus(PickupStatus.InCart);
//  }
//
//  //------------------------
//  // INTERFACE
//  //------------------------
//
//  public boolean setPaymentMethod(PaymentMethod aPaymentMethod)
//  {
//    boolean wasSet = false;
//    paymentMethod = aPaymentMethod;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public PaymentMethod getPaymentMethod()
//  {
//    return paymentMethod;
//  }
//
//  public String getPickupStatusFullName()
//  {
//    String answer = pickupStatus.toString();
//    return answer;
//  }
//
//  public PickupStatus getPickupStatus()
//  {
//    return pickupStatus;
//  }
//
//  public boolean order()
//  {
//    boolean wasEventProcessed = false;
//
//    PickupStatus aPickupStatus = pickupStatus;
//    switch (aPickupStatus)
//    {
//      case InCart:
//        setPickupStatus(PickupStatus.Ordered);
//        wasEventProcessed = true;
//        break;
//      default:
//        // Other states do respond to this event
//    }
//
//    return wasEventProcessed;
//  }
//
//  public boolean prepare()
//  {
//    boolean wasEventProcessed = false;
//
//    PickupStatus aPickupStatus = pickupStatus;
//    switch (aPickupStatus)
//    {
//      case Ordered:
//        setPickupStatus(PickupStatus.Prepared);
//        wasEventProcessed = true;
//        break;
//      default:
//        // Other states do respond to this event
//    }
//
//    return wasEventProcessed;
//  }
//
//  public boolean pay()
//  {
//    boolean wasEventProcessed = false;
//
//    PickupStatus aPickupStatus = pickupStatus;
//    switch (aPickupStatus)
//    {
//      case Prepared:
//        setPickupStatus(PickupStatus.PickedUp);
//        wasEventProcessed = true;
//        break;
//      default:
//        // Other states do respond to this event
//    }
//
//    return wasEventProcessed;
//  }
//
//  private void setPickupStatus(PickupStatus aPickupStatus)
//  {
//    pickupStatus = aPickupStatus;
//  }
//
//  public void delete()
//  {
//    super.delete();
//  }
//
//
//  public String toString()
//  {
//    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
//            "  " + "paymentMethod" + "=" + (getPaymentMethod() != null ? !getPaymentMethod().equals(this)  ? getPaymentMethod().toString().replaceAll("  ","    ") : "this" : "null");
//  }
//}