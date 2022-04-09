package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.HolidayRepository;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayService {
    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    StoreService storeService;

    /**
     * Creates a Holiday with all the credentials of said Holiday
     * @param name the name of the holiday
     * @param startDate the start date of said holiday
     * @param endDate the end date of said holiday
     * @return the holiday object that was just created
     */
    @Transactional
    public Holiday createHoliday(String name, Date startDate, Date endDate) {
        Holiday holiday = new Holiday();
        List<Holiday> holidays = this.getAllHolidays();
        String error = null;
        if (name == null || name.trim().length() == 0) {
            error = "Name can't be empty.";
        }
        else if (startDate == null || startDate.equals("")) {
            error = "Start Date can't be empty.";
        } else if (endDate == null || endDate.equals("")) {
            error = "End Date can't be empty.";
        } else if (startDate.compareTo(endDate) > 0) {
            error = "Start Date can't be after End Date.";
        } else if (holidays != null && holidays.size() != 0) {
            for (Holiday h : holidays) {
                if (h.getName().equals(name)) {
                    error = "An identical holiday with the same name already exists.";
                    break;
                } else if (h.getStartDate().compareTo(startDate) == 0 && h.getEndDate().compareTo(endDate) == 0) {
                    error = "An identical holiday with the same start and end date already exists.";
                }
            }
        }

        if (error != null) {
            throw new IllegalArgumentException(error);
        } else {

            holiday.setName(name);
            holiday.setStartDate(startDate);
            holiday.setEndDate(endDate);
            holidayRepository.save(holiday);
            storeService.addHoliday(holiday);//add holiday does error checking for store existence


            return holiday;
        }
    }

    /**
     * Gets all the holiday from the repository
     * @return a list of all the holidays available in the repo
     */
    @Transactional
    public List<Holiday> getAllHolidays() {
        List<Holiday> holidays = new ArrayList<>();
        for (Holiday holiday : holidayRepository.findAll()) holidays.add(holiday);
        return holidays;
    }

    /**
     * Get the holiday with the name that we are looking for
     * @param name the holiday name that we are looking for
     * @return the object holiday that corresponds to the given name
     */
    @Transactional
    public Holiday getHoliday(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name can't be empty.");
        }
        if(!holidayRepository.existsById(name)){
            throw new IllegalArgumentException("Holiday doesn't exist.");
        }

        return holidayRepository.findByName(name);
    }

    /**
     * Update the start time of the specific holiday with the right name
     * @param name the holiday name that we want to change
     * @param aStartDate the new start date of that holiday
     * @return the newly updated holiday
     */
    @Transactional
    public Holiday updateHolidayDateStart(String name, Date aStartDate) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name can't be empty.");
        }
        if(!holidayRepository.existsById(name)){
            throw new IllegalArgumentException("Holiday doesn't exist.");
        }
        if (aStartDate == null) throw new IllegalArgumentException("Start Date can't be empty.");
        Holiday holiday = holidayRepository.findByName(name);
        if (holiday.getEndDate().before(aStartDate)) {
            throw new IllegalArgumentException("End Date cannot be before Start Date.");
        }
        holiday.setStartDate(aStartDate);
        return holiday;
    }

    /**
     * Update the end time of the specific holiday with the right name
     * @param name the holiday name that we want to change
     * @param aEndDate the new end date of that holiday
     * @return the newly updated holiday
     */
    @Transactional
    public Holiday updateHolidayDateEnd(String name, Date aEndDate) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name can't be empty.");
        }
        if(!holidayRepository.existsById(name)){
            throw new IllegalArgumentException("Holiday doesn't exist.");
        }
        if (aEndDate == null) throw new IllegalArgumentException("Start Date can't be empty.");
        Holiday holiday = holidayRepository.findByName(name);
        if (!holiday.getStartDate().before(aEndDate)) {
            throw new IllegalArgumentException("End Date cannot be before Start Date.");
        }
        holiday.setEndDate(aEndDate);
        return holiday;
    }

    /**
     * Deletes the Holiday that is associated to the name
     * @param name the name of the holiday we wish to delete
     */
    @Transactional
    public void deleteHoliday(String name) {
        String error = null;
        if (name == null || name.trim().length() == 0) {
            error = "Name can't be empty.";
        } else if (!holidayRepository.existsById(name)) {
            error ="The holiday does not exist.";
        }
        if (error != null) {
            throw new IllegalArgumentException(error);
        }
        holidayRepository.deleteById(name);
    }


}
