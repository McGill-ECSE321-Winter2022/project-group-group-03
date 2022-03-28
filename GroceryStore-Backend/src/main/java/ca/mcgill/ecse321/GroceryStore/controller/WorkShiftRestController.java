
package ca.mcgill.ecse321.GroceryStore.controller;



import ca.mcgill.ecse321.GroceryStore.dto.WorkShiftDTO;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import ca.mcgill.ecse321.GroceryStore.service.WorkShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class WorkShiftRestController {

    @Autowired
    private WorkShiftService workShiftService;

    @PostMapping(value = {"/workShift", "/workShift/"})
    public WorkShiftDTO createWorkShift(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime aStartTime,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime aEndTime,
                                      @RequestParam String aDay, @RequestParam String username) throws IllegalArgumentException {
        WorkShift workShift = workShiftService.createWorkShift(Time.valueOf(aStartTime), Time.valueOf(aEndTime), aDay,username );
        return convertToDto(workShift);
    }




    @GetMapping(value = {"/workShift", "/workShift/"})
    public List<WorkShiftDTO> getWorkShifts() throws IllegalArgumentException {
        List<WorkShiftDTO> workShiftDTOS = new ArrayList<>();
        for (WorkShift workShift : workShiftService.getAllWorkShift()) workShiftDTOS.add(convertToDto(workShift));
        return workShiftDTOS;
    }
    @GetMapping(value = {"/workShift/{id}", "/workShift/{id}/"})
    public WorkShiftDTO getWorkShift(@PathVariable("id") int id) throws IllegalArgumentException {

        WorkShift workShift = workShiftService.getWorkShift(id);

        return convertToDto(workShift);
    }
    @PutMapping(value = { "/edit_workShift_startTime/{id}","/edit_holiday_startDate/{id}/"})
    public WorkShiftDTO updateWorkShiftStartTime(@PathVariable("id") int id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime)
            throws IllegalArgumentException {
        WorkShift workShift = workShiftService.updateWorkshiftTimeStart(id, Time.valueOf(startTime));
        return convertToDto(workShift);
    }
    @PutMapping(value = { "/edit_workShift_endTime/{id}","/edit_holiday_endDate/{id}/"})
    public WorkShiftDTO updateWorkShiftEndTime(@PathVariable("id") int id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime)
            throws IllegalArgumentException {
        WorkShift workShift = workShiftService.updateWorkShiftTimeEnd(id, Time.valueOf(endTime));
        return convertToDto(workShift);
    }
    @PutMapping(value = { "/edit_workShift_day/{id}","/edit_holiday_endDate/{id}/"})
    public WorkShiftDTO updateWorkShiftDay(@PathVariable("id") int id, String day)
            throws IllegalArgumentException {
        WorkShift workShift = workShiftService.updateWorkShiftDay(id, day);
        return convertToDto(workShift);
    }

    @DeleteMapping(value = {"/workShift/{id}", "/workShift/{id}/"})
    public void deleteWorkShift(@PathVariable("id") int id) throws IllegalArgumentException {
        workShiftService.deleteWorkShift(id);
    }

    private WorkShiftDTO convertToDto(WorkShift w) {
        if (w == null) {
            throw new IllegalArgumentException("There is no such Work Shift!");
        }
        return new WorkShiftDTO(w.getStartTime(), w.getEndTime(), w.getShiftID(),w.getDay().name());
    }

}

