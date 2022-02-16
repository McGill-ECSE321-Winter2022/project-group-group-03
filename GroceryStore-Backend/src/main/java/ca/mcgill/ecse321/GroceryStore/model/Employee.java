/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import ca.mcgill.ecse321.GroceryStore.model.Employee;

import javax.persistence.*;
import java.util.*;

// line 38 "../../../../../../GroceryStoreStates.ump"
// line 61 "../../../../../../GroceryStoreStates.ump"
// line 32 "../../../../../../model.ump"
// line 120 "../../../../../../model.ump"
// line 153 "../../../../../../model.ump"
// line 168 "../../../../../../model.ump"
// line 199 "../../../../../../model.ump"
@Entity
public class Employee
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Employee> employeesByUsername = new HashMap<String, Employee>();
  private static Map<String, Employee> employeesByEmail = new HashMap<String, Employee>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes

  private String username;
  private String password;
  private String email;
  private String address;



  //Employee State Machines
  public enum WorkingStatus { Hired, Fired }
  @Enumerated
  //@Column(name = "working_status", nullable = false)
  private WorkingStatus workingStatus;

  //Employee Associations

  @OneToMany
  @JoinColumn(name ="employee_shiftID", unique = true)
  private List<WorkShift> workShift;

  @OneToMany
  @JoinColumn(name = "employee_confirmationNumber", unique = true)
  private List<Order> order;

  @Id
  @Column(nullable = false)
  private Integer employeeID;

  public Integer getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(Integer employeeID) {
    this.employeeID = employeeID;
  }
//------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee() {

  }


  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
      return true;
    }
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      employeesByUsername.remove(anOldUsername);
    }
    employeesByUsername.put(aUsername, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      employeesByEmail.remove(anOldEmail);
    }
    employeesByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public void setOrder(List<Order> order) {
    this.order = order;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static Employee getWithUsername(String aUsername)
  {
    return employeesByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static Employee getWithEmail(String aEmail)
  {
    return employeesByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getAddress()
  {
    return address;
  }

  public String getWorkingStatusFullName()
  {
    String answer = workingStatus.toString();
    return answer;
  }

  public List<WorkShift> getWorkShift() {
    return workShift;
  }

  public WorkingStatus getWorkingStatus()
  {
    return workingStatus;
  }

  public boolean fireEmployee()
  {
    boolean wasEventProcessed = false;

    WorkingStatus aWorkingStatus = workingStatus;
    switch (aWorkingStatus)
    {
      case Hired:
        setWorkingStatus(WorkingStatus.Fired);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public void setWorkingStatus(WorkingStatus aWorkingStatus)
  {
    workingStatus = aWorkingStatus;
  }

  public void setWorkShift(List<WorkShift> workShift) {
    this.workShift = workShift;
  }

  public List<Order> getOrder() {
    return order;
  }

  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "email" + ":" + getEmail()+ "," +
            "address" + ":" + getAddress()+ "]";
  }
}