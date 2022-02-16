/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.util.*;

/**
 * add a basket option, potentially a state for each order
 */
// line 8 "../../../../../../model.ump"
// line 248 "../../../../../../model.ump"
// line 263 "../../../../../../model.ump"
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
  @Column(nullable = false)
  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int storeID;
  private String address;
  private int currentActivePickup;
  private int currentActiveDelivery;

  //Store Associations

  @OneToMany
  @JoinColumn(name = "employee_username")
  private List<Employee> employee;

  @OneToMany
  @JoinColumn(name = "item_name")
  private List<Item> item;

  @OneToMany
  @JoinColumn(name = "holiday_name")
  private List<Holiday> holiday;

  @OneToMany
  @JoinColumn(name = "businessHour_hoursID")
  private List<BusinessHour> businessHour = new ArrayList<>();



  //------------------------
  // CONSTRUCTOR
  //------------------------


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

  public void setItem(List<Item> item) {
        this.item = item;
  }

  public void setEmployee(List<Employee> employee) {
        this.employee = employee;
  }

  public void setHoliday(List<Holiday> holiday){
      this.holiday = holiday;
  }

  public void setBusinessHour(List<BusinessHour> businessHour){
      this.businessHour = businessHour;
  }
  public int getStoreID()
  {
        return storeID;
  }

  public List<BusinessHour> getBusinessHour(){
      return businessHour;
  }

  public List<Holiday> getHoliday(){
      return holiday;
  }

  public List<Employee> getEmployee()
  {
        return employee;
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


  public List<Item> getItem()
  {
    List<Item> newItem = Collections.unmodifiableList(item);
    return newItem;
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