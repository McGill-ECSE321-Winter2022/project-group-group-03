package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.HolidayDTO;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import ca.mcgill.ecse321.GroceryStore.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


public class HolidayRestController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping(value = { "/holiday", "/holiday/"})
    public HolidayDTO createHoliday(@RequestParam String name,
                                    @RequestParam Date startDate,
                                    @RequestParam Date endDate) throws IllegalArgumentException{
        Holiday holiday = holidayService.createHoliday(name,  startDate, endDate);
        return HolidayDTO.fromHoliday(holiday);
    }
    @GetMapping(value = { "/holiday/{startDate}/{endDate}", "/holiday/{startDate}/{endDate}/" })
    public List<HolidayDTO> getHolidays(@PathVariable("startDate") Date startDate,
                                        @PathVariable("endDate") Date endDate) throws IllegalArgumentException {
        return holidayService.getHolidays(startDate, endDate).stream()
                .map(holiday -> HolidayDTO.fromHoliday(holiday)).collect(Collectors.toList());
    }

    @DeleteMapping(value = { "/holiday/{name}", "/holiday/{name}/"})
    public void deleteHoliday(@PathVariable("name") String name) throws IllegalArgumentException {
        holidayService.deleteHoliday(name);
    }


}
