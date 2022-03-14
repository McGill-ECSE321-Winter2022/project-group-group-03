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

    @Transactional
    public Holiday createHoliday (String name,Date startDate, Date endDate){
        Holiday holiday = new Holiday();
        List <Holiday>  holidays = this.getAllHolidays();
        String error = null;
        if (name == null|| name.trim().length() == 0) {
            error="Name can't be empty.";
        } else if (startDate == null) {
            error = "Start Date can't be empty.";
        } else if (endDate == null) {
            error = "End Date can't be empty.";
        } else if(startDate.compareTo(endDate) > 0) {
            error = "Start Date can't be after End Date.";
        } else if(holidays != null && holidays.size() != 0) {
            for(Holiday h : holidays) {
                if(h.getName().equals(name)) {
                    error = "An identical holiday with the same name already exists.";
                    break;
                }else if(h.getStartDate().compareTo(startDate)==0 && h.getEndDate().compareTo(endDate)==0){
                    error = "An identical holiday with the same start and end date already exists.";
                }
            }
        }

        if(error != null){
            throw new IllegalArgumentException(error);
        }

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
        String error = null;
        if(name == null|| name.trim().length() == 0){
            error="Name can't be empty.";
        } else if(!holidayRepository.existsById(name)){
            error="Holiday doesn't exist.";
        }

        if(error != null){
            throw new IllegalArgumentException(error);
        }
        return holidayRepository.findByName(name);

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
