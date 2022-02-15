/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.util.*;

// line 85 "../../../../../../model.ump"
// line 132 "../../../../../../model.ump"
// line 214 "../../../../../../model.ump"
@Entity
public abstract class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "O_confirmationNumber")
  private int confirmationNumber;
  @Column(name = "O_totalCost")
  private int totalCost;

  //Order Associations
  @OneToMany
  private List<PurchasedItem> purchasedItem;
  @ManyToOne
  private Store store;
  @ManyToOne
  private Employee employee;
  @ManyToOne
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(int aConfirmationNumber, int aTotalCost, Store aStore, Employee aEmployee, Customer aCustomer)
  {
    confirmationNumber = aConfirmationNumber;
    totalCost = aTotalCost;
    purchasedItem = new ArrayList<PurchasedItem>();
    setStore(aStore);

    setEmployee(aEmployee);

    setCustomer(aCustomer);

  }

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

  public int getConfirmationNumber()
  {
    return confirmationNumber;
  }

  public int getTotalCost()
  {
    return totalCost;
  }
  /* Code from template association_GetMany */
  public PurchasedItem getPurchasedItem(int index)
  {
    PurchasedItem aPurchasedItem = purchasedItem.get(index);
    return aPurchasedItem;
  }


  //@OneToMany
  public List<PurchasedItem> getPurchasedItem()
  {
    List<PurchasedItem> newPurchasedItem = Collections.unmodifiableList(purchasedItem);
    return newPurchasedItem;
  }

  public void setPurchasedItem(List<PurchasedItem> aPurchasedItem){
    this.purchasedItem = aPurchasedItem;
  }

  public int numberOfPurchasedItem()
  {
    int number = purchasedItem.size();
    return number;
  }

  public boolean hasPurchasedItem()
  {
    boolean has = purchasedItem.size() > 0;
    return has;
  }

  public int indexOfPurchasedItem(PurchasedItem aPurchasedItem)
  {
    int index = purchasedItem.indexOf(aPurchasedItem);
    return index;
  }
  /* Code from template association_GetOne */
  //@ManyToOne
  public Store getStore()
  {
    return store;
  }
  /* Code from template association_GetOne */
  //@ManyToOne
  public Employee getEmployee()
  {
    return employee;
  }
  /* Code from template association_GetOne */
  //@ManyToOne
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPurchasedItem()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addPurchasedItem(PurchasedItem aPurchasedItem)
  {
    boolean wasAdded = false;
    if (purchasedItem.contains(aPurchasedItem)) { return false; }
    purchasedItem.add(aPurchasedItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePurchasedItem(PurchasedItem aPurchasedItem)
  {
    boolean wasRemoved = false;
    if (purchasedItem.contains(aPurchasedItem))
    {
      purchasedItem.remove(aPurchasedItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPurchasedItemAt(PurchasedItem aPurchasedItem, int index)
  {  
    boolean wasAdded = false;
    if(addPurchasedItem(aPurchasedItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPurchasedItem()) { index = numberOfPurchasedItem() - 1; }
      purchasedItem.remove(aPurchasedItem);
      purchasedItem.add(index, aPurchasedItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePurchasedItemAt(PurchasedItem aPurchasedItem, int index)
  {
    boolean wasAdded = false;
    if(purchasedItem.contains(aPurchasedItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPurchasedItem()) { index = numberOfPurchasedItem() - 1; }
      purchasedItem.remove(aPurchasedItem);
      purchasedItem.add(index, aPurchasedItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPurchasedItemAt(aPurchasedItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public void setStore(Store aStore)
  {
    if (aStore == null)
    {
      return ;
    }

    Store existingStore = store;
    store = aStore;
    if (existingStore != null && !existingStore.equals(aStore))
    {
      existingStore.removeOrder(this);
    }
    store.addOrder(this);
  }
  /* Code from template association_SetOneToMany */
  public void setEmployee(Employee aEmployee)
  {
    if (aEmployee == null)
    {
      return;
    }

    Employee existingEmployee = employee;
    employee = aEmployee;
    if (existingEmployee != null && !existingEmployee.equals(aEmployee))
    {
      existingEmployee.removeOrder(this);
    }
    employee.addOrder(this);

  }
  /* Code from template association_SetOneToMany */
  public void setCustomer(Customer aCustomer)
  {
    if (aCustomer == null)
    {
      return ;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeOrder(this);
    }
    customer.addOrder(this);
  }

  public void delete()
  {
    purchasedItem.clear();
    Store placeholderStore = store;
    this.store = null;
    if(placeholderStore != null)
    {
      placeholderStore.removeOrder(this);
    }
    Employee placeholderEmployee = employee;
    this.employee = null;
    if(placeholderEmployee != null)
    {
      placeholderEmployee.removeOrder(this);
    }
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "confirmationNumber" + ":" + getConfirmationNumber()+ "," +
            "totalCost" + ":" + getTotalCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}