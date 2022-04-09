package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.HolidayDTO;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import ca.mcgill.ecse321.GroceryStore.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
public class HolidayRestController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping(value = {"/holiday", "/holiday/"})

    public ResponseEntity<?> createHoliday(@RequestParam String name,
                                        @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate,
                                        @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(holidayService.createHoliday(name, Date.valueOf(startDate), Date.valueOf(endDate))));
        }
        catch(IllegalArgumentException error){
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping(value = {"/holiday", "/holiday/"})
    public List<HolidayDTO> getHolidays() throws IllegalArgumentException {
       return holidayService.getAllHolidays().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value = {"/holiday/{name}", "/holiday/{name}/"})
    public ResponseEntity<?> getHoliday(@PathVariable("name") String name) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(holidayService.getHoliday(name)));
        }catch(IllegalArgumentException error){
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
    @PutMapping(value = { "/edit_holiday_startDate/{name}", "/edit_holiday_startDate/{name}/"})
    public ResponseEntity<?> updateHolidayStartDate(@PathVariable("name") String name, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate )
            throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(holidayService.updateHolidayDateStart(name, Date.valueOf(startDate))));
        }catch(IllegalArgumentException error){
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
    @PutMapping(value = { "/edit_holiday_endDate/{name}","/edit_holiday_endDate/{name}/"})
    public ResponseEntity<?> updateHolidayEndDate(@PathVariable("name") String name, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate)
            throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(holidayService.updateHolidayDateEnd(name, Date.valueOf(endDate))));
        } catch(IllegalArgumentException error){
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @DeleteMapping(value = {"/holiday/{name}", "/holiday/{name}/"})
    public void deleteHoliday(@PathVariable("name") String name) throws IllegalArgumentException {
        holidayService.deleteHoliday(name);
    }
    private HolidayDTO convertToDto(Holiday h) {
        if (h == null) {
            throw new IllegalArgumentException("There is no such Holiday!");
        }
        return new HolidayDTO(h.getName(), h.getStartDate(),h.getEndDate());
    }
}