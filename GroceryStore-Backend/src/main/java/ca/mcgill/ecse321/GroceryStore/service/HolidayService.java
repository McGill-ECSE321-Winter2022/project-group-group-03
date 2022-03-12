package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.HolidayRepository;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class HolidayService {
    @Autowired
    HolidayRepository holidayRepository;

    @Transactional
    public Holiday createHoliday (String name,Date startDate, Date endDate){
        Holiday holiday = new Holiday();
        holiday.setName(name);
        holiday.setStartDate(startDate);
        holiday.setEndDate(endDate);
        holidayRepository.save(holiday);

        return holiday;
    }
    @Transactional
    public List<Holiday> getAllHolidays(){
        List<Holiday> holidays = new ArrayList<>();
        for (Holiday holiday:holidayRepository.findAll() ) {
            holidays.add(holiday);
        }

        return holidays;
    }

    @Transactional
    public Holiday getHoliday(String name){
        Holiday holiday = holidayRepository.findByName(name);
        return holiday;
    }

    @Transactional
    public void deleteHoliday(String name){
        Holiday holiday = holidayRepository.findByName(name);
        if (holiday == null) {
            throw new IllegalArgumentException("The holiday with name: " + name + " does not exist.");
        } else {
            holidayRepository.deleteById(name);
        }
    }




}
