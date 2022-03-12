package ca.mcgill.ecse321.GroceryStore.dto;

import ca.mcgill.ecse321.GroceryStore.model.WorkShift;

import java.sql.Time;

public class WorkShiftDTO {

    private Time startTime;
    private Time endTime;
    private int shiftID;
    private String day;




    public WorkShiftDTO(Time startTime, Time endTime, int shiftID, String aDay) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftID = shiftID;
        this.day = aDay;

    }

    public Time getStartTime() {
        return startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    public int getShiftID() {
       return shiftID;
    }
    public String getDay() {
        return day;
    }

}
