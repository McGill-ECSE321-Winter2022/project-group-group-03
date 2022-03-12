package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.BusinessHourDTO;
import ca.mcgill.ecse321.GroceryStore.model.BusinessHour;
import ca.mcgill.ecse321.GroceryStore.service.BusinessHourService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class BusinessHourRestController {

    @Autowired
    private BusinessHourService service;

    @PostMapping(value = { "/businessHour{hoursID}", "/businessHour{hoursID}/" })
    public BusinessHourDTO createBusinessHour(@RequestParam Time startTime,@RequestParam Time endTime,
                                              @RequestParam String day) throws IllegalArgumentException {
        return convertToDto(service.createBusinessHour(startTime,endTime,day));
    }

    @GetMapping(value = {"/businessHour","/businessHour/"})
    public List<BusinessHourDTO> getBusinessHours() throws IllegalArgumentException {
        return service.getAllBusinessHours().stream().map(this::convertToDto).collect(Collectors.toList());

    }

    @GetMapping(value = {"/businessHours/{hoursID}", "/businessHours/{hoursID}/"})
    public BusinessHourDTO getBusinessHour(@PathVariable("hoursID") int hoursID) throws IllegalArgumentException {
        return convertToDto(service.getBusinessHour(hoursID));
    }

    @DeleteMapping(value = {"/businessHours/{hoursID}", "/businessHours/{hoursID}/"})
    public void deleteBusinessHour(@PathVariable("hoursID") int hoursID) throws IllegalArgumentException {
        service.deleteBusinessHour(hoursID);
    }

    private BusinessHourDTO convertToDto(BusinessHour aBusinessHour) {
        if (aBusinessHour == null) throw new IllegalArgumentException("There is no such BusinessHour!");
        return new BusinessHourDTO(aBusinessHour.getHoursID(),aBusinessHour.getStartTime(),aBusinessHour.getEndTime(),aBusinessHour.getDay().name());
    }

}
