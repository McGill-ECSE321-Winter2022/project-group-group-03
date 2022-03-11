package ca.mcgill.ecse321.GroceryStore.dto;


import ca.mcgill.ecse321.GroceryStore.model.WorkShift;

import java.sql.Time;

public class WorkShiftDTO {

    private Time startTime;
    private Time endTime;
    private int shiftID;


    public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday };
    private DayOfWeek day;

    public WorkShiftDTO(Time startTime, Time endTime, int shiftID, DayOfWeek aDay) {
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
    public DayOfWeek getDay() {
        return day;
    }

}
