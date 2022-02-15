/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.util.*;

// line 26 "../../../../../../model.ump"
// line 113 "../../../../../../model.ump"
// line 146 "../../../../../../model.ump"
// line 161 "../../../../../../model.ump"
// line 191 "../../../../../../model.ump"
@Entity
public class Owner
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Owner> ownersByUsername = new HashMap<String, Owner>();
  private static Map<String, Owner> ownersByEmail = new HashMap<String, Owner>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Owner Attributes
  @Id
  @Column(name="Owner_username")
  private String username;
  @Column(name="Owner_Password")
  private String password;
  @Column(name="Owner_email")
  private String email;

  //Owner Associations
  @ManyToOne
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Owner() {}

  public Owner(String aUsername, String aPassword, String aEmail, Store aStore)
  {
    password = aPassword;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    setStore(aStore);
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
      ownersByUsername.remove(anOldUsername);
    }
    ownersByUsername.put(aUsername, this);
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
      ownersByEmail.remove(anOldEmail);
    }
    ownersByEmail.put(aEmail, this);
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static Owner getWithUsername(String aUsername)
  {
    return ownersByUsername.get(aUsername);
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
  public static Owner getWithEmail(String aEmail)
  {
    return ownersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }
  /* Code from template association_GetOne */
  //@ManyToOne
  public Store getStore()
  {
    return this.store;
  }

  public void setStore(Store aStore)
  {
    this.store = aStore;
    }


  public void delete()
  {
    ownersByUsername.remove(getUsername());
    ownersByEmail.remove(getEmail());
    Store placeholderStore = store;
    this.store = null;
    if(placeholderStore != null)
    {
      placeholderStore.removeOwner(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null");
  }
}