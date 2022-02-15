package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Entity
public class Store {
   private ArrayList<BusinessHours> businessHours;
   private ArrayList<Employee> employee;
   private ArrayList<Owner> owner;
   private ArrayList<Item> item;
   private static int nextStoreID = 1;
   public static final int MAXPICKUPS = 10;
   public static final int MAXSHIPPING = 10;
   private String address;
   private int currentActivePickup;
   private int currentActiveDelivery;
   @Id
   private int storeID;
   private GroceryStoreSystem groceryStoreSystem;
   private ArrayList<Holiday> holiday;
   private ArrayList<Order> order;

   public Store(String aAddress, GroceryStoreSystem aGroceryStoreSystem) {
      address = aAddress;
      currentActivePickup = 0;
      currentActiveDelivery = 0;
      storeID = nextStoreID++;
      employee = new ArrayList<>();
      item = new ArrayList<>();
      setGroceryStoreSystem(aGroceryStoreSystem);
      businessHours = new ArrayList<>();
      holiday = new ArrayList<>();
      order = new ArrayList<>();
      groceryStoreSystem = aGroceryStoreSystem;
   }

   public Store() {
   }


   @ManyToMany
   public ArrayList<Holiday> getHoliday() {
      return this.holiday;
   }

   public void setHoliday(ArrayList<Holiday> holidays) {
      this.holiday = holidays;
   }
   
   @ManyToMany
   public ArrayList<BusinessHours> getBusinessHours() {
      return this.businessHours;
   }
   
   public void setBusinessHours(ArrayList<BusinessHours> aBusinessHours) {
      this.businessHours = aBusinessHours;
   }

   @OneToMany
   public ArrayList<Employee> getEmployee() {
      return this.employee;
   }

   public void setEmployee(ArrayList<Employee> employees) {
      this.employee = employees;
   }
   
   @ManyToMany
   public ArrayList<Owner> getOwner() {
      return this.owner;
   }
   
   public void setOwner(ArrayList<Owner> owners) {
      this.owner = owners;
   }

   @ManyToMany
   public ArrayList<Item> getItem() {
      return this.item;
   }
   
   public void setItem(ArrayList<Item> items) {
      this.item = items;
   }

   @ManyToOne(optional=false)
   public GroceryStoreSystem getGroceryStoreSystem() {
      return this.groceryStoreSystem;
   }
   
