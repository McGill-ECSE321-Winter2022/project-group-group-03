package ca.mcgill.ecse321.GroceryStore.controller;


import ca.mcgill.ecse321.GroceryStore.dto.WorkShiftDTO;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import ca.mcgill.ecse321.GroceryStore.service.WorkShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Time;
import java.util.ArrayList;


public class WorkshiftRestController {
    @Autowired
    private WorkShiftService workShiftService;

    @PostMapping(value = {"/workShift", "/workShift/"})
    public WorkShiftDTO createHoliday(@RequestParam Time aStartTime,
                                      @RequestParam Time aEndTime,
                                      @RequestParam WorkShift.DayOfWeek aDay) throws IllegalArgumentException {
        WorkShift workShift = workShiftService.createWorkShift(aStartTime, aEndTime, aDay);
        return convertToDto(workShift);
    }

//    @GetMapping(value = {"/workShift", "/workShift/"})
//    public List<HolidayDTO> getHolidays() throws IllegalArgumentException {
//        List<HolidayDTO> holidayDTOS = new ArrayList<>();
//        for (Holiday holiday : holidayService.getAllHolidays()) holidayDTOS.add(convertToDto(holiday));
//        return holidayDTOS;
//    }
//    @GetMapping(value = {"/workShift/{name}", "/workShift/{name}/"})
//    public HolidayDTO getHoliday(@RequestParam String name) throws IllegalArgumentException {
//
//        Holiday holiday = holidayService.getHoliday(name);
//
//        return convertToDto(holiday);
//    }
//
//    @DeleteMapping(value = {"/workShift/{id}", "/workShift/{id}/"})
//    public void deleteHoliday(@PathVariable("name") String name) throws IllegalArgumentException {
//        holidayService.deleteHoliday(name);
//    }
    private WorkShiftDTO convertToDto(WorkShift w) {
        if (w == null) {
            throw new IllegalArgumentException("There is no such Work Shift!");
        }
        return new WorkShiftDTO(w.getStartTime(), w.getEndTime(), w.getShiftID(), w.getDay());
    }

}
