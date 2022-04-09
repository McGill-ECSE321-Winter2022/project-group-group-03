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

    //TODO: uncomment when changing create function to add to employee
    @Autowired
    EmployeeService employeeService;

    private static int curID = 50000;

    /**
     * Deletes the work shift that is associated to the shift ID
     * @param shiftID the ID of the work shift we wish to delete
     */
    @Transactional
    public void deleteWorkShift(int shiftID) {
        if (!workShiftRepository.existsById(shiftID)) {
            throw new IllegalArgumentException("The work shift does not exist.");
        }
        workShiftRepository.deleteById(shiftID);
    }

    /**
     * Get the work shift with the shift ID that we are looking for
     * @param shiftID the shift ID of the work shift that we are looking for
     * @return the work shift that corresponds to the given shift ID
     */
    @Transactional
    public WorkShift getWorkShift(int shiftID) {
        if(!workShiftRepository.existsById(shiftID)){
            throw new IllegalArgumentException("Work shift doesn't exist.");
        }
        return workShiftRepository.findByShiftID(shiftID);
    }

    /**
     * Update the day of the specific work shift with the right shift ID
     * @param shiftID the ID of the work shift that we want to change
     * @param newDay the new day for that work shift
     * @return the newly updated work shift
     */
    @Transactional
    public WorkShift updateWorkShiftDay(int shiftID, String newDay) {
        if(!workShiftRepository.existsById(shiftID)){
            throw new IllegalArgumentException("Work shift doesn't exist.");
        }
        WorkShift workShift = workShiftRepository.findByShiftID(shiftID);
        switch (newDay) {
            case "Monday" -> workShift.setDay(WorkShift.DayOfWeek.Monday);
            case "Tuesday" -> workShift.setDay(WorkShift.DayOfWeek.Tuesday);
            case "Wednesday" -> workShift.setDay(WorkShift.DayOfWeek.Wednesday);
            case "Thursday" -> workShift.setDay(WorkShift.DayOfWeek.Thursday);
            case "Friday" -> workShift.setDay(WorkShift.DayOfWeek.Friday);
            case "Saturday" -> workShift.setDay(WorkShift.DayOfWeek.Saturday);
            case "Sunday" -> workShift.setDay(WorkShift.DayOfWeek.Sunday);
            default -> throw new IllegalArgumentException("Please enter a valid day of the week.");
        }
        return workShift;
    }

    /**
     * Update the start time of the specific work shift with the right shift ID
     * @param shiftID the ID of the work shift that we want to change
     * @param aStartTime the new start time for that work shift
     * @return the newly updated work shift
     */
    @Transactional
    public WorkShift updateWorkshiftTimeStart(int shiftID,  Time aStartTime) {
        if(!workShiftRepository.existsById(shiftID)){
            throw new IllegalArgumentException("Work shift doesn't exist.");
        }
        if (aStartTime == null) throw new IllegalArgumentException("Start Time can't be empty.");
        WorkShift workShift = workShiftRepository.findByShiftID(shiftID);

        workShift.setStartTime(aStartTime);
        return workShift;
    }

    /**
     * Update the start time and end time of the specific work shift with the right shift ID
     * @param shiftID the ID of the work shift that we want to change
     * @param aStartTime the new start time for that work shift
     * @param aEndTime the new end time for that work shift
     * @return the newly updated work shift
     */
    @Transactional
    public WorkShift updateWorkshiftHours(int shiftID, Time aStartTime, Time aEndTime){
        if(!workShiftRepository.existsById(shiftID)){
            throw new IllegalArgumentException("Work shift doesn't exist.");
        }
        if (aStartTime == null) throw new IllegalArgumentException("Start Time can't be empty.");
        if (aEndTime == null) throw new IllegalArgumentException("End Time can't be empty.");
        WorkShift workShift = workShiftRepository.findByShiftID(shiftID);
        workShift.setStartTime(aStartTime);
        workShift.setEndTime(aEndTime);
        return workShift;
    }

    /**
     * Update the end time of the specific work shift with the right shift ID
     * @param shiftID the ID of the work shift that we want to change
     * @param aEndTime the new end time for that work shift
     * @return the newly updated work shift
     */
    @Transactional
    public WorkShift updateWorkShiftTimeEnd(int shiftID,  Time aEndTime) {
        if(!workShiftRepository.existsById(shiftID)){
            throw new IllegalArgumentException("Work shift doesn't exist.");
        }
        if (aEndTime == null) throw new IllegalArgumentException("End Time can't be empty.");
        WorkShift workShift = workShiftRepository.findByShiftID(shiftID);

        workShift.setEndTime(aEndTime);
        return workShift;
    }

    /**
     * Gets all the work shifts from the repository
     * @return a list of all the work shifts available in the repo
     */
    @Transactional
    public List<WorkShift> getAllWorkShift() {
        return toList(workShiftRepository.findAll());
    }


    /**
     * Creates a work shift with all the credentials of said work shift
     * @param aStartTime the start time of the work shift
     * @param aEndTime the end time of the work shift
     * @param aDayOfWeek the day of week of the work shift
     * @param username associated with the work shift
     * @return the work shift object that was just created
     */
    @Transactional
    public WorkShift createWorkShift(Time aStartTime, Time aEndTime, String aDayOfWeek,String username) {
        WorkShift workShift = new WorkShift();

        if (aStartTime == null) throw new IllegalArgumentException("Start Time can't be empty.");

        if (aEndTime == null) throw new IllegalArgumentException("End Time can't be empty.");

        if (aDayOfWeek ==null || aDayOfWeek.trim().length() == 0) throw new IllegalArgumentException("Day can't be empty.");

        for (WorkShift w : this.getAllWorkShift()) {
            if (w.getStartTime() == aStartTime && w.getEndTime() == aEndTime && w.getDay().name().equals(aDayOfWeek)) {
                throw new IllegalArgumentException("An identical Work shift already exists.");
            }
        }

        switch (aDayOfWeek) {
            case "Monday" -> workShift.setDay(WorkShift.DayOfWeek.Monday);
            case "Tuesday" -> workShift.setDay(WorkShift.DayOfWeek.Tuesday);
            case "Wednesday" -> workShift.setDay(WorkShift.DayOfWeek.Wednesday);
            case "Thursday" -> workShift.setDay(WorkShift.DayOfWeek.Thursday);
            case "Friday" -> workShift.setDay(WorkShift.DayOfWeek.Friday);
            case "Saturday" -> workShift.setDay(WorkShift.DayOfWeek.Saturday);
            case "Sunday" -> workShift.setDay(WorkShift.DayOfWeek.Sunday);
            default -> throw new IllegalArgumentException("Please enter a valid day of the week.");
        }
        while(workShiftRepository.existsById(curID)){
            curID++;
        }
        workShift.setStartTime(aStartTime);
        workShift.setEndTime(aEndTime);
        workShift.setShiftID(curID++);

        //TODO: add workshift to employee
        employeeService.addWorkShift(username, workShift);
        workShiftRepository.save(workShift);
        System.out.println(workShift);
        return workShift;
    }

        private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }



}
