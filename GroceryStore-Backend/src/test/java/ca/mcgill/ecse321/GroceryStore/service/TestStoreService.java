package ca.mcgill.ecse321.GroceryStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.mcgill.ecse321.GroceryStore.dao.*;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class TestStoreService {
    private int STORE_ID_KEY = 123;
    private String ADDRESS = "3064 rue edmond rostand";
    private int CURRENT_ACTIVE_DELIVERY = 0;
    private int CURRENT_ACTIVE_PICKUP = 0;

    @Mock
    private StoreRepository storeRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private BusinessHourRepository businessHourRepository;
    @Mock
    private HolidayRepository holidayRepository;
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private StoreService storeService;
    @InjectMocks
    private EmployeeService employeeService;
    @InjectMocks
    private BusinessHourService businessHourService;
    @InjectMocks
    private HolidayService holidayService;
    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(storeRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(STORE_ID_KEY)) {
                Store store = new Store();
                store.setStoreID(STORE_ID_KEY);
                store.setAddress(ADDRESS);
                store.setCurrentActiveDelivery(CURRENT_ACTIVE_DELIVERY);
                store.setCurrentActivePickup(CURRENT_ACTIVE_PICKUP);

                return store;
            } else {
                return null;
            }
        });
        lenient().when(storeRepository.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(STORE_ID_KEY)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(storeRepository.save(any(Store.class))).thenAnswer(returnParameterAsAnswer);
    }
    @Test
    public void testCreateStore(){
        Store store = null;

        try{
            store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        }catch(IllegalArgumentException e){
            fail();
        }
        assertNotNull(store);
    }
    @Test
    public void testNullStoreID(){
        assertEquals(0, storeService.getAllStores().size());

        Integer storeID = null;
        Store store = null;
        String error = null;

        try {
            store = storeService.createStore(storeID, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(store);
        assertEquals("Store ID can't be null.", error);
    }
    @Test
    public void testUpdateNullStoreID() {
        assertEquals(0, storeService.getAllStores().size());
        Store store = null;
        store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        Integer storeID = null;
        String error = null;

        try {
            storeService.setStoreID(STORE_ID_KEY, storeID);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(store.getStoreID());
        assertEquals("StoreID can't be empty.", error);
    }
    @Test
    public void testUpdateStoreIDZero() {
        assertEquals(0, storeService.getAllStores().size());
        Store store = null;
        store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        Integer storeID = 0;
        String error = null;

        try {
            storeService.setStoreID(STORE_ID_KEY, storeID);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(store.getStoreID());
        assertEquals("StoreID must be greater than 0.", error);
    }
    @Test
    public void testUpdateStoreIDNegative() {
        assertEquals(0, storeService.getAllStores().size());
        Store store = null;
        store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        Integer storeID = -10;
        String error = null;

        try {
            storeService.setStoreID(STORE_ID_KEY, storeID);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(store.getStoreID());
        assertEquals("StoreID must be greater than 0.", error);
    }
    @Test
    public void testUpdateStoreIDWrongID() {
        assertEquals(0, storeService.getAllStores().size());
        Integer storeID = 999;
        String error = null;
        Store store = null;
        store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        try {
            storeService.setStoreID(STORE_ID_KEY, 123);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(store.getStoreID(), STORE_ID_KEY);
        // check error
        assertEquals("StoreID doesn't exist.", error);
    }

    @Test
    public void testCreateStoreIDZero() {
        assertEquals(0, storeService.getAllStores().size());
        int storeID = 0;
        String error = null;
        Store store = null;
        try {
            store = storeService.createStore(storeID, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(store);
        // check error
        assertEquals("Store ID must be greater than 0.", error);

    }
    @Test
    public void testCreateStoreIDNegative() {
        assertEquals(0, storeService.getAllStores().size());
        int storeID = -5;
        String error = null;
        Store store = null;
        try {
            store = storeService.createStore(storeID, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(store);
        // check error
        assertEquals("Store ID must be greater than 0.", error);
    }

    @Test
    public void testStoreCurrentActiveDeliveryNull(){
        assertEquals(0, storeService.getAllStores().size());
        Store store = null;
        String error = null;

        try{
            store = storeService.createStore(STORE_ID_KEY, ADDRESS, null, CURRENT_ACTIVE_PICKUP);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(store);
        assertEquals("Current active delivery can't be null.", error);
    }
    @Test
    public void testUpdateNullActiveDelivery() {
        assertEquals(0, storeService.getAllStores().size());
        Store store = null;
        store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        Integer activeDelivery = null;
        String error = null;

        try {
            storeService.setActiveDelivery(CURRENT_ACTIVE_DELIVERY, activeDelivery);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(store.getCurrentActiveDelivery());
        assertEquals("Active delivery can't be empty.", error);
    }


    @Test
    public void testStoreCurrentActiveDeliveryNegative(){
        assertEquals(0, storeService.getAllStores().size());
        Store store = null;
        String error = null;

        try{
            store = storeService.createStore(STORE_ID_KEY, ADDRESS, -10, CURRENT_ACTIVE_PICKUP);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(store);
        assertEquals("Current active delivery can't be negative.", error);
    }

    @Test
    public void testStoreCurrentActivePickupNull(){
        assertEquals(0, storeService.getAllStores().size());
        Store store = null;
        String error = null;

        try{
            store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, null);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(store);
        assertEquals("Current active pickup can't be null.", error);
    }

    @Test
    public void testStoreCurrentActivePickupNegative(){
        assertEquals(0, storeService.getAllStores().size());
        Store store = null;
        String error = null;

        try{
            store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, -10);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(store);
        assertEquals("Current active pickup can't be negative.", error);
    }
    @Test
    public void testGetEmployee(){
        Store store = storeService.getStore(STORE_ID_KEY);
        Employee employee1 = null;
        Employee employee2 = null;
        List<Employee> employeeList = new ArrayList<>();

        try{
            employee1 = employeeService.createEmployee("emp1@gmail.com", "name1", "1234", "add1");
            employee2 = employeeService.createEmployee("emp2@gmail.com", "name2", "12345", "add2");
            store.setEmployee(Arrays.asList(employee1,employee2));
            //test stub
            when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
            Store myS = storeService.getAllStores().get(0);
            employeeList = myS.getEmployee();
        } catch (Exception e){
            fail();
        }
        assertNotNull(employeeList);
        assertEquals(employeeList, Arrays.asList(employee1, employee2));
    }



    @Test
    public void testGetBusinessHours(){
        Store store = storeService.getStore(STORE_ID_KEY);
        String error = null;
        BusinessHour businessHour = null;
        List<BusinessHour> businessHours = new ArrayList<>();
        try{
            Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 35));
            Time endTime = java.sql.Time.valueOf(LocalTime.of(18, 55));
            BusinessHour.DayOfWeek day = BusinessHour.DayOfWeek.Monday;
            businessHour = businessHourService.createBusinessHour(startTime, endTime, day.name());
            store.setBusinessHour(Arrays.asList(businessHour));
            //test stub
            when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
            Store myS = storeService.getAllStores().get(0);
            businessHours = myS.getBusinessHour();
        }catch (Exception e){
            fail();
        }
        assertNotNull(businessHours);
        assertEquals(businessHours, Arrays.asList(businessHour));
    }
    @Test
    public void testGetItem(){
        Store store = storeService.getStore(STORE_ID_KEY);
        Item item1 = null;
        Item item2 = null;
        List<Item> itemList = new ArrayList<>();

        try{
            item1 = itemService.createItem("apple1", true, 10, "juicy", 2);
            item2 = itemService.createItem("apple2", true, 12, "crunchy", 3);
            store.setItem(Arrays.asList(item1,item2));
            //test stub
            when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
            Store myS = storeService.getAllStores().get(0);
            itemList = myS.getItem();
        } catch (Exception e){
            fail();
        }
        assertNotNull(itemList);
        assertEquals(itemList, Arrays.asList(item1, item2));
    }
    @Test
    public void testGetHoliday(){
        Store store = storeService.getStore(STORE_ID_KEY);
        String error = null;
        Holiday holiday = null;
        List<Holiday> holidays = new ArrayList<>();
        try{
            String name = "TestHoliday";
            LocalDate LSDATE = LocalDate.of(2012, Month.JANUARY, 1);
            LocalDate LEDATE = LocalDate.of(2015, Month.AUGUST, 31);
            Date START_DATE = Date.valueOf(LSDATE);
            Date END_DATE = Date.valueOf(LEDATE);
            holiday = holidayService.createHoliday(name, START_DATE, END_DATE);
            store.setHoliday(Arrays.asList(holiday));
            //test stub
            when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
            Store myS = storeService.getAllStores().get(0);
            holidays = myS.getHoliday();
        }catch (Exception e){
            fail();
        }
        assertNotNull(holidays);
        assertEquals(holidays, Arrays.asList(holiday));
    }

}

