package ca.mcgill.ecse321.GroceryStore.dto;

import java.sql.Time;

public class BusinessHourDTO {

    private int hoursID;
    private Time startTime;
    private Time endTime;
    private String day;


    public BusinessHourDTO() {}

    @SuppressWarnings("unchecked")
    public BusinessHourDTO(int hoursID, Time startTime, Time endTime, String day) {
        this.hoursID = hoursID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public int getHoursID() {return hoursID;}

    public Time getStartTime() {return startTime;}

    public Time getEndTime() {return endTime;}

    public String getDay() {return day;}
}