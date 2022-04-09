/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.util.*;

// line 24 "../../../../../../model.ump"
// line 115 "../../../../../../model.ump"
// line 148 "../../../../../../model.ump"
// line 163 "../../../../../../model.ump"
// line 193 "../../../../../../model.ump"
@Entity
public class Owner
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Owner Attributes
  @Id
  private String username;
  private String password;
  private String email;

  //Owner Associations

  @OneToOne
  @JoinColumn(name = "owner_storeID")
  private Store store;


  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Owner() {

  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setUsername(String aUsername)
  {
    this.username = aUsername;
  }

  public Store getStore(){
    return store;
}
  public void setPassword(String aPassword)
  {
    password = aPassword;
  }

  public void setStore(Store store){
    this.store = store;
  }

  public void setEmail(String aEmail)
  {
    this.email = aEmail;
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


}