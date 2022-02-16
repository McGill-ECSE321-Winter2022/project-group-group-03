///*PLEASE DO NOT EDIT THIS CODE*/
///*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
//
//package ca.mcgill.ecse321.GroceryStore.model;
//import javax.persistence.*;
//import java.util.*;
//
///**
// * add a basket option, potentially a state for each order
// */
//// line 8 "../../../../../../model.ump"
//// line 248 "../../../../../../model.ump"
//// line 263 "../../../../../../model.ump"
//  @Entity
//public class Store
//{
//
//  //------------------------
//  // STATIC VARIABLES
//  //------------------------
//
//  public static final int MAXPICKUPS = 10;
//  public static final int MAXSHIPPING = 10;
//
//  //------------------------
//  // MEMBER VARIABLES
//  //------------------------
//
//  //Store Attributes
//  @Column(nullable = false)
//  @Id
//  //@GeneratedValue(strategy = GenerationType.IDENTITY)
//  private int storeID;
//  private String address;
//  private int currentActivePickup;
//  private int currentActiveDelivery;
//
//  //Store Associations
//
//  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//  //@JoinTable(name = "store_employee")
//  private List<Employee> employee = new ArrayList<>();
//
//  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//  //@JoinTable(name = "store_item")
//  private List<Item> item = new ArrayList<>();
//
//  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//  //@JoinTable(name = "store_holidayhour")
//  private List<Holiday> holiday = new ArrayList<>();
//
//  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//  //@JoinTable(name = "store_businesshour")
//  private List<BusinessHour> businessHour = new ArrayList<>();
//
//
//
////------------------------
//  // CONSTRUCTOR
//  //------------------------
//
//  public Store(int aStoreID, String aAddress)
//  {
//    storeID = aStoreID;
//    address = aAddress;
//    currentActivePickup = 0;
//    currentActiveDelivery = 0;
//    employee = new ArrayList<Employee>();
//    item = new ArrayList<Item>();
//    holiday = new ArrayList<Holiday>();
//    businessHour = new ArrayList<BusinessHour>();
//  }
//
//  public Store() {
//
//  }
//
//  //------------------------
//  // INTERFACE
//  //------------------------
//
//  public boolean setStoreID(int aStoreID)
//  {
//    boolean wasSet = false;
//    storeID = aStoreID;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setAddress(String aAddress)
//  {
//    boolean wasSet = false;
//    address = aAddress;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setCurrentActivePickup(int aCurrentActivePickup)
//  {
//    boolean wasSet = false;
//    currentActivePickup = aCurrentActivePickup;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setCurrentActiveDelivery(int aCurrentActiveDelivery)
//  {
//    boolean wasSet = false;
//    currentActiveDelivery = aCurrentActiveDelivery;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public int getStoreID()
//  {
//    return storeID;
//  }
//
//  public String getAddress()
//  {
//    return address;
//  }
//
//  public int getCurrentActivePickup()
//  {
//    return currentActivePickup;
//  }
//
//  public int getCurrentActiveDelivery()
//  {
//    return currentActiveDelivery;
//  }
//  /* Code from template association_GetMany */
//  public Employee getEmployee(int index)
//  {
//    Employee aEmployee = employee.get(index);
//    return aEmployee;
//  }
//
//  public List<Employee> getEmployee()
//  {
//    List<Employee> newEmployee = Collections.unmodifiableList(employee);
//    return newEmployee;
//  }
//
//  public int numberOfEmployee()
//  {
//    int number = employee.size();
//    return number;
//  }
//
//  public boolean hasEmployee()
//  {
//    boolean has = employee.size() > 0;
//    return has;
//  }
//
//  public int indexOfEmployee(Employee aEmployee)
//  {
//    int index = employee.indexOf(aEmployee);
//    return index;
//  }
//  /* Code from template association_GetMany */
//  public Item getItem(int index)
//  {
//    Item aItem = item.get(index);
//    return aItem;
//  }
//
//  public List<Item> getItem()
//  {
//    List<Item> newItem = Collections.unmodifiableList(item);
//    return newItem;
//  }
//
//  public int numberOfItem()
//  {
//    int number = item.size();
//    return number;
//  }
//
//  public boolean hasItem()
//  {
//    boolean has = item.size() > 0;
//    return has;
//  }
//
//  public int indexOfItem(Item aItem)
//  {
//    int index = item.indexOf(aItem);
//    return index;
//  }
//  /* Code from template association_GetMany */
//  public Holiday getHoliday(int index)
//  {
//    Holiday aHoliday = holiday.get(index);
//    return aHoliday;
//  }
//
//  public List<Holiday> getHoliday()
//  {
//    List<Holiday> newHoliday = Collections.unmodifiableList(holiday);
//    return newHoliday;
//  }
//
//  public int numberOfHoliday()
//  {
//    int number = holiday.size();
//    return number;
//  }
//
//  public boolean hasHoliday()
//  {
//    boolean has = holiday.size() > 0;
//    return has;
//  }
//
//  public int indexOfHoliday(Holiday aHoliday)
//  {
//    int index = holiday.indexOf(aHoliday);
//    return index;
//  }
//  /* Code from template association_GetMany */
//  public BusinessHour getBusinessHour(int index)
//  {
//    BusinessHour aBusinessHour = businessHour.get(index);
//    return aBusinessHour;
//  }
//
//  public List<BusinessHour> getBusinessHour()
//  {
//    List<BusinessHour> newBusinessHour = Collections.unmodifiableList(businessHour);
//    return newBusinessHour;
//  }
//
//  public int numberOfBusinessHour()
//  {
//    int number = businessHour.size();
//    return number;
//  }
//
//  public boolean hasBusinessHour()
//  {
//    boolean has = businessHour.size() > 0;
//    return has;
//  }
//
//  public int indexOfBusinessHour(BusinessHour aBusinessHour)
//  {
//    int index = businessHour.indexOf(aBusinessHour);
//    return index;
//  }
//  /* Code from template association_MinimumNumberOfMethod */
//  public static int minimumNumberOfEmployee()
//  {
//    return 0;
//  }
//  /* Code from template association_AddUnidirectionalMany */
//  public boolean addEmployee(Employee aEmployee)
//  {
//    boolean wasAdded = false;
//    if (employee.contains(aEmployee)) { return false; }
//    employee.add(aEmployee);
//    wasAdded = true;
//    return wasAdded;
//  }
//
//  public boolean removeEmployee(Employee aEmployee)
//  {
//    boolean wasRemoved = false;
//    if (employee.contains(aEmployee))
//    {
//      employee.remove(aEmployee);
//      wasRemoved = true;
//    }
//    return wasRemoved;
//  }
//  /* Code from template association_AddIndexControlFunctions */
//  public boolean addEmployeeAt(Employee aEmployee, int index)
//  {
//    boolean wasAdded = false;
//    if(addEmployee(aEmployee))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfEmployee()) { index = numberOfEmployee() - 1; }
//      employee.remove(aEmployee);
//      employee.add(index, aEmployee);
//      wasAdded = true;
//    }
//    return wasAdded;
//  }
//
//  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
//  {
//    boolean wasAdded = false;
//    if(employee.contains(aEmployee))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfEmployee()) { index = numberOfEmployee() - 1; }
//      employee.remove(aEmployee);
//      employee.add(index, aEmployee);
//      wasAdded = true;
//    }
//    else
//    {
//      wasAdded = addEmployeeAt(aEmployee, index);
//    }
//    return wasAdded;
//  }
//  /* Code from template association_MinimumNumberOfMethod */
//  public static int minimumNumberOfItem()
//  {
//    return 0;
//  }
//  /* Code from template association_AddUnidirectionalMany */
//  public boolean addItem(Item aItem)
//  {
//    boolean wasAdded = false;
//    if (item.contains(aItem)) { return false; }
//    item.add(aItem);
//    wasAdded = true;
//    return wasAdded;
//  }
//
//  public boolean removeItem(Item aItem)
//  {
//    boolean wasRemoved = false;
//    if (item.contains(aItem))
//    {
//      item.remove(aItem);
//      wasRemoved = true;
//    }
//    return wasRemoved;
//  }
//  /* Code from template association_AddIndexControlFunctions */
//  public boolean addItemAt(Item aItem, int index)
//  {
//    boolean wasAdded = false;
//    if(addItem(aItem))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfItem()) { index = numberOfItem() - 1; }
//      item.remove(aItem);
//      item.add(index, aItem);
//      wasAdded = true;
//    }
//    return wasAdded;
//  }
//
//  public boolean addOrMoveItemAt(Item aItem, int index)
//  {
//    boolean wasAdded = false;
//    if(item.contains(aItem))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfItem()) { index = numberOfItem() - 1; }
//      item.remove(aItem);
//      item.add(index, aItem);
//      wasAdded = true;
//    }
//    else
//    {
//      wasAdded = addItemAt(aItem, index);
//    }
//    return wasAdded;
//  }
//  /* Code from template association_MinimumNumberOfMethod */
//  public static int minimumNumberOfHoliday()
//  {
//    return 0;
//  }
//  /* Code from template association_AddUnidirectionalMany */
//  public boolean addHoliday(Holiday aHoliday)
//  {
//    boolean wasAdded = false;
//    if (holiday.contains(aHoliday)) { return false; }
//    holiday.add(aHoliday);
//    wasAdded = true;
//    return wasAdded;
//  }
//
//  public boolean removeHoliday(Holiday aHoliday)
//  {
//    boolean wasRemoved = false;
//    if (holiday.contains(aHoliday))
//    {
//      holiday.remove(aHoliday);
//      wasRemoved = true;
//    }
//    return wasRemoved;
//  }
//  /* Code from template association_AddIndexControlFunctions */
//  public boolean addHolidayAt(Holiday aHoliday, int index)
//  {
//    boolean wasAdded = false;
//    if(addHoliday(aHoliday))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfHoliday()) { index = numberOfHoliday() - 1; }
//      holiday.remove(aHoliday);
//      holiday.add(index, aHoliday);
//      wasAdded = true;
//    }
//    return wasAdded;
//  }
//
//  public boolean addOrMoveHolidayAt(Holiday aHoliday, int index)
//  {
//    boolean wasAdded = false;
//    if(holiday.contains(aHoliday))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfHoliday()) { index = numberOfHoliday() - 1; }
//      holiday.remove(aHoliday);
//      holiday.add(index, aHoliday);
//      wasAdded = true;
//    }
//    else
//    {
//      wasAdded = addHolidayAt(aHoliday, index);
//    }
//    return wasAdded;
//  }
//  /* Code from template association_MinimumNumberOfMethod */
//  public static int minimumNumberOfBusinessHour()
//  {
//    return 0;
//  }
//  /* Code from template association_AddUnidirectionalMany */
//  public boolean addBusinessHour(BusinessHour aBusinessHour)
//  {
//    boolean wasAdded = false;
//    if (businessHour.contains(aBusinessHour)) { return false; }
//    businessHour.add(aBusinessHour);
//    wasAdded = true;
//    return wasAdded;
//  }
//
//  public boolean removeBusinessHour(BusinessHour aBusinessHour)
//  {
//    boolean wasRemoved = false;
//    if (businessHour.contains(aBusinessHour))
//    {
//      businessHour.remove(aBusinessHour);
//      wasRemoved = true;
//    }
//    return wasRemoved;
//  }
//  /* Code from template association_AddIndexControlFunctions */
//  public boolean addBusinessHourAt(BusinessHour aBusinessHour, int index)
//  {
//    boolean wasAdded = false;
//    if(addBusinessHour(aBusinessHour))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfBusinessHour()) { index = numberOfBusinessHour() - 1; }
//      businessHour.remove(aBusinessHour);
//      businessHour.add(index, aBusinessHour);
//      wasAdded = true;
//    }
//    return wasAdded;
//  }
//
//  public boolean addOrMoveBusinessHourAt(BusinessHour aBusinessHour, int index)
//  {
//    boolean wasAdded = false;
//    if(businessHour.contains(aBusinessHour))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfBusinessHour()) { index = numberOfBusinessHour() - 1; }
//      businessHour.remove(aBusinessHour);
//      businessHour.add(index, aBusinessHour);
//      wasAdded = true;
//    }
//    else
//    {
//      wasAdded = addBusinessHourAt(aBusinessHour, index);
//    }
//    return wasAdded;
//  }
//
//  public void delete()
//  {
//    employee.clear();
//    item.clear();
//    holiday.clear();
//    businessHour.clear();
//  }
//
//
//  public String toString()
//  {
//    return super.toString() + "["+
//            "storeID" + ":" + getStoreID()+ "," +
//            "address" + ":" + getAddress()+ "," +
//            "currentActivePickup" + ":" + getCurrentActivePickup()+ "," +
//            "currentActiveDelivery" + ":" + getCurrentActiveDelivery()+ "]";
//  }
//}