package ca.mcgill.ecse321.GroceryStore.dto;

import ca.mcgill.ecse321.GroceryStore.model.Holiday;

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

    public static HolidayDTO fromHoliday(Holiday holiday) throws IllegalArgumentException {
        if(holiday == null) {
            throw new IllegalArgumentException("There is no such holiday.");
        }
        return new HolidayDTO(holiday.getName(), holiday.getStartDate(), holiday.getEndDate());
    }


}
