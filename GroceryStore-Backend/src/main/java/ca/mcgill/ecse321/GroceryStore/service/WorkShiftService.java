package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.WorkShiftRepository;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class WorkShiftService {
    @Autowired
    WorkShiftRepository workShiftRepository;

    @Transactional
    public void deleteWorkShift(int shiftID) {
        workShiftRepository.deleteById(shiftID);
    }

    @Transactional
    public WorkShift getWorkShift(int shiftID) {
        return workShiftRepository.findByShiftID(shiftID);
    }

    @Transactional
    public WorkShift getAllWorkShift() {
        return (WorkShift) toList(workShiftRepository.findAll());
    }

    @Transactional
    public WorkShift createWorkShift(Time aStartTime, Time aEndTime, WorkShift.DayOfWeek aDay, int aShiftID) {
        WorkShift workShift = new WorkShift();

        workShift.setStartTime(aStartTime);
        workShift.setEndTime(aEndTime);
        workShift.setDay(aDay);
        workShift.setShiftID(aShiftID);

        workShiftRepository.save(workShift);
        return workShift;
    }

        private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }



}
