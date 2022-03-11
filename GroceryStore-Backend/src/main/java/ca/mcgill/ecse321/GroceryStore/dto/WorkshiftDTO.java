package ca.mcgill.ecse321.GroceryStore.dto;

import ca.mcgill.ecse321.GroceryStore.model.WorkShift;

import java.sql.Time;
import java.time.DayOfWeek;

public class WorkshiftDTO {

    private Time startTime;
    private Time endTime;
    private int shiftID;

    public WorkshiftDTO() {
    }

    public WorkshiftDTO(Time startTime, Time endTime, int shiftID) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftID = shiftID;
    }

    public Time getStartTime() {
        return startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    public int getShiftID() {
//        return shiftID;
    }


}
