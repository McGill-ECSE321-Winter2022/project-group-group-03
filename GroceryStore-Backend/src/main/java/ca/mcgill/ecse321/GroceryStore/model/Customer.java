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

  private static Map<String, Customer> customersByUsername = new HashMap<String, Customer>();
  private static Map<String, Customer> customersByEmail = new HashMap<String, Customer>();

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

  @OneToMany
  @JoinColumn(name = "customer_confirmationNumber")
  private List<Order> order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer() {

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
      customersByUsername.remove(anOldUsername);
    }
    customersByUsername.put(aUsername, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public void setOrder(List<Order> order) {
    this.order = order;
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
      customersByEmail.remove(anOldEmail);
    }
    customersByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static Customer getWithUsername(String aUsername)
  {
    return customersByUsername.get(aUsername);
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
  public static Customer getWithEmail(String aEmail)
  {
    return customersByEmail.get(aEmail);
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