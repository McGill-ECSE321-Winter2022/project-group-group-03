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

    @Autowired
    StoreService storeService;


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
            if (businessHour.getDay().name().equals(aDayOfWeek)) {
                errorMessages.add("The Business Hour for " + aDayOfWeek + " has already been set.");
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
        storeService.addBusinessHour(newBusinessHour);
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

    @Transactional
    public BusinessHour updateBusinessHourStartTime(int hoursID, Time time) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) throw new IllegalArgumentException("The business hour with ID: " + hoursID + " does not exist.");
        if (time == null) throw new IllegalArgumentException("A time parameter is needed.");
        if (time.after(businessHour.getEndTime())) throw new IllegalArgumentException("Start Time cannot be after End Time.");
        businessHour.setStartTime(time);
        return  businessHour;
    }

    @Transactional
    public BusinessHour updateBusinessHour(int hoursID, Time newStartTime, Time newEndTime) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) throw new IllegalArgumentException("The business hour with ID: " + hoursID + " does not exist.");
        if (newStartTime == null) throw new IllegalArgumentException("A Start Time parameter is needed.");
        if (newEndTime == null) throw new IllegalArgumentException("An End Time parameter is needed.");
        if (newStartTime.after(newEndTime)) throw new IllegalArgumentException("Start Time cannot be after End Time.");
        if (newStartTime.before(businessHour.getStartTime())) {
            businessHour.setStartTime(newStartTime);
            businessHour.setEndTime(newEndTime);
        }
        if (newEndTime.after(businessHour.getEndTime())) {
            businessHour.setEndTime(newEndTime);
            businessHour.setStartTime(newStartTime);
        }
        return businessHour;
    }

    @Transactional
    public BusinessHour updateBusinessHourEndTime(int hoursID, Time time) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) throw new IllegalArgumentException("The business hour with ID: " + hoursID + " does not exist.");
        if (time == null) throw new IllegalArgumentException("A time parameter is needed.");
        if (time.before(businessHour.getStartTime())) throw new IllegalArgumentException("End Time cannot be before Start Time.");
        businessHour.setEndTime(time);
        return businessHour;
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}