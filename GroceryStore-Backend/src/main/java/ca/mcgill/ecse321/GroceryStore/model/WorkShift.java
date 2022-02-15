package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;


@Entity
public class WorkShift{


  

   public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
   
   private static int nextShiftID = 1;
   private Time startTime;
   private Time endTime;
   @Id
   private int shiftID;
   private GroceryStoreSystem groceryStoreSystem;

   public WorkShift(Time aStartTime, Time aEndTime, GroceryStoreSystem aGroceryStoreSystem) {
      startTime = aStartTime;
      endTime = aEndTime;
      shiftID = nextShiftID++;
      groceryStoreSystem = aGroceryStoreSystem;
   }

   public WorkShift() {
   }
   
   @ManyToOne(optional=false)
   public GroceryStoreSystem getGroceryStoreSystem() {
      return this.groceryStoreSystem;
   }
   
   public void setGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
      this.groceryStoreSystem = groceryStoreSystem;
   }
   public Time getStartTime()
   {
      return startTime;
   }

   public void setStartTime(Time aStartTime) {
      startTime = aStartTime;
   }
   public Time getEndTime()
   {
      return endTime;
   }
   public void setEndTime(Time aEndTime)
   {
      endTime = aEndTime;
   }
   public int getShiftID()
   {
      return shiftID;
   }

   }