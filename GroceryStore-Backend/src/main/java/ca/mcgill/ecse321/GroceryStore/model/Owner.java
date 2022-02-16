///*PLEASE DO NOT EDIT THIS CODE*/
///*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
//
//package ca.mcgill.ecse321.GroceryStore.model;
//import javax.persistence.*;
//import java.util.*;
//
//// line 24 "../../../../../../model.ump"
//// line 115 "../../../../../../model.ump"
//// line 148 "../../../../../../model.ump"
//// line 163 "../../../../../../model.ump"
//// line 193 "../../../../../../model.ump"
//@Entity
//public class Owner
//{
//
//  //------------------------
//  // STATIC VARIABLES
//  //------------------------
//
//  private static Map<String, Owner> ownersByUsername = new HashMap<String, Owner>();
//  private static Map<String, Owner> ownersByEmail = new HashMap<String, Owner>();
//
//  //------------------------
//  // MEMBER VARIABLES
//  //------------------------
//
//  //Owner Attributes
//  @Id
//  private String username;
//  private String password;
//  private String email;
//
//  //Owner Associations
//
//  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//  @JoinColumn(name = "store_store_id")
//  private Store store;
//
//
//  //------------------------
//  // CONSTRUCTOR
//  //------------------------
//
//  public Owner(String aUsername, String aPassword, String aEmail, Store aStore)
//  {
//    password = aPassword;
//    if (!setUsername(aUsername))
//    {
//      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
//    }
//    if (!setEmail(aEmail))
//    {
//      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
//    }
//    if (!setStore(aStore))
//    {
//      throw new RuntimeException("Unable to create Owner due to aStore. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
//    }
//  }
//
//  public Owner() {
//
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
//      ownersByUsername.remove(anOldUsername);
//    }
//    ownersByUsername.put(aUsername, this);
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
//      ownersByEmail.remove(anOldEmail);
//    }
//    ownersByEmail.put(aEmail, this);
//    return wasSet;
//  }
//
//  public String getUsername()
//  {
//    return username;
//  }
//  /* Code from template attribute_GetUnique */
//  public static Owner getWithUsername(String aUsername)
//  {
//    return ownersByUsername.get(aUsername);
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
//  public static Owner getWithEmail(String aEmail)
//  {
//    return ownersByEmail.get(aEmail);
//  }
//  /* Code from template attribute_HasUnique */
//  public static boolean hasWithEmail(String aEmail)
//  {
//    return getWithEmail(aEmail) != null;
//  }
//  /* Code from template association_GetOne */
//  public Store getStore()
//  {
//    return store;
//  }
//  /* Code from template association_SetUnidirectionalOne */
//  public boolean setStore(Store aNewStore)
//  {
//    boolean wasSet = false;
//    if (aNewStore != null)
//    {
//      store = aNewStore;
//      wasSet = true;
//    }
//    return wasSet;
//  }
//
//  public void delete()
//  {
//    ownersByUsername.remove(getUsername());
//    ownersByEmail.remove(getEmail());
//    store = null;
//  }
//
//
//  public String toString()
//  {
//    return super.toString() + "["+
//            "username" + ":" + getUsername()+ "," +
//            "password" + ":" + getPassword()+ "," +
//            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
//            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null");
//  }
//}