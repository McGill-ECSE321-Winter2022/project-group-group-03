package ca.mcgill.ecse321.GroceryStore.dto;

import java.sql.Time;

public class BusinessHourDTO {

    private int hoursID;
    private Time startTime;
    private Time endTime;
    private enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
    private DayOfWeek day;

    public BusinessHourDTO() {}

    @SuppressWarnings("unchecked")
    public BusinessHourDTO(int hoursID, Time startTime, Time endTime, DayOfWeek day) {
        this.hoursID = hoursID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public int getHoursID() {return hoursID;}

    public Time getStartTime() {return startTime;}

    public Time getEndTime() {return endTime;}

    public DayOfWeek getDay() {return day;}
}