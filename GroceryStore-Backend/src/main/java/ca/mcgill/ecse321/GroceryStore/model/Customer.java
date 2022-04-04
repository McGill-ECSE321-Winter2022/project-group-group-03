/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.util.*;

// line 41 "../../../../../../model.ump"
// line 125 "../../../../../../model.ump"
// line 158 "../../../../../../model.ump"
// line 173 "../../../../../../model.ump"
// line 207 "../../../../../../model.ump"
@Entity
public class Customer
{

  //------------------------
  // STATIC VARIABLES
  //------------------------



  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  @Id
  private String username;
  private String password;
  private String email;
  private String address;



//Customer Associations
@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
private List<Commission> commissions = new ArrayList<>();


  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer() {

  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setUsername(String aUsername)
  {
    this.username=aUsername;
  }

  public void setPassword(String aPassword)
  {
    this.password=aPassword;
  }

  public void setOrder(List<Commission> commission) {
    this.commissions = commission;
  }

  public void setEmail(String aEmail)
  {
    this.email = aEmail;
  }

  public void setAddress(String aAddress)
  {
    this.address = aAddress;
  }

  public String getUsername()
  {
    return username;
  }

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

  public List<Commission> getOrder() {
    return commissions;
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