package ca.mcgill.ecse321.GroceryStore.dao;

import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StoreTest {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private HolidayRepository holidayRepository;




    //Holiday -> Many holidays therefore we use a list
    Holiday defaultHoliday = new Holiday();
    List<Holiday> holidayList = new ArrayList<Holiday>();

    //Assign attributes to the temporary Holiday object
    public void initializeDefaultHoliday(){
        this.defaultHoliday.setName("Day of the Ari");
        this.defaultHoliday.setStartDate(java.sql.Date.valueOf(LocalDate.of(2022, Month.MAY, 31)));
        this.defaultHoliday.setEndDate(java.sql.Date.valueOf(LocalDate.of(2022, Month.JULY, 10)));
    }

    public void delete1Holiday(Holiday holiday2Delete){
        holidayList.remove(holiday2Delete);
        holidayRepository.deleteById(holiday2Delete.getName());
    }

    @AfterEach
    public void clearDatabase() {

        // First, we clear registrations to avoid exceptions due to inconsistencies
        storeRepository.deleteAll();
        holidayRepository.deleteAll();


    }

    /**
     * Tests read and write capabilities with Store objects and attributes.
     * Association is tested between Store and Holiday by creating a temporary Holiday object initialized with some attributes,
     * then deleting it, and checking to see from the Store class that the holiday instance is deleted.
     */
    @Test
    @Transactional
    public void testPersistAndLoadStore() {
        int storeID = 1;
        String address = "215 avenue Kenaston";
        int currentActivePickup = 5;
        int currentActiveDelivery = 6;
        // First example for object save/load
        Store store2Eval = new Store();
        store2Eval.setStoreID(storeID);                             //set attributes
        store2Eval.setAddress(address);
        store2Eval.setCurrentActiveDelivery(currentActiveDelivery);
        store2Eval.setCurrentActivePickup(currentActivePickup);

        initializeDefaultHoliday();
        holidayList.add(defaultHoliday);
        store2Eval.setHoliday(holidayList);

        holidayRepository.saveAll(holidayList);
        storeRepository.save(store2Eval);
        store2Eval = storeRepository.findById(storeID);

        assertNotNull(store2Eval);
        assertEquals(storeID, store2Eval.getStoreID());

        delete1Holiday(defaultHoliday);
        assertNull(holidayRepository.findByName(defaultHoliday.getName()));
        assertFalse(storeRepository.findById(storeID).getHoliday().contains(defaultHoliday));

    }
}
