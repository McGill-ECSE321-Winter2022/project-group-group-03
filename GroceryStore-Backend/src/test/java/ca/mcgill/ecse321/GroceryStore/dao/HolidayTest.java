package ca.mcgill.ecse321.GroceryStore.dao;


import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HolidayTest {

    @Autowired
    private HolidayRepository holidayRepository;


    @AfterEach
    public void clearDatabase() {

        // First, we clear registrations to avoid exceptions due to inconsistencies
        holidayRepository.deleteAll();

    }

    /**
     * Tests read and write capabilities with Holiday objects and attributes.
     * There is no association to test since we only have one unidirectional association coming from store.
     */
    @Test
    @Transactional
    public void testPersistAndLoadHoliday(){
        String name = "Spring grace week";
        Date startDate = java.sql.Date.valueOf(LocalDate.of(2022, Month.MARCH, 31));
        Date endDate = java.sql.Date.valueOf(LocalDate.of(2022, Month.APRIL, 10));

        Holiday holiday = new Holiday();
        holiday.setName(name);
        holiday.setStartDate(startDate);
        holiday.setEndDate(endDate);

        holidayRepository.save(holiday);

        holiday = holidayRepository.findByName(name);
        assertNotNull(holiday);
        assertEquals(name, holiday.getName());

    }

}
