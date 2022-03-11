package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.HolidayDTO;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import ca.mcgill.ecse321.GroceryStore.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class HolidayRestController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping(value = {"/holiday", "/holiday/"})
    public HolidayDTO createHoliday(@RequestParam String name,
                                    @RequestParam Date startDate,
                                    @RequestParam Date endDate) throws IllegalArgumentException {
        Holiday holiday = holidayService.createHoliday(name, startDate, endDate);
        return HolidayDTO.fromHoliday(holiday);
    }

    @GetMapping(value = {"/holiday/", "/holiday/"})
    public List<HolidayDTO> getHolidays() {
        List<HolidayDTO> holidayDTOS = new ArrayList<>();
        for (Holiday holiday : holidayService.getAllHolidays()) holidayDTOS.add(HolidayDTO.fromHoliday(holiday));

        return holidayDTOS;
    }

    @DeleteMapping(value = {"/holiday/{name}", "/holiday/{name}/"})
    public void deleteHoliday(@PathVariable("name") String name) throws IllegalArgumentException {
        holidayService.deleteHoliday(name);
    }


}
