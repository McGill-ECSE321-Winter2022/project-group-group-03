/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.util.*;

/**
 * add a basket option, potentially a state for each order
 */
// line 8 "../../../../../../model.ump"
// line 244 "../../../../../../model.ump"
// line 259 "../../../../../../model.ump"
  @Entity
public class Store
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int MAXPICKUPS = 10;
  public static final int MAXSHIPPING = 10;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Store Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int storeID;
  private String address;
  private int currentActivePickup;
  private int currentActiveDelivery;

  //Store Associations
  @OneToMany
  private List<Employee> employee;
  @OneToMany
  private List<Owner> owner;
  @OneToMany
  private List<Order> order;
  @OneToMany
  private List<Item> item;
  @OneToMany
  private List<Holiday> holiday;
  @OneToMany
  private List<BusinessHours> businessHour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Store(int aStoreID, String aAddress)
  {
    storeID = aStoreID;
    address = aAddress;
    currentActivePickup = 0;
    currentActiveDelivery = 0;
    employee = new ArrayList<Employee>();
    owner = new ArrayList<Owner>();
    order = new ArrayList<Order>();
    item = new ArrayList<Item>();
    holiday = new ArrayList<Holiday>();
    businessHour = new ArrayList<BusinessHours>();
  }

  public Store() {

  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStoreID(int aStoreID)
  {
    boolean wasSet = false;
    storeID = aStoreID;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentActivePickup(int aCurrentActivePickup)
  {
    boolean wasSet = false;
    currentActivePickup = aCurrentActivePickup;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentActiveDelivery(int aCurrentActiveDelivery)
  {
    boolean wasSet = false;
    currentActiveDelivery = aCurrentActiveDelivery;
    wasSet = true;
    return wasSet;
  }

  public int getStoreID()
  {
    return storeID;
  }

  public String getAddress()
  {
    return address;
  }

  public int getCurrentActivePickup()
  {
    return currentActivePickup;
  }

  public int getCurrentActiveDelivery()
  {
    return currentActiveDelivery;
  }
  /* Code from template association_GetMany */
  public Employee getEmployee(int index)
  {
    Employee aEmployee = employee.get(index);
    return aEmployee;
  }

  //@OneToMany
  public List<Employee> getEmployee()
  {
    List<Employee> newEmployee = Collections.unmodifiableList(employee);
    return newEmployee;
  }

  public void setEmployee(List<Employee> aEmployee){
    this.employee = aEmployee;
  }

  public int numberOfEmployee()
  {
    int number = employee.size();
    return number;
  }

  public boolean hasEmployee()
  {
    boolean has = employee.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employee.indexOf(aEmployee);
    return index;
  }
  /* Code from template association_GetMany */
  public Owner getOwner(int index)
  {
    Owner aOwner = owner.get(index);
    return aOwner;
  }

  //@OneToMany
  public List<Owner> getOwner()
  {
    List<Owner> newOwner = Collections.unmodifiableList(owner);
    return newOwner;
  }

  public void setOwner(List<Owner> aOwner){
    this.owner = aOwner;
  }

  public int numberOfOwner()
  {
    int number = owner.size();
    return number;
  }

  public boolean hasOwner()
  {
    boolean has = owner.size() > 0;
    return has;
  }

  public int indexOfOwner(Owner aOwner)
  {
    int index = owner.indexOf(aOwner);
    return index;
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = order.get(index);
    return aOrder;
  }

  //@OneToMany
  public List<Order> getOrder()
  {
    List<Order> newOrder = Collections.unmodifiableList(order);
    return newOrder;
  }

  public void setOrder(List<Order> aOrder){
    this.order = aOrder;
  }

  public int numberOfOrder()
  {
    int number = order.size();
    return number;
  }

  public boolean hasOrder()
  {
    boolean has = order.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = order.indexOf(aOrder);
    return index;
  }
  /* Code from template association_GetMany */
  public Item getItem(int index)
  {
    Item aItem = item.get(index);
    return aItem;
  }

  //@OneToMany
  public List<Item> getItem()
  {
    List<Item> newItem = Collections.unmodifiableList(item);
    return newItem;
  }

  public void setItem(List<Item> aItem){
    this.item = aItem;
  }

  public int numberOfItem()
  {
    int number = item.size();
    return number;
  }

  public boolean hasItem()
  {
    boolean has = item.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = item.indexOf(aItem);
    return index;
  }
  /* Code from template association_GetMany */
  public Holiday getHoliday(int index)
  {
    Holiday aHoliday = holiday.get(index);
    return aHoliday;
  }

  //@OneToMany
  public List<Holiday> getHoliday()
  {
    List<Holiday> newHoliday = Collections.unmodifiableList(holiday);
    return newHoliday;
  }

  public void setHoliday(List<Holiday> aHoliday){
    this.holiday = aHoliday;
  }

  public int numberOfHoliday()
  {
    int number = holiday.size();
    return number;
  }

  public boolean hasHoliday()
  {
    boolean has = holiday.size() > 0;
    return has;
  }

  public int indexOfHoliday(Holiday aHoliday)
  {
    int index = holiday.indexOf(aHoliday);
    return index;
  }
  /* Code from template association_GetMany */
  public BusinessHours getBusinessHour(int index)
  {
    BusinessHours aBusinessHour = businessHour.get(index);
    return aBusinessHour;
  }

  //@OneToMany
  public List<BusinessHours> getBusinessHour()
  {
    List<BusinessHours> newBusinessHour = Collections.unmodifiableList(businessHour);
    return newBusinessHour;
  }

  public void setBusinessHour(List<BusinessHours> aBusinessHours){
    this.businessHour = aBusinessHours;
  }

  public int numberOfBusinessHour()
  {
    int number = businessHour.size();
    return number;
  }

  public boolean hasBusinessHour()
  {
    boolean has = businessHour.size() > 0;
    return has;
  }

  public int indexOfBusinessHour(BusinessHours aBusinessHour)
  {
    int index = businessHour.indexOf(aBusinessHour);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEmployee()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Employee addEmployee(String aUsername, String aPassword, String aEmail)
  {
    return new Employee(aUsername, aPassword, aEmail, this);
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employee.contains(aEmployee)) { return false; }
    Store existingStore = aEmployee.getStore();
    boolean isNewStore = existingStore != null && !this.equals(existingStore);
    if (isNewStore)
    {
      aEmployee.setStore(this);
    }
    else
    {
      employee.add(aEmployee);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmployee, as it must always have a store
    if (!this.equals(aEmployee.getStore()))
    {
      employee.remove(aEmployee);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployee()) { index = numberOfEmployee() - 1; }
      employee.remove(aEmployee);
      employee.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employee.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployee()) { index = numberOfEmployee() - 1; }
      employee.remove(aEmployee);
      employee.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOwner()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Owner addOwner(String aUsername, String aPassword, String aEmail)
  {
    return new Owner(aUsername, aPassword, aEmail, this);
  }

  public boolean addOwner(Owner aOwner)
  {
    boolean wasAdded = false;
    if (owner.contains(aOwner)) { return false; }
    Store existingStore = aOwner.getStore();
    boolean isNewStore = existingStore != null && !this.equals(existingStore);
    if (isNewStore)
    {
      aOwner.setStore(this);
    }
    else
    {
      owner.add(aOwner);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOwner(Owner aOwner)
  {
    boolean wasRemoved = false;
    //Unable to remove aOwner, as it must always have a store
    if (!this.equals(aOwner.getStore()))
    {
      owner.remove(aOwner);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOwnerAt(Owner aOwner, int index)
  {  
    boolean wasAdded = false;
    if(addOwner(aOwner))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwner()) { index = numberOfOwner() - 1; }
      owner.remove(aOwner);
      owner.add(index, aOwner);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOwnerAt(Owner aOwner, int index)
  {
    boolean wasAdded = false;
    if(owner.contains(aOwner))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwner()) { index = numberOfOwner() - 1; }
      owner.remove(aOwner);
      owner.add(index, aOwner);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOwnerAt(aOwner, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrder()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (order.contains(aOrder)) { return false; }
    Store existingStore = aOrder.getStore();
    boolean isNewStore = existingStore != null && !this.equals(existingStore);
    if (isNewStore)
    {
      aOrder.setStore(this);
    }
    else
    {
      order.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a store
    if (!this.equals(aOrder.getStore()))
    {
      order.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
      order.remove(aOrder);
      order.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(order.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
      order.remove(aOrder);
      order.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItem()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (item.contains(aItem)) { return false; }
    item.add(aItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    if (item.contains(aItem))
    {
      item.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItem()) { index = numberOfItem() - 1; }
      item.remove(aItem);
      item.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(item.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItem()) { index = numberOfItem() - 1; }
      item.remove(aItem);
      item.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHoliday()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addHoliday(Holiday aHoliday)
  {
    boolean wasAdded = false;
    if (holiday.contains(aHoliday)) { return false; }
    holiday.add(aHoliday);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHoliday(Holiday aHoliday)
  {
    boolean wasRemoved = false;
    if (holiday.contains(aHoliday))
    {
      holiday.remove(aHoliday);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHolidayAt(Holiday aHoliday, int index)
  {  
    boolean wasAdded = false;
    if(addHoliday(aHoliday))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHoliday()) { index = numberOfHoliday() - 1; }
      holiday.remove(aHoliday);
      holiday.add(index, aHoliday);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHolidayAt(Holiday aHoliday, int index)
  {
    boolean wasAdded = false;
    if(holiday.contains(aHoliday))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHoliday()) { index = numberOfHoliday() - 1; }
      holiday.remove(aHoliday);
      holiday.add(index, aHoliday);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHolidayAt(aHoliday, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBusinessHour()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addBusinessHour(BusinessHours aBusinessHour)
  {
    boolean wasAdded = false;
    if (businessHour.contains(aBusinessHour)) { return false; }
    businessHour.add(aBusinessHour);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBusinessHour(BusinessHours aBusinessHour)
  {
    boolean wasRemoved = false;
    if (businessHour.contains(aBusinessHour))
    {
      businessHour.remove(aBusinessHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBusinessHourAt(BusinessHours aBusinessHour, int index)
  {  
    boolean wasAdded = false;
    if(addBusinessHour(aBusinessHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinessHour()) { index = numberOfBusinessHour() - 1; }
      businessHour.remove(aBusinessHour);
      businessHour.add(index, aBusinessHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBusinessHourAt(BusinessHours aBusinessHour, int index)
  {
    boolean wasAdded = false;
    if(businessHour.contains(aBusinessHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinessHour()) { index = numberOfBusinessHour() - 1; }
      businessHour.remove(aBusinessHour);
      businessHour.add(index, aBusinessHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBusinessHourAt(aBusinessHour, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=employee.size(); i > 0; i--)
    {
      Employee aEmployee = employee.get(i - 1);
      aEmployee.delete();
    }
    for(int i=owner.size(); i > 0; i--)
    {
      Owner aOwner = owner.get(i - 1);
      aOwner.delete();
    }
    for(int i=order.size(); i > 0; i--)
    {
      Order aOrder = order.get(i - 1);
      aOrder.delete();
    }
    item.clear();
    holiday.clear();
    businessHour.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "storeID" + ":" + getStoreID()+ "," +
            "address" + ":" + getAddress()+ "," +
            "currentActivePickup" + ":" + getCurrentActivePickup()+ "," +
            "currentActiveDelivery" + ":" + getCurrentActiveDelivery()+ "]";
  }
}