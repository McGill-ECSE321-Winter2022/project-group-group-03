package ca.mcgill.ecse321.GroceryStore.controller;
import ca.mcgill.ecse321.GroceryStore.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class GroceryStoreRestController {

    @Autowired
    private HolidayService holidayService;


}