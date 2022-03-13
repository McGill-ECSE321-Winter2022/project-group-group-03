package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.WorkShiftRepository;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkShiftService {
    @Autowired
    WorkShiftRepository workShiftRepository;

    private static int curID = 50000;

    @Transactional
    public void deleteWorkShift(int shiftID) {
        WorkShift workShift= workShiftRepository.findByShiftID(shiftID);
        if (workShift == null ) {
            throw new IllegalArgumentException("The work shift with ID: " + shiftID + " does not exist.");
        } else {
            workShiftRepository.deleteById(shiftID);
        }

    }

    @Transactional
    public WorkShift getWorkShift(int shiftID) {
        return workShiftRepository.findByShiftID(shiftID);
    }

    @Transactional
    public List<WorkShift> getAllWorkShift() {
        return toList(workShiftRepository.findAll());
    }

    @Transactional
    public WorkShift createWorkShift(Time aStartTime, Time aEndTime, String aDayOfWeek) {
        WorkShift workShift = new WorkShift();

        workShift.setStartTime(aStartTime);
        workShift.setEndTime(aEndTime);

        switch (aDayOfWeek) {
            case "Monday" -> workShift.setDay(WorkShift.DayOfWeek.Monday);
            case "Tuesday" -> workShift.setDay(WorkShift.DayOfWeek.Tuesday);
            case "Wednesday" -> workShift.setDay(WorkShift.DayOfWeek.Wednesday);
            case "Thursday" -> workShift.setDay(WorkShift.DayOfWeek.Thursday);
            case "Friday" -> workShift.setDay(WorkShift.DayOfWeek.Friday);
            case "Saturday" -> workShift.setDay(WorkShift.DayOfWeek.Saturday);
            case "Sunday" -> workShift.setDay(WorkShift.DayOfWeek.Sunday);
        }
        workShift.setShiftID(curID++);

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
