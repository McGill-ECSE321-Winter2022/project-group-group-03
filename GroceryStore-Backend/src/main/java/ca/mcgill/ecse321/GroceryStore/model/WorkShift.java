/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

// line 50 "../../../../../../model.ump"
// line 219 "../../../../../../model.ump"
@Entity
public class WorkShift
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WorkShift Attributes
  private Time startTime;
  private Time endTime;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int shiftID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WorkShift() {}

  public WorkShift(Time aStartTime, Time aEndTime, int aShiftID)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    shiftID = aShiftID;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setShiftID(int aShiftID)
  {
    boolean wasSet = false;
    shiftID = aShiftID;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public int getShiftID()
  {
    return shiftID;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "shiftID" + ":" + getShiftID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}