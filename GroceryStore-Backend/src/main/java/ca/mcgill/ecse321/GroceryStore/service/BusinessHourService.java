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
    public BusinessHour createBusinessHour(Time startTime, Time endTime,  String aDayOfWeek) {
        ArrayList<String> errorMessages = new ArrayList<>();

        if (startTime == null) errorMessages.add("Start Time can't be empty.");

        if (endTime == null) errorMessages.add("End Time can't be empty.");

        if (endTime != null && startTime != null && endTime.before(startTime)) {
            errorMessages.add("End Time cannot be before Start Time.");
        }

        if (aDayOfWeek == null) errorMessages.add("Day can't be empty.");

        for (BusinessHour businessHour : this.getAllBusinessHours()) {
            if (businessHour.getStartTime() == startTime && businessHour.getEndTime() == endTime && businessHour.getDay().name().equals(aDayOfWeek)) {
                errorMessages.add("An identical Business Hour already exists.");
                break;
            }
        }

        BusinessHour newBusinessHour = new BusinessHour();

        if (aDayOfWeek != null) {
            switch (aDayOfWeek) {
                case "Monday" -> newBusinessHour.setDay(BusinessHour.DayOfWeek.Monday);
                case "Tuesday" -> newBusinessHour.setDay(BusinessHour.DayOfWeek.Tuesday);
                case "Wednesday" -> newBusinessHour.setDay(BusinessHour.DayOfWeek.Wednesday);
                case "Thursday" -> newBusinessHour.setDay(BusinessHour.DayOfWeek.Thursday);
                case "Friday" -> newBusinessHour.setDay(BusinessHour.DayOfWeek.Friday);
                case "Saturday" -> newBusinessHour.setDay(BusinessHour.DayOfWeek.Saturday);
                case "Sunday" -> newBusinessHour.setDay(BusinessHour.DayOfWeek.Sunday);
                default -> errorMessages.add("Please enter a valid day of the week.");
            }
        }


        if (errorMessages.size() > 0) throw new IllegalArgumentException(String.join(" ", errorMessages));

        while(businessHourRepository.existsById(curID)){
            curID++;
        }


        newBusinessHour.setStartTime(startTime);
        newBusinessHour.setEndTime(endTime);
        newBusinessHour.setHoursID(curID++);

        businessHourRepository.save(newBusinessHour);
        return newBusinessHour;
    }

    @Transactional
    public BusinessHour getBusinessHour(int hoursID) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) throw new IllegalArgumentException("The Business Hour with ID: " + hoursID + " was not found in the database.");
        return businessHour;
    }

    @Transactional
    public List<BusinessHour> getAllBusinessHours() {return toList(businessHourRepository.findAll());}

    @Transactional
    public void deleteBusinessHour(int hoursID) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
       if (businessHour == null) throw new IllegalArgumentException("The business hour with ID: " + hoursID + " does not exist.");
       else businessHourRepository.deleteById(hoursID);
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}