   public void setGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
      this.groceryStoreSystem = groceryStoreSystem;
   }
   public String getAddress()
   {
      return address;
   }
   public void setAddress(String aAddress) {
      address = aAddress;
   }
   public int getCurrentActivePickup()
   {
      return currentActivePickup;
   }
   public void setCurrentActivePickup(int aCurrentActivePickup) {
      currentActivePickup = aCurrentActivePickup;
   }
   public int getCurrentActiveDelivery()
   {
      return currentActiveDelivery;
   }
   public void setCurrentActiveDelivery(int aCurrentActiveDelivery) {
      currentActiveDelivery = aCurrentActiveDelivery;
   }
   public int getStoreID()
   {
      return storeID;
   }
   /* Code from template association_GetMany */

   public Employee getStaff(int index) {
      return employee.get(index);
   }


   public int numberOfStaff() {
      return employee.size();
   }

   public boolean hasStaff() {
      return employee.size() > 0;
   }

   public int indexOfStaff(Employee aStaff) {
      return employee.indexOf(aStaff);
   }

   public boolean hasOwner()
   {
      return owner != null;
   }
   /* Code from template association_GetMany */
   public Item getItem(int index)
   {
      return item.get(index);
   }



   public int numberOfItems()
   {
      return item.size();
   }

   public boolean hasItems()
   {
      return item.size() > 0;
   }

   public int indexOfItem(Item aItem)
   {
      return item.indexOf(aItem);
   }
   public BusinessHours getBusinessHour(int index)
   {
      return businessHours.get(index);
   }


   public int numberOfBusinessHours()
   {
      return businessHours.size();
   }

   public boolean hasBusinessHours()
   {
      return businessHours.size() > 0;
   }

   public int indexOfBusinessHour(BusinessHours aBusinessHour)
   {
      return businessHours.indexOf(aBusinessHour);
   }
   /* Code from template association_GetMany */
   public Holiday getHoliday(int index)
   {
      return holiday.get(index);
   }


   public int numberOfHolidays()
   {
      return holiday.size();
   }

   public boolean hasHolidays()
   {
      return holiday.size() > 0;
   }

   public int indexOfHoliday(Holiday aHoliday)
   {
      return holiday.indexOf(aHoliday);
   }
   /* Code from template association_GetMany */

   public Order getOrder(int index)
   {
      return order.get(index);
   }
   @OneToMany
   public ArrayList<Order> getOrder()
   {
      return this.order;
   }
   public void setOrder(ArrayList<Order> order){
      this.order = order;
   }

   public int numberOfOrders()
   {
      return order.size();
   }

   public boolean hasOrders()
   {
      return order.size() > 0;
   }

   public int indexOfOrder(Order aOrder)
   {
      return order.indexOf(aOrder);
   }
   /* Code from template association_MinimumNumberOfMethod */
   public static int minimumNumberOfStaff()
   {
      return 0;
   }
   /* Code from template association_AddManyToOne */
   public Employee addStaff(String aUsername, String aPassword, String aEmail, GroceryStoreSystem aGroceryStoreSystem, String aName, String aAddress)
   {
      return new Employee(aUsername, aPassword, aEmail, aGroceryStoreSystem, aName, aAddress, this);
   }

   public void addStaff(Employee aStaff)
   {
      boolean wasAdded = false;
      if (employee.contains(aStaff)) { return; }
      Store existingStore = aStaff.getStore();
      boolean isNewStore = existingStore != null && !this.equals(existingStore);
      if (isNewStore)
      {
         aStaff.setStore(this);
      }
      else
      {
         employee.add(aStaff);
      }

   }

   public void removeStaff(Employee aStaff)
   {
      //Unable to remove aStaff, as it must always have a store
      if (!this.equals(aStaff.getStore()))
      {
         employee.remove(aStaff);
      }
   }

   /* Code from template association_SetOptionalOneToOne */
   public void setOwner(Owner aNewOwner)
   {
      if (owner != null && !owner.get(0).equals(aNewOwner) && equals(owner.get(0).getStore().get(0)))
      {
         //Unable to setOwner, as existing owner would become an orphan
         return;
      }

      if (aNewOwner != null && owner != null) owner.add(aNewOwner);
      Store anOldStore = aNewOwner != null ? aNewOwner.getStore().get(0) : null;

      if (!this.equals(anOldStore))
      {
         if (anOldStore != null)
         {
            anOldStore.owner = null;
         }
         if (owner != null)
         {
            ArrayList<Store>stores = new ArrayList<>();
            stores.add(this);
            owner.get(0).setStore(stores);
         }
      }
   }
   /* Code from template association_MinimumNumberOfMethod */
   public static int minimumNumberOfItems()
   {
      return 0;
   }
   /* Code from template association_AddManyToOne */
   public Item addItem(String aName, boolean aPurchasable, int aPrice, String aDescription, int aStock, int aTotalPurchased, GroceryStoreSystem aGroceryStoreSystem)
   {
      return new Item(aName, aPurchasable, aPrice, aDescription, aStock, aTotalPurchased, aGroceryStoreSystem, this);
   }

   public void addItem(Item aItem)
   {
      if (item.contains(aItem)) { return; }
      ArrayList<Store> existingStore = aItem.getStore();
      boolean isNewStore = existingStore != null && !this.equals(existingStore.get(0));
      if (isNewStore)
      {
         ArrayList<Store>stores = new ArrayList<>();
         stores.add(this);
         aItem.setStore(stores);
      }
      else
      {
         item.add(aItem);
      }

   }

   public void removeItem(Item aItem)
   {
      //Unable to remove aItem, as it must always have a store
      if (!this.equals(aItem.getStore().get(0)))
      {
         item.remove(aItem);
      }

   }

   /* Code from template association_MinimumNumberOfMethod */
   public static int minimumNumberOfBusinessHours()
   {
      return 0;
   }
   /* Code from template association_AddManyToOne */
   public BusinessHours addBusinessHour(Time aStartTime, Time aEndTime, GroceryStoreSystem aGroceryStoreSystem)
   {
      return new BusinessHours(aStartTime, aEndTime, this, aGroceryStoreSystem);
   }

   public void addBusinessHour(BusinessHours aBusinessHour)
   {
      if (businessHours.contains(aBusinessHour)) { return; }
      Store existingStore = aBusinessHour.getStore();
      boolean isNewStore = existingStore != null && !this.equals(existingStore);
      if (isNewStore)
      {
         aBusinessHour.setStore(this);
      }
      else
      {
         businessHours.add(aBusinessHour);
      }
   }

   public void removeBusinessHour(BusinessHours aBusinessHour)
   {
      //Unable to remove aBusinessHour, as it must always have a store
      if (!this.equals(aBusinessHour.getStore()))
      {
         businessHours.remove(aBusinessHour);
      }
   }
   public static int minimumNumberOfHolidays()
   {
      return 0;
   }
   /* Code from template association_AddManyToOne */
   public Holiday addHoliday(String aName, Date aStartDate, Date aEndDate, GroceryStoreSystem aGroceryStoreSystem)
   {
      return new Holiday(aName, aStartDate, aEndDate, aGroceryStoreSystem);
   }

   public void addHoliday(Holiday aHoliday)
   {
      if (holiday.contains(aHoliday)) { return; }
      holiday.add(aHoliday);
   }

   public void removeHoliday(Holiday aHoliday){
      //Unable to remove aHoliday, as it must always have a store
      holiday.remove(aHoliday);
   }
   /* Code from template association_AddIndexControlFunctions */

   public static int minimumNumberOfOrders()
   {
      return 0;
   }
   /* Code from template association_AddManyToManyMethod */
   public void addOrder(Order aOrder)
   {
      if (order.contains(aOrder)) { return; }
      order.add(aOrder);
   }
   /* Code from template association_RemoveMany */
   public void removeOrder(Order aOrder)
   {
      if (!order.contains(aOrder))
      {
         return;
      }

      order.remove(aOrder);
   }

}
