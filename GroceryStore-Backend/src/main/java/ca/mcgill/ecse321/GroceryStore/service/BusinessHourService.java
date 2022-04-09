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

    /**
     * This method would create an object business hour in our database with the right checks
     * @param startTime is the time of start of the business hour that is being created
     * @param endTime is the time of end of the business hour that is being created
     * @param aDayOfWeek is the day of the week of the business day that is being crated
     * @return the BusinessHour object that was created
     */

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
        //System.out.println(newBusinessHour);
        return newBusinessHour;
    }

    /**
     * This method grabs the BusinessHour object that corresponds to the id
     * @param hoursID it takes in the specific hour of the business hour
     * @return the object BusinessHour with that ID
     */

    @Transactional
    public BusinessHour getBusinessHour(int hoursID) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) throw new IllegalArgumentException("The Business Hour with ID: " + hoursID + " was not found in the database.");
        return businessHour;
    }

    /**
     * Gets all the businessHour that is in the system
     * @return a list of businessHour
     */

    @Transactional
    public List<BusinessHour> getAllBusinessHours() {
        System.out.println(toList(businessHourRepository.findAll()));
        return toList(businessHourRepository.findAll());
    }

    /**
     * Deletes the item that is associated to that specific id
     * @param hoursID the id that is associated to a specific business hour
     */

    @Transactional
    public void deleteBusinessHour(int hoursID) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) throw new IllegalArgumentException("The business hour with ID: " + hoursID + " does not exist.");
        else businessHourRepository.deleteById(hoursID);
    }

    /**
     * Updates the time of start of a business hour that is associated to the business hour
     * @param hoursID  the id that is associated to a specific business hour
     * @param time the new start time that the business hour wants to update to
     * @return the object business hour with the new start time
     */
    @Transactional
    public BusinessHour updateBusinessHourStartTime(int hoursID, Time time) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) throw new IllegalArgumentException("The business hour with ID: " + hoursID + " does not exist.");
        if (time == null) throw new IllegalArgumentException("A time parameter is needed.");
        //if (time.after(businessHour.getEndTime())) throw new IllegalArgumentException("Start Time cannot be after End Time.");
        businessHour.setStartTime(time);
        System.out.println(businessHour);
        return  businessHour;
    }

    /**
     * Updates the time of start and end time of a business hour that is associated to the business hour
     * @param hoursID  the id that is associated to a specific business hour
     * @param newStartTime the new start time that the business hour wants to update to
     * @param newEndTime the new end time that the business hour wants to update to
     * @return the object business hour with the new start and end time
     */
    @Transactional
    public BusinessHour updateBusinessHour(int hoursID, Time newStartTime, Time newEndTime) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) throw new IllegalArgumentException("The business hour with ID: " + hoursID + " does not exist.");
        if (newStartTime == null) throw new IllegalArgumentException("A Start Time parameter is needed.");
        if (newEndTime == null) throw new IllegalArgumentException("An End Time parameter is needed.");
//       if (newStartTime.after(newEndTime)) throw new IllegalArgumentException("Start Time cannot be after End Time.");
//       if (newStartTime.before(businessHour.getStartTime())) {
//            businessHour.setStartTime(newStartTime);
//            businessHour.setEndTime(newEndTime);
//        }
//        if (newEndTime.after(businessHour.getEndTime())) {
//            businessHour.setEndTime(newEndTime);
//            businessHour.setStartTime(newStartTime);
//        }
        return businessHour;
    }

    /**
     * Updates the time of end of a business hour that is associated to the business hour
     * @param hoursID  the id that is associated to a specific business hour
     * @param time the new end time that the business hour wants to update to
     * @return the object business hour with the new end time
     */
    @Transactional
    public BusinessHour updateBusinessHourEndTime(int hoursID, Time time) {
        BusinessHour businessHour = businessHourRepository.findByHoursID(hoursID);
        if (businessHour == null) throw new IllegalArgumentException("The business hour with ID: " + hoursID + " does not exist.");
        if (time == null) throw new IllegalArgumentException("A time parameter is needed.");
        //if (time.before(businessHour.getStartTime())) throw new IllegalArgumentException("End Time cannot be before Start Time.");
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