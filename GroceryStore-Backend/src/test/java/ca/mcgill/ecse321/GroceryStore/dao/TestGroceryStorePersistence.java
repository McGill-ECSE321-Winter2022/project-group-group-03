package ca.mcgill.ecse321.GroceryStore.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.GroceryStore.model.*;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryStorePersistence {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BusinessHourRepository businessHourRepository;
    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;
    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PickupOrderRepository pickupOrderRepository;
    @Autowired
    private PurchasedItemRepository purchasedItemRepository;
    @Autowired
    private WorkShiftRepository workShiftRepository;

    @AfterEach
	public void clearDatabase() {

        // First, we clear registrations to avoid exceptions due to inconsistencies
        storeRepository.deleteAll();
        ownerRepository.deleteAll();
        employeeRepository.deleteAll();
        customerRepository.deleteAll();
        businessHourRepository.deleteAll();
        workShiftRepository.deleteAll();
        holidayRepository.deleteAll();
        itemRepository.deleteAll();
        pickupOrderRepository.deleteAll();
        deliveryOrderRepository.deleteAll();
        purchasedItemRepository.deleteAll();

    }

    @Test
    @Transactional
	public void testPersistAndLoadStore() {
		int storeID = 1;
        String address = "215 avenue Kenaston";
        int currentActivePickup = 5;
        int currentActiveDelivery = 6;
		// First example for object save/load
		Store store = new Store();
		//store.addEmployee(new Employee("edward", "1234", "asdasd@asdj", "randomaddress"));
        store.setAddress(address);
        store.setStoreID(storeID);
        store.setCurrentActiveDelivery(currentActiveDelivery);
        store.setCurrentActivePickup(currentActivePickup);
		// First example for attribute save/load
		storeRepository.save(store);
        store = null;
        store = storeRepository.findById(storeID);
        assertNotNull(store);
		assertEquals(storeID, store.getStoreID());
	}
    @Test
    @Transactional
    public void testPersistAndLoadEmployee() {
        String username = "Seb";
        int id = 2;
        // First example for object save/load
        Employee employee = new Employee();
        employee.setWorkingStatus(Employee.WorkingStatus.Hired);
        // First example for attribute save/load
        employee.setUsername(username);
        //employee.setEmployeeID(id);
        employee.setAddress("address");
        employee.setEmail("email");
        employee.setPassword("12345");

        employeeRepository.save(employee);

        employee = null;

        employee = employeeRepository.findByUsername(username);
        assertNotNull(employee);
        assertEquals(username, employee.getUsername());
        
    }

    @Test
    @Transactional
    public void testPersistAndLoadCustomer(){
        String username = "Arsters";
        String password = "Testies";
        String email = "arsters.testies@gmail.com";
        String address = "3064 rue edmond rostand";

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setAddress(address);

        customerRepository.save(customer);
        customer = null;

        customer = customerRepository.findByUsername(username);
        assertNotNull(customer);
        assertEquals(username, customer.getUsername());
    }

    @Test
    @Transactional
    public void testPersistAndLoadItem(){
        String name = "Cheese balls";
        boolean purchasable = true;
        int price = 10;
        String description = "Cheesiest balls in the game";
        int stock = 15;
        int totalPurchased = 2;

        Item item = new Item();
        item.setName(name);
        item.setPurchasable(purchasable);
        item.setPrice(price);
        item.setDescription(description);
        item.setStock(stock);
        item.setTotalPurchased(totalPurchased);

        itemRepository.save(item);
        item = null;

        item = itemRepository.findByName(name);
        assertNotNull(item);
        assertEquals(name, item.getName());

    }
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
    @Test
    @Transactional
    public void testPersistAndLoadDeliveryOrder(){
        int confirmationNumber = 123;
        int totalCost = 10;
        String shippingAddress = "3064 rue edmond rostand";
        DeliveryOrder.ShippingStatus status = DeliveryOrder.ShippingStatus.Ordered;

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setConfirmationNumber(confirmationNumber);
        deliveryOrder.setTotalCost(totalCost);
        deliveryOrder.setShippingAddress(shippingAddress);
        deliveryOrder.setShippingStatus(status);

        deliveryOrderRepository.save(deliveryOrder);
        deliveryOrder = null;

        deliveryOrder = deliveryOrderRepository.findByConfirmationNumber(confirmationNumber);
        assertNotNull(deliveryOrder);
        assertEquals(confirmationNumber, deliveryOrder.getConfirmationNumber());
    }
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
        holiday = null;

        holiday = holidayRepository.findByName(name);
        assertNotNull(holiday);
        assertEquals(name, holiday.getName());

    }
    @Test
    @Transactional
    public void testPersistAndLoadOwner(){
        String username = "Thad Castle";
        String password = "BlueMountainState";
        String email = "ThadCastle@gmail.com";
        //Store store = new Store();

        Owner owner = new Owner();
        owner.setUsername(username);
        owner.setEmail(email);
        owner.setPassword(password);
       //owner.setStore(store);

        ownerRepository.save(owner);
        owner = null;

        owner = ownerRepository.findByUsername(username);
        assertNotNull(owner);
        assertEquals(username, owner.getUsername());
    }
    @Test
    @Transactional
    public void testPersistAndLoadPickupOrder(){
        int confirmationNumber = 321;
        int totalCost = 5;
        PickupOrder.PaymentMethod paymentMethod = PickupOrder.PaymentMethod.Cash;
        PickupOrder.PickupStatus status = PickupOrder.PickupStatus.InCart;

        PickupOrder pickupOrder = new PickupOrder();
        pickupOrder.setConfirmationNumber(confirmationNumber);
        pickupOrder.setTotalCost(totalCost);
        pickupOrder.setPaymentMethod(paymentMethod);
        pickupOrder.setPickupStatus(status);

        pickupOrderRepository.save(pickupOrder);
        pickupOrder = null;

        pickupOrder = pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
        assertNotNull(pickupOrder);
        assertEquals(confirmationNumber, pickupOrder.getConfirmationNumber());

    }
    @Test
    @Transactional
    public void testPersistAndLoadPurchasedItem(){
        int purchasedItemID = 789;
        int quantity = 6;

        PurchasedItem purchasedItem = new PurchasedItem();
        purchasedItem.setPurchasedItemID(purchasedItemID);
        purchasedItem.setItemQuantity(quantity);

        purchasedItemRepository.save(purchasedItem);
        purchasedItem = null;

        purchasedItem = purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
        assertNotNull(purchasedItem);
        assertEquals(purchasedItemID, purchasedItem.getPurchasedItemID());
    }
    @Test
    @Transactional
    public void testPersistAndLoadWorkShift(){
        int shiftID = 379;
        Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(10, 55));
        WorkShift.DayOfWeek day = WorkShift.DayOfWeek.Monday;

        WorkShift workShift = new WorkShift();
        workShift.setShiftID(shiftID);
        workShift.setStartTime(startTime);
        workShift.setEndTime(endTime);
        workShift.setDay(day);

        workShiftRepository.save(workShift);
        workShift = null;

        workShift = workShiftRepository.findByShiftID(shiftID);
        assertNotNull(workShift);
        assertEquals(shiftID, workShift.getShiftID());


    }
}