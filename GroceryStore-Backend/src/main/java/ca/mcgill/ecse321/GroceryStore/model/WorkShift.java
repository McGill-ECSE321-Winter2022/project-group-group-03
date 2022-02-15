/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
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
  @Column(name="WS_startT")
  private Time startTime;
  @Column(name="WS_endT")
  private Time endTime;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="WS_id")
  private int shiftID;
  @Enumerated
  @Column(name="WS_day")
  private DayOfWeek day;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WorkShift() {}

  public WorkShift(Time aStartTime, Time aEndTime, int aShiftID, DayOfWeek aDay)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    shiftID = aShiftID;
    day = aDay;
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

  public boolean setDay(DayOfWeek aDay)
  {
    boolean wasSet = false;
    day = aDay;
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

  public DayOfWeek getDay()
  {
    return day;
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