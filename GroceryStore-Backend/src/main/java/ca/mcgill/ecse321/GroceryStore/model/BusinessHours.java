package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BusinessHours{
   public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
   private Time startTime;
   private Time endTime;
   private static int nextHoursID = 1;
   
   private int hoursID;
   private GroceryStoreSystem groceryStoreSystem;



   //------------------------
   // CONSTRUCTOR
   //------------------------

   public BusinessHours() {}

   public BusinessHours(Time aStartTime, Time aEndTime, GroceryStoreSystem aGroceryStoreSystem)
   {
      startTime = aStartTime;
      endTime = aEndTime;
      hoursID = nextHoursID++;
      groceryStoreSystem = aGroceryStoreSystem;
   }


   @ManyToOne(optional=false)
   public GroceryStoreSystem getGroceryStoreSystem() {
      return this.groceryStoreSystem;
   }

   public void setGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
      this.groceryStoreSystem = groceryStoreSystem;
   }



   //------------------------
   // INTERFACE
   //------------------------

   public void setStartTime(Time aStartTime)
   {
      startTime = aStartTime;
   }

   public void setEndTime(Time aEndTime)
   {
      endTime = aEndTime;
   }

   public Time getStartTime()
   {
      return startTime;
   }

   public Time getEndTime()
   {
      return endTime;
   }
   @Id
   public int getHoursID()
   {
      return hoursID;
   }
   
}
