package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GroceryStoreSystem{

   private static int nextSystemID = 1;
   @Id
   private int systemID;

   private ArrayList<User> user;
   private ArrayList<Store> store;
   private ArrayList<Holiday> holiday;
   private ArrayList<BusinessHours> businessHours;
   private ArrayList<Item> item;
   private ArrayList<PurchasedItem> purchasedItem;
   private ArrayList<Order> order;
   private ArrayList<WorkShift> workShift;

   public GroceryStoreSystem()
   {
      systemID = nextSystemID++;
      user = new ArrayList<>();
      order = new ArrayList<>();
      store = new ArrayList<>();
      item = new ArrayList<>();
      workShift = new ArrayList<>();
      holiday = new ArrayList<>();
      businessHours = new ArrayList<>();
   }

   public int getSystemID()
   {
      return systemID;
   }


   @OneToMany(mappedBy="groceryStoreSystem" , cascade={CascadeType.ALL})
   public ArrayList<User> getUser() {
      return this.user;
   }

   public void addUser(User aUser)
   {
      if (user.contains(aUser)) { return; }
      GroceryStoreSystem existingGroceryStoreSystem = aUser.getGroceryStoreSystem();
      boolean isNewGroceryStoreSystem = existingGroceryStoreSystem != null && !this.equals(existingGroceryStoreSystem);
      if (isNewGroceryStoreSystem)
      {
         aUser.setGroceryStoreSystem(this);
      }
      else
      {
         user.add(aUser);
      }
   }

   public void removeUser(User aUser)
   {
      //Unable to remove aUser, as it must always have a groceryStoreSystem
      if (!this.equals(aUser.getGroceryStoreSystem()))
      {
         user.remove(aUser);
      }
   }

   public int numberOfUser() {
      return user.size();
   }

   public void setUser(ArrayList<User> users) {
      this.user = users;
   }
   

   @OneToMany(mappedBy="groceryStoreSystem" , cascade={CascadeType.ALL})
   public ArrayList<Store> getStore() {
      return this.store;
   }

   public void addStore(Store aStore)
   {
      if (store.contains(aStore)) { return; }
      GroceryStoreSystem existingGroceryStoreSystem = aStore.getGroceryStoreSystem();
      boolean isNewGroceryStoreSystem = existingGroceryStoreSystem != null && !this.equals(existingGroceryStoreSystem);
      if (isNewGroceryStoreSystem)
      {
         aStore.setGroceryStoreSystem(this);
      }
      else
      {
         store.add(aStore);
      }
   }

   public void removeStore(Store aStore)
   {
      //Unable to remove aStore, as it must always have a groceryStoreSystem
      if (!this.equals(aStore.getGroceryStoreSystem()))
      {
         store.remove(aStore);
      }
   }
   public int numberOfStore() {
      return store.size();
   }
   public void setStore(ArrayList<Store> stores) {
      this.store = stores;
   }
   

   
   @OneToMany(mappedBy="groceryStoreSystem" , cascade={CascadeType.ALL})
   public ArrayList<Holiday> getHoliday() {
      return this.holiday;
   }

   public void addHoliday(Holiday aHoliday)
   {
      if (holiday.contains(aHoliday)) { return ; }
      GroceryStoreSystem existingGroceryStoreSystem = aHoliday.getGroceryStoreSystem();
      boolean isNewGroceryStoreSystem = existingGroceryStoreSystem != null && !this.equals(existingGroceryStoreSystem);
      if (isNewGroceryStoreSystem)
      {
         aHoliday.setGroceryStoreSystem(this);
      }
      else
      {
         holiday.add(aHoliday);
      }
   }

   public void removeHoliday(Holiday aHoliday)
   {
      //Unable to remove aHoliday, as it must always have a groceryStoreSystem
      if (!this.equals(aHoliday.getGroceryStoreSystem()))
      {
         holiday.remove(aHoliday);
      }
   }

   public int numberOfHoliday() {
      return holiday.size();
   }

   public void setHoliday(ArrayList<Holiday> holidays) {
      this.holiday = holidays;
   }
   

   
   @OneToMany(mappedBy="groceryStoreSystem" , cascade={CascadeType.ALL})
   public ArrayList<BusinessHours> getBusinessHours() {
      return this.businessHours;
   }

   public void addBusinessHour(BusinessHours aBusinessHour)
   {
      if (businessHours.contains(aBusinessHour)) { return; }
      GroceryStoreSystem existingGroceryStoreSystem = aBusinessHour.getGroceryStoreSystem();
      boolean isNewGroceryStoreSystem = existingGroceryStoreSystem != null && !this.equals(existingGroceryStoreSystem);
      if (isNewGroceryStoreSystem)
      {
         aBusinessHour.setGroceryStoreSystem(this);
      }
      else
      {
         businessHours.add(aBusinessHour);
      }
   }

   public void removeBusinessHour(BusinessHours aBusinessHour)
   {
      //Unable to remove aBusinessHour, as it must always have a groceryStoreSystem
      if (!this.equals(aBusinessHour.getGroceryStoreSystem()))
      {
         businessHours.remove(aBusinessHour);
      }
   }

   public int numberOfBusinessHours() {
      int number = businessHours.size();
      return number;
   }

   public void setBusinessHours(ArrayList<BusinessHours> businessHourss) {
      this.businessHours = businessHourss;
   }
   

   
   @OneToMany(mappedBy="groceryStoreSystem" , cascade={CascadeType.ALL})
   public ArrayList<Item> getItem() {
      return this.item;
   }

   public int numberOfItems() {
      int number = item.size();
      return number;
   }

   public void addItem(Item aItem)
   {
      if (item.contains(aItem)) { return; }
      GroceryStoreSystem existingGroceryStoreSystem = aItem.getGroceryStoreSystem();
      boolean isNewGroceryStoreSystem = existingGroceryStoreSystem != null && !this.equals(existingGroceryStoreSystem);
      if (isNewGroceryStoreSystem)
      {
         aItem.setGroceryStoreSystem(this);
      }
      else
      {
         item.add(aItem);
      }
   }

   public void removeItem(Item aItem)
   {
      //Unable to remove aItem, as it must always have a groceryStoreSystem
      if (!this.equals(aItem.getGroceryStoreSystem()))
      {
         item.remove(aItem);
      }
   }

   public void setItem(ArrayList<Item> items) {
      this.item = items;
   }
   

   
   @OneToMany(mappedBy="groceryStoreSystem" , cascade={CascadeType.ALL})
   public ArrayList<PurchasedItem> getPurchasedItem() {
      return this.purchasedItem;
   }


   
   public void setPurchasedItem(ArrayList<PurchasedItem> purchasedItems) {
      this.purchasedItem = purchasedItems;
   }
   

   
   @OneToMany(mappedBy="groceryStoreSystem" , cascade={CascadeType.ALL})
   public ArrayList
           <Order> getOrder() {
      return this.order;
   }


   public void addOrder(Order aOrder)
   {
      if (order.contains(aOrder)) { return; }
      GroceryStoreSystem existingGroceryStoreSystem = aOrder.getGroceryStoreSystem();
      boolean isNewGroceryStoreSystem = existingGroceryStoreSystem != null && !this.equals(existingGroceryStoreSystem);
      if (isNewGroceryStoreSystem)
      {
         aOrder.setGroceryStoreSystem(this);
      }
      else
      {
         order.add(aOrder);
      }
   }
   public void removeOrder(Order aOrder)
   {
      //Unable to remove aOrder, as it must always have a groceryStoreSystem
      if (!this.equals(aOrder.getGroceryStoreSystem()))
      {
         order.remove(aOrder);
      }
   }

   public int numberOfOrders() {
      return order.size();
   }

   public void setOrder(ArrayList<Order> orders) {
      this.order = orders;
   }
   

   
   @OneToMany(mappedBy="groceryStoreSystem" , cascade={CascadeType.ALL})
   public ArrayList<WorkShift> getWorkShift() {
      return this.workShift;
   }

   public void addShift(WorkShift aShift)
   {
      if (workShift.contains(aShift)) { return; }
      GroceryStoreSystem existingGroceryStoreSystem = aShift.getGroceryStoreSystem();
      boolean isNewGroceryStoreSystem = existingGroceryStoreSystem != null && !this.equals(existingGroceryStoreSystem);
      if (isNewGroceryStoreSystem)
      {
         aShift.setGroceryStoreSystem(this);
      }
      else
      {
         workShift.add(aShift);
      }
   }

   public void removeShift(WorkShift aShift)
   {
      boolean wasRemoved = false;
      //Unable to remove aShift, as it must always have a groceryStoreSystem
      if (!this.equals(aShift.getGroceryStoreSystem()))
      {
         workShift.remove(aShift);
         wasRemoved = true;
      }
   }

   public int numberOfShift() {
      return workShift.size();
   }

   public void setWorkShift(ArrayList<WorkShift> workShifts) {
      this.workShift = workShifts;
   }


   public String toString()
   {
      return super.toString() + "["+
              "systemID" + ":" + getSystemID()+ "]";
   }

   }
