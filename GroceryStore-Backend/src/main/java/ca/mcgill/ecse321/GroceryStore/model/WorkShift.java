package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.*;
import java.sql.Time;


@Entity
public class WorkShift{
   @ManyToOne
   private Employee employee;

  

   public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
   @Id
   private static int nextShiftID = 1;
   private Time startTime;
   private Time endTime;
   private int shiftID;
   private GroceryStoreSystem groceryStoreSystem;

   public WorkShift(Time aStartTime, Time aEndTime, Employee aEmployee, GroceryStoreSystem aGroceryStoreSystem) {
      startTime = aStartTime;
      endTime = aEndTime;
      shiftID = nextShiftID++;
      setEmployee(aEmployee);
      groceryStoreSystem = aGroceryStoreSystem;
   }

   public WorkShift() {
   }

   @ManyToOne
   public Employee getEmployee() {
      return this.employee;
   }
   
   public void setEmployee(Employee aEmployee) {
      this.employee = aEmployee;
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