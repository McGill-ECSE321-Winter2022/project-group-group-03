package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.HolidayRepository;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayService {
    @Autowired
    HolidayRepository holidayRepository;

    @Transactional
    public Holiday createHoliday(String name, Date startDate, Date endDate) {
        Holiday holiday = new Holiday();
        List<Holiday> holidays = this.getAllHolidays();
        String error = null;
        if (name == null || name.trim().length() == 0) {
            error = "Name can't be empty.";
        } else if (startDate == null) {
            error = "Start Date can't be empty.";
        } else if (endDate == null) {
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

            return holiday;
        }
    }

    @Transactional
    public List<Holiday> getAllHolidays() {
        List<Holiday> holidays = new ArrayList<>();
        for (Holiday holiday : holidayRepository.findAll()) holidays.add(holiday);
        return holidays;
    }

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
