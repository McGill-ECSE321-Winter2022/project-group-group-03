package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import java.sql.Time;
import java.util.ArrayList;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Employee extends NamedUser{

   private ArrayList<WorkShift> workShift;
   public enum WorkingStatus { Hired, Fired }
   private WorkingStatus workingStatus;

   //Employee Associations
   private Store store;
   public Employee(){}
   public Employee(String aUsername, String aPassword, String aEmail, GroceryStoreSystem aGroceryStoreSystem, String aName, String aAddress, Store aStore)
   {
      super(aUsername, aPassword, aEmail, aGroceryStoreSystem, aName, aAddress);
      setStore(aStore);
      workShift = new ArrayList<>();
      setWorkingStatus(WorkingStatus.Hired);
   }
   @ManyToMany
   public ArrayList<WorkShift> getWorkShift() {
      return this.workShift;
   }
   
   public void setWorkShift(ArrayList<WorkShift> workShifts) {
      this.workShift = workShifts;
   }

   @ManyToOne
   public Store getStore() {
      return this.store;
   }
   public void setStore(Store aStore) {
      this.store = aStore;
   }


   public String getWorkingStatusFullName()
   {
      return workingStatus.toString();
   }
   public void setWorkingStatusFullName(String workingStatusFullName) {}

   public WorkingStatus getWorkingStatus()
   {
      return workingStatus;
   }

   public boolean fireEmployee()
   {
      boolean wasEventProcessed = false;

      WorkingStatus aWorkingStatus = workingStatus;
      // Other states do respond to this event
      if (aWorkingStatus == WorkingStatus.Hired) {
         setWorkingStatus(WorkingStatus.Fired);
         wasEventProcessed = true;
      }

      return wasEventProcessed;
   }
   private void setWorkingStatus(WorkingStatus aWorkingStatus)
   {
      workingStatus = aWorkingStatus;
   }

   public static int minimumNumberOfWorkShifts()
   {
      return 0;
   }
   public WorkShift addWorkShift(Time aStartTime, Time aEndTime, GroceryStoreSystem aGroceryStoreSystem) {
      return new WorkShift(aStartTime, aEndTime, aGroceryStoreSystem);
   }
   public void addWorkShift(WorkShift aWorkShift) {
      if (workShift.contains(aWorkShift)) { return; }
      workShift.add(aWorkShift);
   }
   public boolean removeWorkShift(WorkShift aWorkShift)
   {
      boolean wasRemoved = false;
      //Unable to remove aWorkShift, as it must always have a employee
      workShift.remove(aWorkShift);
      return wasRemoved;
   }
   /* Code from template association_AddIndexControlFunctions */
   public void addWorkShiftAt(WorkShift aWorkShift, int index)
   {

      if(index < 0 ) { index = 0; }
         if(index > numberOfWorkShifts()) { index = numberOfWorkShifts() - 1; }
         workShift.remove(aWorkShift);
         workShift.add(index, aWorkShift);
   }

   public void addOrMoveWorkShiftAt(WorkShift aWorkShift, int index)
   {
      if(workShift.contains(aWorkShift))
      {
         if(index < 0 ) { index = 0; }
         if(index > numberOfWorkShifts()) { index = numberOfWorkShifts() - 1; }
         workShift.remove(aWorkShift);
         workShift.add(index, aWorkShift);
      }
      else
      {
         addWorkShiftAt(aWorkShift, index);
      }
   }
   public int numberOfWorkShifts()
   {
      return workShift.size();
   }
   public boolean hasWorkShifts()
   {
      return workShift.size() > 0;
   }
   public int indexOfWorkShift(WorkShift aWorkShift)
   {
      return workShift.indexOf(aWorkShift);
   }

   }
