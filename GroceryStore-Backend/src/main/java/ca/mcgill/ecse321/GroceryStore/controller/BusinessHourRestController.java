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

@CrossOrigin(origins = "*")
@RestController
public class BusinessHourRestController {

    @Autowired
    private BusinessHourService service;

    @GetMapping(value = { "/business_hour/{hoursID}", "/business_hour/{hoursID}/" })
    public BusinessHourDTO createBusinessHour(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time startTime,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time endTime, @RequestParam String day) throws IllegalArgumentException{

        BusinessHour businessHour = service.createBusinessHour(startTime,endTime,day);
    }

}
