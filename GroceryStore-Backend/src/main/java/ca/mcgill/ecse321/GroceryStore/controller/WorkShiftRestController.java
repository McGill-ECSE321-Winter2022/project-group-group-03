
package ca.mcgill.ecse321.GroceryStore.controller;


import ca.mcgill.ecse321.GroceryStore.dto.WorkShiftDTO;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import ca.mcgill.ecse321.GroceryStore.service.WorkShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class WorkShiftRestController {

    @Autowired
    private WorkShiftService workShiftService;

    @PostMapping(value = {"/workShift", "/workShift/"})
    public WorkShiftDTO createWorkShift(@RequestParam Time aStartTime,
                                      @RequestParam Time aEndTime,
                                      @RequestParam String aDay) throws IllegalArgumentException {
        WorkShift workShift = workShiftService.createWorkShift(aStartTime, aEndTime, aDay);
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

