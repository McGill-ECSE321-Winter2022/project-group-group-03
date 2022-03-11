package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.BusinessHourRepository;
import ca.mcgill.ecse321.GroceryStore.model.BusinessHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessHourService {

    @Autowired
    BusinessHourRepository businessHourRepository;


    private static int curID = 10000;

    @Transactional
    public BusinessHour createBusinessHour(Time startTime, Time endTime, BusinessHour.DayOfWeek aDayOfWeek){
        BusinessHour newBusinessHour = new BusinessHour();
        newBusinessHour.setStartTime(startTime);
        newBusinessHour.setEndTime(endTime);
        newBusinessHour.setDay(aDayOfWeek);
        newBusinessHour.setHoursID(curID++);
        businessHourRepository.save(newBusinessHour);
        return newBusinessHour;
    }

    @Transactional
    public BusinessHour getBusinessHour(int hoursID) {
        return businessHourRepository.findByHoursID(hoursID);
    }

    @Transactional
    public List<BusinessHour> getAllBusinessHours() {
        return toList(businessHourRepository.findAll());
    }

    @Transactional
    public void deleteBusinessHour(int hoursID) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) {
            throw new IllegalArgumentException("The business hour with ID: " + hoursID + " does not exist.");
        } else {
            businessHourRepository.deleteById(hoursID);
        }
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
