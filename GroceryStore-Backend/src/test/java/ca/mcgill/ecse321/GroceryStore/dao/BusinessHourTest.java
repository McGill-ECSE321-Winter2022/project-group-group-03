package ca.mcgill.ecse321.GroceryStore.dao;


import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest


public class BusinessHourTest {

    @Autowired
    private BusinessHourRepository businessHourRepository;



    @AfterEach
    public void clearDatabase() {

        // First, we clear registrations to avoid exceptions due to inconsistencies
        businessHourRepository.deleteAll();

    }
    /**
     * Tests read and write capabilities with BusinessHour objects and attributes.
     * There is no association to test since we only have one unidirectional association coming from store.
     */
    @Test
    @Transactional
    public void testPersistAndLoadBusinessHour(){
        int hoursID = 123;
        Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(18, 55));
        BusinessHour.DayOfWeek day = BusinessHour.DayOfWeek.Monday;

        BusinessHour businessHour = new BusinessHour();
        businessHour.setHoursID(hoursID);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHour.setDay(day);

        businessHourRepository.save(businessHour);

        businessHour = businessHourRepository.findByHoursID(hoursID);
        assertNotNull(businessHour);
        assertEquals(hoursID, businessHour.getHoursID());

    }
}
