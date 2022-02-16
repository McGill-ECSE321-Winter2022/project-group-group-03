///*PLEASE DO NOT EDIT THIS CODE*/
///*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
//
//package ca.mcgill.ecse321.GroceryStore.model;
//import ca.mcgill.ecse321.GroceryStore.model.Employee;
//
//import javax.persistence.*;
//import java.util.*;
//
//// line 38 "../../../../../../GroceryStoreStates.ump"
//// line 61 "../../../../../../GroceryStoreStates.ump"
//// line 32 "../../../../../../model.ump"
//// line 120 "../../../../../../model.ump"
//// line 153 "../../../../../../model.ump"
//// line 168 "../../../../../../model.ump"
//// line 199 "../../../../../../model.ump"
//@Entity
//public class Employee
//{
//
//  //------------------------
//  // STATIC VARIABLES
//  //------------------------
//
//  private static Map<String, Employee> employeesByUsername = new HashMap<String, Employee>();
//  private static Map<String, Employee> employeesByEmail = new HashMap<String, Employee>();
//
//  //------------------------
//  // MEMBER VARIABLES
//  //------------------------
//
//  //Employee Attributes
//
//  private String username;
//  private String password;
//  private String email;
//  private String address;
//
//
//
//  //Employee State Machines
//  public enum WorkingStatus { Hired, Fired }
//  @Enumerated
//  //@Column(name = "working_status", nullable = false)
//  private WorkingStatus workingStatus;
//
//  //Employee Associations
//
//  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//  //@JoinColumn(name = "employee_username", unique = true)
//  private List<WorkShift> workShift = new ArrayList<>();
//
//  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//  //@JoinColumn(name = "employee_username", unique = true)
//  private List<Order> order = new ArrayList<>();
//
//  @Id
//  @Column(name = "employee_id", nullable = false)
//  private Integer employeeID;
//
//  public Integer getEmployeeID() {
//    return employeeID;
//  }
//
//  public void setEmployeeID(Integer employeeID) {
//    this.employeeID = employeeID;
//  }
////------------------------
//  // CONSTRUCTOR
//  //------------------------
//
//  public Employee() {
//
//  }
//
//  public Employee(String aUsername, String aPassword, String aEmail, String aAddress)
//  {
//    password = aPassword;
//    address = aAddress;
//    if (!setUsername(aUsername))
//    {
//      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
//    }
//    if (!setEmail(aEmail))
//    {
//      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
//    }
//    workShift = new ArrayList<WorkShift>();
//    order = new ArrayList<Order>();
//    setWorkingStatus(WorkingStatus.Hired);
//  }
//
//  //------------------------
//  // INTERFACE
//  //------------------------
//
//  public boolean setUsername(String aUsername)
//  {
//    boolean wasSet = false;
//    String anOldUsername = getUsername();
//    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
//      return true;
//    }
//    if (hasWithUsername(aUsername)) {
//      return wasSet;
//    }
//    username = aUsername;
//    wasSet = true;
//    if (anOldUsername != null) {
//      employeesByUsername.remove(anOldUsername);
//    }
//    employeesByUsername.put(aUsername, this);
//    return wasSet;
//  }
//
//  public boolean setPassword(String aPassword)
//  {
//    boolean wasSet = false;
//    password = aPassword;
//    wasSet = true;
//    return wasSet;
//  }
//
//  public boolean setEmail(String aEmail)
//  {
//    boolean wasSet = false;
//    String anOldEmail = getEmail();
//    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
//      return true;
//    }
//    if (hasWithEmail(aEmail)) {
//      return wasSet;
//    }
//    email = aEmail;
//    wasSet = true;
//    if (anOldEmail != null) {
//      employeesByEmail.remove(anOldEmail);
//    }
//    employeesByEmail.put(aEmail, this);
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
//  public String getUsername()
//  {
//    return username;
//  }
//  /* Code from template attribute_GetUnique */
//  public static Employee getWithUsername(String aUsername)
//  {
//    return employeesByUsername.get(aUsername);
//  }
//  /* Code from template attribute_HasUnique */
//  public static boolean hasWithUsername(String aUsername)
//  {
//    return getWithUsername(aUsername) != null;
//  }
//
//  public String getPassword()
//  {
//    return password;
//  }
//
//  public String getEmail()
//  {
//    return email;
//  }
//  /* Code from template attribute_GetUnique */
//  public static Employee getWithEmail(String aEmail)
//  {
//    return employeesByEmail.get(aEmail);
//  }
//  /* Code from template attribute_HasUnique */
//  public static boolean hasWithEmail(String aEmail)
//  {
//    return getWithEmail(aEmail) != null;
//  }
//
//  public String getAddress()
//  {
//    return address;
//  }
//
//  public String getWorkingStatusFullName()
//  {
//    String answer = workingStatus.toString();
//    return answer;
//  }
//
//  public WorkingStatus getWorkingStatus()
//  {
//    return workingStatus;
//  }
//
//  public boolean fireEmployee()
//  {
//    boolean wasEventProcessed = false;
//
//    WorkingStatus aWorkingStatus = workingStatus;
//    switch (aWorkingStatus)
//    {
//      case Hired:
//        setWorkingStatus(WorkingStatus.Fired);
//        wasEventProcessed = true;
//        break;
//      default:
//        // Other states do respond to this event
//    }
//
//    return wasEventProcessed;
//  }
//
//  public void setWorkingStatus(WorkingStatus aWorkingStatus)
//  {
//    workingStatus = aWorkingStatus;
//  }
//  /* Code from template association_GetMany */
//  public WorkShift getWorkShift(int index)
//  {
//    WorkShift aWorkShift = workShift.get(index);
//    return aWorkShift;
//  }
//
//  public List<WorkShift> getWorkShift()
//  {
//    List<WorkShift> newWorkShift = Collections.unmodifiableList(workShift);
//    return newWorkShift;
//  }
//
//  public int numberOfWorkShift()
//  {
//    int number = workShift.size();
//    return number;
//  }
//
//  public boolean hasWorkShift()
//  {
//    boolean has = workShift.size() > 0;
//    return has;
//  }
//
//  public int indexOfWorkShift(WorkShift aWorkShift)
//  {
//    int index = workShift.indexOf(aWorkShift);
//    return index;
//  }
//  /* Code from template association_GetMany */
//  public Order getOrder(int index)
//  {
//    Order aOrder = order.get(index);
//    return aOrder;
//  }
//
//  public List<Order> getOrder()
//  {
//    List<Order> newOrder = Collections.unmodifiableList(order);
//    return newOrder;
//  }
//
//  public int numberOfOrder()
//  {
//    int number = order.size();
//    return number;
//  }
//
//  public boolean hasOrder()
//  {
//    boolean has = order.size() > 0;
//    return has;
//  }
//
//  public int indexOfOrder(Order aOrder)
//  {
//    int index = order.indexOf(aOrder);
//    return index;
//  }
//  /* Code from template association_MinimumNumberOfMethod */
//  public static int minimumNumberOfWorkShift()
//  {
//    return 0;
//  }
//  /* Code from template association_AddUnidirectionalMany */
//  public boolean addWorkShift(WorkShift aWorkShift)
//  {
//    boolean wasAdded = false;
//    if (workShift.contains(aWorkShift)) { return false; }
//    workShift.add(aWorkShift);
//    wasAdded = true;
//    return wasAdded;
//  }
//
//  public boolean removeWorkShift(WorkShift aWorkShift)
//  {
//    boolean wasRemoved = false;
//    if (workShift.contains(aWorkShift))
//    {
//      workShift.remove(aWorkShift);
//      wasRemoved = true;
//    }
//    return wasRemoved;
//  }
//  /* Code from template association_AddIndexControlFunctions */
//  public boolean addWorkShiftAt(WorkShift aWorkShift, int index)
//  {
//    boolean wasAdded = false;
//    if(addWorkShift(aWorkShift))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfWorkShift()) { index = numberOfWorkShift() - 1; }
//      workShift.remove(aWorkShift);
//      workShift.add(index, aWorkShift);
//      wasAdded = true;
//    }
//    return wasAdded;
//  }
//
//  public boolean addOrMoveWorkShiftAt(WorkShift aWorkShift, int index)
//  {
//    boolean wasAdded = false;
//    if(workShift.contains(aWorkShift))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfWorkShift()) { index = numberOfWorkShift() - 1; }
//      workShift.remove(aWorkShift);
//      workShift.add(index, aWorkShift);
//      wasAdded = true;
//    }
//    else
//    {
//      wasAdded = addWorkShiftAt(aWorkShift, index);
//    }
//    return wasAdded;
//  }
//  /* Code from template association_MinimumNumberOfMethod */
//  public static int minimumNumberOfOrder()
//  {
//    return 0;
//  }
//  /* Code from template association_AddUnidirectionalMany */
//  public boolean addOrder(Order aOrder)
//  {
//    boolean wasAdded = false;
//    if (order.contains(aOrder)) { return false; }
//    order.add(aOrder);
//    wasAdded = true;
//    return wasAdded;
//  }
//
//  public boolean removeOrder(Order aOrder)
//  {
//    boolean wasRemoved = false;
//    if (order.contains(aOrder))
//    {
//      order.remove(aOrder);
//      wasRemoved = true;
//    }
//    return wasRemoved;
//  }
//  /* Code from template association_AddIndexControlFunctions */
//  public boolean addOrderAt(Order aOrder, int index)
//  {
//    boolean wasAdded = false;
//    if(addOrder(aOrder))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
//      order.remove(aOrder);
//      order.add(index, aOrder);
//      wasAdded = true;
//    }
//    return wasAdded;
//  }
//
//  public boolean addOrMoveOrderAt(Order aOrder, int index)
//  {
//    boolean wasAdded = false;
//    if(order.contains(aOrder))
//    {
//      if(index < 0 ) { index = 0; }
//      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
//      order.remove(aOrder);
//      order.add(index, aOrder);
//      wasAdded = true;
//    }
//    else
//    {
//      wasAdded = addOrderAt(aOrder, index);
//    }
//    return wasAdded;
//  }
//
//  public void delete()
//  {
//    employeesByUsername.remove(getUsername());
//    employeesByEmail.remove(getEmail());
//    workShift.clear();
//    order.clear();
//  }
//
//
//  public String toString()
//  {
//    return super.toString() + "["+
//            "username" + ":" + getUsername()+ "," +
//            "password" + ":" + getPassword()+ "," +
//            "email" + ":" + getEmail()+ "," +
//            "address" + ":" + getAddress()+ "]";
//  }
//}