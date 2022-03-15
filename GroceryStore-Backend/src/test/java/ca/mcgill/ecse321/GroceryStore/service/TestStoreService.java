package ca.mcgill.ecse321.GroceryStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
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
        lenient().when(storeRepository.findById(any(int.class))).thenAnswer((InvocationOnMock invocation) -> {
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
        assertEquals("Store ID number must be greater than 0.", error);

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
//    @Test
//    public void testGetEmployee(){
//        Store store = null;
//        Employee employee1 = null;
//        Employee employee2 = null;
//        List<Employee> employeeList = null;
//
//        try{
//            store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY,CURRENT_ACTIVE_PICKUP);
//            employee1 = employeeService.createEmployee("emp1@gmail.com", "name1", "1234", "add1");
//            employee2 = employeeService.createEmployee();
//            store.setEmployee(Arrays.asList(employee1,employee2));
//            //test stub
//            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
//            workShiftList = employeeService.getEmployeeWorkShifts(EMPLOYEE_USERNAME);
//        } catch (Exception e){
//            fail();
//        }
//        assertNotNull(workShiftList);
//        assertEquals(workShiftList, Arrays.asList(workShift1, workShift2));
//    }

    @Test
    public void testGetBusinessHours(){
        String error = null;
        BusinessHour businessHour = new BusinessHour();
        when(businessHourRepository.findByHoursID(anyInt())).thenReturn(businessHour);
        Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(18, 55));
        BusinessHour.DayOfWeek day = BusinessHour.DayOfWeek.Monday;

        BusinessHour temp = businessHourService.createBusinessHour(startTime, endTime, day.name());
       // Store store = new Store(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        List<BusinessHour> checker = storeRepository.findById(STORE_ID_KEY).getBusinessHour();
        //Check that item and purchased item are successfully added to "repository"
        assertNull(checker);
//        assertNotNull(purchasedItem2Check);
//        assertNotNull(pickupOrder);

//        List<PurchasedItem> purchasedItemList = new ArrayList<>();
//        purchasedItemList.add(purchasedItem2Check);
//        pickupOrder.setPurchasedItem(purchasedItemList);
//
//        assertNotNull(pickupOrder.getPurchasedItem());
//
//        item2Check.setStock(2);
//
//        try{
//            purchasedItemService.purchasedItemRepository.findByPurchasedItemID(pickupOrder.getPurchasedItem().get(0).getPurchasedItemID()).setItemQuantity(10);
//        }catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNotNull(error);
//        assertEquals(error, "itemQuantity cannot be greater than the stock.");

    }
}

