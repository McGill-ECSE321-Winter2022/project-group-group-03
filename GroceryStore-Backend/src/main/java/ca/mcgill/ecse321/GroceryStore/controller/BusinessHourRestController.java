package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.BusinessHourDTO;
import ca.mcgill.ecse321.GroceryStore.model.BusinessHour;
import ca.mcgill.ecse321.GroceryStore.service.BusinessHourService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class BusinessHourRestController {

    @Autowired
    private BusinessHourService service;

    @PostMapping(value = { "/businessHour", "/businessHour/" })
    public BusinessHourDTO createBusinessHour(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime startTime, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime endTime,
                                              @RequestParam String day) throws IllegalArgumentException {
        return convertToDto(service.createBusinessHour(Time.valueOf(startTime),Time.valueOf(endTime),day));
    }

    @GetMapping(value = {"/businessHour","/businessHour/"})
    public List<BusinessHourDTO> getBusinessHours() throws IllegalArgumentException {
        return service.getAllBusinessHours().stream().map(this::convertToDto).collect(Collectors.toList());

    }

    @GetMapping(value = {"/businessHour/{hoursID}", "/businessHour/{hoursID}/"})
    public BusinessHourDTO getBusinessHour(@PathVariable("hoursID") int hoursID) throws IllegalArgumentException {
        return convertToDto(service.getBusinessHour(hoursID));
    }

    @DeleteMapping(value = {"/businessHour/{hoursID}", "/businessHour/{hoursID}/"})
    public void deleteBusinessHour(@PathVariable("hoursID") int hoursID) throws IllegalArgumentException {
        service.deleteBusinessHour(hoursID);
    }

    @PutMapping(value = {"/editBusinessHourStartTime/{hoursID}"})
    public BusinessHourDTO updateBusinessHourStartTime(@PathVariable("hoursID") int hoursID,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime startTime) throws IllegalArgumentException {
        return convertToDto(service.updateBusinessHourStartTime(hoursID,Time.valueOf(startTime)));
    }

    @PutMapping(value = {"/editBusinessHourEndTime/{hoursID}"})
    public BusinessHourDTO updateBusinessHourEndTime(@PathVariable("hoursID") int hoursID,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime endTime) throws IllegalArgumentException {
        return convertToDto(service.updateBusinessHourEndTime(hoursID,Time.valueOf(endTime)));
    }

    @PutMapping(value = {"/editBusinessHour/{hoursID}"})
    public BusinessHourDTO updateBusinessHour(@PathVariable("hoursID") int hoursID,
                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime startTime,
                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss") LocalTime endTime) throws IllegalArgumentException {
        return convertToDto(service.updateBusinessHour(hoursID,Time.valueOf(startTime),Time.valueOf(endTime)));
    }

    private BusinessHourDTO convertToDto(BusinessHour aBusinessHour) {
        if (aBusinessHour == null) throw new IllegalArgumentException("There is no such BusinessHour!");
        return new BusinessHourDTO(aBusinessHour.getHoursID(),aBusinessHour.getStartTime(),aBusinessHour.getEndTime(),aBusinessHour.getDay().name());
    }

}
