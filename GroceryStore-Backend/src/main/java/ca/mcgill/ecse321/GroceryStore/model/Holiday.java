/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.GroceryStore.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;
import java.sql.Date;

// line 66 "../../../../../../model.ump"
// line 235 "../../../../../../model.ump"
@Entity
public class Holiday
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Holiday Attributes
  @Id
  private String name;
  private Date startDate;
  private Date endDate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

//  public Holiday(String aName, Date aStartDate, Date aEndDate)
//  {
//    startDate = aStartDate;
//    endDate = aEndDate;
//    if (!setName(aName))
//    {
//      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
//    }
//  }

  public Holiday() {

  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setName(String aName) {
   this.name =aName;
  }

  public void setStartDate(Date aStartDate) {
    startDate = aStartDate;
  }

  public void setEndDate(Date aEndDate) {
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



  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}