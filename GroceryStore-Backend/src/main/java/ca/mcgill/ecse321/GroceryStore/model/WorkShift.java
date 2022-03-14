/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

// line 50 "../../../../../../model.ump"
// line 223 "../../../../../../model.ump"
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
 // @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int shiftID;

  @Enumerated
  @Column(nullable = false)
  private DayOfWeek day;



  //------------------------
  // CONSTRUCTOR
  //------------------------

//  public WorkShift(Time aStartTime, Time aEndTime, int aShiftID, DayOfWeek aDay)
//  {
//    startTime = aStartTime;
//    endTime = aEndTime;
//    shiftID = aShiftID;
//    day = aDay;
//  }
  public WorkShift() {

  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setStartTime(Time aStartTime) {
    startTime = aStartTime;
  }

  public void setEndTime(Time aEndTime)
  {
    endTime = aEndTime;

  }

  public boolean setShiftID(int aShiftID)
  {
    boolean wasSet = false;
    shiftID = aShiftID;
    wasSet = true;
    return wasSet;
  }

  public DayOfWeek getDay() {
    return day;
  }

  public void setDay(DayOfWeek day) {
    this.day = day;
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
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "day" + "=" + (getDay() != null ? !getDay().equals(this)  ? getDay().toString().replaceAll("  ","    ") : "this" : "null");
  }
}