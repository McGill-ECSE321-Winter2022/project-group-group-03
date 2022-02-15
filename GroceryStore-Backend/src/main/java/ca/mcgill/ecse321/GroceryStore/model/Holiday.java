package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Holiday{
   //------------------------
   // STATIC VARIABLES
   //------------------------

   private static int nextHolidayID = 1;

   //------------------------
   // MEMBER VARIABLES
   //------------------------

   //Holiday Attributes
   private String name;
   private Date startDate;
   private Date endDate;

   //Autounique Attributes
   @Id
   private int holidayID;

   //Holiday Associations
   private Store store;
   private GroceryStoreSystem groceryStoreSystem;

   //------------------------
   // CONSTRUCTOR
   //------------------------


   public Holiday() {}

   public Holiday(String aName, Date aStartDate, Date aEndDate, Store aStore, GroceryStoreSystem aGroceryStoreSystem)
   {
      name = aName;
      startDate = aStartDate;
      endDate = aEndDate;
      holidayID = nextHolidayID++;
      store = aStore;
      groceryStoreSystem = aGroceryStoreSystem;
   }


   @ManyToOne
   public Store getStore() {
      return this.store;
   }
   public void setStore(Store aStore) {
      this.store = aStore;
   }

   
   @ManyToOne(optional=false)
   public GroceryStoreSystem getGroceryStoreSystem() {
      return this.groceryStoreSystem;
   }
   
   public void setGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
      this.groceryStoreSystem = groceryStoreSystem;
   }
   public void setName(String aName)
   {
      name = aName;
   }

   public void setStartDate(Date aStartDate)
   {
      startDate = aStartDate;
   }

   public void setEndDate(Date aEndDate)
   {
      endDate = aEndDate;
   }

   public String getName()
   {
      return name;
   }

   public Date getStartDate()
   {
      return startDate;
   }

   public Date getEndDate()
   {
      return endDate;
   }

   public int getHolidayID()
   {
      return holidayID;
   }

   
   
}
