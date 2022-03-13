package ca.mcgill.ecse321.GroceryStore.dto;



import java.sql.Date;

public class HolidayDTO {
    private String name;
    private Date startDate;
    private Date endDate;



    public HolidayDTO(String aName, Date aStartDate, Date aEndDate) {
        this.name = aName;
        this.startDate = aStartDate;
        this.endDate = aEndDate;
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




}
