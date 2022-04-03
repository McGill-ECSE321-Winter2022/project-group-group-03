/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;

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
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  @Id
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
  @JoinColumn(name ="employee_shiftID")
  private List<WorkShift> workShift;

  @OneToMany
  @JoinColumn(name = "employee_confirmationNumber", unique = true)
  private List<Commission> commission;


//------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee() {

  }


  //------------------------
  // INTERFACE
  //------------------------

  public void setUsername(String aUsername)
  {
    username = aUsername;
  }

  public void setPassword(String aPassword)
  {
    password = aPassword;
  }

  public void setEmail(String aEmail)
  {
    email = aEmail;
  }

  public void setAddress(String aAddress)
  {
    address = aAddress;
  }

  public void setOrder(List<Commission> commission) {
    this.commission = commission;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */

  public String getPassword()
  {
    return password;
  }

  public String getEmail()
  {
    return email;
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

  public List<Commission> getOrder() {
    return commission;
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