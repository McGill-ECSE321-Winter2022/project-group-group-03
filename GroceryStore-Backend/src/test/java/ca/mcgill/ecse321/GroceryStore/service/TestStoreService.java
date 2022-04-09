package ca.mcgill.ecse321.GroceryStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
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
    @Mock
    private CustomerRepository customerRepository;

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
        lenient().when(storeRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            ArrayList<Store> storeArrayList = new ArrayList<>();
            return storeArrayList;
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
            store = storeService.createStore(ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        }catch(IllegalArgumentException e){
            fail();
        }
        assertNotNull(store);
    }


    @Test
    public void testStoreCurrentActiveDeliveryNull(){
        Store store = null;
        String error = null;

        try{
            store = storeService.createStore(ADDRESS, null, CURRENT_ACTIVE_PICKUP);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(store);
        assertEquals("Active delivery can't be null.", error);
    }
    @Test
    public void testUpdateNullActiveDelivery() {
        Store store = null;
        store = storeService.createStore(ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        Integer activeDelivery = null;
        String error = null;

        try {
            storeService.setActiveDelivery(activeDelivery);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(store.getCurrentActiveDelivery());
        assertEquals("Active delivery can't be empty.", error);
    }



    @Test
    public void testStoreCurrentActiveDeliveryNegative(){
        Store store = null;
        String error = null;

        try{
            store = storeService.createStore(ADDRESS, -10, CURRENT_ACTIVE_PICKUP);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(store);
        assertEquals("Active delivery can't be negative.", error);
    }
    @Test
    public void testUpdateNegativeActiveDelivery() {
        Store store = null;
        store = storeService.createStore(ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        Integer activeDelivery = -20;
        String error = null;

        try {
            storeService.setActiveDelivery(activeDelivery);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(store.getCurrentActiveDelivery());
        assertEquals("Active delivery can't be negative.", error);
    }

    @Test
    public void testStoreCurrentActivePickupNull(){
        Store store = null;
        String error = null;

        try{
            store = storeService.createStore(ADDRESS, CURRENT_ACTIVE_DELIVERY, null);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(store);
        assertEquals("Active pickup can't be null.", error);
    }

    @Test
    public void testStoreCurrentActivePickupNegative(){
        Store store = null;
        String error = null;

        try{
            store = storeService.createStore(ADDRESS, CURRENT_ACTIVE_DELIVERY, -10);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(store);
        assertEquals("Active pickup can't be negative.", error);
    }

    @Test
    public void testNullAddressStore(){
        String address = null;
        Store store = null;
        String error = null;

        try {
            store = storeService.createStore(address, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(store);
        // check error
        assertEquals("Address can't be empty.", error);
    }
    @Test
    public void testEmptyAddressStore(){
        String address = "";
        Store store = null;
        String error = null;

        try {
            store = storeService.createStore(address, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(store);
        // check error
        assertEquals("Address can't be empty.", error);
    }
    @Test
    public void testEmptySpaceAddressStore(){
        String address = " ";
        Store store = null;
        String error = null;

        try {
            store = storeService.createStore(address, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(store);
        // check error
        assertEquals("Address can't be empty.", error);
    }

    @Test
    public void testUpdateAddressWithNullAddress() {
        //Integer activeDelivery = 999;
        String error = null;
        Store store = null;
        store = storeService.createStore(ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        try {
            storeService.setAddress(null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(store.getAddress(), ADDRESS);
        // check error
        assertEquals("Address can't be empty.", error);
    }
    @Test
    public void testUpdateAddressWithEmptyAddress() {
        //Integer activeDelivery = 999;
        String error = null;
        Store store = null;
        store = storeService.createStore(ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        try {
            storeService.setAddress("");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(store.getAddress(), ADDRESS);
        // check error
        assertEquals("Address can't be empty.", error);
    }
    @Test
    public void testUpdateAddressWithEmptySpaceAddress() {
        //Integer activeDelivery = 999;
        String error = null;
        Store store = null;
        store = storeService.createStore(ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        try {
            storeService.setAddress(" ");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(store.getAddress(), ADDRESS);
        // check error
        assertEquals("Address can't be empty.", error);
    }
    @Test
    public void testGetEmployee(){
        lenient().when(customerRepository.findAll()).thenReturn(new ArrayList<>());
        Store store = new Store();
        store.setAddress(ADDRESS);
        store.setCurrentActiveDelivery(CURRENT_ACTIVE_DELIVERY);
        store.setCurrentActivePickup(CURRENT_ACTIVE_PICKUP);
        Employee employee1 = null;
        Employee employee2 = null;
        List<Employee> employeeList = new ArrayList<>();



        try{
            employee1 = new Employee();
            employee1.setUsername("Joe");
            employee1.setEmail("emp1@mail.com");
            employee1.setPassword("1234");
            employee1.setAddress("Street");
            employee1.setOrder(new ArrayList<>());
            employee1.setWorkShift(new ArrayList<>());
            employee1.setWorkingStatus(Employee.WorkingStatus.Hired);

            employee1 = new Employee();
            employee1.setUsername("Monco");
            employee1.setEmail("emp2@mail.com");
            employee1.setPassword("1234");
            employee1.setAddress("Street2");
            employee1.setOrder(new ArrayList<>());
            employee1.setWorkShift(new ArrayList<>());
            employee1.setWorkingStatus(Employee.WorkingStatus.Hired);

            store.setEmployee(Arrays.asList(employee1,employee2));
            //test stub
            lenient().when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
            Store myS = storeService.getStore();
            employeeList = myS.getEmployee();
        } catch (Exception e){
            fail();
        }
        assertNotNull(employeeList);
        assertEquals(employeeList, Arrays.asList(employee1, employee2));

    }



    @Test
    public void testGetBusinessHours(){
        Store store = new Store();
        store.setAddress(ADDRESS);
        store.setCurrentActiveDelivery(CURRENT_ACTIVE_DELIVERY);
        store.setCurrentActivePickup(CURRENT_ACTIVE_PICKUP);
        String error = null;
        BusinessHour businessHour = null;
        List<BusinessHour> businessHours = new ArrayList<>();
        try{
            Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 35));
            Time endTime = java.sql.Time.valueOf(LocalTime.of(18, 55));
            BusinessHour.DayOfWeek day = BusinessHour.DayOfWeek.Monday;
            businessHour = new BusinessHour();
            businessHour.setHoursID(69420);
            businessHour.setDay(day);
            businessHour.setStartTime(startTime);
            businessHour.setEndTime(endTime);
            store.setBusinessHour(Arrays.asList(businessHour));
            //test stub
            when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
            Store myS = storeService.getStore();
            businessHours = myS.getBusinessHour();
        }catch (Exception e){
            fail();
        }
        assertNotNull(businessHours);
        assertEquals(businessHours, Arrays.asList(businessHour));
    }
    @Test
    public void testGetItem(){
        Store store = new Store();
        store.setAddress(ADDRESS);
        store.setCurrentActiveDelivery(CURRENT_ACTIVE_DELIVERY);
        store.setCurrentActivePickup(CURRENT_ACTIVE_PICKUP);
        Item item1 = null;
        Item item2 = null;
        List<Item> itemList = new ArrayList<>();

        try{
            item1 = new Item();
            item1.setPrice(10);
            item1.setStock(2);
            item1.setPurchasable(true);
            item1.setName("apple1");
            item1.setDescription("juicy");

            item2 = new Item();
            item2.setPrice(10);
            item2.setStock(2);
            item2.setPurchasable(true);
            item2.setName("apple2");
            item2.setDescription("tasty");
            store.setItem(Arrays.asList(item1,item2));
            //test stub
            when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
            Store myS = storeService.getStore();
            itemList = myS.getItem();
        } catch (Exception e){
            fail();
        }
        assertNotNull(itemList);
        assertEquals(itemList, Arrays.asList(item1, item2));
    }
    @Test
    public void testGetHoliday(){
        Store store = new Store();
        store.setAddress(ADDRESS);
        store.setCurrentActiveDelivery(CURRENT_ACTIVE_DELIVERY);
        store.setCurrentActivePickup(CURRENT_ACTIVE_PICKUP);
        String error = null;
        Holiday holiday = null;
        List<Holiday> holidays = new ArrayList<>();
        try{
            String name = "TestHoliday";
            LocalDate LSDATE = LocalDate.of(2012, Month.JANUARY, 1);
            LocalDate LEDATE = LocalDate.of(2015, Month.AUGUST, 31);
            Date START_DATE = Date.valueOf(LSDATE);
            Date END_DATE = Date.valueOf(LEDATE);
            holiday = new Holiday();
            holiday.setEndDate(END_DATE);
            holiday.setName(name);
            holiday.setStartDate(START_DATE);
            store.setHoliday(Arrays.asList(holiday));
            //test stub
            when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
            Store myS = storeService.getStore();
            holidays = myS.getHoliday();
        }catch (Exception e){
            fail();
        }
        assertNotNull(holidays);
        assertEquals(holidays, Arrays.asList(holiday));
    }


}

