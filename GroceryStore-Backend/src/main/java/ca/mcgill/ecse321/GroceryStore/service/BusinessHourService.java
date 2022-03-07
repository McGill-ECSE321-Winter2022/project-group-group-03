package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.BusinessHourRepository;
import ca.mcgill.ecse321.GroceryStore.model.BusinessHour;
import ca.mcgill.ecse321.GroceryStore.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class BusinessHourService {
    @Autowired
    BusinessHourRepository businessHourRepository;

    public BusinessHour createBusinessHour(Time startTime, Time endTime, BusinessHour.DayOfWeek aDayOfWeek){
        BusinessHour newBusinessHour = new BusinessHour();
        newBusinessHour.setStartTime(startTime);
        newBusinessHour.setEndTime(endTime);
        newBusinessHour.setDay(aDayOfWeek);
        return newBusinessHour;
    }
}
