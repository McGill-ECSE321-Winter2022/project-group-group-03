/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.*;
import java.sql.Time;

// line 58 "../../../../../../model.ump"
// line 225 "../../../../../../model.ump"
@Entity
public class BusinessHours
{


  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BusinessHours Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="BH_id")
  private int hoursID;
  @Column(name="BH_startT")
  private Time startTime;
  @Column(name="BH_endT")
  private Time endTime;
  @Enumerated
  @Column(name="BH_day")
  private DayOfWeek day;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BusinessHours() {}

  public BusinessHours(int aHoursID, Time aStartTime, Time aEndTime, DayOfWeek aDay)
  {
    hoursID = aHoursID;
    startTime = aStartTime;
    endTime = aEndTime;
    day = aDay;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHoursID(int aHoursID)
  {
    boolean wasSet = false;
    hoursID = aHoursID;
    wasSet = true;
    return wasSet;
  }

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

  public boolean setDay(DayOfWeek aDay)
  {
    boolean wasSet = false;
    day = aDay;
    wasSet = true;
    return wasSet;
  }

  public int getHoursID()
  {
    return hoursID;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
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
            "hoursID" + ":" + getHoursID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}